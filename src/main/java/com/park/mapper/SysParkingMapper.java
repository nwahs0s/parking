package com.park.mapper;

import com.park.domain.SysParking;

public interface SysParkingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysParking record);

    int insertSelective(SysParking record);

    SysParking selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysParking record);

    int updateByPrimaryKey(SysParking record);
}