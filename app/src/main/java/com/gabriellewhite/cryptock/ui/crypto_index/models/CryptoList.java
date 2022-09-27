package com.gabriellewhite.cryptock.ui.crypto_index.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.validation.Valid;

public class CryptoList {

    @SerializedName("uuid")
    @Expose
    public String uuid;
    @SerializedName("symbol")
    @Expose
    public String symbol;
    @SerializedName("name")
    @Expose
    public String cryptoName;
    @SerializedName("color")
    @Expose
    public String color;
    @SerializedName("iconUrl")
    @Expose
    public String iconUrl;
    @SerializedName("marketCap")
    @Expose
    public String marketCap;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("listedAt")
    @Expose
    public Integer listedAt;
    @SerializedName("tier")
    @Expose
    public Integer tier;
    @SerializedName("change")
    @Expose
    public String change;
    @SerializedName("rank")
    @Expose
    public Integer rank;

    @SerializedName("sparkline")
    @Expose
    @Valid
    private List<String> cryptoSparkline;

    @SerializedName("lowVolume")
    @Expose
    public Boolean lowVolume;
    @SerializedName("coinrankingUrl")
    @Expose
    public String coinrankingUrl;
    @SerializedName("24hVolume")
    @Expose
    public String _24hVolume;
    @SerializedName("btcPrice")
    @Expose
    public String btcPrice;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setcryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getListedAt() {
        return listedAt;
    }

    public void setListedAt(Integer listedAt) {
        this.listedAt = listedAt;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<String> getCryptoSparkline() {
        return cryptoSparkline;
    }

    public void setCryptoSparkline(List<String> cryptoSparkline) {
        this.cryptoSparkline = cryptoSparkline;
    }

    public Boolean getLowVolume() {
        return lowVolume;
    }

    public void setLowVolume(Boolean lowVolume) {
        this.lowVolume = lowVolume;
    }

    public String getCoinrankingUrl() {
        return coinrankingUrl;
    }

    public void setCoinrankingUrl(String coinrankingUrl) {
        this.coinrankingUrl = coinrankingUrl;
    }

    public String get_24hVolume() {
        return _24hVolume;
    }

    public void set_24hVolume(String _24hVolume) {
        this._24hVolume = _24hVolume;
    }

    public String getBtcPrice() {
        return btcPrice;
    }

    public void setBtcPrice(String btcPrice) {
        this.btcPrice = btcPrice;
    }
}


