package com.mycompany.flsapp.Model;

import android.util.Log;

import com.mycompany.flsapp.Data.APIData.DataCities;
import com.mycompany.flsapp.Data.APIData.DataCountries;

import java.util.ArrayList;

public class DataMerging
{
    public DataMerging()
    {

    }

    public static ArrayList<DataMerging.Data> mergeData(ArrayList<DataCountries> countriesList, ArrayList<DataCities> citiesList)
    {
        ArrayList<Data> dataList = new ArrayList<>();
        if (countriesList != null && citiesList != null)
        {
            for(DataCountries dataCountries : countriesList)
            {
                for(DataCities dataCities : citiesList)
                {
                    if(dataCountries.getCode().equals(dataCities.getCountry_code()))
                    {
                        Data data = new Data();
                        data.setCode(dataCities.getCountry_code());
                        data.setCountryName(dataCountries.getName());
                        data.setCityName(dataCities.getName());
                        dataList.add(data);
                    }
                }
            }
        }
        Log.d("DataMerging", "Received Merging data: " + dataList.toString());
        return dataList;
    }


    public static class Data
    {
        String code;
        String countryName;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        String cityName;

    }
}
