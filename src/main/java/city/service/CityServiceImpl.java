package city.service;

import city.models.City;
import storage.IStorageService;

public class CityServiceImpl implements  ICityService{

    IStorageService storageService;

    private static final double MAX_DISTANCE = 110D;

    public CityServiceImpl(IStorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public Boolean registerCity(City city) {
        this.storageService.registerCity(city);
        return true;
    }
}