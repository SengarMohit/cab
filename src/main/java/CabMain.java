import booking.models.Booking;
import booking.service.BookingServiceImpl;
import booking.service.IBookingService;
import city.service.CityServiceImpl;
import city.service.ICityService;
import city.models.City;
import driver.models.Driver;
import driver.services.DriverServiceImpl;
import driver.services.IDriverService;
import rider.models.Rider;
import rider.services.IRiderService;
import rider.services.RiderServiceImpl;
import storage.IStorageService;
import storage.StorageServiceImpl;
import vehicle.models.Vehicle;
import vehicle.services.IVehicleService;
import vehicle.services.VehicleServiceImpl;


import java.util.List;

public class CabMain {
    private static IStorageService storageService = new StorageServiceImpl();
    private static IRiderService riderService = new RiderServiceImpl(storageService);
    private static IDriverService driverService = new DriverServiceImpl(storageService);
    private static IVehicleService vehicleService = new VehicleServiceImpl(storageService);
    private static ICityService cityService = new CityServiceImpl(storageService);
    private static IBookingService bookingService = new BookingServiceImpl(vehicleService, storageService);

    public static void main(String args[]){
        checkFirstFreeCarGettingFirstRide();

        //RideNotAvailableInNotRegisteredCity();

    }

    private static void RideNotAvailableInNotRegisteredCity()
    {
        registerCity(1, "Delhi");
        addRider("test1", "+91", "123");
        addDriver("harsh Driver", "+91", "9431");


        Booking booking1 = bookingService.book("+91123", "Noida");
        if (booking1 != null)
        bookingService.endTrip(System.currentTimeMillis(), booking1.getBookingId());
    }

    private static void checkFirstFreeCarGettingFirstRide()
    {
        registerCity(1, "Delhi");
        registerCity(2, "Noida");

        addRider("test1", "+91", "123");
        addRider("test2", "+91", "1234");
        addRider("test3", "+91", "1235678");

        addDriver("harsh Driver", "+91", "9431");

        addVehicle("KA01HK", "Noida");
        addVehicle("KA02HK", "Noida");

        Booking booking1 = bookingService.book("+91123", "Noida");
        bookingService.endTrip(System.currentTimeMillis(), booking1.getBookingId());
        Booking booking2 = bookingService.book("+911234", "Noida");
        bookingService.endTrip(System.currentTimeMillis(), booking2.getBookingId());

        Booking booking3 = bookingService.book("+911234", "Noida");
    }

    private static void addDriver(String name, String countyCode, String phonenumber) {
        Driver driver = new Driver();
        driver.setName(name);
        driver.setCountryCode(countyCode);
        driver.setPhoneNumber(phonenumber);
        driverService.register(driver);
    }

    private static void registerCity(long id, String cityName) {
        City city = new City();
        city.setID(id);
        city.setName(cityName);
        cityService.registerCity(city);
    }

    private static void addRider(String name, String code, String phonenumber) {
        Rider rider = new Rider();
        rider.setName(name);
        rider.setCountryCode(code);
        rider.setPhoneNumber(phonenumber);
        riderService.register(rider);
    }

    private static void addVehicle(String carNumber, String cityName) {
        Vehicle vehicle = new Vehicle();
        vehicle.setCarNumber(carNumber);
        vehicle.setCityName(cityName);
        vehicleService.registerVehicle(vehicle);
    }
}
