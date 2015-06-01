package com.richardradics.core.network;

import android.content.Context;

import com.richardradics.commons.util.ConnectivityUtil;

import java.io.IOException;

import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;

/**
 * Created by mark on 2015. 03. 30..
 */

public class ConnectivityAwareUrlClient implements Client {

    private static final String TAG = "BaseRetrofitClient";


    private Context context;


    private Client wrappedClient;

    public ConnectivityAwareUrlClient(Context context, Client client) {
        this.context = context;
        this.wrappedClient = client;
    }

    @Override
    public Response execute(Request request) throws IOException {
        if (!ConnectivityUtil.isConnected(context)) {
            throw RetrofitError.unexpectedError("No internet", new NoConnectivityException("No Internet"));
        } else {
            return wrappedClient.execute(request);
        }
    }


}
