package vehicle.services;

import storage.IStorageService;
import vehicle.models.Vehicle;

public class VehicleServiceImpl implements IVehicleService {
    IStorageService storageService;

    private static final double MAX_DISTANCE = 110D;

    public VehicleServiceImpl(IStorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public Boolean registerVehicle(Vehicle vehicle) {
        this.storageService.registerVehicle(vehicle);
        return true;
    }

    @Override
    public Boolean updateLocation(Vehicle vehicle) {
        this.storageService.updateLocation(vehicle);
        return true;
    }

    @Override
    public Vehicle find(String cityName) {
        Vehicle vehicle = this.storageService.find(cityName);
        return vehicle;
    }

    @Override
    public void SetVehicleOnTrip(Vehicle vehicle)
    {
        if (vehicle != null)
            vehicle.setStatus(Vehicle.ON_TRIP);
    }

    @Override
    public Vehicle getVehicleByName(String carNumber) {
        return this.storageService.getVehicle(carNumber);
    }

    @Override
    public void SetVehicleIDLE(Vehicle vehicle)
    {
        if (vehicle != null)
            vehicle.setStatus(Vehicle.IDLE);
    }
 }
