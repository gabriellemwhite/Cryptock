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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import com.gabriellewhite.cryptock.R;
import com.gabriellewhite.cryptock.ui.crypto_index.models.CryptoList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.MyViewHolder> {

    private final List<CryptoList> cryptoList;
    private final Context context;
    static Integer cryptoPosition;
    CryptoItemListener cryptoItemListener;
    ShineButton favoritesBtn;

    private final HashMap<String, String>  cryptoFavoritesMap = new HashMap<>();

    //Database
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    //Formatting Utilities
    DecimalFormat currency = new DecimalFormat("$###,###.####");
    DecimalFormat percentFormat = new DecimalFormat("#.##");

    public CryptoAdapter(List<CryptoList> cryptoList, Context context) {
        this.cryptoList = cryptoList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.crypto_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, final int position) {
        final MyViewHolder holder = holders;
        CryptoList model = cryptoList.get(position);

        holder.setIsRecyclable(false);
        cryptoPosition = holder.getLayoutPosition();

        cryptoFavoritesMap.put(model.getCryptoName(), model.getSymbol());
        for (Map.Entry mapElement :  cryptoFavoritesMap.entrySet()) {
            String symbol = (String) mapElement.getKey();
            String name = (String) mapElement.getValue();
            cryptoFavoritesMap.put(symbol, name);
        }

        //Index Holder
        holder.cryptoSymbol.setText(model.getSymbol());
        holder.cryptoName.setText(model.getCryptoName());
        holder.price.setText(currency.format(Double.valueOf(model.getPrice())));
        holder.change.setText(percentFormat.format(Double.valueOf(model.getChange())) + "%");

        Glide.with(context)
                .load(cryptoList.get(position).getIconUrl())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.cryptoIcon);

        holder.cryptoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cryptoItemListener.onItemClickCrypto(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public void setOnItemClickListener(CryptoItemListener cryptoItemListener){
        this.cryptoItemListener = cryptoItemListener;
    }

    public interface CryptoItemListener {
        void onItemClickCrypto(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cryptoName, cryptoSymbol, price, change;
        ImageView cryptoIcon;

        ProgressBar progressBar;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();
        String userId = userEmail;


        public MyViewHolder(View itemView) {
            super(itemView);

            cryptoName = itemView.findViewById(R.id.cryptoName);
            cryptoSymbol = itemView.findViewById(R.id.symbol);
            cryptoIcon = itemView.findViewById(R.id.cryptoIcon);
            price = itemView.findViewById(R.id.price);
            change = itemView.findViewById(R.id.change);
            favoritesBtn = itemView.findViewById(R.id.favoritesBtn);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
