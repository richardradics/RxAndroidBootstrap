package com.richardradics.cleanrx.repository.api.mapper;



import com.richardradics.cleanrx.domain.City;

import java.util.List;

/**
 * Created by radicsrichard on 15. 05. 14..
 */
public interface WeatherResponseMapper<T> {
    List<City> mapResponse(T response);
}
