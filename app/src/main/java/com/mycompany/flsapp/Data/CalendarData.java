package com.mycompany.flsapp.Data;

import java.util.ArrayList;

public class CalendarData {
    String year;
    String month;
    ArrayList<String> dayDataList;
    ArrayList<DataPositions> positions = new ArrayList<>();


    public ArrayList<DataPositions> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<DataPositions> positions) {
        this.positions = positions;
    }

    public int getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public void setFirstDayOfWeek(int firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    int firstDayOfWeek;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public ArrayList<String> getDayDataList() {
        return dayDataList;
    }

    public void setDayDataList(ArrayList<String> dayDataList) {
        this.dayDataList = dayDataList;
    }

}
