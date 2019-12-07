package com.example.mc.ui.underCatalog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UnderCatalogViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UnderCatalogViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}