package com.asamarsal.sindatatest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers({
            "inputUserkey: 0FA7FCE7E597EFE14695CCE9A80CE366CAD42EFE",
            "inputUsername: sindata"
    })
    @POST("/")
    Call<ResponseBody> postData(@Body RequestBodyModel body);
}
