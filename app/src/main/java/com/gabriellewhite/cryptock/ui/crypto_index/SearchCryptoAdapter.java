package com.gabriellewhite.cryptock.ui.crypto_index;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.gabriellewhite.cryptock.R;
import com.gabriellewhite.cryptock.ui.crypto_index.models.CryptoList;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCryptoAdapter extends RecyclerView.Adapter<SearchCryptoAdapter.MyViewHolder> {

    private final List<CryptoList> searchList;
    private final Context context;
    static CryptoList searchCryptoPosition;
    CryptoSearchItemListener cryptoSearchItemListener;
    ShineButton favoritesBtn;

    private final HashMap<String, String>  searchFavoritesMap = new HashMap<>();

    public SearchCryptoAdapter(List<CryptoList> searchList, Context context) {
        this.searchList = searchList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.crypto_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        CryptoList model = searchList.get(position);

        searchCryptoPosition = searchList.get(position);

        searchFavoritesMap.put(model.getCryptoName(), model.getSymbol());
        for (Map.Entry mapElement :  searchFavoritesMap.entrySet()) {
            String symbol = (String) mapElement.getKey();
            String name = (String) mapElement.getValue();
            searchFavoritesMap.put(symbol, name);
        }

        //Index Holder
        holder.cryptoSymbol.setText(model.getSymbol());
        holder.cryptoName.setText(model.getCryptoName());

        GlideApp.with(context)
                .load(searchList.get(position).getIconUrl())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.cryptoIcon);

        holder.cryptoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cryptoSearchItemListener.onItemClickCryptoSearch(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public void setOnItemClickListener(CryptoSearchItemListener cryptoSearchItemListener) {
        this.cryptoSearchItemListener = cryptoSearchItemListener;
    }

    public interface CryptoSearchItemListener {
        void onItemClickCryptoSearch(int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cryptoName, cryptoSymbol;
        ImageView cryptoIcon;

        ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);

            cryptoName = itemView.findViewById(R.id.cryptoName);
            cryptoSymbol = itemView.findViewById(R.id.symbol);
            cryptoIcon = itemView.findViewById(R.id.cryptoIcon);
            favoritesBtn = itemView.findViewById(R.id.favoritesBtn);
            progressBar = itemView.findViewById(R.id.progressBar);

        }
    }
}
