package com.park.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.park.mapper.SysParkingMapper;

public class SysParkingServiceImpl implements SysParkingService {
	
	@Autowired
	SysParkingMapper sysParkingMapper;
	
	
	
}
