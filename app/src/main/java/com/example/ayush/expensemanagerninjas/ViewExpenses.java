package com.example.ayush.expensemanagerninjas;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ViewExpenses extends AppCompatActivity {


    final String[] gridViewString = {
            "Utilities", "Food", "Travel", "Miscellaneous"

    };
    ArrayList<String> allnames = new ArrayList<String>();
    ArrayList<String> alldates = new ArrayList<String>();
    ArrayList<String> allquantity = new ArrayList<String>();
    ArrayList<String> allprice = new ArrayList<String>();
    ArrayList<ListObject> arrayList = new ArrayList<ListObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        for (int i = 0; i < gridViewString.length; i++) {
            String str = gridViewString[i];
            SharedPreferences preferences = getSharedPreferences(str, MODE_PRIVATE);


            String name = preferences.getString("Name", "");
            String date = preferences.getString("Date", "");
            String quantity = preferences.getString("Quantity", "");
            String price = preferences.getString("Price", "");


            for (int j = 0; j < name.length(); ) {
                int k = j;
                while (k < name.length() && name.charAt(k) != '\n')
                    k++;
                allnames.add(name.substring(j, k));
                j = k + 1;

            }
            for (int j = 0; j < date.length(); ) {
                int k = j;
                while (k < date.length() && date.charAt(k) != '\n')
                    k++;
                alldates.add(date.substring(j, k));
                j = k + 1;

            }
            for (int j = 0; j < quantity.length(); ) {
                int k = j;
                while (k < quantity.length() && quantity.charAt(k) != '\n')
                    k++;
                allquantity.add(quantity.substring(j, k));
                j = k + 1;

            }
            for (int j = 0; j < price.length(); ) {
                int k = j;
                while (k < price.length() && price.charAt(k) != '\n')
                    k++;
                allprice.add(price.substring(j, k));
                j = k + 1;

            }

            for(int itr=0; itr<alldates.size(); itr++)
            {
                Log.d("TAG","RUN");
                arrayList.add(new ListObject(ViewExpenses.this,allnames.get(itr),gridViewString[i],allquantity.get(itr),allprice.get(itr),alldates.get(itr)));
            }
            alldates.clear();
            allprice.clear();
            allquantity.clear();
            allnames.clear();

        }


        //Sorting the array list on the basis of date

        try {
            Collections.sort(arrayList);
        } catch (Exception e)
        {
            Toast.makeText(this, "Couldnt sort", Toast.LENGTH_SHORT).show();
        }

        /////////////////DEBUG
        try {
           for(int itr=0; itr<arrayList.size(); itr++)
            Log.d("TAG",arrayList.get(itr).print());

        }
        catch (Exception e)
        {
            Log.d("TAG","THIS was null I guess");

        }
        Log.d("TAG",String.valueOf(allnames.size()));
        Log.d("TAG",String.valueOf(arrayList.size()));
        Log.d("TAG",String.valueOf(alldates.size()));
        Log.d("TAG",String.valueOf(allquantity.size()));
        Log.d("TAG",String.valueOf(allprice.size()));
        /////////////////////////

        ListView listView= (ListView) findViewById(R.id.list);
        listView.setAdapter(new ListAdapter(this,arrayList));

    }

}