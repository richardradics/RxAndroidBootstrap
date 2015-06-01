package com.richardradics.cleanrx.repository;

import com.richardradics.cleanrx.domain.City;

import java.util.List;

import rx.Observable;

/**
 * Created by radicsrichard on 15. 05. 29..
 */
public interface CleanRepository {

    Observable<List<City>> getCities(Double latitude, Double longitude, Integer count);
}
