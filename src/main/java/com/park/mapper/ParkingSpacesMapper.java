package com.park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.park.domain.ParkingSpaces;

public interface ParkingSpacesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkingSpaces record);

    int insertSelective(ParkingSpaces record);

    ParkingSpaces selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkingSpaces record);

    int updateByPrimaryKey(ParkingSpaces record);
    
    List<ParkingSpaces> query(@Param("type") String type, @Param("status")Integer status);
}