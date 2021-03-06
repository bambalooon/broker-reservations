import org.hibernate.HibernateException;
import pl.bb.broker.brokerdb.broker.entities.CompaniesEntity;
import pl.bb.broker.brokerdb.broker.entities.ReservationsEntity;
import pl.bb.broker.brokerdb.util.BrokerDBReservUtil;
import pl.bb.broker.brokerdb.util.HibernateConfiguration;
import pl.bb.broker.brokerreservations.beans.ReservationBean;
import pl.bb.broker.brokerreservations.service.*;

import javax.faces.application.FacesMessage;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 02.06.14
 * Time: 20:06
 * To change this template use File | Settings | File Templates.
 */


public class Test {

    @org.junit.Test
    public void test() throws Exception {
        ReservationRequest request = new ReservationRequest();
        request.setUsername("luffy");
        request.setFirstname("luffy");
        request.setSurname("Monkey D.");
        request.setArrival(0L);
        request.setDeparture(1000000000000L);
        request.setFacility("scout camp");
        request.setRoomType("N10");
        ReservationResponse response = null;
        ReservationService service = null;

        ReservationServiceImplService serviceImpl = new ReservationServiceImplService(new URL("http://localhost/company-main/ws/reservations/?wsdl"));
        service = serviceImpl.getReservationServiceImplPort();
        response = service.makeReservation(request);
    }

    @org.junit.Test
    public void TestNotify() throws Exception {
        Field field = HibernateConfiguration.class.getDeclaredField("sessionFactory");
        field.setAccessible(true);
        field.set(null, TestHibernateUtil.getInstance());

        System.out.println(BrokerDBReservUtil.FACTORY.getUserNewReservations("luffy"));

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

}
