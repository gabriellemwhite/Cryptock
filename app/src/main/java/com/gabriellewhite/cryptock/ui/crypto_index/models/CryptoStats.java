package com.gabriellewhite.cryptock.ui.crypto_index.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoStats {

    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("totalMarkets")
    @Expose
    public Integer totalMarkets;
    @SerializedName("totalExchanges")
    @Expose
    public Integer totalExchanges;
    @SerializedName("totalMarketCap")
    @Expose
    public String totalMarketCap;
    @SerializedName("total24hVolume")
    @Expose
    public String total24hVolume;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalMarkets() {
        return totalMarkets;
    }

    public void setTotalMarkets(Integer totalMarkets) {
        this.totalMarkets = totalMarkets;
    }

    public Integer getTotalExchanges() {
        return totalExchanges;
    }

    public void setTotalExchanges(Integer totalExchanges) {
        this.totalExchanges = totalExchanges;
    }

    public String getTotalMarketCap() {
        return totalMarketCap;
    }

    public void setTotalMarketCap(String totalMarketCap) {
        this.totalMarketCap = totalMarketCap;
    }

    public String getTotal24hVolume() {
        return total24hVolume;
    }

    public void setTotal24hVolume(String total24hVolume) {
        this.total24hVolume = total24hVolume;
    }

}
