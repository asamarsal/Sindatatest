package com.asamarsal.sindatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws1.e1-vhp.com:8080/VHPMobile3/rest/Report/mobileDev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        // Membuat instance model body dengan nilai yang diinginkan
        RequestBodyModel.RequestData requestData = new RequestBodyModel.RequestData(0, false, false, 0, "03/01/2024");
        RequestBodyModel requestBody = new RequestBodyModel(requestData);

        // Mengirim permintaan
        sendPostRequest(requestBody);
    }

    private void sendPostRequest(RequestBodyModel requestBody) {
        Call<ResponseBody> call = apiService.postData(requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        Log.d("Response", responseBody);
                        // Tangani respons yang berhasil
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Tangani respons yang tidak berhasil
                    Log.d("Response", "Gagal");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Tangani kegagalan koneksi atau permintaan
                Log.d("Response", "Gagal");
            }
        });

        ImageButton buttontekan = findViewById(R.id.btn_back);

        buttontekan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Utama.class);
                finish();
            }
        });
    }
}
