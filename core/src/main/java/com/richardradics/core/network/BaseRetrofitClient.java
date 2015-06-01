package com.richardradics.core.network;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.net.CookieHandler;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by radicsrichard on 15. 04. 28..
 */

public class BaseRetrofitClient {


    protected static OkHttpClient okHttpClient;

    protected ConnectivityAwareUrlClient connectivityAwareUrlClient;


    public <T> T initRestAdapter(Context context, String endPoint, Class<T> restInterface) {
        okHttpClient = new OkHttpClient();
        okHttpClient.setCookieHandler(CookieHandler.getDefault());
        okHttpClient.setConnectTimeout(10, TimeUnit.MINUTES);
        connectivityAwareUrlClient = new ConnectivityAwareUrlClient(context, new OkClient(okHttpClient));

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setClient(connectivityAwareUrlClient)
                        //  .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return restAdapter.create(restInterface);
    }


}
