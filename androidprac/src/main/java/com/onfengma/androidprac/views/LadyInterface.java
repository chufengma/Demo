package com.onfengma.androidprac.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chuyifeng on 2015/11/5.
 */
public class LadyInterface {

    public static final int REQUEST_CAPTURE_IMAGE = 1;

    private Activity context;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public LadyInterface(Activity context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String string) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void showDialog(String string) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(string);
        builder.show();
    }

    @JavascriptInterface
    public void uploadPic(String successCallback, String errorCallback) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            File file = createImageFile();
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(file));
            fileName = file.getAbsolutePath();
            context.startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getCacheDir();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }
}
