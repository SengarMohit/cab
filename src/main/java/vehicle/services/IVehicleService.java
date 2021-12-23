package vehicle.services;

import vehicle.models.Vehicle;

public interface IVehicleService {
    Boolean registerVehicle(Vehicle vehicle);
    Boolean updateLocation(Vehicle vehicle);
    Vehicle find(String cityName);
    void SetVehicleIDLE(Vehicle vehicle);
    void SetVehicleOnTrip(Vehicle vehicle);
    Vehicle getVehicleByName(String carNumber);
}
