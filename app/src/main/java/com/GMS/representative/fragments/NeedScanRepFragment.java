package com.GMS.representative.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.GMS.R;

import com.GMS.databinding.FragmentNeedScanRepBinding;
import com.GMS.representative.adapters.RecyclerViewRepAdapterCitizen;
import com.GMS.representative.helperClass.CitizenItemOfRep;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class NeedScanRepFragment extends Fragment {

    public static final  int FRAGMENT_ID=1 ;
    FragmentNeedScanRepBinding mBinding ;
    RecyclerViewRepAdapterCitizen adapter ;
    public NeedScanRepFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentNeedScanRepBinding.inflate(inflater , container , false);

        ArrayList<CitizenItemOfRep> items = new ArrayList<>();
        items.add(new CitizenItemOfRep("Abdulrahman Khalid" , "45d55d45s55g" , 3  , R.drawable.ic_qr_need_scan));
        items.add(new CitizenItemOfRep("Abdulrahman Khalid" , "45d55d45s55g" , 3 ,R.drawable.ic_qr_need_scan ));
        items.add(new CitizenItemOfRep("Saad Khalid" , "45d55d45s55g" , 3 , R.drawable.ic_qr_need_scan));
        items.add(new CitizenItemOfRep("Abdulrahman Khalid" , "45d55d45s55g" ,  3, R.drawable.ic_qr_need_scan));
        items.add(new CitizenItemOfRep("Abdulrahman Khalid" , "45d55d45s55g" , 3 , R.drawable.ic_qr_need_scan));
        items.add(new CitizenItemOfRep("Abdulrahman Khalid" , "45d55d45s55g" , 3 , R.drawable.ic_qr_need_scan));

        adapter = new RecyclerViewRepAdapterCitizen(items , FRAGMENT_ID);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvNeedScanFragment.setHasFixedSize(true);
        mBinding.rvNeedScanFragment.setLayoutManager(layoutManager);
        mBinding.rvNeedScanFragment.setAdapter(adapter);
        return mBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_item_menu , menu);
        MenuItem searchItem = menu.findItem(R.id.search_ic);
        SearchView searchView= (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}