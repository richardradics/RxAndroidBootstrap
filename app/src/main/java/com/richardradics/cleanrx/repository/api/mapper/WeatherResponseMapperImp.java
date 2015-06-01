package com.richardradics.cleanrx.repository.api.mapper;


import com.richardradics.cleanrx.domain.City;
import com.richardradics.cleanrx.repository.api.model.openweatherwrapper.OpenWeatherWrapper;
import com.richardradics.cleanrx.repository.api.model.openweatherwrapper.WeatherItem;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by radicsrichard on 15. 05. 14..
 */

public class WeatherResponseMapperImp implements WeatherResponseMapper<OpenWeatherWrapper> {

    @Inject
    public WeatherResponseMapperImp(){}

    @Override
    public List<City> mapResponse(OpenWeatherWrapper response) {
        List<City> cityList = new ArrayList<>();

        for (WeatherItem weatherItem : response.getList()) {
            try {
                City w = new City();
                w.setId(weatherItem.getId());
                w.setName(weatherItem.getName());
                w.setCountry(weatherItem.getSys().getCountry());

                cityList.add(w);
            } catch (Exception e) {

            }
        }

        return cityList;
    }

}
