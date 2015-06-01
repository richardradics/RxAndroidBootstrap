package com.richardradics.cleanrx.repository;

import android.content.Context;

import com.richardradics.cleanrx.domain.City;
import com.richardradics.cleanrx.repository.api.mapper.WeatherResponseMapperImp;
import com.richardradics.cleanrx.repository.api.model.openweatherwrapper.OpenWeatherWrapper;
import com.richardradics.core.network.BaseRetrofitClient;

import java.util.List;

import javax.inject.Inject;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by radicsrichard on 15. 05. 31..
 */
public class CleanWeatherService extends BaseRetrofitClient implements CleanRepository {
    private static final String TAG = "WeatherClient";
    public static final String ENDPOINT = "http://api.openweathermap.org";

    OpenWeatherAPI openWeatherAPI;
    WeatherResponseMapperImp weatherResponseMapperImp;

    public interface OpenWeatherAPI {
        @GET("/data/2.5/find")
        Observable<OpenWeatherWrapper> getWeatherItems(@Query("lat") Double latitude, @Query("lon") Double longitude, @Query("cnt") Integer count);
    }

    @Inject
    public CleanWeatherService(Context context, WeatherResponseMapperImp weatherResponseMapperImp) {
        this.weatherResponseMapperImp = weatherResponseMapperImp;
        openWeatherAPI = initRestAdapter(context, ENDPOINT, OpenWeatherAPI.class);
    }


    @Override
    public Observable<List<City>> getCities(Double latitude, Double longitude, Integer count) {
        return openWeatherAPI.getWeatherItems(latitude, longitude, count)
                .map(c -> weatherResponseMapperImp.mapResponse(c));
    }
}
