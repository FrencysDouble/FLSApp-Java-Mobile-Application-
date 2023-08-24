package com.mycompany.flsapp.Model;

import android.util.Log;

import com.mycompany.flsapp.Data.CalendarData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarModel {
    private ArrayList<CalendarData> calendarData = new ArrayList<>();

    public ArrayList<CalendarData> getCalendarData()
    {
        return  calendarData;
    }

    public ArrayList<CalendarData> generateCalendarData()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        for (int i = 0; i < 8; i++) {
            int year = calendar.get(Calendar.YEAR);

            // Получаем название месяца
            SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
            String monthName = monthFormat.format(calendar.getTime());

            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            int firstDayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
            ArrayList<String> dayDataList = new ArrayList<>();
            // Генерируем данные для дней в месяце
            for (int day = 1; day <= daysInMonth; day++) {
                dayDataList.add(String.valueOf(day));
            }

            CalendarData data = new CalendarData();
            data.setYear(String.valueOf(year));
            data.setMonth(monthName);
            data.setDayDataList(dayDataList);
            data.setFirstDayOfWeek(firstDayOfWeek);
            calendarData.add(data);
            Log.d("CalendarModel","First Day of week" + data.getFirstDayOfWeek());

            // Переходим к следующему месяцу
            calendar.add(Calendar.MONTH, 1);
        }
        return calendarData;
    }
}
