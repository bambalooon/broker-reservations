package pl.bb.broker.brokerreservations.notifiers;

import org.hibernate.HibernateException;
import pl.bb.broker.brokerdb.broker.entities.*;
import pl.bb.broker.brokerdb.util.BrokerDBReservUtil;
import pl.bb.broker.brokerreservations.beans.ReservationBean;
import pl.bb.broker.brokerreservations.service.*;
import pl.bb.broker.settings.MailSettings;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 14.06.14
 * Time: 01:51
 * To change this template use File | Settings | File Templates.
 */

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class ReservationNotifier {

    @Resource(name = MailSettings.EMAIL_SESSION_JNDI_PATH)
    private Session mailSession;

    @Schedule(hour = "*", minute = "0,10,20,30,40,50", second = "0", persistent = false)
    public void tryToReserve() throws Exception {
        List<ReservationsEntity> reservations =
                BrokerDBReservUtil.FACTORY.getUnacceptedReservations();
        for(ReservationsEntity reserv : reservations) {
            if(reserv.getOffer().getCompany().getResources()!=null) {
                this.onlineReservation(reserv);
            }
        }

        List<CompaniesEntity> companies = new ArrayList<>();
        for(ReservationsEntity reserv : reservations) {
            CompaniesEntity company = reserv.getOffer().getCompany();
            if(reserv.getAccepted()==null && !companies.contains(company)) {
                companies.add(company);
            }
        }
        this.notify(companies);
    }

    private void notify(List<CompaniesEntity> companies) throws Exception {
        for(CompaniesEntity company : companies) {
            Message message = new MimeMessage(this.getEmailSession());
            message.setFrom(new InternetAddress("reservations@broker.pl"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(company.getEmail()));
            message.setSubject("Rezerwacja");
            message.setText("W serwisie pojawiły się oczekujące na zatwierdzenie rezerwacje. \n" +
                    "Pozdrawiamy,\nDreamTeam Jednoosobowy");

            Transport.send(message);
        }
    }

    private void onlineReservation(ReservationsEntity reservation) {
        ReservationService service = null;
        try {
            ReservationServiceImplService serviceImpl = new ReservationServiceImplService(
                    new URL(reservation.getOffer().getCompany().getResources()+ ReservationBean.reservationWS)
            );
            service = serviceImpl.getReservationServiceImplPort();
        } catch (Exception e) {
            return;
        }

        ReservationRequest request = this.createRequest(reservation);

        ReservationResponse response = null;

        try {
            response = service.makeReservation(request);
        } catch (WSException e) {
            return;
        }

        if(response.getResponseType()==ResponseType.ACCEPTED) {
            reservation.setAccepted(true);
            try {
                BrokerDBReservUtil.FACTORY.updateReservation(reservation);
            } catch (HibernateException e) {}
        }
        else if(response.getResponseType()==ResponseType.REJECTED) {
            reservation.setAccepted(false);
            try {
                BrokerDBReservUtil.FACTORY.updateReservation(reservation);
            } catch (HibernateException e) {}
        }
    }

    private ReservationRequest createRequest(ReservationsEntity reservation) {
        ReservationRequest request = new ReservationRequest();
        request.setArrival(reservation.getArrival().getTime());
        request.setDeparture(reservation.getDeparture().getTime());
        request.setRoomType(reservation.getRoom().getRoomType());
        request.setFacility(reservation.getOffer().getFacility());
        request.setUsername(reservation.getUser().getUsername());
        request.setFirstname(reservation.getUser().getFirstname());
        request.setSurname(reservation.getUser().getSurname());
        return request;
    }

    private Session getEmailSession() throws Exception {
        return mailSession;
    }

}
