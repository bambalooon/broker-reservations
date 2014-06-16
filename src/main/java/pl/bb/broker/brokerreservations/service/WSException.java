
package pl.bb.broker.brokerreservations.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "WSException", targetNamespace = "http://services.ws.companyreserv.company.broker.bb.pl/")
public class WSException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PlBbBrokerCompanyCompanyreservWsExceptionsWSException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public WSException(String message, PlBbBrokerCompanyCompanyreservWsExceptionsWSException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public WSException(String message, PlBbBrokerCompanyCompanyreservWsExceptionsWSException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: pl.bb.broker.brokerreservations.service.PlBbBrokerCompanyCompanyreservWsExceptionsWSException
     */
    public PlBbBrokerCompanyCompanyreservWsExceptionsWSException getFaultInfo() {
        return faultInfo;
    }

}
