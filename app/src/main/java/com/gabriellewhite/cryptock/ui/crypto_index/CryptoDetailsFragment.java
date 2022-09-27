package com.gabriellewhite.cryptock.ui.crypto_index;

import static com.gabriellewhite.cryptock.ui.crypto_index.CryptoIndexFragment.coinObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriellewhite.cryptock.R;
import com.gabriellewhite.cryptock.ui.crypto_index.models.CryptoIndex;
import com.gabriellewhite.cryptock.ui.crypto_index.models.CryptoList;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CryptoDetailsFragment extends Fragment {

    private RecyclerView recyclerView;
    public CryptoDetailsAdapter cryptoDetailsAdapter;
    public List<CryptoList> cryptoDetailsList = new ArrayList<>();
    public ProgressBar progressBar;
    TextView cryptoName;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.crypto_details_fragment, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.crypto_details_feed);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        getCryptoCoin();
        return v;
    }

    private void getCryptoCoin() {
        CryptoRequestInterface cryptoRequestInterface = APIClient.getClient().create(CryptoRequestInterface.class);
        Call<CryptoIndex> call;
        call = cryptoRequestInterface.getCryptoCoin(coinObject);

        call.enqueue(new Callback<CryptoIndex>() {
            @Override
            public void onResponse(Call<CryptoIndex> call, Response<CryptoIndex> response) {
                if (response.isSuccessful() && response.body() != null) {

                    Toasty.success(getContext(), "Onclick to Details Worked!", Toast.LENGTH_SHORT).show();

                    System.out.println(response.code());



                    //for (CryptoList cryptoList : cryptoDetailsList) {
                   //     String content = "";
                    //    content += "Test: " + cryptoList.getCryptoName();
                    //    cryptoName.setText(content);
                   // }
                    cryptoDetailsList = response.body().getCryptodata().getCryptoList();
                    cryptoDetailsAdapter = new CryptoDetailsAdapter(cryptoDetailsList, getContext());
                    cryptoDetailsAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(cryptoDetailsAdapter);

                    //System.out.println("UUID: " + coinObject);
                }
            }

            @Override
            public void onFailure(Call<CryptoIndex> call, Throwable t) {
                //progressBar.setVisibility(View.GONE);
                Toasty.error(getContext(), "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());

            }
        });
    }
}