package pl.bb.broker.brokerreservations.beans;

import pl.bb.broker.brokerdb.broker.entities.ReservationsEntity;
import pl.bb.broker.brokerdb.util.BrokerDBOfferUtil;
import pl.bb.broker.brokerdb.util.BrokerDBReservUtil;
import pl.bb.broker.security.settings.SecurityGroups;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 16.06.14
 * Time: 02:14
 * To change this template use File | Settings | File Templates.
 */

@RolesAllowed("CLIENT")
@ManagedBean
@RequestScoped
public class ReservationManagementBean {
    private List<ReservationsEntity> reservations;
    private List<ReservationsEntity> newReservations;
    private List<ReservationsEntity> unaccepted;

    public List<ReservationsEntity> getReservations() throws Exception {
        if(reservations==null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
            Principal principal = req.getUserPrincipal();
            if(principal!=null) {
                if(!req.isUserInRole("CLIENT")) {
                    context.getExternalContext().redirect(SecurityGroups.COMPANY_REDIRECT);
                    return null;
                }
                reservations = BrokerDBReservUtil.FACTORY.getUserReservations(principal.getName());
            }
            else {
                reservations = null;
            }
        }
        return reservations;
    }

    public List<ReservationsEntity> getNewReservations() {
        if(newReservations==null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
            Principal principal = req.getUserPrincipal();
            if(principal!=null) {
                newReservations = BrokerDBReservUtil.FACTORY.getUserNewReservations(principal.getName());
            }
            else {
                newReservations = null;
            }
        }
        return newReservations;
    }

    public List<ReservationsEntity> getUnaccepted() {
        if(unaccepted==null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
            Principal principal = req.getUserPrincipal();
            if(principal!=null) {
                unaccepted = BrokerDBReservUtil.FACTORY.getUnacceptedCompanyReservations(principal.getName());
            }
            else {
                unaccepted = null;
            }
        }
        return unaccepted;
    }

    public String accept(ReservationsEntity reservation) {
        unaccepted.remove(reservation);
        reservation.setAccepted(true);
        BrokerDBReservUtil.FACTORY.updateReservation(reservation);
        return null;
    }

    public String reject(ReservationsEntity reservation) {
        unaccepted.remove(reservation);
        reservation.setAccepted(false);
        BrokerDBReservUtil.FACTORY.updateReservation(reservation);
        return null;
    }

    public String translate(ReservationsEntity reserv) {
        Boolean accepted = reserv.getAccepted();
        return (accepted==null ? "W trakcie realizacji" : (accepted ? "Zatwierdzona" : "Odrzucona"));
    }
}
