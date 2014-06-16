
package pl.bb.broker.brokerreservations.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ReservationService", targetNamespace = "http://services.ws.companyreserv.company.broker.bb.pl/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ReservationService {


    /**
     * 
     * @param request
     * @return
     *     returns pl.bb.broker.brokerreservations.service.ReservationResponse
     * @throws WSException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "makeReservation", targetNamespace = "http://services.ws.companyreserv.company.broker.bb.pl/", className = "pl.bb.broker.brokerreservations.service.MakeReservation")
    @ResponseWrapper(localName = "makeReservationResponse", targetNamespace = "http://services.ws.companyreserv.company.broker.bb.pl/", className = "pl.bb.broker.brokerreservations.service.MakeReservationResponse")
    public ReservationResponse makeReservation(
        @WebParam(name = "request", targetNamespace = "")
        ReservationRequest request)
        throws WSException
    ;

}
