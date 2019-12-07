package com.example.mc.ui.catalog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mc.DataAdapterUnderCatalog;
import com.example.mc.R;
import com.example.mc.UnderCatalog;

import java.util.ArrayList;
import java.util.List;


public class CatalogFragment extends Fragment {

    private List<UnderCatalog> underCatalogList = new ArrayList<>();
    private CatalogViewModel catalogViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        catalogViewModel =
                ViewModelProviders.of(this).get(CatalogViewModel.class);
        View root = inflater.inflate(R.layout.fragment_catalog, container, false);
        setInitialData();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list2);
        // создаем адаптер
        DataAdapterUnderCatalog adapter = new DataAdapterUnderCatalog(root.getContext(), underCatalogList);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        return root;
    }
    private void setInitialData(){

    }
}