package com.example.bms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bms.services.ApiRetrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_singin2;
    private EditText edt_Username, edt_Password;

    //token
    private String token;

    //private static final String EMAIL_ADDRESS = "@mncgroup.com"
    //private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_Username = (EditText) findViewById(R.id.edt_username);
        edt_Password = (EditText) findViewById(R.id.edt_password);

        btn_singin2 = (Button) findViewById(R.id.btn_singin2);

        findViewById(R.id.btn_singin2).setOnClickListener(LoginActivity.this);


    }

    private void userLogin() {
        String username = edt_Username.getText().toString().trim();
        String password = edt_Password.getText().toString().trim();

        if (username.isEmpty()) {
            edt_Username.setError("Username is required");
            edt_Username.requestFocus();
            return;
        }

        /*
        //email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches)
        {
            edt_Username.setError("Enter a valid email");
            edt_Username.requestFocus();
        }


        //username
        if (!Pattern.compile(USERNAME_PATTERN).matcher(username).matches()) {
            edt_Username.setError("Enter a valid username");
            edt_Username.requestFocus();
        }
        */

        //password
        if (password.isEmpty()) {
            edt_Password.setError("Password is required");
            edt_Password.requestFocus();
        }

        /*
        //validasi
        if (password.length() > 6){
            edt_Password.setError("Password should be atleast 6 character long");
            edt_Password.requestFocus();
        }
        */

        //call class interface and class Apiretrofit
        Call<ResponseBody> call = ApiRetrofit
                .getInstance()
                .getApiClien()
                .UserLdapClient(username, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String s = response.body().string();



                    Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_singin2:
                userLogin();
                break;
//            case R.id.btn_singUp:
//                userSignUp();
//                break;
        }

    }
}
