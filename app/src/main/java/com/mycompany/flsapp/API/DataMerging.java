package com.mycompany.flsapp.API;

import android.util.Log;

import com.mycompany.flsapp.Interfaces.DataCallback;

import java.util.ArrayList;
import java.util.List;

public class DataMerging{
    protected List<DataCountries> countriesList;
    protected List<DataCities> citiesList;
    protected List<Data> dataList = new ArrayList<>();

    public DataMerging()
    {

    }

    public void CountriesSet(List<DataCountries> countriesList)
    {
        this.countriesList = countriesList;
    }
    public void CitiesSet(List<DataCities> citiesList)
    {
        this.citiesList = citiesList;
    }

    public void Merging(DataCallback callback)
    {
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
        callback.onCountriesCityDataRecieved(dataList);
    }


    public class Data
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
