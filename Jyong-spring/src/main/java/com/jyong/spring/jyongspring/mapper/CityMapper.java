package com.jyong.spring.jyongspring.mapper;

import com.jyong.spring.jyongspring.entity.CityModel;
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
