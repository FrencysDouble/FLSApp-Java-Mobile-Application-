package com.mycompany.flsapp.Interfaces;

import com.mycompany.flsapp.API.DataMerging;

import java.util.List;

public interface DataCallback {
    void onCountriesCityDataRecieved(List<DataMerging.Data> dataList);
    void onCountriesCityDataFailed(Throwable throwable);
}
