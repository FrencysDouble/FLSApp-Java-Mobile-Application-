package com.mycompany.flsapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mycompany.flsapp.Data.User;

import java.util.Objects;

public class RegistViewModel extends ViewModel {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference users = db.getReference("Users");

    public LiveData<Boolean> createUser(String email, String password, String username) {
        MutableLiveData<Boolean> registrationResult = new MutableLiveData<>();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    User user = new User();
                    user.setName(username);
                    user.setEmail(email);
                    user.setPassword(password);
                    users.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                            .setValue(user);
                    registrationResult.setValue(true);
                })
                .addOnFailureListener(e -> registrationResult.setValue(false));

        return registrationResult;
    }
}
