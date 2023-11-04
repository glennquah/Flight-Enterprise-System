/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.Airport;
import entity.Cabin;
import entity.Flight;
import entity.FlightRoute;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AircraftConfigurationDoesNotExistException;
import util.exception.FlightDoesNotExist;
import util.exception.FlightRouteDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Stateless
public class FlightSessionBean implements FlightSessionBeanRemote, FlightSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewFlight(Flight flight, Long flightRouteId, Long aircraftConfigId) throws FlightRouteDoesNotExistException, AircraftConfigurationDoesNotExistException {
        try {
            FlightRoute fr = em.find(FlightRoute.class, flightRouteId);
            try {
                AircraftConfiguration ac = em.find(AircraftConfiguration.class, aircraftConfigId);

                //sync flight route to flight
                List<Flight> listOfFlights = fr.getListOfFlights();
                listOfFlights.add(flight);
                fr.setListOfFlights(listOfFlights);

                flight.setFlightRoute(fr);

                //sync aircraft config to flight
                List<Flight> listOfAcFlights = ac.getListOfFlights();
                listOfAcFlights.add(flight);
                //might have error here when updating
                ac.setListOfFlights(listOfAcFlights);
                flight.setAircraftConfig(ac);

                em.persist(flight);
                em.flush();

                return flight.getFlightId();
            } catch (NoResultException e) {
                throw new AircraftConfigurationDoesNotExistException("Aircraft Configuration Does Not Exist"); 
            }
        } catch (NoResultException e) {
            throw new FlightRouteDoesNotExistException("Flightroute does not exist");
        }
    }
    
    @Override
    public Integer getTotalSeats(Long id) throws FlightDoesNotExist {
            Flight flight = getFlightWithId(id);
            List<Cabin> listOfCabs = flight.getAircraftConfig().getListOfCabins();
            listOfCabs.size();
            Integer totalSeats = 0;
            for (int i = 0; i < listOfCabs.size(); i++) {
                totalSeats += listOfCabs.get(i).getTotalSeats();
            }
            return totalSeats;
    }
    
    @Override
    public Integer getReservedSeats(Long id) throws FlightDoesNotExist {
        try {
            Flight flight = getFlightWithId(id);
            List<Cabin> listOfCabs = flight.getAircraftConfig().getListOfCabins();
            listOfCabs.size();
            Integer reservedSeats = 0;
            for (int i = 0; i < listOfCabs.size(); i++) {
                reservedSeats += listOfCabs.get(i).getReservedSeats();
            }
            return reservedSeats;
        } catch (NoResultException e) {
            throw new FlightDoesNotExist("Flight Route Does Not Exist");
        }
    }
    
    @Override
    public List<Flight> retrieveAllFlights() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT f FROM Flight f");
        return query.getResultList();
    }
    
    @Override
    public Flight getFlightWithId(Long id) throws FlightDoesNotExist {
        try {
            Query query = em.createQuery("Select f from Flight f WHERE f.flightId = :flightId");
            query.setParameter("flightId", id);
            Flight f= (Flight)query.getSingleResult();
            System.out.println("*********************** f:" + f.getFlightId());
            return f;
        } catch (NoResultException e) {
            throw new FlightDoesNotExist("Flight Route Does Not Exist");
        }
    }

    @Override
    public long removeFlight(Long id) throws FlightDoesNotExist {
        try {
            Flight flight = getFlightWithId(id);
            em.remove(flight);
            em.flush();
            return flight.getFlightId();
        } catch (NoResultException e) {
            throw new FlightDoesNotExist("Flight Does Not Exist");
        }
    }
    
    @Override
    public Long changeFlightNumber(Long flightIdNum, Integer newFlightNum) throws FlightDoesNotExist {
        try {
            Flight flight = getFlightWithId(flightIdNum);
            flight.setFlightNumber(newFlightNum);
            return flight.getFlightId();
        } catch (NoResultException e) {
            throw new FlightDoesNotExist("Flight Does Not Exist");
        }
    }
    
    @Override
    public Long changeFlightRoute(Long flightIdNum, Long newFlightRouteId, Long oldFlightRouteId) throws FlightDoesNotExist {
        try {        
            Flight flight = getFlightWithId(flightIdNum);
            // to desynch first
            FlightRoute Oldfr = em.find(FlightRoute.class, oldFlightRouteId);
            List<Flight> listOfFlights = Oldfr.getListOfFlights();
            List<Flight> listOfFlightAfterRemoval = new ArrayList<>();
            for (int i = 0; i < listOfFlights.size(); i ++) {
                if (listOfFlights.get(i).getFlightId() != flightIdNum) {
                    listOfFlightAfterRemoval.add(listOfFlights.get(i));
                }
            }
            Oldfr.setListOfFlights(listOfFlightAfterRemoval);

            //synch new one
            FlightRoute Newfr = em.find(FlightRoute.class, newFlightRouteId);
            List<Flight> listOfFlightsNew = Newfr.getListOfFlights();
            listOfFlightsNew.add(flight);
            Newfr.setListOfFlights(listOfFlightsNew);
            flight.setFlightRoute(Newfr);
            return flight.getFlightId();
        } catch (NoResultException e) {
            throw new FlightDoesNotExist("Flight Does Not Exist");
        }
    }
    
    @Override
    public Long changeFlightConfig(Long flightIdNum, Long newFlightConfigId, Long oldFlightConfigId) throws FlightDoesNotExist {
        try {
            Flight flight = getFlightWithId(flightIdNum);
            // to desynch first
            AircraftConfiguration OldAC = em.find(AircraftConfiguration.class, oldFlightConfigId);
            List<Flight> listOfFlights = OldAC.getListOfFlights();
            List<Flight> listOfFlightAfterRemoval = new ArrayList<>();
            for (int i = 0; i < listOfFlights.size(); i ++) {
                if (listOfFlights.get(i).getFlightId() != flightIdNum) {
                    listOfFlightAfterRemoval.add(listOfFlights.get(i));
                }
            }
            OldAC.setListOfFlights(listOfFlightAfterRemoval);

            //synch new one
            AircraftConfiguration NewAC = em.find(AircraftConfiguration.class, newFlightConfigId);
            List<Flight> listOfFlightsNew = NewAC.getListOfFlights();
            listOfFlightsNew.add(flight);
            NewAC.setListOfFlights(listOfFlightsNew);
            flight.setAircraftConfig(NewAC);
            return flight.getFlightId();
        } catch (NoResultException e) {
            throw new NoResultException("Flight Does Not Exist");
        }
    }
    
    @Override
    public List<Flight> retrieveFlightsThatHasDepAndDest(Long originAirport, Long destAirport) {
        Airport ogAirport = em.find(Airport.class, originAirport);
        Airport desAirport = em.find(Airport.class, destAirport);
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightRoute.origin = :origin AND f.flightRoute.destination = :destination");
        query.setParameter("origin", ogAirport);
        query.setParameter("destination", desAirport);
        return (List<Flight>)query.getResultList();
    }     
    
    @Override
    public List<Flight> retrieveFlightsThatHasDepAndDestConnectingFlight(Long originAirport, Long destAirport) {
        Airport ogAirport = em.find(Airport.class, originAirport);
        //Becasuse hub is TPE
        long hubAirportId = 1;
        Airport hubAirport = em.find(Airport.class, hubAirportId);
        Airport desAirport = em.find(Airport.class, destAirport);
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightRoute.origin = :origin AND f.flightRoute.destination = :destination");
        query.setParameter("origin", ogAirport);
        query.setParameter("destination", hubAirport);
        List<Flight> listOFlightsToHub = (List<Flight>)query.getResultList();
        query.setParameter("origin", hubAirport);
        query.setParameter("destination", desAirport);
        List<Flight> listOFlightsFromHub = (List<Flight>)query.getResultList();
        List<Flight> combinedList = new ArrayList<>(listOFlightsFromHub); 
        combinedList.addAll(listOFlightsToHub); 
        return combinedList;
    }   
}
