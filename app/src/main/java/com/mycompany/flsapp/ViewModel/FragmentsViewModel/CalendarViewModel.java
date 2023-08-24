package com.mycompany.flsapp.ViewModel.FragmentsViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mycompany.flsapp.Data.CalendarData;
import com.mycompany.flsapp.Model.CalendarModel;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.core.Observable;

public class CalendarViewModel extends ViewModel {
    MutableLiveData<ArrayList<CalendarData>> calendarListLiveData = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

    public MutableLiveData<ArrayList<CalendarData>> getCalendarList()
    {
        return calendarListLiveData;
    }

    public void loadData()
    {
        CalendarModel calendarModel = new CalendarModel();

        Disposable disposable = Observable.just(calendarModel.generateCalendarData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(calendarListLiveData::setValue,throwable -> Log.e("CitiesAndCountViewModel", "Ошибка при получении данных: " + throwable.getMessage()));
        disposables.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
