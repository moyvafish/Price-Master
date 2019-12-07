package com.example.mc.ui.underCatalog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mc.DataAdapterGoods;
import com.example.mc.Good;
import com.example.mc.R;
import com.example.mc.ui.underCatalog.UnderCatalogViewModel;

import java.util.ArrayList;
import java.util.List;

public class UnderCatalogFragment extends Fragment {

    private UnderCatalogViewModel underCatalogViewModel;
    private List<Good> goods = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        underCatalogViewModel =
                ViewModelProviders.of(this).get(UnderCatalogViewModel.class);
        View root = inflater.inflate(R.layout.fragment_undercatalog, container, false);
//        setInitialData();
//        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list);
//        // создаем адаптер
//        DataAdapterGoods adapter = new DataAdapterGoods(root.getContext(), goods);
//        // устанавливаем для списка адаптер
//        recyclerView.setAdapter(adapter);
        return root;
    }
//    private void setInitialData(){
//
//        goods.add(new Good ("Хлеб", "Завод", "android.resource://Monitoring/res/drawable/bread.png", "Good bread", "100 EZ"));
//
//    }
}