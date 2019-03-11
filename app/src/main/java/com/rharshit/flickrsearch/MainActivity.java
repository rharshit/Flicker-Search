package com.rharshit.flickrsearch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rharshit.flickrsearch.flickr.FlickrPhotosAdapter;
import com.rharshit.flickrsearch.flickr.FlickrPhotosParse;
import com.rharshit.flickrsearch.flickr.api.FlickrClient;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    private final String API_BASE_URL = "https://api.flickr.com/";
    private EditText etSeatch;
    private Button bSearch;
    private GridView gvImages;

    private String api_key;

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        etSeatch = findViewById(R.id.et_search);
        bSearch = findViewById(R.id.b_search);
        gvImages = findViewById(R.id.gv_images);

        api_key = getResources().getString(R.string.api_key);

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = etSeatch.getText().toString();

                OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                builder.readTimeout(10, TimeUnit.SECONDS);
                builder.connectTimeout(5, TimeUnit.SECONDS);

                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                builder.addInterceptor(interceptor);

                OkHttpClient okHttpClient = builder.build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                FlickrClient client = retrofit.create(FlickrClient.class);
                Call<String> call = client.photosForTagJson(api_key, tag);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String s = response.body().toString();
                        Log.i(TAG, "onResponse: "+s);
                        String json = s.replace("jsonFlickrApi(","");
                        json = json.substring(0, json.length()-1);
                        Log.i(TAG, "onResponse: "+json);

                        Gson gson = new Gson();
                        FlickrPhotosParse photos = gson.fromJson(json, FlickrPhotosParse.class);
                        photos.debugInfo();

                        gvImages.setAdapter(new FlickrPhotosAdapter(mContext, 0, photos.getPageResult()));
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e(TAG, "onFailure: Error");
                        Log.e(TAG, t.toString());
                        Toast.makeText(mContext, "Error processing the request", Toast.LENGTH_SHORT);
                    }
                });
            }
        });

        ((RadioButton) findViewById(R.id.rb_num_col_2)).setChecked(true);
    }

    public void changeCols(View v){
        switch (v.getId()){
            case R.id.rb_num_col_2:
                gvImages.setNumColumns(2);
                break;
            case R.id.rb_num_col_3:
                gvImages.setNumColumns(3);
                break;
            case R.id.rb_num_col_4:
                gvImages.setNumColumns(4);
                break;
        }
    }
}
