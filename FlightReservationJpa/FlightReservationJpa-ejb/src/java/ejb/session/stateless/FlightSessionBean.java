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
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.enumeration.FlightRouteStatusEnum;
import util.enumeration.FlightStatusEnum;
import util.exception.AircraftConfigurationDoesNotExistException;
import util.exception.FlightDoesNotExistException;
import util.exception.FlightRouteDisabledException;
import util.exception.FlightRouteDoesNotExistException;
import util.exception.FlightScheduleDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Stateless
public class FlightSessionBean implements FlightSessionBeanRemote, FlightSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewFlight(Flight flight, Long flightRouteId, Long aircraftConfigId) throws FlightRouteDisabledException, FlightRouteDoesNotExistException, AircraftConfigurationDoesNotExistException {
        try {
            FlightRoute fr = em.find(FlightRoute.class, flightRouteId);
            
            if (fr.getFlightRouteStatus() == FlightRouteStatusEnum.DISABLED) {
                throw new FlightRouteDisabledException("This Flight Route is Disabled!");
            }
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
                throw new AircraftConfigurationDoesNotExistException("Aircraft Configuration Does Not Exist!"); 
            }
        } catch (NoResultException e) {
            throw new FlightRouteDoesNotExistException("Flight Route Does Not Exist!");
        }
    }
    
    @Override
    public Long createNewFlightWithReturn(Flight flight, Flight returnFlight, Long flightRouteId, Long aircraftConfigId) throws FlightRouteDisabledException, FlightRouteDoesNotExistException, AircraftConfigurationDoesNotExistException {
        try {
            FlightRoute fr = em.find(FlightRoute.class, flightRouteId);
//            Long returnFrId = fr.getReturnFlightRouteId();
//            FlightRoute returnFr = em.find(FlightRoute.class, returnFrId);
            
            if (fr.getFlightRouteStatus() == FlightRouteStatusEnum.DISABLED) {
                throw new FlightRouteDisabledException("This Flight Route is Disabled!");
            }
            try {
                AircraftConfiguration ac = em.find(AircraftConfiguration.class, aircraftConfigId);

                //sync flight route to flight
                List<Flight> listOfFlights = fr.getListOfFlights();
                listOfFlights.add(flight);
                fr.setListOfFlights(listOfFlights);
                
//                List<Flight> listOfFlightsReturn = returnFr.getListOfFlights();
//                listOfFlightsReturn.add(returnFlight);
//                returnFr.setListOfFlights(listOfFlightsReturn);
//
                flight.setFlightRoute(fr);
//                returnFlight.setFlightRoute(returnFr);

                //sync aircraft config to flight
                List<Flight> listOfAcFlights = ac.getListOfFlights();
                listOfAcFlights.add(flight);
                listOfAcFlights.add(returnFlight);
                //might have error here when updating
                ac.setListOfFlights(listOfAcFlights);
                flight.setAircraftConfig(ac);
                returnFlight.setAircraftConfig(ac);

                flight.setReturnFlightNumber(returnFlight.getFlightNumber());
                returnFlight.setReturnFlightNumber(flight.getFlightNumber());
                
                em.persist(flight);
                em.persist(returnFlight);
                em.flush();

                return flight.getFlightId();
            } catch (NoResultException e) {
                throw new AircraftConfigurationDoesNotExistException("Aircraft Configuration Does Not Exist!"); 
            }
        } catch (NoResultException e) {
            throw new FlightRouteDoesNotExistException("Flight Route Does Not Exist!");
        }
    }
    
    @Override
    public Integer getTotalSeats(Long id) throws FlightDoesNotExistException {
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
    public Integer getReservedSeats(Long id) throws FlightDoesNotExistException {
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
            throw new FlightDoesNotExistException("Flight Route Does Not Exist!");
        }
    }
    
    @Override
    public List<Flight> retrieveAllFlights() {
        Query query = em.createQuery("SELECT f FROM Flight f");
        return query.getResultList();
    }
    
    @Override
    public Flight getFlightWithId(Long id) throws FlightDoesNotExistException {
        try {
            Query query = em.createQuery("Select f from Flight f WHERE f.flightId = :flightId");
            query.setParameter("flightId", id);
            Flight f= (Flight)query.getSingleResult();
            System.out.println("*********************** f:" + f.getFlightId());
            return f;
        } catch (NoResultException e) {
            throw new FlightDoesNotExistException("Flight Route Does Not Exist!");
        }
    }

    @Override
    public long removeFlight(Long id) throws FlightDoesNotExistException {
        try {
            Flight flight = getFlightWithId(id);
            List<FlightSchedulePlan> flightSchedulePlan = flight.getListOfFlightSchedulePlans();
            flightSchedulePlan.size();
            
            if (flightSchedulePlan.size() == 0) {
                em.remove(flight);
                em.flush();
            } else {
                flight.setFlightStatus(FlightStatusEnum.DISABLED);
            }      

            return flight.getFlightId();
        } catch (NoResultException e) {
            throw new FlightDoesNotExistException("Flight Does Not Exist!");
        }
    }
    
    @Override
    public Long changeFlightNumber(Long flightIdNum, Integer newFlightNum) throws FlightDoesNotExistException {
        try {
            Flight flight = getFlightWithId(flightIdNum);
            flight.setFlightNumber(newFlightNum);
            return flight.getFlightId();
        } catch (NoResultException e) {
            throw new FlightDoesNotExistException("Flight Does Not Exist!");
        }
    }
    
    @Override
    public Long changeFlightRoute(Long flightIdNum, Long newFlightRouteId, Long oldFlightRouteId) throws FlightRouteDisabledException, FlightDoesNotExistException {
        try {        
            Flight flight = getFlightWithId(flightIdNum);
            // to desynch first
            FlightRoute oldFlightRoute = em.find(FlightRoute.class, oldFlightRouteId);
            List<Flight> listOfFlights = oldFlightRoute.getListOfFlights();
            List<Flight> listOfFlightAfterRemoval = new ArrayList<>();
            
            for (int i = 0; i < listOfFlights.size(); i ++) {
                if (listOfFlights.get(i).getFlightId() != flightIdNum) {
                    listOfFlightAfterRemoval.add(listOfFlights.get(i));
                }
            }
            
            oldFlightRoute.setListOfFlights(listOfFlightAfterRemoval);

            //synch new one
            FlightRoute newFlightRoute = em.find(FlightRoute.class, newFlightRouteId);
            if (newFlightRoute.getFlightRouteStatus() == FlightRouteStatusEnum.DISABLED) {
                throw new FlightRouteDisabledException("This Flight Route is Disabled!");
            }
            
            List<Flight> listOfFlightsNew = newFlightRoute.getListOfFlights();
            listOfFlightsNew.add(flight);
            newFlightRoute.setListOfFlights(listOfFlightsNew);
            flight.setFlightRoute(newFlightRoute);
            
            return flight.getFlightId();
        } catch (NoResultException e) {
            throw new FlightDoesNotExistException("Flight Does Not Exist!");
        }
    }
    
    @Override
    public Long changeFlightConfig(Long flightIdNum, Long newFlightConfigId, Long oldFlightConfigId) throws FlightDoesNotExistException {
        try {
            Flight flight = getFlightWithId(flightIdNum);
            // to desynch first
            AircraftConfiguration oldAircraftConfig = em.find(AircraftConfiguration.class, oldFlightConfigId);
            List<Flight> listOfFlights = oldAircraftConfig.getListOfFlights();
            List<Flight> listOfFlightAfterRemoval = new ArrayList<>();
            
            for (int i = 0; i < listOfFlights.size(); i ++) {
                if (listOfFlights.get(i).getFlightId() != flightIdNum) {
                    listOfFlightAfterRemoval.add(listOfFlights.get(i));
                }
            }
            
            oldAircraftConfig.setListOfFlights(listOfFlightAfterRemoval);

            //synch new one
            AircraftConfiguration newAircraftConfig = em.find(AircraftConfiguration.class, newFlightConfigId);
            List<Flight> listOfFlightsNew = newAircraftConfig.getListOfFlights();
            listOfFlightsNew.add(flight);
            
            newAircraftConfig.setListOfFlights(listOfFlightsNew);
            flight.setAircraftConfig(newAircraftConfig);
            
            return flight.getFlightId();
        } catch (NoResultException e) {
            throw new NoResultException("Flight Does Not Exist!");
        }
    }
    
    @Override 
    public List<Cabin> getCabin(Integer flightNumber) throws FlightScheduleDoesNotExistException {
        try {
        Query query = em.createQuery("Select f from Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight)query.getSingleResult();
        
        List<Cabin> cabins = flight.getAircraftConfig().getListOfCabins();
        cabins.size();

        return cabins;
        } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
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
    
    @Override
    public Boolean haveComplementaryFlight(Integer flightNumber) {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight) query.getSingleResult();
        
        return flight.getReturnFlightNumber() != 0;
    }
    
    @Override
    public Integer returnFlightNumber(Integer flightNumber) {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight) query.getSingleResult();
        
        return flight.getReturnFlightNumber();
    }
}
