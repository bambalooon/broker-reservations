package pl.bb.broker.brokerreservations.client;

import pl.bb.broker.brokerreservations.service.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 02.06.14
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
public class ReservationClient {
    public static final String serviceAddress = "http://localhost:8080/ws/reservations/";

    public boolean reserve() throws Exception {

        ReservationServiceImplService serviceImpl = new ReservationServiceImplService();
        ReservationService service = serviceImpl.getReservationServiceImplPort();
        ReservationRequest request = new ReservationRequest();
        request.setUsername("luffy");
        request.setFirstname("Luffy");
        request.setSurname("Monkey D.");
        request.setFacility("scout camp");
        request.setRoomType("N10");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        java.util.Date date = sdf.parse("03-06-2014");
        request.setArrival(date.getTime());
        date = sdf.parse("14-06-2014");
        request.setDeparture(date.getTime());

        try {
            ReservationResponse response = service.makeReservation(request);
            System.out.println(response.getResponseType().toString());
        } catch (WSException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}
