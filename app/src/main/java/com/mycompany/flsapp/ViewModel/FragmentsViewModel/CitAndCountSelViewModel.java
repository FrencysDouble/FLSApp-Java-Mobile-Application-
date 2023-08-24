package com.mycompany.flsapp.ViewModel.FragmentsViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mycompany.flsapp.API.APIManager;
import com.mycompany.flsapp.Model.DataMerging;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class CitAndCountSelViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<DataMerging.Data>> dataListLiveData = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

    public LiveData<ArrayList<DataMerging.Data>> getDataListLiveData() {
        return dataListLiveData;
    }

    public void loadData() throws IOException {
        APIManager apiManager = new APIManager();

        Disposable disposable = (apiManager.getCountryAndCitiesData())
                .subscribe(
                        dataListLiveData::postValue,
                        throwable -> Log.e("CitiesAndCountViewModel", "Ошибка при получении данных: " + throwable.getMessage())
                );
        disposables.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
