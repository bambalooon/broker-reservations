package pl.bb.broker.brokerreservations.beans;

import org.hibernate.HibernateException;
import pl.bb.broker.brokerdb.broker.entities.*;
import pl.bb.broker.brokerdb.util.BrokerDBReservUtil;
import pl.bb.broker.brokerreservations.service.*;
import pl.bb.broker.security.settings.SecurityGroups;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import java.net.URL;
import java.security.Principal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 02.06.14
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@RequestScoped
public class ReservationBean {
    public static final String reservationWS = "ws/reservations/?wsdl";

    private int offerId;
    private String roomType;
    private Date arrival;
    private Date departure;

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public String reserve() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = req.getUserPrincipal();

        OffersEntity offer = null;
        UsersEntity user = null;

        try {
            offer = BrokerDBReservUtil.FACTORY.getOffer(offerId);
            user = BrokerDBReservUtil.FACTORY.getUser(principal.getName());
            if(user==null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Musisz być zalogowany, żeby dokonać rezerwacji", null));
                return null;
            }
            boolean has = false;
            for(RolesEntity role : user.getRoles()) {
                if(role.getRole().equals(SecurityGroups.CLIENT.toString())) {
                    has = true;
                    break;
                }
            }
            if(!has) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Nie masz uprawnień do rezerwacji", null));
                return null;
            }
        } catch (HibernateException | NullPointerException e) {
            if(principal==null || principal.getName()==null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Musisz być zalogowany, żeby dokonać rezerwacji", null));
            }
            else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Serwis nieaktywny, spróbuj później", null));
            }
            return null;
        }
        if(offer.getCompany().getResources()==null) {
            this.offlineReservation(offer, user, context);
            return null;
        }
        this.onlineReservation(offer, user, context);
        return null;
    }

    private void onlineReservation(OffersEntity offer, UsersEntity user, FacesContext context) {
        ReservationService service = null;
        try {
            ReservationServiceImplService serviceImpl = new ReservationServiceImplService(
                    new URL(offer.getCompany().getResources()+ReservationBean.reservationWS)
            );
            service = serviceImpl.getReservationServiceImplPort();
        } catch (Exception e) {
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                    "Problem z połączeniem z serwisem pensjonatu. Spróbuj później.", null));
            this.offlineReservation(offer, user, context);
            return;
        }

        ReservationRequest request = this.createRequest(offer, user);

        ReservationResponse response = null;

        try {
            response = service.makeReservation(request);
        } catch (WSException e) {
            this.offlineReservation(offer, user, context);
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Serwis nieaktywny, spróbuj później. \n"+e.getMessage(), null));
            return;
        }

        if(response.getResponseType()==ResponseType.ACCEPTED) {

            ReservationsEntity reservation = this.createReservation(offer, user);
            reservation.setAccepted(true);

            try {
                BrokerDBReservUtil.FACTORY.saveReservation(reservation);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Rezerwacja zaakceptowana! Informacje o Twoich rezerwacjach znajdziesz w swoim profilu.", null));
            } catch (HibernateException e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Problem z połączeniem z bazą danych! Skontaktuj się z administratorem.", null));
            }

        }
        else if(response.getResponseType()==ResponseType.REJECTED) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Rezerwacja odrzucona! Spróbuj w inny dzień albo inny pokój.", null));
        }

    }

    private void offlineReservation(OffersEntity offer, UsersEntity user, FacesContext context) {
        ReservationsEntity reservation = this.createReservation(offer, user);
        try {
            BrokerDBReservUtil.FACTORY.saveReservation(reservation);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Rezerwacja w trakcie realizacji. Sprawdzaj swój profil.", null));
        } catch (HibernateException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problem z połączeniem z bazą danych! Skontaktuj się z administratorem.", null));
        }
    }

    private ReservationRequest createRequest(OffersEntity offer, UsersEntity user) {
        ReservationRequest request = new ReservationRequest();
        request.setArrival(arrival.getTime());
        request.setDeparture(departure.getTime());
        request.setRoomType(roomType);
        request.setFacility(offer.getFacility());
        request.setUsername(user.getUsername());
        request.setFirstname(user.getFirstname());
        request.setSurname(user.getSurname());
        return request;
    }

    private ReservationsEntity createReservation(OffersEntity offer, UsersEntity user) {
        OfferDetailsEntity room = new OfferDetailsEntity();
        room.setOffer(offer);
        room.setRoomType(roomType);

        ReservationsEntity reservation = new ReservationsEntity();
        reservation.setArrival(new java.sql.Date(arrival.getTime()));
        reservation.setDeparture(new java.sql.Date(departure.getTime()));
        reservation.setUser(user);
        reservation.setOffer(offer);
        reservation.setRoom(room);
        return reservation;
    }
}
