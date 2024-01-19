package com.thisit.softwaregroup.Fragment;

import static android.app.Activity.RESULT_OK;
import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.exifinterface.media.ExifInterface;

import com.bumptech.glide.Glide;
import com.thisit.softwaregroup.R;
import com.thisit.softwaregroup.common.BaseFragment;
import com.thisit.softwaregroup.dashboard.view.ECardActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class HistroyFragment extends BaseFragment {

    private View root;
    public static final int ACTION_REQUEST_PERMISSIONS = 102;
    public static final String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private static final int IMAGE_CAPTURE_CODE = 1001;
    private static final int GALLERY_IMAGE_CODE = 1002;
    private static final int PERMISSION_CODE = 1000;

    private String encodedImage = "";

    private Bitmap bitmap;

    Uri image_uri;

    TextView choose_prdtv;

    ImageView imageView;
EditText File_Path,File_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.activity_product_update, container, false);

        choose_prdtv = (TextView) root.findViewById(R.id.choose_prdtv);
        imageView = (ImageView) root.findViewById(R.id.imageView);
        File_name = (EditText) root.findViewById(R.id.File_name);
        File_Path = (EditText) root.findViewById(R.id.File_Path);
        //camera
        if (checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }


        choose_prdtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFrontImage();
            }
        });

        return root;

    }


    private void selectFrontImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                                || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                            // Permission not enabled, request it
                            String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            // Show popup to request permissions
                            ActivityCompat.requestPermissions(getActivity(), permissions, ACTION_REQUEST_PERMISSIONS);
                        } else {
                            // Permission already granted
                            openCamera();
                        }
                    } else {
                        //system os < marshmallow
                        openCamera();
                    }
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, GALLERY_IMAGE_CODE);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void openCamera() {
        ContentResolver contentResolver = getActivity().getContentResolver();

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");


        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //Camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //this method is called, when user presses Allow or Deny from Permission Request Popup
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission from popup was granted
                    openCamera();
                } else {
                    //permission from popup was denied
                    Toast.makeText(getActivity(), "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult 01 : " + requestCode + " - " + resultCode);
        //System.out.println("onActivityResult 03 : "+data.getExtras());
        /*if (resultCode == RESULT_OK && data.getData()!=null) {*/
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_CAPTURE_CODE) {

                imageView.setImageURI(image_uri);
                ContentResolver contentResolver = getActivity().getContentResolver();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = contentResolver.query(image_uri, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                String fileName = new File(picturePath).getName();

                File_name .setText(fileName);
                File_Path .setText(picturePath);

                c.close();
                bitmap = (BitmapFactory.decodeFile(picturePath));
                ByteArrayOutputStream Bytes = new ByteArrayOutputStream();

                int imageRotation = getImageRotation(new File(picturePath));

                if (imageRotation != 0) {
                    bitmap = getBitmapRotatedByDegree(bitmap, imageRotation);
                }

                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, Bytes);
                System.out.println("onActivityResult 0221 : " + bitmap.getByteCount());
                byte[] imageBytes = Bytes.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            } else if (requestCode == GALLERY_IMAGE_CODE) {
                if (data != null) {
                    Uri contentURI = data.getData();
                    try {
                        ContentResolver contentResolver = getActivity().getContentResolver();
                        bitmap = MediaStore.Images.Media.getBitmap(contentResolver, contentURI);
                        Glide.with(this)
                                .load(bitmap)
                                .into(imageView);
                        //imageView.setImageBitmap(bitmap);
                        ByteArrayOutputStream Bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, Bytes);
                        byte[] imageBytes = Bytes.toByteArray();
                        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                        String fileName = getFileName(contentURI, contentResolver);
                        String filePath = getFilePath(contentURI, contentResolver);

                        File_name .setText(fileName);
                        File_Path .setText(filePath);


                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        }

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText("Upload Image");

    }
    private String getFileName(Uri uri, ContentResolver contentResolver) {
        String[] projection = {MediaStore.Images.Media.DISPLAY_NAME};
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        String fileName = "";

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
                fileName = cursor.getString(columnIndex);
            }
            cursor.close();
        }

        return fileName;
    }

    private String getFilePath(Uri uri, ContentResolver contentResolver) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        String filePath = "";

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
        }

        return filePath;
    }
    private static int getImageRotation(final File imageFile) {

        ExifInterface exif = null;
        int exifRotation = 0;

        try {
            exif = new ExifInterface(imageFile.getPath());
            exifRotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (exif == null)
            return 0;
        else
            return exifToDegrees(exifRotation);
    }

    private static int exifToDegrees(int rotation) {
        if (rotation == ExifInterface.ORIENTATION_ROTATE_90)
            return 90;
        else if (rotation == ExifInterface.ORIENTATION_ROTATE_180)
            return 180;
        else if (rotation == ExifInterface.ORIENTATION_ROTATE_270)
            return 270;

        return 0;
    }

    private static Bitmap getBitmapRotatedByDegree(Bitmap bitmap, int rotationDegree) {
        Matrix matrix = new Matrix();
        matrix.preRotate(rotationDegree);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

}

