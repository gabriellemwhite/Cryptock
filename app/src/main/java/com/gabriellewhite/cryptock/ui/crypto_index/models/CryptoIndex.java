package com.gabriellewhite.cryptock.ui.crypto_index.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoIndex {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private CryptoData cryptodata;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CryptoData getCryptodata() {
        return cryptodata;
    }

    public void setCryptodata(CryptoData cryptodata) {
        this.cryptodata = cryptodata;
    }

}
