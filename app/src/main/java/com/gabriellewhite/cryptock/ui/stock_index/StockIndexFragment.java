package com.gabriellewhite.cryptock.ui.stock_index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriellewhite.cryptock.R;
import com.gabriellewhite.cryptock.ui.stock_index.models.StockIndex;
import com.gabriellewhite.cryptock.ui.stock_index.models.StockSearch;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StockIndexFragment extends Fragment {

    private List<StockSearch> stockSearch = new ArrayList<>();
    private StockAdapter stockAdapter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.stock_index_fragment, container, false);

        progressBar = v.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        searchView = (SearchView) v.findViewById(R.id.stockSearchView);

        recyclerView = (RecyclerView) v.findViewById(R.id.stock_index_feed);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        getStockSearchResults();
        return v;
    }

    private void getStockSearchResults() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String stockQuery) {

                StockRequestInterface stockRequestInterface = StockAPIClient.getStockClient().create(StockRequestInterface.class);
                Call<StockIndex> call;
                call = stockRequestInterface.getStockSearch(stockQuery);


                call.enqueue(new Callback<StockIndex>() {
                    @Override
                    public void onResponse(Call<StockIndex> call, Response<StockIndex> response) {

                        if (response.isSuccessful() && response.body().getStockSearch() != null) {
                            progressBar.setVisibility(View.GONE);

                            stockSearch = response.body().getStockSearch();
                            stockAdapter = new StockAdapter(stockSearch, getContext());
                            recyclerView.setAdapter(stockAdapter);
                            stockAdapter.notifyDataSetChanged();

                            System.out.println(response.code());
                            Toasty.success(getContext(), "Search Query Worked!", Toast.LENGTH_SHORT).show();

                            stockAdapter.setOnItemClickListener(new StockAdapter.StockItemListener() {
                                @Override
                                public void onItemClickStock(int pos) {
                                    Toasty.success(getContext(), "OnClick for Stock Worked!", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                                            .navigate(R.id.nav_stock_details);
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<StockIndex> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toasty.error(getContext(), "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();
                        System.out.println(t.getMessage());

                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}