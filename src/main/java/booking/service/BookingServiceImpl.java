package booking.service;

import booking.models.Booking;
import storage.IStorageService;
import vehicle.models.Vehicle;
import vehicle.services.IVehicleService;

import java.util.UUID;

public class BookingServiceImpl implements IBookingService {
    IVehicleService vehicleService;
    IStorageService storageService;

    public BookingServiceImpl(IVehicleService vehicleService, IStorageService storageService) {
        this.vehicleService = vehicleService;
        this.storageService = storageService;
    }

    @Override
    public Booking book(String riderUserId, String cityName) {
        //find cab
        Vehicle vehicle = vehicleService.find(cityName);

        if (vehicle != null) {
            //TODO lock the cab
            Booking booking = new Booking();
            booking.setBookingId(UUID.randomUUID().toString());
            booking.setCarNumber(vehicle.getCarNumber());
            booking.setRiderUserId(riderUserId);
            storageService.book(booking);

            vehicleService.SetVehicleOnTrip(vehicle);
            System.out.println("Cab Booked for city " + cityName + " =>" + vehicle.getCarNumber());
            return booking;
        }
        else {
            System.out.println("^^^^^^^^^ No Cab found ^^^^^^^^^^^^^ ");
            return null;
        }
    }

    @Override
    public Boolean endTrip(Long timeStamp, String bookingId) {
        storageService.endTrip(timeStamp, bookingId);

        Vehicle vehicle = vehicleService.getVehicleByName(bookingId);
        vehicleService.SetVehicleIDLE(vehicle);
        vehicle.setLastIdleTime(System.currentTimeMillis());

        System.out.println("Trip Ended for " + vehicle.getCarNumber());
        return true;
    }
}
