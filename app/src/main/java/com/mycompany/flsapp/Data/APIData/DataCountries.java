package com.mycompany.flsapp.Data.APIData;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class DataCountries {
    @SerializedName("code")
    private String code;
    @SerializedName("name")
    private String name;
    @SerializedName("name_translations")
    private Map<String, String> nameTranslations;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getNameTranslations() {
        return nameTranslations;
    }
}