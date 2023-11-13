
package ws.partner;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.partner package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
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

    private final static QName _InvalidLoginCredentialException_QNAME = new QName("http://ws.session.ejb/", "InvalidLoginCredentialException");
    private final static QName _Airport_QNAME = new QName("http://ws.session.ejb/", "airport");
    private final static QName _GetPartnerId_QNAME = new QName("http://ws.session.ejb/", "getPartnerId");
    private final static QName _GetPartnerIdResponse_QNAME = new QName("http://ws.session.ejb/", "getPartnerIdResponse");
    private final static QName _Login_QNAME = new QName("http://ws.session.ejb/", "login");
    private final static QName _LoginResponse_QNAME = new QName("http://ws.session.ejb/", "loginResponse");
    private final static QName _RetrieveAllAirports_QNAME = new QName("http://ws.session.ejb/", "retrieveAllAirports");
    private final static QName _RetrieveAllAirportsResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveAllAirportsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.partner
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvalidLoginCredentialException }
     * 
     */
    public InvalidLoginCredentialException createInvalidLoginCredentialException() {
        return new InvalidLoginCredentialException();
    }

    /**
     * Create an instance of {@link Airport }
     * 
     */
    public Airport createAirport() {
        return new Airport();
    }

    /**
     * Create an instance of {@link GetPartnerId }
     * 
     */
    public GetPartnerId createGetPartnerId() {
        return new GetPartnerId();
    }

    /**
     * Create an instance of {@link GetPartnerIdResponse }
     * 
     */
    public GetPartnerIdResponse createGetPartnerIdResponse() {
        return new GetPartnerIdResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link RetrieveAllAirports }
     * 
     */
    public RetrieveAllAirports createRetrieveAllAirports() {
        return new RetrieveAllAirports();
    }

    /**
     * Create an instance of {@link RetrieveAllAirportsResponse }
     * 
     */
    public RetrieveAllAirportsResponse createRetrieveAllAirportsResponse() {
        return new RetrieveAllAirportsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidLoginCredentialException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InvalidLoginCredentialException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "InvalidLoginCredentialException")
    public JAXBElement<InvalidLoginCredentialException> createInvalidLoginCredentialException(InvalidLoginCredentialException value) {
        return new JAXBElement<InvalidLoginCredentialException>(_InvalidLoginCredentialException_QNAME, InvalidLoginCredentialException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Airport }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Airport }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "airport")
    public JAXBElement<Airport> createAirport(Airport value) {
        return new JAXBElement<Airport>(_Airport_QNAME, Airport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPartnerId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPartnerId }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getPartnerId")
    public JAXBElement<GetPartnerId> createGetPartnerId(GetPartnerId value) {
        return new JAXBElement<GetPartnerId>(_GetPartnerId_QNAME, GetPartnerId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPartnerIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPartnerIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getPartnerIdResponse")
    public JAXBElement<GetPartnerIdResponse> createGetPartnerIdResponse(GetPartnerIdResponse value) {
        return new JAXBElement<GetPartnerIdResponse>(_GetPartnerIdResponse_QNAME, GetPartnerIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Login }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveAllAirports }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveAllAirports }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveAllAirports")
    public JAXBElement<RetrieveAllAirports> createRetrieveAllAirports(RetrieveAllAirports value) {
        return new JAXBElement<RetrieveAllAirports>(_RetrieveAllAirports_QNAME, RetrieveAllAirports.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveAllAirportsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveAllAirportsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveAllAirportsResponse")
    public JAXBElement<RetrieveAllAirportsResponse> createRetrieveAllAirportsResponse(RetrieveAllAirportsResponse value) {
        return new JAXBElement<RetrieveAllAirportsResponse>(_RetrieveAllAirportsResponse_QNAME, RetrieveAllAirportsResponse.class, null, value);
    }

}
