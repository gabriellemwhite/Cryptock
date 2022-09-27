package com.gabriellewhite.cryptock.ui.crypto_index;

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
import com.gabriellewhite.cryptock.ui.crypto_index.models.CryptoIndex;
import com.gabriellewhite.cryptock.ui.crypto_index.models.CryptoList;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CryptoIndexFragment extends Fragment {

    private List<CryptoList> cryptoList = new ArrayList<>();
    private List<CryptoList> searchList = new ArrayList<>();

    private CryptoAdapter cryptoAdapter;
    private SearchCryptoAdapter searchCryptoAdapter;

    public static String coinObject;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.crypto_index_fragment, container, false);

        progressBar = v.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        searchView = (SearchView) v.findViewById(R.id.searchView);

        recyclerView = (RecyclerView) v.findViewById(R.id.crypto_index_feed);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        getCryptoIndexFunction();
        return v;
    }

    private void getCryptoIndexFunction() {
        CryptoRequestInterface cryptoRequestInterface = APIClient.getClient().create(CryptoRequestInterface.class);
        Call<CryptoIndex> call;
        call = cryptoRequestInterface.getCryptoIndex();

        call.enqueue(new Callback<CryptoIndex>() {
            @Override
            public void onResponse(Call<CryptoIndex> call, Response<CryptoIndex> response) {
                if (response.isSuccessful() && response.body().getCryptodata().getCryptoList() != null) {
                    progressBar.setVisibility(View.GONE);
                    //System.out.println(response.code());

                    cryptoList = response.body().getCryptodata().getCryptoList();
                    cryptoAdapter = new CryptoAdapter(cryptoList, getContext());
                    cryptoAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(cryptoAdapter);

                    cryptoAdapter.setOnItemClickListener(new CryptoAdapter.CryptoItemListener() {
                        @Override
                        public void onItemClickCrypto(int position) {

                            System.out.println(cryptoList.get(position).getCryptoName());
                            cryptoList.get(position);
                            Toasty.success(getContext(), "OnClick Worked!", Toast.LENGTH_SHORT).show();

                            coinObject = cryptoList.get(position).getUuid();
                            System.out.println(coinObject);
                            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_crypto_details);


                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<CryptoIndex> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toasty.error(getContext(), "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CryptoRequestInterface cryptoRequestInterface = APIClient.getClient().create(CryptoRequestInterface.class);
                Call<CryptoIndex> call;
                call = cryptoRequestInterface.getCryptoSearchFunction(query);

                call.enqueue(new Callback<CryptoIndex>() {
                    @Override
                    public void onResponse(Call<CryptoIndex> call, Response<CryptoIndex> response) {

                        if (response.isSuccessful() && response.body().getCryptodata().getCryptoList() != null) {
                            progressBar.setVisibility(View.GONE);


                            searchList = response.body().getCryptodata().getCryptoList();
                            searchCryptoAdapter = new SearchCryptoAdapter(searchList, getContext());
                            recyclerView.setAdapter(searchCryptoAdapter);
                            searchCryptoAdapter.notifyDataSetChanged();

                            System.out.println(response.code());
                            Toast.makeText(getContext(), "Search Query Worked!", Toast.LENGTH_SHORT).show();

                            searchCryptoAdapter.setOnItemClickListener(new SearchCryptoAdapter.CryptoSearchItemListener() {
                                @Override
                                public void onItemClickCryptoSearch(int position) {
                                    System.out.println(cryptoList.get(position).getCryptoName());
                                    cryptoList.get(position);
                                    Toasty.success(getContext(), "OnClick Worked!", Toast.LENGTH_SHORT).show();

                                    coinObject = cryptoList.get(position).getUuid();
                                    System.out.println(coinObject);
                                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_crypto_details);
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<CryptoIndex> call, Throwable t) {
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