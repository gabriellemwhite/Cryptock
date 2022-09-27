package com.gabriellewhite.cryptock.ui.crypto_index.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CryptoData {

    @SerializedName("stats")
    @Expose
    private CryptoStats cryptoStats;

    @SerializedName("coins")
    @Expose
    private List<CryptoList> cryptoList;


    public CryptoStats getCryptoStats() {
        return cryptoStats;
    }

    public void setCryptoStats(CryptoStats cryptoStats) {
        this.cryptoStats = cryptoStats;
    }

    public List<CryptoList> getCryptoList() {
        return cryptoList;
    }

    public void setCryptoList(List<CryptoList> cryptoList) {
        this.cryptoList = cryptoList;
    }


}
