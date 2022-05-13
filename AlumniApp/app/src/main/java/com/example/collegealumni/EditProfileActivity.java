package com.example.collegealumni;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collegealumni.service.UserService;
import com.example.collegealumni.serviceIMPL.UserServiceIMPL;
import com.example.collegealumni.util.FormValidation;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

public class EditProfileActivity extends AppCompatActivity {
    private Uri currentImageURI;

    private TextInputEditText bioEdit;
    private ImageView profileImage;
    private TextView editPicBtn;
    private Button submitBtn;

    private UserService userService = new UserServiceIMPL();
    private FormValidation formValidation = new FormValidation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileImage = findViewById( R.id.editProfilePic);
        bioEdit = findViewById( R.id.editProfileBio);
        editPicBtn = findViewById( R.id.editProfilePicBtn);
        submitBtn = findViewById( R.id.editProfileBtn);

        editPicBtn.setOnClickListener(this::selectImage);

        submitBtn.setOnClickListener(this::saveProfile);

        initializeViews();
    }

    private void selectImage( View view) {
        ImagePicker.Companion.with( this)
                .cropSquare()
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data);
        if (resultCode == -1) {
            currentImageURI = data != null ? data.getData() : null;
            profileImage.setImageURI( currentImageURI);
        } else if (resultCode == 64) {
            Toast.makeText( this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText( this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveProfile(View view) {
        String bio = bioEdit.getText().toString();

        String validationResponse = formValidation.validateSurvey( currentImageURI);

        if( validationResponse.equals( "Profile Updated Successfully")) {
            userService.getSurvey(isSuccessful -> {
                if( isSuccessful) {
                    Toast.makeText(EditProfileActivity.this, validationResponse, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent( this, MainActivity.class);
                    startActivity( intent);
                } else {
                    Toast.makeText(EditProfileActivity.this, "Updating Failed", Toast.LENGTH_SHORT).show();
                }
            }, bio, currentImageURI);
        } else {
            userService.getSurvey(isSuccessful -> {
                if( isSuccessful) {
                    Toast.makeText(EditProfileActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent( this, MainActivity.class);
                    startActivity( intent);
                } else {
                    Toast.makeText(EditProfileActivity.this, "Updating Failed", Toast.LENGTH_SHORT).show();
                }
            }, bio);
        }
    }

    private void initializeViews() {
        userService.getCurrentUser(user -> {
            bioEdit.setText( user.getBio());

            Picasso.get()
                    .load( user.getPic())
                    .into( profileImage);
        });
    }
}