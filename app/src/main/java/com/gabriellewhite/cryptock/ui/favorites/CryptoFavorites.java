package com.gabriellewhite.cryptock.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.gabriellewhite.cryptock.R;
import com.gabriellewhite.cryptock.ui.favorites.models.CryptoFavoritesModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CryptoFavorites extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userEmail = user.getEmail();
    String userId = userEmail;

    //Database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cryptoRef = db.collection("users" + userId + "/favorites" + "/crypto");

    private FavoritesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favorites_crypto_fragment, container, false);

        progressBar = v.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView) v.findViewById(R.id.crypto_favorites_feed);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        Query query = cryptoRef;

        FirestoreRecyclerOptions<CryptoFavoritesModel> options = new FirestoreRecyclerOptions.Builder<CryptoFavoritesModel>()
                .setQuery(query, CryptoFavoritesModel.class)
                .build();

        adapter = new FavoritesAdapter(options);
        recyclerView.setAdapter(adapter);


        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}

/**
 * DocumentReference docRef = db.collection("users/" + userId + "/favorites").document("crypto");
 * <p>
 * // Source can be CACHE, SERVER, or DEFAULT.
 * Source source = Source.DEFAULT;
 * <p>
 * // Get the document, forcing the SDK to use the offline cache
 * docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
 *
 * @Override public void onComplete(@NonNull Task<DocumentSnapshot> task) {
 * if (task.isSuccessful()) {
 * // Document found in the offline cache
 * DocumentSnapshot document = task.getResult();
 * Log.d(TAG, "Cached document data: " + document.getData());
 * } else {
 * Log.d(TAG, "Cached get failed: ", task.getException());
 * }
 * }
 * });
 * }
 **/