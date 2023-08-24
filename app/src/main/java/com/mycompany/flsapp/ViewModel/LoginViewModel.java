package com.mycompany.flsapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {

    protected FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();

    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> loginSuccess.setValue(true))
                .addOnFailureListener(e -> loginSuccess.setValue(false));
    }
}
