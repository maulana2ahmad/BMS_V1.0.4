package com.example.bms.model;

import com.google.gson.annotations.SerializedName;

public class AccessTokenLdap {

    @SerializedName("token")
    private String token;

    public AccessTokenLdap(String token) {
        this.token = token;
    }
}
