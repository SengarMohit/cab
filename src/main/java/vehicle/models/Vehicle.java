package vehicle.models;


public class Vehicle {
    public static final String IDLE = "IDLE";
    public static final String ON_TRIP = "ONTRIP";

    private String carNumber;
    private String cityName;
    private String type;
    private String driverId;
    private String status = IDLE;

    public Long getLastIdleTime() {
        return lastIdleTime;
    }

    public void setLastIdleTime(Long lastIdleTime) {
        this.lastIdleTime = lastIdleTime;
    }

    private Long lastIdleTime = System.currentTimeMillis();

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAvailable()
    {
        return IDLE.equals(this.status);
    }
}
