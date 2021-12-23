package city.service;

import storage.IStorageService;
import city.models.City;

public interface ICityService {
    Boolean registerCity(City city);
}
