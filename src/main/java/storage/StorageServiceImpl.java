package storage;

import booking.models.Booking;
import driver.models.Driver;
import rider.models.Rider;
import vehicle.models.Vehicle;
import city.models.City;

import java.util.*;

public class StorageServiceImpl implements IStorageService {
    public static final String BOOKING_COMPLETED = "COMPLETED";
    public static final String  BOOKING_STARTED = "STARTED";

    private Map<String, Rider> riderStorage;
    private Map<String, Driver> driverStorage;
    private Map<String, Vehicle> vehicleStorage;
    private Map<String, Booking> bookingStorage;
    private Map<Long, City>  cityStorage;

    public StorageServiceImpl() {
        this.riderStorage = new HashMap<>();
        this.driverStorage = new HashMap<>();
        this.vehicleStorage = new HashMap<>();
        this.bookingStorage = new HashMap<>();
        this.cityStorage = new HashMap<>();
    }

    public Boolean registerRider(Rider rider) {
        StringBuffer sb = new StringBuffer();
        sb.append(rider.getCountryCode()).append(rider.getPhoneNumber());
        String riderUniqueId = sb.toString();
        if(this.riderStorage.get(riderUniqueId) != null){
            throw new RuntimeException("Rider already exist in the system");
        }
        this.riderStorage.put(riderUniqueId, rider);
        System.out.println("******* riderStorage******"+riderStorage);
        return true;
    }

    public Boolean registerCity(City city) {
        Long cityUniqueId = city.getID();
        if(this.cityStorage.get(cityUniqueId) != null){
            throw new RuntimeException("City already exist in the system");
        }

        this.cityStorage.put(cityUniqueId, city);
        System.out.println("******* CityStorage ******" + cityStorage);
        return true;
    }

    @Override
    public Boolean registerDriver(Driver driver) {
        StringBuffer sb = new StringBuffer();
        sb.append(driver.getCountryCode()).append(driver.getPhoneNumber());
        String driverUniqueId = sb.toString();
        if(this.driverStorage.get(driverUniqueId) != null){
            throw new RuntimeException("Driver already exist in the system");
        }
        this.driverStorage.put(driverUniqueId, driver);
        return true;
    }

    @Override
    public Boolean registerVehicle(Vehicle vehicle) {
        if(this.vehicleStorage.get(vehicle.getCarNumber()) != null){
            System.out.println("Vehicle already exist in the system");
            return false;
        }

        this.vehicleStorage.put(vehicle.getCarNumber(), vehicle);
        System.out.println("******* vehicleStorage******"+vehicleStorage);
        return true;
    }

    @Override
    public Boolean updateLocation(Vehicle vehicle) {
        if(this.vehicleStorage.get(vehicle.getCarNumber()) == null){
            System.out.println("Vehicle does not exist in the system");
            return false;
        }

        Vehicle vehicleInDb = this.vehicleStorage.get(vehicle.getCarNumber());
        vehicleInDb.setCityName(vehicle.getCityName());
        this.vehicleStorage.put(vehicle.getCarNumber(), vehicleInDb);
        System.out.println("******* Vehicle location updated ***************");
        return true;
    }

    @Override
    public Boolean book(Booking booking) {
        this.bookingStorage.put(booking.getBookingId(), booking);
        Rider rider = this.riderStorage.get(booking.getRiderUserId());
        this.riderStorage.put(booking.getRiderUserId(), rider);
        booking.setStatus(BOOKING_STARTED);
        System.out.println("******* bookingStorage ******" + bookingStorage);
        return true;
    }


    @Override
    public Vehicle find(String cityName) {
        TreeMap<Long, Vehicle> cityVehicleMap = new TreeMap<>();

        for(String vehicleId : this.vehicleStorage.keySet()){
            Vehicle vehicle = this.vehicleStorage.get(vehicleId);
            String city = vehicle.getCityName();
            if( city.equals(cityName)  && vehicle.isAvailable()) {
                cityVehicleMap.put(vehicle.getLastIdleTime(), vehicle);
            }
        }
        if (cityVehicleMap.size() == 0)
            return null;
        return cityVehicleMap.pollFirstEntry().getValue();
    }

    @Override
    public Boolean endTrip(Long timeStamp, String bookingId) {
        Booking booking = this.bookingStorage.get(bookingId);
        if(booking == null){
            System.out.println("No trip by this Id");
        }
        if(booking.getStatus() == BOOKING_COMPLETED){
            System.out.println("Booking already ended");
        }

        booking.setEndTime(timeStamp);
        booking.setStatus(BOOKING_COMPLETED);
        return true;
    }

    @Override
    public Vehicle getVehicle(String bookingID) {
        Booking booking = this.bookingStorage.get(bookingID);
        return vehicleStorage.get(booking.getCarNumber());
    }
}
