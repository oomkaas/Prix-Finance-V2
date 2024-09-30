package com.st10079970.prixfinance.Api;

import com.squareup.moshi.Moshi;
import com.st10079970.prixfinance.Api.Services.BudgetsApiService;
import com.st10079970.prixfinance.Api.Services.GamificationsApiService;
import com.st10079970.prixfinance.Api.Services.GoalsApiService;
import com.st10079970.prixfinance.Api.Services.NotificationsApiService;
import com.st10079970.prixfinance.Api.Services.SecuritySettingsApiService;
import com.st10079970.prixfinance.Api.Services.SyncDataApiService;
import com.st10079970.prixfinance.Api.Services.TransactionsApiService;
import com.st10079970.prixfinance.Api.Services.UsersApiService;

import java.util.UUID;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

// Custom UUID Adapter
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class RetrofitClient {

    private static final String BASE_URL = "https://prixfinanceapi.azure-api.net";

    // Define the UUID Adapter for Moshi
    public static class UUIDAdapter {
        @ToJson
        public String toJson(UUID uuid) {
            return uuid.toString();
        }

        @FromJson
        public UUID fromJson(String json) {
            return UUID.fromString(json);
        }
    }

    // Define the Date Adapter for Moshi
    public static class DateAdapter {
        private static final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", java.util.Locale.US);

        @ToJson
        public String toJson(Date date) {
            return dateFormat.format(date);
        }

        @FromJson
        public Date fromJson(String json) {
            try {
                return dateFormat.parse(json);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    // Logging Interceptor
    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    // OkHttpClient
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build();

    // Create Moshi instance and register the UUID and Date adapters
    private static final Moshi moshi = new Moshi.Builder()
            .add(new UUIDAdapter())  // Register UUID Adapter
            .add(new DateAdapter())  // Register Date Adapter
            .build();

    // Retrofit Instance
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))  // Use Moshi with UUID and Date Adapters
            .build();

    // API Services
    public static final BudgetsApiService apiService = retrofit.create(BudgetsApiService.class);
    public static final GamificationsApiService gamificationsApiService = retrofit.create(GamificationsApiService.class);
    public static final GoalsApiService goalsApiService = retrofit.create(GoalsApiService.class);
    public static final TransactionsApiService transactionsApiService = retrofit.create(TransactionsApiService.class);
    public static final UsersApiService usersApiService = retrofit.create(UsersApiService.class);
    public static final NotificationsApiService notificationsApiService = retrofit.create(NotificationsApiService.class);
    public static final SyncDataApiService syncDataApiService = retrofit.create(SyncDataApiService.class);
    public static final SecuritySettingsApiService securitySettingsApiService = retrofit.create(SecuritySettingsApiService.class);
}
