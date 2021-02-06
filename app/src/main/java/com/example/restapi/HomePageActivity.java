package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePageActivity extends AppCompatActivity {

    private static final String ACTIVITY_LABEL ="HOME_ACTIVITY_COM_PNEUP" ;
    private TextView textViewResult;
    private TextView textStringResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        textViewResult = findViewById(R.id.tvWelcome);

        String value = getIntent().getStringExtra(ACTIVITY_LABEL);
        textStringResult = findViewById(R.id.tvWelcome1);
        textStringResult.setText(value);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("code: "+ response.code());
                    return;
                }
                List<Post> posts = response.body();

                for(Post post: posts ){
                    if(post.getUserId() == 1) {
                        String content = "";
                        content += "ID: " + post.getId() + "\n";
                        content += "User ID: " + post.getUserId() + "\n";
                        content += "Title: " + post.getTitle() + "\n";
                        content += "Text: " + post.getText() + "\n\n";

                        textViewResult.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public static Intent getIntent(Context context, String value ){
        Intent intent = new Intent(context, HomePageActivity.class);
        intent.putExtra(HomePageActivity.ACTIVITY_LABEL, value);
        return intent;
    }
}