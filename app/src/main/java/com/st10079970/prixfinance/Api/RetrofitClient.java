package com.st10079970.prixfinance.Api;

import com.st10079970.prixfinance.Api.Services.BudgetsApiService;
import com.st10079970.prixfinance.Api.Services.GamificationsApiService;
import com.st10079970.prixfinance.Api.Services.GoalsApiService;
import com.st10079970.prixfinance.Api.Services.NotificationsApiService;
import com.st10079970.prixfinance.Api.Services.SecuritySettingsApiService;
import com.st10079970.prixfinance.Api.Services.SyncDataApiService;
import com.st10079970.prixfinance.Api.Services.TransactionsApiService;
import com.st10079970.prixfinance.Api.Services.UsersApiService;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;  // Or GsonConverterFactory
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class RetrofitClient {

    private static final String BASE_URL = "https://prixfinanceapi.azure-api.net";


    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);


    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build();


    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create()) // Or GsonConverterFactory
            .build();


   public static final BudgetsApiService apiService = retrofit.create(BudgetsApiService.class);
   public static final GamificationsApiService gamificationsApiService = retrofit.create(GamificationsApiService.class);
   public static final GoalsApiService goalsApiService = retrofit.create(GoalsApiService.class);
   public static final TransactionsApiService transactionsApiService = retrofit.create(TransactionsApiService.class);
   public static final UsersApiService usersApiService = retrofit.create(UsersApiService.class);
   public static final NotificationsApiService notificationsApiService = retrofit.create(NotificationsApiService.class);
   public static final SyncDataApiService syncDataApiService = retrofit.create(SyncDataApiService.class);
   public static final SecuritySettingsApiService securitySettingsApiService = retrofit.create(SecuritySettingsApiService.class);

}