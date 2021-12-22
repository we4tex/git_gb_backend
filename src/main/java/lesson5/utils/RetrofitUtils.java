package lesson5.utils;

import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import java.time.Duration;

@UtilityClass
public class RetrofitUtils {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new PrettyLogger());
    public Retrofit getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofMinutes(1L))
                .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl("http://localhost:8189/market/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}
