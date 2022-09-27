package com.gabriellewhite.cryptock.ui.news;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsRequestInterface {

    @GET("api/v1/news?category=general&token=c1gf25748v6p69n8usg0")
    Call<List<NewsModel>> getGeneral();

    @GET("api/v1/news?category=forex&token=c1gf25748v6p69n8usg0")
    Call<List<NewsModel>> getForex();

    @GET("api/v1/news?category=crypto&token=c1gf25748v6p69n8usg0")
    Call<List<NewsModel>> getCrypto();

    @GET("api/v1/news?category=merger&token=c1gf25748v6p69n8usg0")
    Call<List<NewsModel>> getMerger();
}
