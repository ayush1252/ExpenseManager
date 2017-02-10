package com.example.ayush.expensemanagerninjas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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

    final String[] gridViewString = {
            "Utilities", "Food", "Travel", "Miscellaneous"

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




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
        {
            Intent intent=new Intent(MainActivity.this,ViewExpenses.class);
            startActivity(intent);
        }
        else
        {
            Integer totalval=0;
            AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("All Expenses ");
            builder.setCancelable(false);
            builder.setIcon(R.drawable.ic_beer);
            String[] value=new String[5];
            for(int i=0 ; i<gridViewString.length; i++) {
                String str=gridViewString[i];
                SharedPreferences preferences = getSharedPreferences(str, MODE_PRIVATE);
               totalval+= preferences.getInt("Total",0);
                value[i]=gridViewString[i]+"  :  "+String .valueOf(preferences.getInt("Total",0));
            }
            value[4]="Total Expenditure"+"  :  "+String .valueOf(totalval);
            builder.setItems(value, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
            Toast.makeText(this,String.valueOf(totalval), Toast.LENGTH_SHORT).show();

        }

        return  true;
    }
}
