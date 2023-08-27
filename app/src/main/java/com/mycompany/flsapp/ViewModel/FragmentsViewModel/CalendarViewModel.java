package com.mycompany.flsapp.ViewModel.FragmentsViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mycompany.flsapp.Data.CalendarData;
import com.mycompany.flsapp.Data.DataPositions;
import com.mycompany.flsapp.Model.CalendarModel;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarViewModel extends ViewModel {
    MutableLiveData<ArrayList<CalendarData>> calendarListLiveData = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private int monthPositionF;
    private int monthPositionS;

    public MutableLiveData<ArrayList<CalendarData>> getCalendarList() {
        return calendarListLiveData;
    }

    public void loadData() {
        CalendarModel calendarModel = new CalendarModel();

        Disposable disposable = Observable.just(calendarModel.generateCalendarData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(calendarListLiveData::setValue, throwable -> Log.e("CitiesAndCountViewModel", "Ошибка при получении данных: " + throwable.getMessage()));
        disposables.add(disposable);
    }

    public void toggleDaySelection(int monthPosition,int position) {
        ArrayList<CalendarData> calendarDataList = calendarListLiveData.getValue();
        if (calendarDataList != null && !calendarDataList.isEmpty()) {
            CalendarData calendarData = calendarDataList.get(monthPosition);

            // Если ни один день не выбран, выберите первый день
            if (calendarData.getFirstSelPos() == -1 && calendarData.getSecSelPos() == -1) {
                calendarData.setFirstSelPos(position);
                this.monthPositionF = monthPosition;
            }
            // Если первый день выбран, но второй нет, выберите второй день
            else if (calendarData.getFirstSelPos() != -1 && calendarData.getSecSelPos() == -1) {
                calendarData.setSecSelPos(position);
                this.monthPositionS = monthPosition;

                // Если первый и второй дни выбраны, очистите предыдущий выбор и установите новый
            } else if (calendarData.getFirstSelPos() != -1 && calendarData.getSecSelPos() != -1) {
                calendarData.setFirstSelPos(position);
                calendarData.setSecSelPos(-1);
            }

            // После каждого изменения выбора, обновите список выбранных дней
            selectedDayList(calendarData);

            // Обновите LiveData
            calendarListLiveData.setValue(calendarDataList);
        }
    }

    public void selectedDayList(CalendarData calendarData) {
        ArrayList<DataPositions> dataPositionsList = new ArrayList<>();

        int firstSelPos = calendarData.getFirstSelPos();
        int secSelPos = calendarData.getSecSelPos();

        if (monthPositionF == monthPositionS) {
            Log.d("Obichnii","F = " + monthPositionF +"S = " + monthPositionS);
            ArrayList<Integer> positions = new ArrayList<>();

            if (secSelPos == -1 && firstSelPos != -1) {
                positions.add(firstSelPos);
            } else if (secSelPos != -1 && firstSelPos != -1) {
                int min = Math.min(firstSelPos, secSelPos);
                int max = Math.max(firstSelPos, secSelPos);
                for (int j = min; j <= max; j++) {
                    positions.add(j);
                }
            }
            DataPositions dataPositions = new DataPositions();
            dataPositions.setMonthPositions(monthPositionS);
            dataPositions.setPositions(positions);
            dataPositionsList.add(dataPositions);
        }

        else {
            Log.d("NEObichnii","F = " + monthPositionF + "S = " + monthPositionS);
            for (int i = monthPositionF; i <= monthPositionS; i++) {
                CalendarData currentMonthCalendar = calendarListLiveData.getValue().get(i);
                DataPositions dataPositions = new DataPositions();
                ArrayList<Integer> positions = new ArrayList<>();
                dataPositions.setMonthPositions(i);

                if (i == monthPositionF) {
                    // Если мы находимся в первом месяце, начнем с firstSelPos
                    for (int j = firstSelPos; j < currentMonthCalendar.getDayDataList().size(); j++) {
                        positions.add(j);
                    }
                } else if (i == monthPositionS) {
                    // Если мы находимся в последнем месяце, закончим на secSelPos
                    for (int j = 0; j <= secSelPos; j++) {
                        positions.add(j);
                    }
                } else {
                    // Если мы находимся в промежуточных месяцах, добавим все дни
                    for (int j = 0; j < currentMonthCalendar.getDayDataList().size(); j++) {
                        positions.add(j);
                    }
                }

                dataPositions.setMonthPositions(i);
                dataPositions.setPositions(positions);
                dataPositionsList.add(dataPositions);
            }
            calendarData.setPositions(dataPositionsList);
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
