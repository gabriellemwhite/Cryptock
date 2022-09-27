package com.gabriellewhite.cryptock.ui.crypto_index;

import static com.gabriellewhite.cryptock.ui.crypto_index.CryptoAdapter.cryptoPosition;

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
import com.google.firebase.firestore.SetOptions;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CryptoDetailsAdapter extends RecyclerView.Adapter<CryptoDetailsAdapter.MyViewHolder> {

    private final List<CryptoList> cryptoDetailsList;
    private final Context context;

    ShineButton favoritesBtn;

    private final HashMap<String, String>  cryptoFavoritesMap = new HashMap<>();

    //Database
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    //Formatting Utilities
    DecimalFormat currency = new DecimalFormat("$###,###.####");
    DecimalFormat percentFormat = new DecimalFormat("#.##");


    public CryptoDetailsAdapter(List<CryptoList> cryptoDetailsList, Context context) {
        this.cryptoDetailsList = cryptoDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.crypto_details_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, final int position) {
        final MyViewHolder holder = holders;
        CryptoList model = cryptoDetailsList.get(position);


        for (Map.Entry mapElement :  cryptoFavoritesMap.entrySet()) {
            String symbol = (String) mapElement.getKey();
            String cryptoSymbol = (String) mapElement.getValue();
            cryptoFavoritesMap.put(symbol, cryptoSymbol);
        }

        cryptoFavoritesMap.put(model.getSymbol(), model.getCryptoName());

        //Index Holder
        holder.cryptoSymbol.setText(model.getSymbol());
        holder.cryptoName.setText(model.getCryptoName());
        holder.cryptoPrice.setText(currency.format(Double.valueOf(model.getPrice())));
        holder.cryptoMarketCap.setText(currency.format(Double.valueOf(model.getMarketCap())));

       // holder.change.setText(percentFormat.format(Double.valueOf(model.getChange())));

        Glide.with(context)
                .load(cryptoDetailsList.get(position).getIconUrl())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.cryptoIcon);
    }

    @Override
    public int getItemCount() {
        return cryptoDetailsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cryptoName, cryptoSymbol, cryptoPrice, cryptoPriceChange, cryptoMarketCap;
        ImageView cryptoIcon;

        ProgressBar progressBar;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();
        String userId = userEmail;

        public MyViewHolder(View itemView) {
            super(itemView);

            cryptoName = itemView.findViewById(R.id.cryptoName);
            cryptoSymbol = itemView.findViewById(R.id.cryptoSymbol);
            cryptoIcon = itemView.findViewById(R.id.cryptoIcon);
            cryptoPrice = itemView.findViewById(R.id.cryptoPrice);
            cryptoMarketCap = itemView.findViewById(R.id.cryptoMarketCap);


           // change = itemView.findViewById(R.id.change);
            favoritesBtn = itemView.findViewById(R.id.favoritesBtn);
            progressBar = itemView.findViewById(R.id.progressBar);

            favoritesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(userEmail);
                    System.out.println(cryptoPosition);
                    db.collection("users")
                            .document(userId)
                            .collection("favorites")
                            .document("crypto")
                            .set(cryptoFavoritesMap, SetOptions.merge());

                }
            });
        }
    }
}
