
package pl.bb.broker.brokerreservations.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.bb.broker.brokerreservations.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WSException_QNAME = new QName("http://services.ws.companyreserv.company.broker.bb.pl/", "WSException");
    private final static QName _MakeReservationResponse_QNAME = new QName("http://services.ws.companyreserv.company.broker.bb.pl/", "makeReservationResponse");
    private final static QName _ReservationResponse_QNAME = new QName("http://services.ws.companyreserv.company.broker.bb.pl/", "reservationResponse");
    private final static QName _ExceptionFault_QNAME = new QName("http://services.ws.companyreserv.company.broker.bb.pl/", "ExceptionFault");
    private final static QName _MakeReservation_QNAME = new QName("http://services.ws.companyreserv.company.broker.bb.pl/", "makeReservation");
    private final static QName _ReservationRequest_QNAME = new QName("http://services.ws.companyreserv.company.broker.bb.pl/", "reservationRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.bb.broker.brokerreservations.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReservationRequest }
     * 
     */
    public ReservationRequest createReservationRequest() {
        return new ReservationRequest();
    }

    /**
     * Create an instance of {@link MakeReservation }
     * 
     */
    public MakeReservation createMakeReservation() {
        return new MakeReservation();
    }

    /**
     * Create an instance of {@link PlBbBrokerCompanyCompanyreservationsExceptionsWSException }
     * 
     */
    public PlBbBrokerCompanyCompanyreservationsExceptionsWSException createPlBbBrokerCompanyCompanyreservationsExceptionsWSException() {
        return new PlBbBrokerCompanyCompanyreservationsExceptionsWSException();
    }

    /**
     * Create an instance of {@link ReservationResponse }
     * 
     */
    public ReservationResponse createReservationResponse() {
        return new ReservationResponse();
    }

    /**
     * Create an instance of {@link MakeReservationResponse }
     * 
     */
    public MakeReservationResponse createMakeReservationResponse() {
        return new MakeReservationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlBbBrokerCompanyCompanyreservationsExceptionsWSException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.ws.companyreserv.company.broker.bb.pl/", name = "WSException")
    public JAXBElement<PlBbBrokerCompanyCompanyreservationsExceptionsWSException> createWSException(PlBbBrokerCompanyCompanyreservationsExceptionsWSException value) {
        return new JAXBElement<PlBbBrokerCompanyCompanyreservationsExceptionsWSException>(_WSException_QNAME, PlBbBrokerCompanyCompanyreservationsExceptionsWSException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeReservationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.ws.companyreserv.company.broker.bb.pl/", name = "makeReservationResponse")
    public JAXBElement<MakeReservationResponse> createMakeReservationResponse(MakeReservationResponse value) {
        return new JAXBElement<MakeReservationResponse>(_MakeReservationResponse_QNAME, MakeReservationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReservationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.ws.companyreserv.company.broker.bb.pl/", name = "reservationResponse")
    public JAXBElement<ReservationResponse> createReservationResponse(ReservationResponse value) {
        return new JAXBElement<ReservationResponse>(_ReservationResponse_QNAME, ReservationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlBbBrokerCompanyCompanyreservationsExceptionsWSException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.ws.companyreserv.company.broker.bb.pl/", name = "ExceptionFault")
    public JAXBElement<PlBbBrokerCompanyCompanyreservationsExceptionsWSException> createExceptionFault(PlBbBrokerCompanyCompanyreservationsExceptionsWSException value) {
        return new JAXBElement<PlBbBrokerCompanyCompanyreservationsExceptionsWSException>(_ExceptionFault_QNAME, PlBbBrokerCompanyCompanyreservationsExceptionsWSException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeReservation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.ws.companyreserv.company.broker.bb.pl/", name = "makeReservation")
    public JAXBElement<MakeReservation> createMakeReservation(MakeReservation value) {
        return new JAXBElement<MakeReservation>(_MakeReservation_QNAME, MakeReservation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReservationRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.ws.companyreserv.company.broker.bb.pl/", name = "reservationRequest")
    public JAXBElement<ReservationRequest> createReservationRequest(ReservationRequest value) {
        return new JAXBElement<ReservationRequest>(_ReservationRequest_QNAME, ReservationRequest.class, null, value);
    }

}
