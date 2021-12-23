package storage;

import booking.models.Booking;
import driver.models.Driver;
import rider.models.Rider;
import vehicle.models.Vehicle;
import city.models.City;

import java.util.List;

public interface IStorageService {
    Boolean registerRider(Rider rider);
    Boolean registerDriver(Driver driver);
    Boolean registerVehicle(Vehicle vehicle);
    Boolean updateLocation(Vehicle vehicle);
    Boolean book(Booking booking);
    Boolean registerCity(City city);
    Vehicle find(String cityName);
    Boolean endTrip(Long timeStamp, String bookingId);
    Vehicle getVehicle(String carNumber);
}
