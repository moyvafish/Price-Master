package com.example.mc.ui.city;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.mc.R;

public class CityFragment extends Fragment{

    private CityViewModel cityViewModel;
    AutoCompleteTextView mAutoCompleteTextView;
    String[] cities = {"Хельсинки", "Эспоо"};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cityViewModel =
                ViewModelProviders.of(this).get(CityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_city, container, false);

        mAutoCompleteTextView = (AutoCompleteTextView) root.findViewById(R.id.autoCompleteTextView);
        mAutoCompleteTextView.setAdapter(new ArrayAdapter(root.getContext(), android.R.layout.select_dialog_item, cities));
        return root;
    }
}