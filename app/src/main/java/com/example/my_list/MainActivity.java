package com.example.my_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView itemsList;
    ItemDAO itemDAO;
    ArrayAdapter<Item > arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsList = findViewById(R.id.list);
        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = arrayAdapter.getItem(position);
                if(item != null) {
                    Intent intent = new Intent(getApplicationContext(),  ItemFormActivity.class);
                    intent.putExtra("id", item.getId());
                    startActivity(intent);
                }
            }
        });
        itemDAO = new  ItemDAO(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        itemDAO.open();
        List<Item > items = itemDAO.getItems();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(arrayAdapter);
        itemDAO.close();
    }

    public void add(View view) {
        Intent intent = new Intent(this,  ItemFormActivity.class);
        startActivity(intent);
    }
}