package com.mycompany.flsapp.ViewModel;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Intent> navigateToRegistration = new MutableLiveData<>();
    private MutableLiveData<Intent> navigateToLogin = new MutableLiveData<>();
    private MutableLiveData<Intent> navigateTest = new MutableLiveData<>();

    public MutableLiveData<Intent> getNavigateToRegistration() {
        return navigateToRegistration;
    }

    public void navigateToRegistration() {
        navigateToRegistration.setValue(new Intent());
    }

    public MutableLiveData<Intent> getNavigateToLogin() {
        return navigateToLogin;
    }

    public void navigateToLogin() {
        navigateToLogin.setValue(new Intent());
    }

    public MutableLiveData<Intent> getNavigateTest() {
        return navigateTest;
    }

    public void navigateTest() {
        navigateTest.setValue(new Intent());
    }

}
