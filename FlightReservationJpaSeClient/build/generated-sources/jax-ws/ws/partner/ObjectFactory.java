
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
    private final static QName _Aircraft_QNAME = new QName("http://ws.session.ejb/", "aircraft");
    private final static QName _AircraftConfiguration_QNAME = new QName("http://ws.session.ejb/", "aircraftConfiguration");
    private final static QName _Airport_QNAME = new QName("http://ws.session.ejb/", "airport");
    private final static QName _BookSeat_QNAME = new QName("http://ws.session.ejb/", "bookSeat");
    private final static QName _BookSeatResponse_QNAME = new QName("http://ws.session.ejb/", "bookSeatResponse");
    private final static QName _Cabin_QNAME = new QName("http://ws.session.ejb/", "cabin");
    private final static QName _CheckIfFlightSchedIdIsDirect_QNAME = new QName("http://ws.session.ejb/", "checkIfFlightSchedIdIsDirect");
    private final static QName _CheckIfFlightSchedIdIsDirectResponse_QNAME = new QName("http://ws.session.ejb/", "checkIfFlightSchedIdIsDirectResponse");
    private final static QName _CheckSeatIfAvailable_QNAME = new QName("http://ws.session.ejb/", "checkSeatIfAvailable");
    private final static QName _CheckSeatIfAvailableResponse_QNAME = new QName("http://ws.session.ejb/", "checkSeatIfAvailableResponse");
    private final static QName _CreateReservationDetailsForPartner_QNAME = new QName("http://ws.session.ejb/", "createReservationDetailsForPartner");
    private final static QName _CreateReservationDetailsForPartnerResponse_QNAME = new QName("http://ws.session.ejb/", "createReservationDetailsForPartnerResponse");
    private final static QName _FlightRoute_QNAME = new QName("http://ws.session.ejb/", "flightRoute");
    private final static QName _FlightSchedule_QNAME = new QName("http://ws.session.ejb/", "flightSchedule");
    private final static QName _FlightSchedulePlan_QNAME = new QName("http://ws.session.ejb/", "flightSchedulePlan");
    private final static QName _GetAirportCodeWithAirportId_QNAME = new QName("http://ws.session.ejb/", "getAirportCodeWithAirportId");
    private final static QName _GetAirportCodeWithAirportIdResponse_QNAME = new QName("http://ws.session.ejb/", "getAirportCodeWithAirportIdResponse");
    private final static QName _GetAirportDest_QNAME = new QName("http://ws.session.ejb/", "getAirportDest");
    private final static QName _GetAirportDestResponse_QNAME = new QName("http://ws.session.ejb/", "getAirportDestResponse");
    private final static QName _GetAirportIdWithFlightScheduleId_QNAME = new QName("http://ws.session.ejb/", "getAirportIdWithFlightScheduleId");
    private final static QName _GetAirportIdWithFlightScheduleIdResponse_QNAME = new QName("http://ws.session.ejb/", "getAirportIdWithFlightScheduleIdResponse");
    private final static QName _GetAirportOrigin_QNAME = new QName("http://ws.session.ejb/", "getAirportOrigin");
    private final static QName _GetAirportOriginResponse_QNAME = new QName("http://ws.session.ejb/", "getAirportOriginResponse");
    private final static QName _GetCabinSeatsList_QNAME = new QName("http://ws.session.ejb/", "getCabinSeatsList");
    private final static QName _GetCabinSeatsListResponse_QNAME = new QName("http://ws.session.ejb/", "getCabinSeatsListResponse");
    private final static QName _GetCabins_QNAME = new QName("http://ws.session.ejb/", "getCabins");
    private final static QName _GetCabinsResponse_QNAME = new QName("http://ws.session.ejb/", "getCabinsResponse");
    private final static QName _GetFRUsingFSId_QNAME = new QName("http://ws.session.ejb/", "getFRUsingFSId");
    private final static QName _GetFRUsingFSIdResponse_QNAME = new QName("http://ws.session.ejb/", "getFRUsingFSIdResponse");
    private final static QName _GetFareUsingId_QNAME = new QName("http://ws.session.ejb/", "getFareUsingId");
    private final static QName _GetFareUsingIdResponse_QNAME = new QName("http://ws.session.ejb/", "getFareUsingIdResponse");
    private final static QName _GetFlightScheduleWithId_QNAME = new QName("http://ws.session.ejb/", "getFlightScheduleWithId");
    private final static QName _GetFlightScheduleWithIdResponse_QNAME = new QName("http://ws.session.ejb/", "getFlightScheduleWithIdResponse");
    private final static QName _GetFlightSchedules_QNAME = new QName("http://ws.session.ejb/", "getFlightSchedules");
    private final static QName _GetFlightSchedulesResponse_QNAME = new QName("http://ws.session.ejb/", "getFlightSchedulesResponse");
    private final static QName _GetHighestFareIdInCabin_QNAME = new QName("http://ws.session.ejb/", "getHighestFareIdInCabin");
    private final static QName _GetHighestFareIdInCabinResponse_QNAME = new QName("http://ws.session.ejb/", "getHighestFareIdInCabinResponse");
    private final static QName _GetHighestFareUsingCabinName_QNAME = new QName("http://ws.session.ejb/", "getHighestFareUsingCabinName");
    private final static QName _GetHighestFareUsingCabinNameResponse_QNAME = new QName("http://ws.session.ejb/", "getHighestFareUsingCabinNameResponse");
    private final static QName _GetIslesPlan_QNAME = new QName("http://ws.session.ejb/", "getIslesPlan");
    private final static QName _GetIslesPlanResponse_QNAME = new QName("http://ws.session.ejb/", "getIslesPlanResponse");
    private final static QName _GetListOfHubsId_QNAME = new QName("http://ws.session.ejb/", "getListOfHubsId");
    private final static QName _GetListOfHubsIdConnecting_QNAME = new QName("http://ws.session.ejb/", "getListOfHubsIdConnecting");
    private final static QName _GetListOfHubsIdConnectingResponse_QNAME = new QName("http://ws.session.ejb/", "getListOfHubsIdConnectingResponse");
    private final static QName _GetListOfHubsIdResponse_QNAME = new QName("http://ws.session.ejb/", "getListOfHubsIdResponse");
    private final static QName _GetPartnerId_QNAME = new QName("http://ws.session.ejb/", "getPartnerId");
    private final static QName _GetPartnerIdResponse_QNAME = new QName("http://ws.session.ejb/", "getPartnerIdResponse");
    private final static QName _GetReservationDetailsPartner_QNAME = new QName("http://ws.session.ejb/", "getReservationDetailsPartner");
    private final static QName _GetReservationDetailsPartnerResponse_QNAME = new QName("http://ws.session.ejb/", "getReservationDetailsPartnerResponse");
    private final static QName _LinkCreditCard_QNAME = new QName("http://ws.session.ejb/", "linkCreditCard");
    private final static QName _LinkCreditCardResponse_QNAME = new QName("http://ws.session.ejb/", "linkCreditCardResponse");
    private final static QName _LinkFlightSchedule_QNAME = new QName("http://ws.session.ejb/", "linkFlightSchedule");
    private final static QName _LinkFlightScheduleResponse_QNAME = new QName("http://ws.session.ejb/", "linkFlightScheduleResponse");
    private final static QName _Login_QNAME = new QName("http://ws.session.ejb/", "login");
    private final static QName _LoginResponse_QNAME = new QName("http://ws.session.ejb/", "loginResponse");
    private final static QName _Partner_QNAME = new QName("http://ws.session.ejb/", "partner");
    private final static QName _Persist_QNAME = new QName("http://ws.session.ejb/", "persist");
    private final static QName _PersistResponse_QNAME = new QName("http://ws.session.ejb/", "persistResponse");
    private final static QName _ReservationDetails_QNAME = new QName("http://ws.session.ejb/", "reservationDetails");
    private final static QName _RetrieveAllAirports_QNAME = new QName("http://ws.session.ejb/", "retrieveAllAirports");
    private final static QName _RetrieveAllAirportsResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveAllAirportsResponse");
    private final static QName _RetrieveDateOfFlightPicked_QNAME = new QName("http://ws.session.ejb/", "retrieveDateOfFlightPicked");
    private final static QName _RetrieveDateOfFlightPickedResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveDateOfFlightPickedResponse");
    private final static QName _RetrieveFlightScheduleInPlan_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightScheduleInPlan");
    private final static QName _RetrieveFlightScheduleInPlanResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightScheduleInPlanResponse");
    private final static QName _RetrieveFlightSchedulePlanAfterTiming_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanAfterTiming");
    private final static QName _RetrieveFlightSchedulePlanAfterTimingResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanAfterTimingResponse");
    private final static QName _RetrieveFlightSchedulePlanAfterTimingReturnConnecting_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanAfterTimingReturnConnecting");
    private final static QName _RetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanAfterTimingReturnConnectingResponse");
    private final static QName _RetrieveFlightSchedulePlanWith1DayAfter_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith1DayAfter");
    private final static QName _RetrieveFlightSchedulePlanWith1DayAfterResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith1DayAfterResponse");
    private final static QName _RetrieveFlightSchedulePlanWith1DayAfterReturnConnecting_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith1DayAfterReturnConnecting");
    private final static QName _RetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysAfterConnecting_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysAfterConnecting");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysAfterConnectingResponse");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysAfterPartner_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysAfterPartner");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysAfterPartnerResponse");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysBeforeConnecting_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysBeforeConnecting");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysBeforePartner_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysBeforePartner");
    private final static QName _RetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWith3DaysBeforePartnerResponse");
    private final static QName _RetrieveFlightSchedulePlanWithSameTimingConnecting_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWithSameTimingConnecting");
    private final static QName _RetrieveFlightSchedulePlanWithSameTimingConnectingResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWithSameTimingConnectingResponse");
    private final static QName _RetrieveFlightSchedulePlanWithSameTimingPartner_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWithSameTimingPartner");
    private final static QName _RetrieveFlightSchedulePlanWithSameTimingPartnerResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveFlightSchedulePlanWithSameTimingPartnerResponse");

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
     * Create an instance of {@link Aircraft }
     * 
     */
    public Aircraft createAircraft() {
        return new Aircraft();
    }

    /**
     * Create an instance of {@link AircraftConfiguration }
     * 
     */
    public AircraftConfiguration createAircraftConfiguration() {
        return new AircraftConfiguration();
    }

    /**
     * Create an instance of {@link Airport }
     * 
     */
    public Airport createAirport() {
        return new Airport();
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
     * Create an instance of {@link Cabin }
     * 
     */
    public Cabin createCabin() {
        return new Cabin();
    }

    /**
     * Create an instance of {@link CheckIfFlightSchedIdIsDirect }
     * 
     */
    public CheckIfFlightSchedIdIsDirect createCheckIfFlightSchedIdIsDirect() {
        return new CheckIfFlightSchedIdIsDirect();
    }

    /**
     * Create an instance of {@link CheckIfFlightSchedIdIsDirectResponse }
     * 
     */
    public CheckIfFlightSchedIdIsDirectResponse createCheckIfFlightSchedIdIsDirectResponse() {
        return new CheckIfFlightSchedIdIsDirectResponse();
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
     * Create an instance of {@link CreateReservationDetailsForPartner }
     * 
     */
    public CreateReservationDetailsForPartner createCreateReservationDetailsForPartner() {
        return new CreateReservationDetailsForPartner();
    }

    /**
     * Create an instance of {@link CreateReservationDetailsForPartnerResponse }
     * 
     */
    public CreateReservationDetailsForPartnerResponse createCreateReservationDetailsForPartnerResponse() {
        return new CreateReservationDetailsForPartnerResponse();
    }

    /**
     * Create an instance of {@link FlightRoute }
     * 
     */
    public FlightRoute createFlightRoute() {
        return new FlightRoute();
    }

    /**
     * Create an instance of {@link FlightSchedule }
     * 
     */
    public FlightSchedule createFlightSchedule() {
        return new FlightSchedule();
    }

    /**
     * Create an instance of {@link FlightSchedulePlan }
     * 
     */
    public FlightSchedulePlan createFlightSchedulePlan() {
        return new FlightSchedulePlan();
    }

    /**
     * Create an instance of {@link GetAirportCodeWithAirportId }
     * 
     */
    public GetAirportCodeWithAirportId createGetAirportCodeWithAirportId() {
        return new GetAirportCodeWithAirportId();
    }

    /**
     * Create an instance of {@link GetAirportCodeWithAirportIdResponse }
     * 
     */
    public GetAirportCodeWithAirportIdResponse createGetAirportCodeWithAirportIdResponse() {
        return new GetAirportCodeWithAirportIdResponse();
    }

    /**
     * Create an instance of {@link GetAirportDest }
     * 
     */
    public GetAirportDest createGetAirportDest() {
        return new GetAirportDest();
    }

    /**
     * Create an instance of {@link GetAirportDestResponse }
     * 
     */
    public GetAirportDestResponse createGetAirportDestResponse() {
        return new GetAirportDestResponse();
    }

    /**
     * Create an instance of {@link GetAirportIdWithFlightScheduleId }
     * 
     */
    public GetAirportIdWithFlightScheduleId createGetAirportIdWithFlightScheduleId() {
        return new GetAirportIdWithFlightScheduleId();
    }

    /**
     * Create an instance of {@link GetAirportIdWithFlightScheduleIdResponse }
     * 
     */
    public GetAirportIdWithFlightScheduleIdResponse createGetAirportIdWithFlightScheduleIdResponse() {
        return new GetAirportIdWithFlightScheduleIdResponse();
    }

    /**
     * Create an instance of {@link GetAirportOrigin }
     * 
     */
    public GetAirportOrigin createGetAirportOrigin() {
        return new GetAirportOrigin();
    }

    /**
     * Create an instance of {@link GetAirportOriginResponse }
     * 
     */
    public GetAirportOriginResponse createGetAirportOriginResponse() {
        return new GetAirportOriginResponse();
    }

    /**
     * Create an instance of {@link GetCabinSeatsList }
     * 
     */
    public GetCabinSeatsList createGetCabinSeatsList() {
        return new GetCabinSeatsList();
    }

    /**
     * Create an instance of {@link GetCabinSeatsListResponse }
     * 
     */
    public GetCabinSeatsListResponse createGetCabinSeatsListResponse() {
        return new GetCabinSeatsListResponse();
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
     * Create an instance of {@link GetFRUsingFSId }
     * 
     */
    public GetFRUsingFSId createGetFRUsingFSId() {
        return new GetFRUsingFSId();
    }

    /**
     * Create an instance of {@link GetFRUsingFSIdResponse }
     * 
     */
    public GetFRUsingFSIdResponse createGetFRUsingFSIdResponse() {
        return new GetFRUsingFSIdResponse();
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
     * Create an instance of {@link GetHighestFareUsingCabinName }
     * 
     */
    public GetHighestFareUsingCabinName createGetHighestFareUsingCabinName() {
        return new GetHighestFareUsingCabinName();
    }

    /**
     * Create an instance of {@link GetHighestFareUsingCabinNameResponse }
     * 
     */
    public GetHighestFareUsingCabinNameResponse createGetHighestFareUsingCabinNameResponse() {
        return new GetHighestFareUsingCabinNameResponse();
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
     * Create an instance of {@link GetListOfHubsId }
     * 
     */
    public GetListOfHubsId createGetListOfHubsId() {
        return new GetListOfHubsId();
    }

    /**
     * Create an instance of {@link GetListOfHubsIdConnecting }
     * 
     */
    public GetListOfHubsIdConnecting createGetListOfHubsIdConnecting() {
        return new GetListOfHubsIdConnecting();
    }

    /**
     * Create an instance of {@link GetListOfHubsIdConnectingResponse }
     * 
     */
    public GetListOfHubsIdConnectingResponse createGetListOfHubsIdConnectingResponse() {
        return new GetListOfHubsIdConnectingResponse();
    }

    /**
     * Create an instance of {@link GetListOfHubsIdResponse }
     * 
     */
    public GetListOfHubsIdResponse createGetListOfHubsIdResponse() {
        return new GetListOfHubsIdResponse();
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
     * Create an instance of {@link GetReservationDetailsPartner }
     * 
     */
    public GetReservationDetailsPartner createGetReservationDetailsPartner() {
        return new GetReservationDetailsPartner();
    }

    /**
     * Create an instance of {@link GetReservationDetailsPartnerResponse }
     * 
     */
    public GetReservationDetailsPartnerResponse createGetReservationDetailsPartnerResponse() {
        return new GetReservationDetailsPartnerResponse();
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
     * Create an instance of {@link Partner }
     * 
     */
    public Partner createPartner() {
        return new Partner();
    }

    /**
     * Create an instance of {@link Persist }
     * 
     */
    public Persist createPersist() {
        return new Persist();
    }

    /**
     * Create an instance of {@link PersistResponse }
     * 
     */
    public PersistResponse createPersistResponse() {
        return new PersistResponse();
    }

    /**
     * Create an instance of {@link ReservationDetails }
     * 
     */
    public ReservationDetails createReservationDetails() {
        return new ReservationDetails();
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
     * Create an instance of {@link RetrieveFlightSchedulePlanAfterTiming }
     * 
     */
    public RetrieveFlightSchedulePlanAfterTiming createRetrieveFlightSchedulePlanAfterTiming() {
        return new RetrieveFlightSchedulePlanAfterTiming();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanAfterTimingResponse }
     * 
     */
    public RetrieveFlightSchedulePlanAfterTimingResponse createRetrieveFlightSchedulePlanAfterTimingResponse() {
        return new RetrieveFlightSchedulePlanAfterTimingResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanAfterTimingReturnConnecting }
     * 
     */
    public RetrieveFlightSchedulePlanAfterTimingReturnConnecting createRetrieveFlightSchedulePlanAfterTimingReturnConnecting() {
        return new RetrieveFlightSchedulePlanAfterTimingReturnConnecting();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse }
     * 
     */
    public RetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse createRetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse() {
        return new RetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith1DayAfter }
     * 
     */
    public RetrieveFlightSchedulePlanWith1DayAfter createRetrieveFlightSchedulePlanWith1DayAfter() {
        return new RetrieveFlightSchedulePlanWith1DayAfter();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith1DayAfterResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWith1DayAfterResponse createRetrieveFlightSchedulePlanWith1DayAfterResponse() {
        return new RetrieveFlightSchedulePlanWith1DayAfterResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith1DayAfterReturnConnecting }
     * 
     */
    public RetrieveFlightSchedulePlanWith1DayAfterReturnConnecting createRetrieveFlightSchedulePlanWith1DayAfterReturnConnecting() {
        return new RetrieveFlightSchedulePlanWith1DayAfterReturnConnecting();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse createRetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse() {
        return new RetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysAfterConnecting }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysAfterConnecting createRetrieveFlightSchedulePlanWith3DaysAfterConnecting() {
        return new RetrieveFlightSchedulePlanWith3DaysAfterConnecting();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse createRetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse() {
        return new RetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysAfterPartner }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysAfterPartner createRetrieveFlightSchedulePlanWith3DaysAfterPartner() {
        return new RetrieveFlightSchedulePlanWith3DaysAfterPartner();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse createRetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse() {
        return new RetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysBeforeConnecting }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysBeforeConnecting createRetrieveFlightSchedulePlanWith3DaysBeforeConnecting() {
        return new RetrieveFlightSchedulePlanWith3DaysBeforeConnecting();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse createRetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse() {
        return new RetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysBeforePartner }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysBeforePartner createRetrieveFlightSchedulePlanWith3DaysBeforePartner() {
        return new RetrieveFlightSchedulePlanWith3DaysBeforePartner();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse createRetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse() {
        return new RetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWithSameTimingConnecting }
     * 
     */
    public RetrieveFlightSchedulePlanWithSameTimingConnecting createRetrieveFlightSchedulePlanWithSameTimingConnecting() {
        return new RetrieveFlightSchedulePlanWithSameTimingConnecting();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWithSameTimingConnectingResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWithSameTimingConnectingResponse createRetrieveFlightSchedulePlanWithSameTimingConnectingResponse() {
        return new RetrieveFlightSchedulePlanWithSameTimingConnectingResponse();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWithSameTimingPartner }
     * 
     */
    public RetrieveFlightSchedulePlanWithSameTimingPartner createRetrieveFlightSchedulePlanWithSameTimingPartner() {
        return new RetrieveFlightSchedulePlanWithSameTimingPartner();
    }

    /**
     * Create an instance of {@link RetrieveFlightSchedulePlanWithSameTimingPartnerResponse }
     * 
     */
    public RetrieveFlightSchedulePlanWithSameTimingPartnerResponse createRetrieveFlightSchedulePlanWithSameTimingPartnerResponse() {
        return new RetrieveFlightSchedulePlanWithSameTimingPartnerResponse();
    }

    /**
     * Create an instance of {@link Flight }
     * 
     */
    public Flight createFlight() {
        return new Flight();
    }

    /**
     * Create an instance of {@link Fare }
     * 
     */
    public Fare createFare() {
        return new Fare();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Aircraft }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Aircraft }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "aircraft")
    public JAXBElement<Aircraft> createAircraft(Aircraft value) {
        return new JAXBElement<Aircraft>(_Aircraft_QNAME, Aircraft.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AircraftConfiguration }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AircraftConfiguration }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "aircraftConfiguration")
    public JAXBElement<AircraftConfiguration> createAircraftConfiguration(AircraftConfiguration value) {
        return new JAXBElement<AircraftConfiguration>(_AircraftConfiguration_QNAME, AircraftConfiguration.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Cabin }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Cabin }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "cabin")
    public JAXBElement<Cabin> createCabin(Cabin value) {
        return new JAXBElement<Cabin>(_Cabin_QNAME, Cabin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckIfFlightSchedIdIsDirect }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckIfFlightSchedIdIsDirect }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "checkIfFlightSchedIdIsDirect")
    public JAXBElement<CheckIfFlightSchedIdIsDirect> createCheckIfFlightSchedIdIsDirect(CheckIfFlightSchedIdIsDirect value) {
        return new JAXBElement<CheckIfFlightSchedIdIsDirect>(_CheckIfFlightSchedIdIsDirect_QNAME, CheckIfFlightSchedIdIsDirect.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckIfFlightSchedIdIsDirectResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckIfFlightSchedIdIsDirectResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "checkIfFlightSchedIdIsDirectResponse")
    public JAXBElement<CheckIfFlightSchedIdIsDirectResponse> createCheckIfFlightSchedIdIsDirectResponse(CheckIfFlightSchedIdIsDirectResponse value) {
        return new JAXBElement<CheckIfFlightSchedIdIsDirectResponse>(_CheckIfFlightSchedIdIsDirectResponse_QNAME, CheckIfFlightSchedIdIsDirectResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateReservationDetailsForPartner }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateReservationDetailsForPartner }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "createReservationDetailsForPartner")
    public JAXBElement<CreateReservationDetailsForPartner> createCreateReservationDetailsForPartner(CreateReservationDetailsForPartner value) {
        return new JAXBElement<CreateReservationDetailsForPartner>(_CreateReservationDetailsForPartner_QNAME, CreateReservationDetailsForPartner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateReservationDetailsForPartnerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateReservationDetailsForPartnerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "createReservationDetailsForPartnerResponse")
    public JAXBElement<CreateReservationDetailsForPartnerResponse> createCreateReservationDetailsForPartnerResponse(CreateReservationDetailsForPartnerResponse value) {
        return new JAXBElement<CreateReservationDetailsForPartnerResponse>(_CreateReservationDetailsForPartnerResponse_QNAME, CreateReservationDetailsForPartnerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightRoute }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FlightRoute }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "flightRoute")
    public JAXBElement<FlightRoute> createFlightRoute(FlightRoute value) {
        return new JAXBElement<FlightRoute>(_FlightRoute_QNAME, FlightRoute.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightSchedule }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FlightSchedule }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "flightSchedule")
    public JAXBElement<FlightSchedule> createFlightSchedule(FlightSchedule value) {
        return new JAXBElement<FlightSchedule>(_FlightSchedule_QNAME, FlightSchedule.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightSchedulePlan }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FlightSchedulePlan }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "flightSchedulePlan")
    public JAXBElement<FlightSchedulePlan> createFlightSchedulePlan(FlightSchedulePlan value) {
        return new JAXBElement<FlightSchedulePlan>(_FlightSchedulePlan_QNAME, FlightSchedulePlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAirportCodeWithAirportId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAirportCodeWithAirportId }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getAirportCodeWithAirportId")
    public JAXBElement<GetAirportCodeWithAirportId> createGetAirportCodeWithAirportId(GetAirportCodeWithAirportId value) {
        return new JAXBElement<GetAirportCodeWithAirportId>(_GetAirportCodeWithAirportId_QNAME, GetAirportCodeWithAirportId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAirportCodeWithAirportIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAirportCodeWithAirportIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getAirportCodeWithAirportIdResponse")
    public JAXBElement<GetAirportCodeWithAirportIdResponse> createGetAirportCodeWithAirportIdResponse(GetAirportCodeWithAirportIdResponse value) {
        return new JAXBElement<GetAirportCodeWithAirportIdResponse>(_GetAirportCodeWithAirportIdResponse_QNAME, GetAirportCodeWithAirportIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAirportDest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAirportDest }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getAirportDest")
    public JAXBElement<GetAirportDest> createGetAirportDest(GetAirportDest value) {
        return new JAXBElement<GetAirportDest>(_GetAirportDest_QNAME, GetAirportDest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAirportDestResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAirportDestResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getAirportDestResponse")
    public JAXBElement<GetAirportDestResponse> createGetAirportDestResponse(GetAirportDestResponse value) {
        return new JAXBElement<GetAirportDestResponse>(_GetAirportDestResponse_QNAME, GetAirportDestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAirportIdWithFlightScheduleId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAirportIdWithFlightScheduleId }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getAirportIdWithFlightScheduleId")
    public JAXBElement<GetAirportIdWithFlightScheduleId> createGetAirportIdWithFlightScheduleId(GetAirportIdWithFlightScheduleId value) {
        return new JAXBElement<GetAirportIdWithFlightScheduleId>(_GetAirportIdWithFlightScheduleId_QNAME, GetAirportIdWithFlightScheduleId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAirportIdWithFlightScheduleIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAirportIdWithFlightScheduleIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getAirportIdWithFlightScheduleIdResponse")
    public JAXBElement<GetAirportIdWithFlightScheduleIdResponse> createGetAirportIdWithFlightScheduleIdResponse(GetAirportIdWithFlightScheduleIdResponse value) {
        return new JAXBElement<GetAirportIdWithFlightScheduleIdResponse>(_GetAirportIdWithFlightScheduleIdResponse_QNAME, GetAirportIdWithFlightScheduleIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAirportOrigin }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAirportOrigin }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getAirportOrigin")
    public JAXBElement<GetAirportOrigin> createGetAirportOrigin(GetAirportOrigin value) {
        return new JAXBElement<GetAirportOrigin>(_GetAirportOrigin_QNAME, GetAirportOrigin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAirportOriginResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAirportOriginResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getAirportOriginResponse")
    public JAXBElement<GetAirportOriginResponse> createGetAirportOriginResponse(GetAirportOriginResponse value) {
        return new JAXBElement<GetAirportOriginResponse>(_GetAirportOriginResponse_QNAME, GetAirportOriginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCabinSeatsList }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCabinSeatsList }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getCabinSeatsList")
    public JAXBElement<GetCabinSeatsList> createGetCabinSeatsList(GetCabinSeatsList value) {
        return new JAXBElement<GetCabinSeatsList>(_GetCabinSeatsList_QNAME, GetCabinSeatsList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCabinSeatsListResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCabinSeatsListResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getCabinSeatsListResponse")
    public JAXBElement<GetCabinSeatsListResponse> createGetCabinSeatsListResponse(GetCabinSeatsListResponse value) {
        return new JAXBElement<GetCabinSeatsListResponse>(_GetCabinSeatsListResponse_QNAME, GetCabinSeatsListResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFRUsingFSId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFRUsingFSId }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getFRUsingFSId")
    public JAXBElement<GetFRUsingFSId> createGetFRUsingFSId(GetFRUsingFSId value) {
        return new JAXBElement<GetFRUsingFSId>(_GetFRUsingFSId_QNAME, GetFRUsingFSId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFRUsingFSIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFRUsingFSIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getFRUsingFSIdResponse")
    public JAXBElement<GetFRUsingFSIdResponse> createGetFRUsingFSIdResponse(GetFRUsingFSIdResponse value) {
        return new JAXBElement<GetFRUsingFSIdResponse>(_GetFRUsingFSIdResponse_QNAME, GetFRUsingFSIdResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHighestFareUsingCabinName }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetHighestFareUsingCabinName }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getHighestFareUsingCabinName")
    public JAXBElement<GetHighestFareUsingCabinName> createGetHighestFareUsingCabinName(GetHighestFareUsingCabinName value) {
        return new JAXBElement<GetHighestFareUsingCabinName>(_GetHighestFareUsingCabinName_QNAME, GetHighestFareUsingCabinName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHighestFareUsingCabinNameResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetHighestFareUsingCabinNameResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getHighestFareUsingCabinNameResponse")
    public JAXBElement<GetHighestFareUsingCabinNameResponse> createGetHighestFareUsingCabinNameResponse(GetHighestFareUsingCabinNameResponse value) {
        return new JAXBElement<GetHighestFareUsingCabinNameResponse>(_GetHighestFareUsingCabinNameResponse_QNAME, GetHighestFareUsingCabinNameResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListOfHubsId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetListOfHubsId }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getListOfHubsId")
    public JAXBElement<GetListOfHubsId> createGetListOfHubsId(GetListOfHubsId value) {
        return new JAXBElement<GetListOfHubsId>(_GetListOfHubsId_QNAME, GetListOfHubsId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListOfHubsIdConnecting }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetListOfHubsIdConnecting }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getListOfHubsIdConnecting")
    public JAXBElement<GetListOfHubsIdConnecting> createGetListOfHubsIdConnecting(GetListOfHubsIdConnecting value) {
        return new JAXBElement<GetListOfHubsIdConnecting>(_GetListOfHubsIdConnecting_QNAME, GetListOfHubsIdConnecting.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListOfHubsIdConnectingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetListOfHubsIdConnectingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getListOfHubsIdConnectingResponse")
    public JAXBElement<GetListOfHubsIdConnectingResponse> createGetListOfHubsIdConnectingResponse(GetListOfHubsIdConnectingResponse value) {
        return new JAXBElement<GetListOfHubsIdConnectingResponse>(_GetListOfHubsIdConnectingResponse_QNAME, GetListOfHubsIdConnectingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListOfHubsIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetListOfHubsIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getListOfHubsIdResponse")
    public JAXBElement<GetListOfHubsIdResponse> createGetListOfHubsIdResponse(GetListOfHubsIdResponse value) {
        return new JAXBElement<GetListOfHubsIdResponse>(_GetListOfHubsIdResponse_QNAME, GetListOfHubsIdResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReservationDetailsPartner }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetReservationDetailsPartner }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getReservationDetailsPartner")
    public JAXBElement<GetReservationDetailsPartner> createGetReservationDetailsPartner(GetReservationDetailsPartner value) {
        return new JAXBElement<GetReservationDetailsPartner>(_GetReservationDetailsPartner_QNAME, GetReservationDetailsPartner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReservationDetailsPartnerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetReservationDetailsPartnerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getReservationDetailsPartnerResponse")
    public JAXBElement<GetReservationDetailsPartnerResponse> createGetReservationDetailsPartnerResponse(GetReservationDetailsPartnerResponse value) {
        return new JAXBElement<GetReservationDetailsPartnerResponse>(_GetReservationDetailsPartnerResponse_QNAME, GetReservationDetailsPartnerResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Partner }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Partner }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "partner")
    public JAXBElement<Partner> createPartner(Partner value) {
        return new JAXBElement<Partner>(_Partner_QNAME, Partner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Persist }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Persist }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "persist")
    public JAXBElement<Persist> createPersist(Persist value) {
        return new JAXBElement<Persist>(_Persist_QNAME, Persist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PersistResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "persistResponse")
    public JAXBElement<PersistResponse> createPersistResponse(PersistResponse value) {
        return new JAXBElement<PersistResponse>(_PersistResponse_QNAME, PersistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReservationDetails }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReservationDetails }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "reservationDetails")
    public JAXBElement<ReservationDetails> createReservationDetails(ReservationDetails value) {
        return new JAXBElement<ReservationDetails>(_ReservationDetails_QNAME, ReservationDetails.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanAfterTiming }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanAfterTiming }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanAfterTiming")
    public JAXBElement<RetrieveFlightSchedulePlanAfterTiming> createRetrieveFlightSchedulePlanAfterTiming(RetrieveFlightSchedulePlanAfterTiming value) {
        return new JAXBElement<RetrieveFlightSchedulePlanAfterTiming>(_RetrieveFlightSchedulePlanAfterTiming_QNAME, RetrieveFlightSchedulePlanAfterTiming.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanAfterTimingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanAfterTimingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanAfterTimingResponse")
    public JAXBElement<RetrieveFlightSchedulePlanAfterTimingResponse> createRetrieveFlightSchedulePlanAfterTimingResponse(RetrieveFlightSchedulePlanAfterTimingResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanAfterTimingResponse>(_RetrieveFlightSchedulePlanAfterTimingResponse_QNAME, RetrieveFlightSchedulePlanAfterTimingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanAfterTimingReturnConnecting }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanAfterTimingReturnConnecting }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanAfterTimingReturnConnecting")
    public JAXBElement<RetrieveFlightSchedulePlanAfterTimingReturnConnecting> createRetrieveFlightSchedulePlanAfterTimingReturnConnecting(RetrieveFlightSchedulePlanAfterTimingReturnConnecting value) {
        return new JAXBElement<RetrieveFlightSchedulePlanAfterTimingReturnConnecting>(_RetrieveFlightSchedulePlanAfterTimingReturnConnecting_QNAME, RetrieveFlightSchedulePlanAfterTimingReturnConnecting.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanAfterTimingReturnConnectingResponse")
    public JAXBElement<RetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse> createRetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse(RetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse>(_RetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse_QNAME, RetrieveFlightSchedulePlanAfterTimingReturnConnectingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith1DayAfter }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith1DayAfter }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith1DayAfter")
    public JAXBElement<RetrieveFlightSchedulePlanWith1DayAfter> createRetrieveFlightSchedulePlanWith1DayAfter(RetrieveFlightSchedulePlanWith1DayAfter value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith1DayAfter>(_RetrieveFlightSchedulePlanWith1DayAfter_QNAME, RetrieveFlightSchedulePlanWith1DayAfter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith1DayAfterResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith1DayAfterResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith1DayAfterResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWith1DayAfterResponse> createRetrieveFlightSchedulePlanWith1DayAfterResponse(RetrieveFlightSchedulePlanWith1DayAfterResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith1DayAfterResponse>(_RetrieveFlightSchedulePlanWith1DayAfterResponse_QNAME, RetrieveFlightSchedulePlanWith1DayAfterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith1DayAfterReturnConnecting }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith1DayAfterReturnConnecting }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith1DayAfterReturnConnecting")
    public JAXBElement<RetrieveFlightSchedulePlanWith1DayAfterReturnConnecting> createRetrieveFlightSchedulePlanWith1DayAfterReturnConnecting(RetrieveFlightSchedulePlanWith1DayAfterReturnConnecting value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith1DayAfterReturnConnecting>(_RetrieveFlightSchedulePlanWith1DayAfterReturnConnecting_QNAME, RetrieveFlightSchedulePlanWith1DayAfterReturnConnecting.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse> createRetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse(RetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse>(_RetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse_QNAME, RetrieveFlightSchedulePlanWith1DayAfterReturnConnectingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfterConnecting }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfterConnecting }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysAfterConnecting")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfterConnecting> createRetrieveFlightSchedulePlanWith3DaysAfterConnecting(RetrieveFlightSchedulePlanWith3DaysAfterConnecting value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfterConnecting>(_RetrieveFlightSchedulePlanWith3DaysAfterConnecting_QNAME, RetrieveFlightSchedulePlanWith3DaysAfterConnecting.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysAfterConnectingResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse> createRetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse(RetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse>(_RetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse_QNAME, RetrieveFlightSchedulePlanWith3DaysAfterConnectingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfterPartner }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfterPartner }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysAfterPartner")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfterPartner> createRetrieveFlightSchedulePlanWith3DaysAfterPartner(RetrieveFlightSchedulePlanWith3DaysAfterPartner value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfterPartner>(_RetrieveFlightSchedulePlanWith3DaysAfterPartner_QNAME, RetrieveFlightSchedulePlanWith3DaysAfterPartner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysAfterPartnerResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse> createRetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse(RetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse>(_RetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse_QNAME, RetrieveFlightSchedulePlanWith3DaysAfterPartnerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBeforeConnecting }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBeforeConnecting }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysBeforeConnecting")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysBeforeConnecting> createRetrieveFlightSchedulePlanWith3DaysBeforeConnecting(RetrieveFlightSchedulePlanWith3DaysBeforeConnecting value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysBeforeConnecting>(_RetrieveFlightSchedulePlanWith3DaysBeforeConnecting_QNAME, RetrieveFlightSchedulePlanWith3DaysBeforeConnecting.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse> createRetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse(RetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse>(_RetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse_QNAME, RetrieveFlightSchedulePlanWith3DaysBeforeConnectingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBeforePartner }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBeforePartner }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysBeforePartner")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysBeforePartner> createRetrieveFlightSchedulePlanWith3DaysBeforePartner(RetrieveFlightSchedulePlanWith3DaysBeforePartner value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysBeforePartner>(_RetrieveFlightSchedulePlanWith3DaysBeforePartner_QNAME, RetrieveFlightSchedulePlanWith3DaysBeforePartner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWith3DaysBeforePartnerResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse> createRetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse(RetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse>(_RetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse_QNAME, RetrieveFlightSchedulePlanWith3DaysBeforePartnerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTimingConnecting }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTimingConnecting }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWithSameTimingConnecting")
    public JAXBElement<RetrieveFlightSchedulePlanWithSameTimingConnecting> createRetrieveFlightSchedulePlanWithSameTimingConnecting(RetrieveFlightSchedulePlanWithSameTimingConnecting value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWithSameTimingConnecting>(_RetrieveFlightSchedulePlanWithSameTimingConnecting_QNAME, RetrieveFlightSchedulePlanWithSameTimingConnecting.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTimingConnectingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTimingConnectingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWithSameTimingConnectingResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWithSameTimingConnectingResponse> createRetrieveFlightSchedulePlanWithSameTimingConnectingResponse(RetrieveFlightSchedulePlanWithSameTimingConnectingResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWithSameTimingConnectingResponse>(_RetrieveFlightSchedulePlanWithSameTimingConnectingResponse_QNAME, RetrieveFlightSchedulePlanWithSameTimingConnectingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTimingPartner }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTimingPartner }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWithSameTimingPartner")
    public JAXBElement<RetrieveFlightSchedulePlanWithSameTimingPartner> createRetrieveFlightSchedulePlanWithSameTimingPartner(RetrieveFlightSchedulePlanWithSameTimingPartner value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWithSameTimingPartner>(_RetrieveFlightSchedulePlanWithSameTimingPartner_QNAME, RetrieveFlightSchedulePlanWithSameTimingPartner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTimingPartnerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RetrieveFlightSchedulePlanWithSameTimingPartnerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveFlightSchedulePlanWithSameTimingPartnerResponse")
    public JAXBElement<RetrieveFlightSchedulePlanWithSameTimingPartnerResponse> createRetrieveFlightSchedulePlanWithSameTimingPartnerResponse(RetrieveFlightSchedulePlanWithSameTimingPartnerResponse value) {
        return new JAXBElement<RetrieveFlightSchedulePlanWithSameTimingPartnerResponse>(_RetrieveFlightSchedulePlanWithSameTimingPartnerResponse_QNAME, RetrieveFlightSchedulePlanWithSameTimingPartnerResponse.class, null, value);
    }

}
