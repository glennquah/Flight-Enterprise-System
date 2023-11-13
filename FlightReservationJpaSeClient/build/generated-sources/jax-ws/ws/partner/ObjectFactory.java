
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

    private final static QName _FlightDoesNotExistException_QNAME = new QName("http://ws.session.ejb/", "FlightDoesNotExistException");
    private final static QName _FlightScheduleDoesNotExistException_QNAME = new QName("http://ws.session.ejb/", "FlightScheduleDoesNotExistException");
    private final static QName _InvalidLoginCredentialException_QNAME = new QName("http://ws.session.ejb/", "InvalidLoginCredentialException");
    private final static QName _BookSeat_QNAME = new QName("http://ws.session.ejb/", "bookSeat");
    private final static QName _BookSeatResponse_QNAME = new QName("http://ws.session.ejb/", "bookSeatResponse");
    private final static QName _BookgetHighestFareUsingCabinNameSeat_QNAME = new QName("http://ws.session.ejb/", "bookgetHighestFareUsingCabinNameSeat");
    private final static QName _BookgetHighestFareUsingCabinNameSeatResponse_QNAME = new QName("http://ws.session.ejb/", "bookgetHighestFareUsingCabinNameSeatResponse");
    private final static QName _CheckSeatIfAvailable_QNAME = new QName("http://ws.session.ejb/", "checkSeatIfAvailable");
    private final static QName _CheckSeatIfAvailableResponse_QNAME = new QName("http://ws.session.ejb/", "checkSeatIfAvailableResponse");
    private final static QName _GetCabinSeats_QNAME = new QName("http://ws.session.ejb/", "getCabinSeats");
    private final static QName _GetCabinSeatsResponse_QNAME = new QName("http://ws.session.ejb/", "getCabinSeatsResponse");
    private final static QName _GetCabins_QNAME = new QName("http://ws.session.ejb/", "getCabins");
    private final static QName _GetCabinsResponse_QNAME = new QName("http://ws.session.ejb/", "getCabinsResponse");
    private final static QName _GetFlightScheduleWithId_QNAME = new QName("http://ws.session.ejb/", "getFlightScheduleWithId");
    private final static QName _GetFlightScheduleWithIdResponse_QNAME = new QName("http://ws.session.ejb/", "getFlightScheduleWithIdResponse");
    private final static QName _GetIslesPlan_QNAME = new QName("http://ws.session.ejb/", "getIslesPlan");
    private final static QName _GetIslesPlanResponse_QNAME = new QName("http://ws.session.ejb/", "getIslesPlanResponse");
    private final static QName _GetPartnerId_QNAME = new QName("http://ws.session.ejb/", "getPartnerId");
    private final static QName _GetPartnerIdResponse_QNAME = new QName("http://ws.session.ejb/", "getPartnerIdResponse");
    private final static QName _GetReservationDetails_QNAME = new QName("http://ws.session.ejb/", "getReservationDetails");
    private final static QName _GetReservationDetailsResponse_QNAME = new QName("http://ws.session.ejb/", "getReservationDetailsResponse");
    private final static QName _Login_QNAME = new QName("http://ws.session.ejb/", "login");
    private final static QName _LoginResponse_QNAME = new QName("http://ws.session.ejb/", "loginResponse");
    private final static QName _RetrieveAllAirports_QNAME = new QName("http://ws.session.ejb/", "retrieveAllAirports");
    private final static QName _RetrieveAllAirportsResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveAllAirportsResponse");
    private final static QName _RetrieveDateOfFlightPicked_QNAME = new QName("http://ws.session.ejb/", "retrieveDateOfFlightPicked");
    private final static QName _RetrieveDateOfFlightPickedResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveDateOfFlightPickedResponse");
    private final static QName _RetrieveFlightScheduleInPlan_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightScheduleInPlan");
    private final static QName _RetrieveFlightScheduleInPlanResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightScheduleInPlanResponse");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysAfter_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysAfter");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysAfterResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysAfterResponse");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysBefore_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysBefore");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysBeforeResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysBeforeResponse");
    private final static QName _RetrieveFlightSchedulePlanWithSameTiming_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWithSameTiming");
    private final static QName _RetrieveFlightSchedulePlanWithSameTimingResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWithSameTimingResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.partner
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FlightDoesNotExistException }
     * 
     */
    public FlightDoesNotExistException createFlightDoesNotExistException() {
        return new FlightDoesNotExistException();
    }

    /**
     * Create an instance of {@link FlightScheduleDoesNotExistException }
     * 
     */
    public FlightScheduleDoesNotExistException createFlightScheduleDoesNotExistException() {
        return new FlightScheduleDoesNotExistException();
    }

    /**
     * Create an instance of {@link InvalidLoginCredentialException }
     * 
     */
    public InvalidLoginCredentialException createInvalidLoginCredentialException() {
        return new InvalidLoginCredentialException();
    }

    /**
     * Create an instance of {@link BookSeat }
     * 
     */
    public BookSeat createBookSeat() {
        return new BookSeat();
    }

    /**
     * Create an instance of {@link BookSeatResponse }
     * 
     */
    public BookSeatResponse createBookSeatResponse() {
        return new BookSeatResponse();
    }

    /**
     * Create an instance of {@link BookgetHighestFareUsingCabinNameSeat }
     * 
     */
    public BookgetHighestFareUsingCabinNameSeat createBookgetHighestFareUsingCabinNameSeat() {
        return new BookgetHighestFareUsingCabinNameSeat();
    }

    /**
     * Create an instance of {@link BookgetHighestFareUsingCabinNameSeatResponse }
     * 
     */
    public BookgetHighestFareUsingCabinNameSeatResponse createBookgetHighestFareUsingCabinNameSeatResponse() {
        return new BookgetHighestFareUsingCabinNameSeatResponse();
    }

    /**
     * Create an instance of {@link CheckSeatIfAvailable }
     * 
     */
    public CheckSeatIfAvailable createCheckSeatIfAvailable() {
        return new CheckSeatIfAvailable();
    }

    /**
     * Create an instance of {@link CheckSeatIfAvailableResponse }
     * 
     */
    public CheckSeatIfAvailableResponse createCheckSeatIfAvailableResponse() {
        return new CheckSeatIfAvailableResponse();
    }

    /**
     * Create an instance of {@link GetCabinSeats }
     * 
     */
    public GetCabinSeats createGetCabinSeats() {
        return new GetCabinSeats();
    }

    /**
     * Create an instance of {@link GetCabinSeatsResponse }
     * 
     */
    public GetCabinSeatsResponse createGetCabinSeatsResponse() {
        return new GetCabinSeatsResponse();
    }

    /**
     * Create an instance of {@link GetCabins }
     * 
     */
    public GetCabins createGetCabins() {
        return new GetCabins();
    }

    /**
     * Create an instance of {@link GetCabinsResponse }
     * 
     */
    public GetCabinsResponse createGetCabinsResponse() {
        return new GetCabinsResponse();
    }

    /**
     * Create an instance of {@link GetFlightScheduleWithId }
     * 
     */
    public GetFlightScheduleWithId createGetFlightScheduleWithId() {
        return new GetFlightScheduleWithId();
    }

    /**
     * Create an instance of {@link GetFlightScheduleWithIdResponse }
     * 
     */
    public GetFlightScheduleWithIdResponse createGetFlightScheduleWithIdResponse() {
        return new GetFlightScheduleWithIdResponse();
    }

    /**
     * Create an instance of {@link GetIslesPlan }
     * 
     */
    public GetIslesPlan createGetIslesPlan() {
        return new GetIslesPlan();
    }

    /**
     * Create an instance of {@link GetIslesPlanResponse }
     * 
     */
    public GetIslesPlanResponse createGetIslesPlanResponse() {
        return new GetIslesPlanResponse();
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
     * Create an instance of {@link GetReservationDetails }
     * 
     */
    public GetReservationDetails createGetReservationDetails() {
        return new GetReservationDetails();
    }

    /**
     * Create an instance of {@link GetReservationDetailsResponse }
     * 
     */
    public GetReservationDetailsResponse createGetReservationDetailsResponse() {
        return new GetReservationDetailsResponse();
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
     * Create an instance of {@link RetrieveDateOfFlightPicked }
     * 
     */
    public RetrieveDateOfFlightPicked createRetrieveDateOfFlightPicked() {
        return new RetrieveDateOfFlightPicked();
    }

    /**
     * Create an instance of {@link RetrieveDateOfFlightPickedResponse }
     * 
     */
    public RetrieveDateOfFlightPickedResponse createRetrieveDateOfFlightPickedResponse() {
        return new RetrieveDateOfFlightPickedResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightScheduleInPlan }
     * 
     */
    public RetrieveFlightScheduleInPlan createRetrieveFlightScheduleInPlan() {
        return new RetrieveFlightScheduleInPlan();
    }

    /**
     * Create an instance of {@link RetrieveFlightScheduleInPlanResponse }
     * 
     */
    public RetrieveFlightScheduleInPlanResponse createRetrieveFlightScheduleInPlanResponse() {
        return new RetrieveFlightScheduleInPlanResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysAfter }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysAfter createRetrieveFlightSchedulePlanWith3DaysAfter() {
        return new RetrieveFlightSchedulePlanWith3DaysAfter();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysAfterResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysAfterResponse createRetrieveFlightSchedulePlanWith3DaysAfterResponse() {
        return new RetrieveFlightSchedulePlanWith3DaysAfterResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysBefore }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysBefore createRetrieveFlightSchedulePlanWith3DaysBefore() {
        return new RetrieveFlightSchedulePlanWith3DaysBefore();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysBeforeResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysBeforeResponse createRetrieveFlightSchedulePlanWith3DaysBeforeResponse() {
        return new RetrieveFlightSchedulePlanWith3DaysBeforeResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWithSameTiming }
     * 
     */
    public RetrieveFlightSchedulePlanWithSameTiming createRetrieveFlightSchedulePlanWithSameTiming() {
        return new RetrieveFlightSchedulePlanWithSameTiming();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWithSameTimingResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWithSameTimingResponse createRetrieveFlightSchedulePlanWithSameTimingResponse() {
        return new RetrieveFlightSchedulePlanWithSameTimingResponse();
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
     * Create an instance of {@link FlightSchedule }
     * 
     */
    public FlightSchedule createFlightSchedule() {
        return new FlightSchedule();
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
     * Create an instance of {@link ReservationDetails }
     * 
     */
    public ReservationDetails createReservationDetails() {
        return new ReservationDetails();
    }

    /**
     * Create an instance of {@link Partner }
     * 
     */
    public Partner createPartner() {
        return new Partner();
    }

    /**
     * Create an instance of {@link Duration }
     * 
     */
    public Duration createDuration() {
        return new Duration();
    }

    /**
     * Create an instance of {@link UnsignedShortArray }
     * 
     */
    public UnsignedShortArray createUnsignedShortArray() {
        return new UnsignedShortArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightDoesNotExistException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FlightDoesNotExistException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "FlightDoesNotExistException")
    public JAXBElement<FlightDoesNotExistException> createFlightDoesNotExistException(FlightDoesNotExistException value) {
        return new JAXBElement<FlightDoesNotExistException>(_FlightDoesNotExistException_QNAME, FlightDoesNotExistException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightScheduleDoesNotExistException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FlightScheduleDoesNotExistException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "FlightScheduleDoesNotExistException")
    public JAXBElement<FlightScheduleDoesNotExistException> createFlightScheduleDoesNotExistException(FlightScheduleDoesNotExistException value) {
        return new JAXBElement<FlightScheduleDoesNotExistException>(_FlightScheduleDoesNotExistException_QNAME, FlightScheduleDoesNotExistException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link BookSeat }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BookSeat }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "bookSeat")
    public JAXBElement<BookSeat> createBookSeat(BookSeat value) {
        return new JAXBElement<BookSeat>(_BookSeat_QNAME, BookSeat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookSeatResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BookSeatResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "bookSeatResponse")
    public JAXBElement<BookSeatResponse> createBookSeatResponse(BookSeatResponse value) {
        return new JAXBElement<BookSeatResponse>(_BookSeatResponse_QNAME, BookSeatResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookgetHighestFareUsingCabinNameSeat }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BookgetHighestFareUsingCabinNameSeat }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "bookgetHighestFareUsingCabinNameSeat")
    public JAXBElement<BookgetHighestFareUsingCabinNameSeat> createBookgetHighestFareUsingCabinNameSeat(BookgetHighestFareUsingCabinNameSeat value) {
        return new JAXBElement<BookgetHighestFareUsingCabinNameSeat>(_BookgetHighestFareUsingCabinNameSeat_QNAME, BookgetHighestFareUsingCabinNameSeat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookgetHighestFareUsingCabinNameSeatResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BookgetHighestFareUsingCabinNameSeatResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "bookgetHighestFareUsingCabinNameSeatResponse")
    public JAXBElement<BookgetHighestFareUsingCabinNameSeatResponse> createBookgetHighestFareUsingCabinNameSeatResponse(BookgetHighestFareUsingCabinNameSeatResponse value) {
        return new JAXBElement<BookgetHighestFareUsingCabinNameSeatResponse>(_BookgetHighestFareUsingCabinNameSeatResponse_QNAME, BookgetHighestFareUsingCabinNameSeatResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckSeatIfAvailable }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckSeatIfAvailable }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "checkSeatIfAvailable")
    public JAXBElement<CheckSeatIfAvailable> createCheckSeatIfAvailable(CheckSeatIfAvailable value) {
        return new JAXBElement<CheckSeatIfAvailable>(_CheckSeatIfAvailable_QNAME, CheckSeatIfAvailable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckSeatIfAvailableResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckSeatIfAvailableResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "checkSeatIfAvailableResponse")
    public JAXBElement<CheckSeatIfAvailableResponse> createCheckSeatIfAvailableResponse(CheckSeatIfAvailableResponse value) {
        return new JAXBElement<CheckSeatIfAvailableResponse>(_CheckSeatIfAvailableResponse_QNAME, CheckSeatIfAvailableResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCabinSeats }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCabinSeats }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getCabinSeats")
    public JAXBElement<GetCabinSeats> createGetCabinSeats(GetCabinSeats value) {
        return new JAXBElement<GetCabinSeats>(_GetCabinSeats_QNAME, GetCabinSeats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCabinSeatsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCabinSeatsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getCabinSeatsResponse")
    public JAXBElement<GetCabinSeatsResponse> createGetCabinSeatsResponse(GetCabinSeatsResponse value) {
        return new JAXBElement<GetCabinSeatsResponse>(_GetCabinSeatsResponse_QNAME, GetCabinSeatsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCabins }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCabins }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getCabins")
    public JAXBElement<GetCabins> createGetCabins(GetCabins value) {
        return new JAXBElement<GetCabins>(_GetCabins_QNAME, GetCabins.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCabinsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCabinsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getCabinsResponse")
    public JAXBElement<GetCabinsResponse> createGetCabinsResponse(GetCabinsResponse value) {
        return new JAXBElement<GetCabinsResponse>(_GetCabinsResponse_QNAME, GetCabinsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlightScheduleWithId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFlightScheduleWithId }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getFlightScheduleWithId")
    public JAXBElement<GetFlightScheduleWithId> createGetFlightScheduleWithId(GetFlightScheduleWithId value) {
        return new JAXBElement<GetFlightScheduleWithId>(_GetFlightScheduleWithId_QNAME, GetFlightScheduleWithId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlightScheduleWithIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFlightScheduleWithIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getFlightScheduleWithIdResponse")
    public JAXBElement<GetFlightScheduleWithIdResponse> createGetFlightScheduleWithIdResponse(GetFlightScheduleWithIdResponse value) {
        return new JAXBElement<GetFlightScheduleWithIdResponse>(_GetFlightScheduleWithIdResponse_QNAME, GetFlightScheduleWithIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIslesPlan }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetIslesPlan }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getIslesPlan")
    public JAXBElement<GetIslesPlan> createGetIslesPlan(GetIslesPlan value) {
        return new JAXBElement<GetIslesPlan>(_GetIslesPlan_QNAME, GetIslesPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIslesPlanResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetIslesPlanResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getIslesPlanResponse")
    public JAXBElement<GetIslesPlanResponse> createGetIslesPlanResponse(GetIslesPlanResponse value) {
        return new JAXBElement<GetIslesPlanResponse>(_GetIslesPlanResponse_QNAME, GetIslesPlanResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReservationDetails }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetReservationDetails }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getReservationDetails")
    public JAXBElement<GetReservationDetails> createGetReservationDetails(GetReservationDetails value) {
        return new JAXBElement<GetReservationDetails>(_GetReservationDetails_QNAME, GetReservationDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReservationDetailsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetReservationDetailsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getReservationDetailsResponse")
    public JAXBElement<GetReservationDetailsResponse> createGetReservationDetailsResponse(GetReservationDetailsResponse value) {
        return new JAXBElement<GetReservationDetailsResponse>(_GetReservationDetailsResponse_QNAME, GetReservationDetailsResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveDateOfFlightPicked }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveDateOfFlightPicked }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveDateOfFlightPicked")
    public JAXBElement<RetrieveDateOfFlightPicked> createRetrieveDateOfFlightPicked(RetrieveDateOfFlightPicked value) {
        return new JAXBElement<RetrieveDateOfFlightPicked>(_RetrieveDateOfFlightPicked_QNAME, RetrieveDateOfFlightPicked.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveDateOfFlightPickedResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveDateOfFlightPickedResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveDateOfFlightPickedResponse")
    public JAXBElement<RetrieveDateOfFlightPickedResponse> createRetrieveDateOfFlightPickedResponse(RetrieveDateOfFlightPickedResponse value) {
        return new JAXBElement<RetrieveDateOfFlightPickedResponse>(_RetrieveDateOfFlightPickedResponse_QNAME, RetrieveDateOfFlightPickedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightScheduleInPlan }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightScheduleInPlan }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightScheduleInPlan")
    public JAXBElement<RetrieveFlightScheduleInPlan> createRetrieveFlightScheduleInPlan(RetrieveFlightScheduleInPlan value) {
        return new JAXBElement<RetrieveFlightScheduleInPlan>(_RetrieveFlightScheduleInPlan_QNAME, RetrieveFlightScheduleInPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightScheduleInPlanResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightScheduleInPlanResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightScheduleInPlanResponse")
    public JAXBElement<RetrieveFlightScheduleInPlanResponse> createRetrieveFlightScheduleInPlanResponse(RetrieveFlightScheduleInPlanResponse value) {
        return new JAXBElement<RetrieveFlightScheduleInPlanResponse>(_RetrieveFlightScheduleInPlanResponse_QNAME, RetrieveFlightScheduleInPlanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfter }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfter }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysAfter")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfter> createRetrieveFlightSchedulePlanWith3DaysAfter(RetrieveFlightSchedulePlanWith3DaysAfter value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfter>(_RetrieveFlightSchedulePlanWith3DaysAfter_QNAME, RetrieveFlightSchedulePlanWith3DaysAfter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfterResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfterResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysAfterResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfterResponse> createRetrieveFlightSchedulePlanWith3DaysAfterResponse(RetrieveFlightSchedulePlanWith3DaysAfterResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfterResponse>(_RetrieveFlightSchedulePlanWith3DaysAfterResponse_QNAME, RetrieveFlightSchedulePlanWith3DaysAfterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBefore }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBefore }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysBefore")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysBefore> createRetrieveFlightSchedulePlanWith3DaysBefore(RetrieveFlightSchedulePlanWith3DaysBefore value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysBefore>(_RetrieveFlightSchedulePlanWith3DaysBefore_QNAME, RetrieveFlightSchedulePlanWith3DaysBefore.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBeforeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBeforeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysBeforeResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysBeforeResponse> createRetrieveFlightSchedulePlanWith3DaysBeforeResponse(RetrieveFlightSchedulePlanWith3DaysBeforeResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysBeforeResponse>(_RetrieveFlightSchedulePlanWith3DaysBeforeResponse_QNAME, RetrieveFlightSchedulePlanWith3DaysBeforeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTiming }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTiming }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWithSameTiming")
    public JAXBElement<RetrieveFlightSchedulePlanWithSameTiming> createRetrieveFlightSchedulePlanWithSameTiming(RetrieveFlightSchedulePlanWithSameTiming value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWithSameTiming>(_RetrieveFlightSchedulePlanWithSameTiming_QNAME, RetrieveFlightSchedulePlanWithSameTiming.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTimingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTimingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWithSameTimingResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWithSameTimingResponse> createRetrieveFlightSchedulePlanWithSameTimingResponse(RetrieveFlightSchedulePlanWithSameTimingResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWithSameTimingResponse>(_RetrieveFlightSchedulePlanWithSameTimingResponse_QNAME, RetrieveFlightSchedulePlanWithSameTimingResponse.class, null, value);
    }

}
