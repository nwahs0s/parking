package com.park.mapper;

import java.util.List;

import com.park.domain.CarParkingRecord;

public interface CarParkingRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarParkingRecord record);

    int insertSelective(CarParkingRecord record);

    CarParkingRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarParkingRecord record);

    int updateByPrimaryKey(CarParkingRecord record);
    
    List<CarParkingRecord> getParingRecord(Integer status);
}