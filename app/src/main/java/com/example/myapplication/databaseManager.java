package com.example.myapplication;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class databaseManager {
    private Context context;

    public RequestQueue queue;

    public databaseManager (Context context){
        this.context = context;
        this.queue = Volley.newRequestQueue(context);
    }


}
