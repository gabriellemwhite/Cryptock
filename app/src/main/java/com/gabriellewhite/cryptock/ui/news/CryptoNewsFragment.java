package com.gabriellewhite.cryptock.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriellewhite.cryptock.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CryptoNewsFragment extends Fragment {

    private NewsAdapter newsAdapter;
    private ArrayList<NewsModel> articles = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    public static String cryptoNewsUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.crypto_news_layout, container, false);

        progressBar = v.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) v.findViewById(R.id.crypto_news_feed);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        getCryptoNews();
        return v;
    }

    private void getCryptoNews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://finnhub.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsRequestInterface request = retrofit.create(NewsRequestInterface.class);
        Call<List<NewsModel>> call1 = request.getCrypto();
        call1.enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    articles = new ArrayList<>(response.body());
                    newsAdapter = new NewsAdapter(articles, getContext());
                    recyclerView.setAdapter(newsAdapter);

                    newsAdapter.setOnItemClickListener(new NewsAdapter.ItemListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position) {
                            newsAdapter.notifyDataSetChanged();

                            cryptoNewsUrl = articles.get(position).getNewsUrl();
                            System.out.println("NEWS IN FRAGMENT" + cryptoNewsUrl);

                            Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                                    .navigate(R.id.nav_crypto_news_webview);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}