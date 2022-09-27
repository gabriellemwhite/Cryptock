package com.gabriellewhite.cryptock.ui.crypto_index;

import com.gabriellewhite.cryptock.ui.crypto_index.models.CryptoIndex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CryptoRequestInterface {

    @GET("coins")
    @Headers("x-rapidapi-key: 00f4033a0c0e2362d315c1f6b6ebd69f3cfc198f4fbb9b7f")
    Call<CryptoIndex> getCryptoIndex();

    @GET("search-suggestions")
    @Headers("x-rapidapi-key: 00f4033a0c0e2362d315c1f6b6ebd69f3cfc198f4fbb9b7f")
    Call<CryptoIndex> getCryptoSearchFunction(
            @Query("query") String query);

    @GET("coins")
    @Headers("x-rapidapi-key: 00f4033a0c0e2362d315c1f6b6ebd69f3cfc198f4fbb9b7f")
    Call<CryptoIndex> getCryptoCoin(
            @Query(value = "uuids[]", encoded = true)
                    String uuidQuery);
  //  @GET("coin")
   // @Headers("x-rapidapi-key: 00f4033a0c0e2362d315c1f6b6ebd69f3cfc198f4fbb9b7f")
  //  Call<CryptoIndex> getCryptoCoin(
  //          @Query("") String uuidQuery);
}
