package com.example.booksinfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPointInterface {
    @GET("books/v1/volumes?q=twostates")
    Call<String> getData();
}
