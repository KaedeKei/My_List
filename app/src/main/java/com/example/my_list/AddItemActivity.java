package com.example.my_list;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddItemActivity extends AppCompatActivity {
    private EditText nameAddField;
    private Button loadPictureBtn, savePurchaseBtn;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        nameAddField = findViewById(R.id.name_add_field);
        savePurchaseBtn = findViewById(R.id.save_purchase_btn);
        dbHelper = new DatabaseHelper(this);
        savePurchaseBtn.setOnClickListener(v -> saveItemsToDb());
    }

    private void saveItemsToDb() {
        String name = nameAddField.getText().toString();
        dbHelper.getWritableDatabase().execSQL("insert into items(name) values(?)", new Object[]{name});
        setResult(RESULT_OK);
        finish();
    }
}