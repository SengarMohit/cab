package booking.service;

import booking.models.Booking;

import java.util.List;

public interface IBookingService {
    Boolean endTrip(Long timeStamp, String bookingId);
    Booking book(String riderUserId, String cityName);
}
