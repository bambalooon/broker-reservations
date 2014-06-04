package pl.bb.broker.brokerreservations.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Future;
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

    public String reserve() throws Exception {

        throw new Exception(this.roomType+": "+this.offerId);

    }
}
