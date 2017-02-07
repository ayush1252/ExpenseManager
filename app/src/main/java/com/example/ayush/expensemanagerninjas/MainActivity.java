package com.example.ayush.expensemanagerninjas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView androidGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] gridViewString = {
                "Utilities", "Food", "Travel", "Miscellaneous"

        };


        int[] gridViewImageId = {
                R.drawable.ic_milk, R.drawable.ic_beer, R.drawable.ic_taxi, R.drawable.ic_milk
        };

        CustomGridView adapterViewAndroid = new CustomGridView(MainActivity.this, gridViewString, gridViewImageId);
        androidGridView = (GridView) findViewById(R.id.gridview);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
               // Toast.makeText(MainActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
                Intent ie= new Intent(MainActivity.this,ExpenseClass.class);
                ie.putExtra("ClassName",gridViewString[+i].toString());
                startActivity(ie);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.viewall)
            Toast.makeText(this, "Select all", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Total Value", Toast.LENGTH_SHORT).show();

        return  true;
    }
}
