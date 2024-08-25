package com.example.my_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ItemFormActivity extends AppCompatActivity {
    EditText nameField;
    Button saveBtn;
    Button deleteBtn;

    ItemDAO itemDAO;
    long itemId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nameField = findViewById(R.id.nameField);
        saveBtn = findViewById(R.id.saveBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        itemDAO = new ItemDAO(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            itemId = extras.getLong("id");
        }

        if(itemId > 0) {
            itemDAO.open();
            Item item = itemDAO.getItem(itemId);
            nameField.setText(item.getName());
            itemDAO.close();
        } else {
            //иначе добавление
            deleteBtn.setVisibility(View.GONE);//скрытие кнопки удаления
        }
    }

    public void save(View view) {
        String name = nameField.getText().toString();
        Item item = new Item(itemId, name);

        itemDAO.open();
        if(itemId > 0) {
            itemDAO.update(item);
        } else {
            itemDAO.insert(item);
        }
        goHome();
    }

    public void delete(View view) {
        itemDAO.open();
        itemDAO.delete(itemId);
        itemDAO.close();
        goHome();
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
