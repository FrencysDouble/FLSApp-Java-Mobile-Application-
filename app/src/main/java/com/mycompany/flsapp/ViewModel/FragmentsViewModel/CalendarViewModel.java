package com.mycompany.flsapp.ViewModel.FragmentsViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mycompany.flsapp.Data.CalendarData;
import com.mycompany.flsapp.Data.DataPositions;
import com.mycompany.flsapp.Data.FirstSecPosData;
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
    FirstSecPosData firstSecPosData = new FirstSecPosData();


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
            if (firstSecPosData.getFirstSelPos() == -1 && firstSecPosData.getSecSelPos() == -1) {
                firstSecPosData.setFirstSelPos(position);
                this.monthPositionF = monthPosition;
            }
            // Если первый день выбран, но второй нет, выберите второй день
            else if (firstSecPosData.getFirstSelPos() != -1 && firstSecPosData.getSecSelPos() == -1) {
                firstSecPosData.setSecSelPos(position);
                this.monthPositionS = monthPosition;

                // Если первый и второй дни выбраны, очистите предыдущий выбор и установите новый
            } else if (firstSecPosData.getFirstSelPos() != -1 && firstSecPosData.getSecSelPos() != -1) {
                firstSecPosData.setFirstSelPos(position);
                firstSecPosData.setSecSelPos(-1);
                this.monthPositionF = monthPosition;
                this.monthPositionS = -1;

                clearSelectedPositions(calendarDataList);
            }

            // После каждого изменения выбора, обновите список выбранных дней
            selectedDayList(calendarData,firstSecPosData);

            // Обновите LiveData
            calendarListLiveData.setValue(calendarDataList);
        }
    }

    public void selectedDayList(CalendarData calendarData, FirstSecPosData firstSecPosData) {
        ArrayList<DataPositions> dataPositionsList = new ArrayList<>();
        DataPositions dataPositions = new DataPositions();

        int firstSelPos = firstSecPosData.getFirstSelPos();
        int secSelPos = firstSecPosData.getSecSelPos();
        Log.d("SelectedDay","firstsel = " + firstSelPos + " SecSel = " + secSelPos);

        if (monthPositionF == monthPositionS) {
            Log.d("Obichnii","F = " + monthPositionF +"S = " + monthPositionS);
            ArrayList<Integer> positions = new ArrayList<>();

           if (secSelPos == -1 && firstSelPos != -1) {
               positions.add(firstSelPos);
           }
             else if (secSelPos != -1 && firstSelPos != -1) {
                int min = Math.min(firstSelPos, secSelPos);
                int max = Math.max(firstSelPos, secSelPos);
                for (int j = min; j <= max; j++) {
                    positions.add(j);
                }
            }
            dataPositions.setMonthPositions(monthPositionS);
            dataPositions.setPositions(positions);
            dataPositionsList.add(dataPositions);
            calendarData.setPositions(dataPositionsList);
            Log.d("Obichnii", "EndList = " + dataPositionsList);
        }

        else {
            for (int i = monthPositionF; i <= monthPositionS; i++) {
                Log.d("Neobichnii","FSP = " + firstSelPos + "SSP = " + secSelPos);
                CalendarData currentMonthCalendar = calendarListLiveData.getValue().get(i);
                DataPositions dataPositions1 = new DataPositions();
                ArrayList<Integer> positions = new ArrayList<>();

                if (i == monthPositionF) {
                    // Если мы находимся в первом месяце, начнем с firstSelPos
                    for (int j = firstSelPos; j <= currentMonthCalendar.getDayDataList().size(); j++) {
                        Log.d("Neobichnii","First = " + j);
                        positions.add(j);
                    }
                } else if (i == monthPositionS) {
                    // Если мы находимся в последнем месяце, закончим на secSelPos
                    for (int j = calendarData.getFirstDayOfWeek()-1; j <= secSelPos; j++) {
                        Log.d("Neobichnii","Sec = " + j);
                        positions.add(j);
                    }
                } else {
                    // Если мы находимся в промежуточных месяцах, добавим все дни
                    for (int j = calendarData.getFirstDayOfWeek(); j <= currentMonthCalendar.getDayDataList().size() + calendarData.getFirstDayOfWeek(); j++) {
                        Log.d("Neobichnii","Third = " + j);
                        positions.add(j);
                    }
                }

                dataPositions1.setMonthPositions(i);
                dataPositions1.setPositions(positions);
                dataPositionsList.add(dataPositions1);
                Log.d("NEObichnii","End");
            }
            calendarData.setPositions(dataPositionsList);
            Log.d("Neobichnii", "EndList = " + dataPositionsList);
        }
    }

    private void clearSelectedPositions(ArrayList<CalendarData> calendarDataList) {
        for (CalendarData calendarData : calendarDataList) {
            ArrayList<DataPositions> dataPositionsList = calendarData.getPositions();
            for (DataPositions dataPositions : dataPositionsList) {
                dataPositions.getPositions().clear();
            }
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
