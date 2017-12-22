package com.feliperoriz.watchdog.network;

import android.support.annotation.NonNull;

import com.feliperoriz.watchdog.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by feliperoriz on 12/21/17.
 */

public class CongressApiInterceptor implements Interceptor {

    private final String HEADER_API_KEY = "X-API-Key";
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader(HEADER_API_KEY, BuildConfig.Pro_Publica_Data_Store_Key)
                .build();

        Timber.d("GET: %s", request.url().toString());

        Response response = chain.proceed(request);
        return response;
    }
}
