package com.mycompany.flsapp.API;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class DataCities {
    @SerializedName("code")
    private String code;
    @SerializedName("name_translations")
    private Map<String, String> name_translations;
    @SerializedName("country_code")
    private String country_code;

    public String getName() {
        return name;
    }

    @SerializedName("name")
    private String name;


    public String getCountry_code() {
        return country_code;
    }

    public String getCode() {
        return code;
    }

    public Map<String, String> getNameTranslations() {
        return name_translations;
    }

    @Override
    public String toString() {
        return "CityBasicInfo{" +
                "code='" + code + '\'' +
                ", name_translations=" + name_translations +
                '}';
    }
}
