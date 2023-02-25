package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Customer customer=customerRepository2.findById(customerId).get();
		customerRepository2.delete(customer);

	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		List<Driver> drivers = driverRepository2.findAll();
		TripBooking tripBooking = new TripBooking();
		boolean flag = false;
		for (Driver driver : drivers) {
			if (driver.getCab().getAvailable() == true) {
				driver.getCab().setAvailable(false);
				tripBooking.setTripStatus(TripStatus.CONFIRMED);
				tripBooking.setBill(driver.getCab().getPerkmRate() * distanceInKm);
				tripBooking.setFromLocation(fromLocation);
				tripBooking.setToLocation(toLocation);
				tripBooking.setDistinKm(distanceInKm);


				Customer customer = customerRepository2.findById(customerId).get();
				List<TripBooking> list = customer.getCustomertripBookingList();
				list.add(tripBooking);
				customer.setCustomertripBookingList(list);
				tripBooking.setCustomer(customer);

				List<TripBooking> list1 = driver.getDrivertripBookingList();
				list1.add(tripBooking);
				driver.setDrivertripBookingList(list1);
				tripBooking.setDriver(driver);

				driverRepository2.save(driver);
				customerRepository2.save(customer);
				flag = true;
				break;
			}
		}
		if (flag == false) {
			throw new Exception("No cab available!");
		} else {
			return tripBooking;
		}}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking trip=tripBookingRepository2.findById(tripId).get();
		trip.setTripStatus(TripStatus.CANCELED);
		trip.setBill(0);
		trip.getDriver().getCab().setAvailable(Boolean.TRUE);
		tripBookingRepository2.save(trip);

	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking=new TripBooking();
		tripBooking=tripBookingRepository2.findById(tripId).get();
		tripBooking.setTripStatus(TripStatus.COMPLETED);
		int bill = tripBooking.getDriver().getCab().getPerkmRate()*tripBooking.getDistinKm();
		tripBooking.setBill(bill);
		tripBooking.getDriver().getCab().setAvailable(Boolean.TRUE);
		tripBookingRepository2.save(tripBooking);
	}


	}

