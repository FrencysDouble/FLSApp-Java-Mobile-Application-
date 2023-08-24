package com.mycompany.flsapp.Interfaces;

import com.mycompany.flsapp.Model.DataMerging;
import com.mycompany.flsapp.Data.CalendarData;

import java.util.ArrayList;
import java.util.List;

public interface DataCallback {
    void onCountriesCityDataRecieved(List<DataMerging.Data> dataList);
    void onCountriesCityDataFailed(Throwable throwable);
    void onCalendarModelDataRecieved(ArrayList<CalendarData> calendarDataList);
    void onCalendarModelDataFailed(Throwable throwable);
}
