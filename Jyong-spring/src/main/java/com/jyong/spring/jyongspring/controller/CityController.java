package com.jyong.spring.jyongspring.controller;


import com.datawaiter.springboot.mapper.CityMapper;
import com.datawaiter.springboot.entity.CityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CityController {

    @Autowired
    private CityMapper mapper;


    @GetMapping("/searchCitys")
    public List<CityModel> searchCitys(){
        List<CityModel> cityModels = mapper.searchCitys();
        return cityModels.stream().limit(100).collect(Collectors.toList());
    }

}
