package com.example.collegealumni.serviceIMPL;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.collegealumni.model.User;
import com.example.collegealumni.service.UserService;
import com.example.collegealumni.util.CallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UserServiceIMPL implements UserService {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    public UserServiceIMPL() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        storageReference = FirebaseStorage.getInstance().getReference().child("users");
    }

    @Override
    public void saveUser(CallBack<Boolean> finishedCallBack, User user) {

    }

    @Override
    public void getSurvey(CallBack<Boolean> finishedCallBack, String bio, Uri imageURI) {
        DatabaseReference userReference = databaseReference.child( firebaseAuth.getUid());

        databaseReference.child( firebaseAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String key = databaseReference.push().getKey();
                final StorageReference imageReference = storageReference.child( key);
                UploadTask task = storageReference.child( key).putFile( imageURI);

                task.continueWithTask(
                        task1 -> imageReference.getDownloadUrl())
                        .addOnCompleteListener(task12 -> {
                            if( task12.isSuccessful()) {
                                Uri downloadUri = task12.getResult();
                                final String url = downloadUri.toString();

                                userReference.child("pic").setValue( url);
                            }
                        });
                userReference.child("bio").setValue( bio);
                finishedCallBack.callback(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                finishedCallBack.callback(false);
            }
        });
    }

    @Override
    public void getSurvey(CallBack<Boolean> finishedCallBack, String bio) {
        DatabaseReference userReference = databaseReference.child( firebaseAuth.getUid());

        databaseReference.child( firebaseAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userReference.child("bio").setValue( bio);
                finishedCallBack.callback(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                finishedCallBack.callback(false);
            }
        });
    }

    @Override
    public void getUserById(@NonNull CallBack<User> callback, String id) {
        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue( User.class);
                callback.callback( user);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    @Override
    public void getCurrentUser(@NonNull CallBack<User> callBack) {

        databaseReference.child( firebaseAuth.getUid()).get()
            .addOnCompleteListener(
                    task -> callBack.callback( task.getResult().getValue( User.class)));
    }
}
