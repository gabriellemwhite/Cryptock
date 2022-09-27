package com.gabriellewhite.cryptock.ui.stock_index;

import com.gabriellewhite.cryptock.ui.stock_index.models.StockIndex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StockRequestInterface {

    @GET("?function=SYMBOL_SEARCH&keywords=&apikey=7KEZ8Y6X53CZ4NR2")
    Call<StockIndex> getStockSearch(
            @Query("keywords") String stockQuery);

}
