package com.gabriellewhite.cryptock.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gabriellewhite.cryptock.R;

public class StockFavorites extends Fragment {

    //private RecyclerView recyclerView;
   // private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favorites_stock_fragment, container, false);

       // progressBar = v.findViewById(R.id.progress_bar);
       // progressBar.setVisibility(View.VISIBLE);
        //recyclerView = (RecyclerView) v.findViewById(R.id.stock_favorites_feed);
       // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //recyclerView.setLayoutManager(layoutManager);

        //getStockFavorites();
        return v;

    }

    private void getStockFavorites(){

    }
}