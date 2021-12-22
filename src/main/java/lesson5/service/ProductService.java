package lesson5.service;

import retrofit2.Call;
import retrofit2.http.*;
import lesson5.dto.Product;

public interface ProductService {

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Integer id);

    @POST("products")
    Call<Product> createProduct(@Body Product product);

    @PUT("products")
    Call<Product> updateProduct(@Body Product product);

}
