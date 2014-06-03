package pl.bb.broker.brokerreservations.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Date;

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
    //time error but don't care - only date important
    public void setArrival(java.util.Date arrival) {
        this.arrival = new Date(arrival.getTime());
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(java.util.Date departure) {
        this.departure = new Date(departure.getTime());
    }

    public String reserve() throws Exception {
        throw new Exception(this.roomType+": "+this.offerId);

    }
}
