package com.example.onlineshop2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {

    private static final String TAG = "MainActivity";
    public static final String EXTRA_TEXT = "com.example.onlineshop2.example.EXTRA_TEXT";

    final private String productTextViewValueContent = "productTextViewValueContent";
    ListView listView;
    TextView productTextView;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        button = (Button) findViewById(R.id.button);

        final ArrayList<String> products = new ArrayList<>();

//        for (int i = 0; i < 10; i++) {
//            products.add(String.format(Locale.ENGLISH, "Product 0%d", i));
//        }
        products.add(String.format(Locale.ENGLISH, "product1", 0));
        products.add(String.format(Locale.ENGLISH, "product2", 1));
        products.add(String.format(Locale.ENGLISH, "product3", 2));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);

        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

        productTextView = findViewById(R.id.textview);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(productTextViewValueContent)) {
                String content = savedInstanceState.getString(productTextViewValueContent);
                productTextView.setText(content);
            }
        }

        // show the selected item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = (String) listView.getItemAtPosition(position);
                productTextView.setText(clickedItem);
            }
        });

        textViewMessage = (TextView) findViewById(R.id.textview_message);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(productTextViewValueContent, productTextView.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item3) {
            openActivity2();
        } else if (item.getItemId() == R.id.item2) {
            Toast.makeText(this, "Hi! My First Android Application :)", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.item1) {
            openDialog();
        }
        return true;
    }

    public void openActivity2() {
        TextView textview_message1 = (TextView) findViewById(R.id.textview_message);
        String text = textview_message1.getText().toString();

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
    }

    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String message) {
        textViewMessage.setText(message);
    }
}