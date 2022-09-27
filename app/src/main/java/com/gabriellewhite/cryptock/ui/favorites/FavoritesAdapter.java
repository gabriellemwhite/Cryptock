package com.gabriellewhite.cryptock.ui.favorites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.gabriellewhite.cryptock.R;
import com.gabriellewhite.cryptock.ui.favorites.models.CryptoFavoritesModel;

import org.jetbrains.annotations.NotNull;

public class FavoritesAdapter extends FirestoreRecyclerAdapter<CryptoFavoritesModel, FavoritesAdapter.FavoritesHolder> {

    public FavoritesAdapter(@NonNull @NotNull FirestoreRecyclerOptions<CryptoFavoritesModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull FavoritesHolder holder, int position, @NonNull @NotNull CryptoFavoritesModel model) {
        holder.textViewName.setText(model.getSymbol());
        System.out.println(model.getSymbol());
    }

    @NonNull
    @NotNull
    @Override
    public FavoritesHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_crypto_layout, parent, false);
        return new FavoritesHolder(v);
    }

    class FavoritesHolder extends RecyclerView.ViewHolder{

    TextView textViewName;

    public FavoritesHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.cryptoName);
        System.out.println(itemView);
    }
}

}
