package com.gabriellewhite.cryptock.ui.stock_index;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriellewhite.cryptock.R;
import com.gabriellewhite.cryptock.ui.stock_index.models.StockSearch;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StockAdapter extends RecyclerView.Adapter<StockAdapter.MyViewHolder> {

    private final List<StockSearch> stockSearch;
    private final Context context;

    private final HashMap<String, String>  stockFavoritesMap = new HashMap<>();

    ShineButton favoritesBtn;

    //Database
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    StockItemListener stockItemListener;

    //formatting utilities
    DecimalFormat currency = new DecimalFormat("$###,###.##");
    DecimalFormat percentFormat = new DecimalFormat("#.##");

    public StockAdapter(List<StockSearch> stockSearch, Context context) {
        this.stockSearch = stockSearch;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stock_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, final int position) {
        final MyViewHolder holder = holders;
        StockSearch model = stockSearch.get(position);

        //Index Holder
        holder.stockSymbol.setText(model.getStockSymbol());
        holder.stockName.setText(model.getStockName());

        holder.stockName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockItemListener.onItemClickStock(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stockSearch.size();
    }

    public void setOnItemClickListener(StockItemListener stockItemListener) {
        this.stockItemListener = stockItemListener;
    }

    public interface StockItemListener {
        void onItemClickStock(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView stockSymbol, stockName, stockPrice, stockPriceChange;

        ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);


            stockName = itemView.findViewById(R.id.stockName);
            stockSymbol = itemView.findViewById(R.id.stockSymbol);
            favoritesBtn = itemView.findViewById(R.id.favoritesBtn);

           // stockPrice = itemView.findViewById(R.id.stockPrice);
           // stockPriceChange = itemView.findViewById(R.id.stockPriceChange);

            progressBar = itemView.findViewById(R.id.progressBar);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            String userId = user.getEmail();
            List<HashMap> str = new ArrayList<HashMap>();
            str.add(stockFavoritesMap);

            for (Map.Entry mapElement :  stockFavoritesMap.entrySet()) {

                String symbol = (String) mapElement.getKey();
                String name = (String) mapElement.getValue();
                stockFavoritesMap.put(symbol, name);
            }
        }

    }

}