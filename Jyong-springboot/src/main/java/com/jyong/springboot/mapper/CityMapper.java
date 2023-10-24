package com.jyong.springboot.mapper;

import com.jyong.springboot.entity.CityModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CityMapper {

    List<CityModel> searchCitys();

    CityModel queryCityById(CityModel id);

    int addCityMode(CityModel cityModel);

    int updateCityMode(CityModel cityModel);

    int deleteCityMode(CityModel cityModel);
}
