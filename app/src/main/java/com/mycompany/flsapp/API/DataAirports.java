package com.mycompany.flsapp.API;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class DataAirports {
    private String code;
    @SerializedName("name")
    private static String name;
    @SerializedName("iata-type")
    private String iataType;
    private boolean flightable;
    private Coordinates coordinates;
    @SerializedName("time_zone")
    private String timeZone;
    @SerializedName("name_translations")
    private Map<String, String> nameTranslations;
    @SerializedName("country_code")
    private String countryCode;
    @SerializedName("city_code")
    private String cityCode;

    public String getCode() {
        return code;
    }

    public static String getName() {
        return name;
    }

    public String getIataType() {
        return iataType;
    }

    public boolean isFlightable() {
        return flightable;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public Map<String, String> getNameTranslations() {
        return nameTranslations;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public class Coordinates {
        private double lon;
        private double lat;

        public double getLon() {
            return lon;
        }

        public double getLat() {
            return lat;
        }
    }
}
