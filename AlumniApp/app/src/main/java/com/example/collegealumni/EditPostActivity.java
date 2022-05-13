package com.example.collegealumni;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collegealumni.model.Post;
import com.example.collegealumni.service.PostService;
import com.example.collegealumni.serviceIMPL.PostServiceIMPL;
import com.example.collegealumni.util.FormValidation;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class EditPostActivity extends AppCompatActivity {

    private Uri currentImageURI;
    private String postId = null;
    private String defaultImageUrl = null;

    private ImageView selectedImageView;
    private TextInputEditText editTitle, editCaption;
    private Button addPostBtn, selectImageBtn;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private FormValidation formValidation = new FormValidation();
    private PostService postService = new PostServiceIMPL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        postId = getIntent().getStringExtra( "postId");

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference().child("posts");

        selectedImageView = findViewById( R.id.selectedImageView);
        editTitle = findViewById( R.id.editTitle);
        editCaption = findViewById( R.id.editCaption);

        addPostBtn = findViewById( R.id.addPostBtn);
        selectImageBtn = findViewById( R.id.selectImgBtn);

        databaseReference.child( "posts").child( postId).get()
            .addOnCompleteListener(task -> {
                Post post = task.getResult().getValue( Post.class);
                editTitle.setText( post.getTitle());
                editCaption.setText( post.getCaption());

                defaultImageUrl = post.getImageUrl();

                Picasso.get()
                        .load( post.getImageUrl())
                        .into( selectedImageView);
            });

        selectImageBtn.setOnClickListener( v -> selectImage());
        addPostBtn.setOnClickListener(v -> addPost());
    }

    private void addPost() {
        String title = editTitle.getText().toString().trim();
        String caption = editCaption.getText().toString().trim();

        String validationResponse = formValidation.validatePost( title, caption, currentImageURI);

        if( validationResponse.equals("Posted Successfully")) {

            final String key = postId;
            final StorageReference imageReference = storageReference.child( key);
            UploadTask task = storageReference.child( key).putFile( currentImageURI);

            task.continueWithTask(
                    task1 -> imageReference.getDownloadUrl())
                    .addOnCompleteListener(task12 -> {
                        if( task12.isSuccessful()) {
                            Uri downloadUri = task12.getResult();
                            final String url = downloadUri.toString();

                            Post post = new Post( key, title, caption, url, firebaseAuth.getUid());

                            postService.updatePost(isSuccessful -> {
                                if( isSuccessful) {
                                    Toast.makeText( getApplicationContext(), validationResponse, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent( getApplicationContext(), MainActivity.class);
                                    startActivity( intent);
                                } else {
                                    Toast.makeText( getApplicationContext(), "Problem occurred check your internet", Toast.LENGTH_SHORT).show();
                                }
                            }, post);
                        }
                    });
        } else if( validationResponse.equals("Select Image to post")){
            Post post = new Post( postId, title, caption, defaultImageUrl, null);

            postService.updatePost(isSuccessful -> {
                if( isSuccessful) {
                    Toast.makeText( getApplicationContext(), "Post updated successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent( getApplicationContext(), MainActivity.class);
                    startActivity( intent);
                } else {
                    Toast.makeText( getApplicationContext(), "Problem occurred check your internet", Toast.LENGTH_SHORT).show();
                }
            }, post);
        } else {
            Toast.makeText( getApplicationContext(), validationResponse, Toast.LENGTH_SHORT).show();
        }
    }

    private void selectImage() {
        ImagePicker.Companion.with(this)
                //.cropSquare()	    			//Crop image(Optional), Check Customization for more option
                //.compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data);
        if (resultCode == -1) {
            currentImageURI = data != null ? data.getData() : null;
            selectedImageView.setImageURI( currentImageURI);
        } else if (resultCode == 64) {
            Toast.makeText( getApplicationContext(), ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText( getApplicationContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}