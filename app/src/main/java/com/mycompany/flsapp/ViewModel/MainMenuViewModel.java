package com.mycompany.flsapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainMenuViewModel extends ViewModel {

    private MutableLiveData<Integer> clickedViewId = new MutableLiveData<>();
    private MutableLiveData<String> selectedCityOut = new MutableLiveData<>();
    private MutableLiveData<String> selectedCityIn = new MutableLiveData<>();

    public void setClickedViewId(int id)
    {
        clickedViewId.setValue(id);
    }
    public MutableLiveData<Integer> getClickedViewId() {
        return clickedViewId;
    }

    public void setSelectedCityOut(String city) {
        selectedCityOut.setValue(city);
    }

    public LiveData<String> getSelectedCityOut() {
        return selectedCityOut;
    }

    public void setSelectedCityIn(String city) {
        selectedCityIn.setValue(city);
    }

    public LiveData<String> getSelectedCityIn() {
        return selectedCityIn;
    }
}
