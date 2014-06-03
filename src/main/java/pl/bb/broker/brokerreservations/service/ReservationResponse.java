
package pl.bb.broker.brokerreservations.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reservationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reservationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{http://services.ws.companyreserv.company.broker.bb.pl/}reservationRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="responseType" type="{http://services.ws.companyreserv.company.broker.bb.pl/}responseType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservationResponse", propOrder = {
    "request"
})
public class ReservationResponse {

    protected ReservationRequest request;
    @XmlAttribute(name = "responseType")
    protected ResponseType responseType;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link ReservationRequest }
     *     
     */
    public ReservationRequest getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationRequest }
     *     
     */
    public void setRequest(ReservationRequest value) {
        this.request = value;
    }

    /**
     * Gets the value of the responseType property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseType }
     *     
     */
    public ResponseType getResponseType() {
        return responseType;
    }

    /**
     * Sets the value of the responseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseType }
     *     
     */
    public void setResponseType(ResponseType value) {
        this.responseType = value;
    }

}
