package com.gabriellewhite.cryptock.ui.stock_index.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockSearch {

    public StockSearch(){
    }

    @SerializedName("1. symbol")
    @Expose
    public String stockSymbol;
    @SerializedName("2. name")
    @Expose
    public String stockName;
    @SerializedName("3. type")
    @Expose
    public String _3Type;
    @SerializedName("4. region")
    @Expose
    public String _4Region;
    @SerializedName("5. marketOpen")
    @Expose
    public String _5MarketOpen;
    @SerializedName("6. marketClose")
    @Expose
    public String _6MarketClose;
    @SerializedName("7. timezone")
    @Expose
    public String _7Timezone;
    @SerializedName("8. currency")
    @Expose
    public String _8Currency;
    @SerializedName("9. matchScore")
    @Expose
    public String _9MatchScore;

    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getStockName() {
        return stockName;
    }

    public String get_3Type() {
        return _3Type;
    }

    public String get_4Region() {
        return _4Region;
    }

    public String get_5MarketOpen() {
        return _5MarketOpen;
    }

    public String get_6MarketClose() {
        return _6MarketClose;
    }

    public String get_7Timezone() {
        return _7Timezone;
    }

    public String get_8Currency() {
        return _8Currency;
    }

    public String get_9MatchScore() {
        return _9MatchScore;
    }
}
