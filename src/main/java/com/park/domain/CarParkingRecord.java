package com.park.domain;

import java.util.Date;

public class CarParkingRecord {
    private Integer id;

    private String carId;

    private String type;

    private Integer parkingSpacesId;

    private Date startTime;

    private Date endTime;

    private Integer charge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId == null ? null : carId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getParkingSpacesId() {
        return parkingSpacesId;
    }

    public void setParkingSpacesId(Integer parkingSpacesId) {
        this.parkingSpacesId = parkingSpacesId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }
}