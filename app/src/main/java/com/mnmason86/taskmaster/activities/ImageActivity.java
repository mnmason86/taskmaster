package com.mnmason86.taskmaster.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.mnmason86.taskmaster.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageActivity extends AppCompatActivity {

    public static final String TAG = "ImageActivity";
    ActivityResultLauncher<Intent> activityResultLauncher;
    private String s3ImageKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        activityResultLauncher = getImagePickingActivityResultLauncher();

        createAddImageButton();
        createSaveImageButton();
    }

    private void createSaveImageButton(){
        findViewById(R.id.imageActivitySaveButton).setOnClickListener(b -> {
            saveTeam(s3ImageKey);

            Intent goToSettingsActivity = new Intent(ImageActivity.this, SettingsActivity.class);
            startActivity(goToSettingsActivity);
        });
    }

    private void createAddImageButton(){
        findViewById(R.id.imageActivityAddButton).setOnClickListener(button -> {
            launchImageSelectionIntent();
        });
    }

    private void saveTeam(String s3key){
        String newTeamName = ((EditText) findViewById(R.id.imageActivityTeamNameEditText)).getText().toString();
        Team teamToSave = Team.builder()
                .name(newTeamName)
                .productImageS3Key(s3key)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(teamToSave),
                success -> Log.i(TAG, "Successfully created new team with s3ImageKey: " + s3key),
                failure -> Log.i(TAG, "Failed to create a new team with message: " + failure.getMessage())
        );
    }

    private void launchImageSelectionIntent(){
        Intent imageFilePickingIntent = new Intent(Intent.ACTION_GET_CONTENT);
        imageFilePickingIntent.setType("*/*");
        imageFilePickingIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/png"});
        activityResultLauncher.launch(imageFilePickingIntent);
    }

    private ActivityResultLauncher<Intent> getImagePickingActivityResultLauncher(){
        ActivityResultLauncher<Intent> imagePickingActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                Uri pickedImageFileUri = result.getData().getData();
                                try {
                                    InputStream pickedImageInputStream = getContentResolver().openInputStream(pickedImageFileUri);
                                    String pickedImageFilename = getFileNameFromUri(pickedImageFileUri);
                                    uploadInputStreamToS3(pickedImageInputStream, pickedImageFilename, pickedImageFileUri);
                                } catch (FileNotFoundException fnfe) {
                                    Log.e(TAG, "Could not get file from file picker. " + fnfe.getMessage(), fnfe);
                                }
                            }
                        }
                );
        return imagePickingActivityResultLauncher;
    }

    private void uploadInputStreamToS3(InputStream pickedImageInputStream, String pickedImageFilename, Uri pickedImageFileUri){
        Amplify.Storage.uploadInputStream(
                pickedImageFilename,
                pickedImageInputStream,
                success -> {
                    Log.i(TAG, "Succeeded in getting file uploaded to S3. Key is: " + success.getKey());
                    ImageView teamImageView = findViewById(R.id.imageActivityTeamImageView);
                    InputStream pickedImageInputStreamCopy = null;
                    try {
                        pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageFileUri);
                    } catch (FileNotFoundException fnfe) {
                        Log.e(TAG, "Could not get file stream from URI. " + fnfe.getMessage(), fnfe);
                    }
                    teamImageView.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
                },
                failure -> Log.e(TAG, "Failure in uploading file to S3 with filename: " + pickedImageFilename + " with error: " + failure.getMessage())
        );
    }
    // Taken from https://stackoverflow.com/a/25005243/16889809
    @SuppressLint("Range")
    public String getFileNameFromUri(Uri uri){
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}