package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class createAccountActivity extends AppCompatActivity {

    private TextView alreadyHasAccountBtn;
    private TextView errorCreateAccountTextView;
    private Button createAccountBtn;

    private EditText usernameEditText;
    private EditText passwordEditText;

    private String username;
    private String password;

    private databaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        alreadyHasAccountBtn = findViewById(R.id.alreadyHasAccountBtn);
        createAccountBtn = findViewById(R.id.createAccountBtn);
        usernameEditText = findViewById(R.id.createUsernameEditText);
        passwordEditText = findViewById(R.id.createPasswordEditText);
        errorCreateAccountTextView = findViewById(R.id.errorCreateAccountTextView);

        databaseManager = new databaseManager(getApplicationContext());

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                //lancer la requete bdd
                createAccount();
            }
        });

        alreadyHasAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connectToAccountActivity = new Intent(getApplicationContext(), loginAccountActivity.class);
                startActivity(connectToAccountActivity);
            }
        });
    }

    public void onApiResponse(JSONObject response){
        Boolean success = null;
        String error = "";

        try{
            success = response.getBoolean("success");

            if(success == true){
                Intent interfaceActivity = new Intent(getApplicationContext(), interfaceActivity.class);
                interfaceActivity.putExtra("username", username);
                startActivity(interfaceActivity);
                finish();
            } else{
                error = response.getString("error");
                errorCreateAccountTextView.setVisibility(View.VISIBLE);
                errorCreateAccountTextView.setText(error);
            }
        } catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void createAccount(){
        String url = "http://10.0.2.2/APISPOT/actions/registrationUser/createAccount.php";

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onApiResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

        databaseManager.queue.add(jsonObjectRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "ONSTART", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "ONSTOP", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "ONDESTROY", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "ONPAUSE", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "ONRESUME", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "ONRESTART", Toast.LENGTH_SHORT).show();
    }
}