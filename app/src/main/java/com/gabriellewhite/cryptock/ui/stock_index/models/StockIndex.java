package com.gabriellewhite.cryptock.ui.stock_index.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StockIndex {

    public StockIndex() {
    }

    @SerializedName("bestMatches")
    @Expose
    public List<StockSearch> stockSearch;

    public List<StockSearch> getStockSearch() {
        return stockSearch;
    }

}
