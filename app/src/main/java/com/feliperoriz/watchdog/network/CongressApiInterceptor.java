package com.feliperoriz.watchdog.network;

import android.support.annotation.NonNull;

import com.feliperoriz.watchdog.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Interceptor to add API Key to request Headers.
 */
public class CongressApiInterceptor implements Interceptor {

    private final String HEADER_API_KEY = "X-API-Key";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader(HEADER_API_KEY, BuildConfig.Pro_Publica_Data_Store_Key)
                .build();

        return chain.proceed(request);
    }
}
