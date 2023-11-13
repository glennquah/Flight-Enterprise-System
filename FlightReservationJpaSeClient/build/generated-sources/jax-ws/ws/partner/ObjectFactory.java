
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
    private final static QName _CreateReservationDetails_QNAME = new QName("http://ws.session.ejb/", "createReservationDetails");
    private final static QName _CreateReservationDetailsResponse_QNAME = new QName("http://ws.session.ejb/", "createReservationDetailsResponse");
    private final static QName _GetFareUsingId_QNAME = new QName("http://ws.session.ejb/", "getFareUsingId");
    private final static QName _GetFareUsingIdResponse_QNAME = new QName("http://ws.session.ejb/", "getFareUsingIdResponse");
    private final static QName _GetFlightSchedules_QNAME = new QName("http://ws.session.ejb/", "getFlightSchedules");
    private final static QName _GetFlightSchedulesResponse_QNAME = new QName("http://ws.session.ejb/", "getFlightSchedulesResponse");
    private final static QName _GetHighestFareIdInCabin_QNAME = new QName("http://ws.session.ejb/", "getHighestFareIdInCabin");
    private final static QName _GetHighestFareIdInCabinResponse_QNAME = new QName("http://ws.session.ejb/", "getHighestFareIdInCabinResponse");
    private final static QName _GetPartnerId_QNAME = new QName("http://ws.session.ejb/", "getPartnerId");
    private final static QName _GetPartnerIdResponse_QNAME = new QName("http://ws.session.ejb/", "getPartnerIdResponse");
    private final static QName _LinkCreditCard_QNAME = new QName("http://ws.session.ejb/", "linkCreditCard");
    private final static QName _LinkCreditCardResponse_QNAME = new QName("http://ws.session.ejb/", "linkCreditCardResponse");
    private final static QName _LinkFlightSchedule_QNAME = new QName("http://ws.session.ejb/", "linkFlightSchedule");
    private final static QName _LinkFlightScheduleResponse_QNAME = new QName("http://ws.session.ejb/", "linkFlightScheduleResponse");
    private final static QName _Login_QNAME = new QName("http://ws.session.ejb/", "login");
    private final static QName _LoginResponse_QNAME = new QName("http://ws.session.ejb/", "loginResponse");
    private final static QName _RetrieveAllAirports_QNAME = new QName("http://ws.session.ejb/", "retrieveAllAirports");
    private final static QName _RetrieveAllAirportsResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveAllAirportsResponse");
    private final static QName _RetrieveFlightSchedulePlanWithSameFlight_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWithSameFlight");
    private final static QName _RetrieveFlightSchedulePlanWithSameFlightResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWithSameFlightResponse");
    private final static QName _RetrieveFlightsThatHasDepAndDest_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightsThatHasDepAndDest");
    private final static QName _RetrieveFlightsThatHasDepAndDestResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightsThatHasDepAndDestResponse");

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
     * Create an instance of {@link CreateReservationDetails }
     * 
     */
    public CreateReservationDetails createCreateReservationDetails() {
        return new CreateReservationDetails();
    }

    /**
     * Create an instance of {@link CreateReservationDetailsResponse }
     * 
     */
    public CreateReservationDetailsResponse createCreateReservationDetailsResponse() {
        return new CreateReservationDetailsResponse();
    }

    /**
     * Create an instance of {@link GetFareUsingId }
     * 
     */
    public GetFareUsingId createGetFareUsingId() {
        return new GetFareUsingId();
    }

    /**
     * Create an instance of {@link GetFareUsingIdResponse }
     * 
     */
    public GetFareUsingIdResponse createGetFareUsingIdResponse() {
        return new GetFareUsingIdResponse();
    }

    /**
     * Create an instance of {@link GetFlightSchedules }
     * 
     */
    public GetFlightSchedules createGetFlightSchedules() {
        return new GetFlightSchedules();
    }

    /**
     * Create an instance of {@link GetFlightSchedulesResponse }
     * 
     */
    public GetFlightSchedulesResponse createGetFlightSchedulesResponse() {
        return new GetFlightSchedulesResponse();
    }

    /**
     * Create an instance of {@link GetHighestFareIdInCabin }
     * 
     */
    public GetHighestFareIdInCabin createGetHighestFareIdInCabin() {
        return new GetHighestFareIdInCabin();
    }

    /**
     * Create an instance of {@link GetHighestFareIdInCabinResponse }
     * 
     */
    public GetHighestFareIdInCabinResponse createGetHighestFareIdInCabinResponse() {
        return new GetHighestFareIdInCabinResponse();
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
     * Create an instance of {@link LinkCreditCard }
     * 
     */
    public LinkCreditCard createLinkCreditCard() {
        return new LinkCreditCard();
    }

    /**
     * Create an instance of {@link LinkCreditCardResponse }
     * 
     */
    public LinkCreditCardResponse createLinkCreditCardResponse() {
        return new LinkCreditCardResponse();
    }

    /**
     * Create an instance of {@link LinkFlightSchedule }
     * 
     */
    public LinkFlightSchedule createLinkFlightSchedule() {
        return new LinkFlightSchedule();
    }

    /**
     * Create an instance of {@link LinkFlightScheduleResponse }
     * 
     */
    public LinkFlightScheduleResponse createLinkFlightScheduleResponse() {
        return new LinkFlightScheduleResponse();
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
     * Create an instance of {@link RetrieveFlightSchedulePlanWithSameFlight }
     * 
     */
    public RetrieveFlightSchedulePlanWithSameFlight createRetrieveFlightSchedulePlanWithSameFlight() {
        return new RetrieveFlightSchedulePlanWithSameFlight();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWithSameFlightResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWithSameFlightResponse createRetrieveFlightSchedulePlanWithSameFlightResponse() {
        return new RetrieveFlightSchedulePlanWithSameFlightResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightsThatHasDepAndDest }
     * 
     */
    public RetrieveFlightsThatHasDepAndDest createRetrieveFlightsThatHasDepAndDest() {
        return new RetrieveFlightsThatHasDepAndDest();
    }

    /**
     * Create an instance of {@link RetrieveFlightsThatHasDepAndDestResponse }
     * 
     */
    public RetrieveFlightsThatHasDepAndDestResponse createRetrieveFlightsThatHasDepAndDestResponse() {
        return new RetrieveFlightsThatHasDepAndDestResponse();
    }

    /**
     * Create an instance of {@link ReservationDetails }
     * 
     */
    public ReservationDetails createReservationDetails() {
        return new ReservationDetails();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link Account }
     * 
     */
    public Account createAccount() {
        return new Account();
    }

    /**
     * Create an instance of {@link FlightSchedule }
     * 
     */
    public FlightSchedule createFlightSchedule() {
        return new FlightSchedule();
    }

    /**
     * Create an instance of {@link Duration }
     * 
     */
    public Duration createDuration() {
        return new Duration();
    }

    /**
     * Create an instance of {@link FlightSchedulePlan }
     * 
     */
    public FlightSchedulePlan createFlightSchedulePlan() {
        return new FlightSchedulePlan();
    }

    /**
     * Create an instance of {@link Flight }
     * 
     */
    public Flight createFlight() {
        return new Flight();
    }

    /**
     * Create an instance of {@link AircraftConfiguration }
     * 
     */
    public AircraftConfiguration createAircraftConfiguration() {
        return new AircraftConfiguration();
    }

    /**
     * Create an instance of {@link Aircraft }
     * 
     */
    public Aircraft createAircraft() {
        return new Aircraft();
    }

    /**
     * Create an instance of {@link Cabin }
     * 
     */
    public Cabin createCabin() {
        return new Cabin();
    }

    /**
     * Create an instance of {@link Fare }
     * 
     */
    public Fare createFare() {
        return new Fare();
    }

    /**
     * Create an instance of {@link FlightRoute }
     * 
     */
    public FlightRoute createFlightRoute() {
        return new FlightRoute();
    }

    /**
     * Create an instance of {@link Airport }
     * 
     */
    public Airport createAirport() {
        return new Airport();
    }

    /**
     * Create an instance of {@link Partner }
     * 
     */
    public Partner createPartner() {
        return new Partner();
    }

    /**
     * Create an instance of {@link UnsignedShortArray }
     * 
     */
    public UnsignedShortArray createUnsignedShortArray() {
        return new UnsignedShortArray();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateReservationDetails }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateReservationDetails }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "createReservationDetails")
    public JAXBElement<CreateReservationDetails> createCreateReservationDetails(CreateReservationDetails value) {
        return new JAXBElement<CreateReservationDetails>(_CreateReservationDetails_QNAME, CreateReservationDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateReservationDetailsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateReservationDetailsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "createReservationDetailsResponse")
    public JAXBElement<CreateReservationDetailsResponse> createCreateReservationDetailsResponse(CreateReservationDetailsResponse value) {
        return new JAXBElement<CreateReservationDetailsResponse>(_CreateReservationDetailsResponse_QNAME, CreateReservationDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFareUsingId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFareUsingId }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getFareUsingId")
    public JAXBElement<GetFareUsingId> createGetFareUsingId(GetFareUsingId value) {
        return new JAXBElement<GetFareUsingId>(_GetFareUsingId_QNAME, GetFareUsingId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFareUsingIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFareUsingIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getFareUsingIdResponse")
    public JAXBElement<GetFareUsingIdResponse> createGetFareUsingIdResponse(GetFareUsingIdResponse value) {
        return new JAXBElement<GetFareUsingIdResponse>(_GetFareUsingIdResponse_QNAME, GetFareUsingIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlightSchedules }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFlightSchedules }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getFlightSchedules")
    public JAXBElement<GetFlightSchedules> createGetFlightSchedules(GetFlightSchedules value) {
        return new JAXBElement<GetFlightSchedules>(_GetFlightSchedules_QNAME, GetFlightSchedules.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlightSchedulesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFlightSchedulesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getFlightSchedulesResponse")
    public JAXBElement<GetFlightSchedulesResponse> createGetFlightSchedulesResponse(GetFlightSchedulesResponse value) {
        return new JAXBElement<GetFlightSchedulesResponse>(_GetFlightSchedulesResponse_QNAME, GetFlightSchedulesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHighestFareIdInCabin }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetHighestFareIdInCabin }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getHighestFareIdInCabin")
    public JAXBElement<GetHighestFareIdInCabin> createGetHighestFareIdInCabin(GetHighestFareIdInCabin value) {
        return new JAXBElement<GetHighestFareIdInCabin>(_GetHighestFareIdInCabin_QNAME, GetHighestFareIdInCabin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHighestFareIdInCabinResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetHighestFareIdInCabinResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getHighestFareIdInCabinResponse")
    public JAXBElement<GetHighestFareIdInCabinResponse> createGetHighestFareIdInCabinResponse(GetHighestFareIdInCabinResponse value) {
        return new JAXBElement<GetHighestFareIdInCabinResponse>(_GetHighestFareIdInCabinResponse_QNAME, GetHighestFareIdInCabinResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkCreditCard }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LinkCreditCard }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "linkCreditCard")
    public JAXBElement<LinkCreditCard> createLinkCreditCard(LinkCreditCard value) {
        return new JAXBElement<LinkCreditCard>(_LinkCreditCard_QNAME, LinkCreditCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkCreditCardResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LinkCreditCardResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "linkCreditCardResponse")
    public JAXBElement<LinkCreditCardResponse> createLinkCreditCardResponse(LinkCreditCardResponse value) {
        return new JAXBElement<LinkCreditCardResponse>(_LinkCreditCardResponse_QNAME, LinkCreditCardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkFlightSchedule }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LinkFlightSchedule }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "linkFlightSchedule")
    public JAXBElement<LinkFlightSchedule> createLinkFlightSchedule(LinkFlightSchedule value) {
        return new JAXBElement<LinkFlightSchedule>(_LinkFlightSchedule_QNAME, LinkFlightSchedule.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkFlightScheduleResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LinkFlightScheduleResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "linkFlightScheduleResponse")
    public JAXBElement<LinkFlightScheduleResponse> createLinkFlightScheduleResponse(LinkFlightScheduleResponse value) {
        return new JAXBElement<LinkFlightScheduleResponse>(_LinkFlightScheduleResponse_QNAME, LinkFlightScheduleResponse.class, null, value);
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameFlight }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameFlight }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWithSameFlight")
    public JAXBElement<RetrieveFlightSchedulePlanWithSameFlight> createRetrieveFlightSchedulePlanWithSameFlight(RetrieveFlightSchedulePlanWithSameFlight value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWithSameFlight>(_RetrieveFlightSchedulePlanWithSameFlight_QNAME, RetrieveFlightSchedulePlanWithSameFlight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameFlightResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameFlightResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWithSameFlightResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWithSameFlightResponse> createRetrieveFlightSchedulePlanWithSameFlightResponse(RetrieveFlightSchedulePlanWithSameFlightResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWithSameFlightResponse>(_RetrieveFlightSchedulePlanWithSameFlightResponse_QNAME, RetrieveFlightSchedulePlanWithSameFlightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightsThatHasDepAndDest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightsThatHasDepAndDest }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightsThatHasDepAndDest")
    public JAXBElement<RetrieveFlightsThatHasDepAndDest> createRetrieveFlightsThatHasDepAndDest(RetrieveFlightsThatHasDepAndDest value) {
        return new JAXBElement<RetrieveFlightsThatHasDepAndDest>(_RetrieveFlightsThatHasDepAndDest_QNAME, RetrieveFlightsThatHasDepAndDest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightsThatHasDepAndDestResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightsThatHasDepAndDestResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightsThatHasDepAndDestResponse")
    public JAXBElement<RetrieveFlightsThatHasDepAndDestResponse> createRetrieveFlightsThatHasDepAndDestResponse(RetrieveFlightsThatHasDepAndDestResponse value) {
        return new JAXBElement<RetrieveFlightsThatHasDepAndDestResponse>(_RetrieveFlightsThatHasDepAndDestResponse_QNAME, RetrieveFlightsThatHasDepAndDestResponse.class, null, value);
    }

}
