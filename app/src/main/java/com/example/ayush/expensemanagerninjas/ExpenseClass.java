package com.example.ayush.expensemanagerninjas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class ExpenseClass extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
TextView textView;
    EditText date;
    EditText itemname;
    EditText itemqty;
    EditText itemprice;
    Button submit;
    String name,dat,qty,price;
    Integer quantity , pricee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_class);

        //Setting the Name of the Expense that we will be adding
        Intent i=getIntent();
        final String str=i.getStringExtra("ClassName");
        textView= (TextView) findViewById(R.id.Activityid);
        if(str.length()!=0)
            textView.setText(str);

        date= (EditText) findViewById(R.id.Date);
        itemname= (EditText) findViewById(R.id.Itemname);
        itemqty= (EditText) findViewById(R.id.quantity);
        itemprice= (EditText) findViewById(R.id.price);
        submit= (Button) findViewById(R.id.submit);

        //Calender Dialog
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    return;
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        (DatePickerDialog.OnDateSetListener) ExpenseClass.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
                dpd.setVersion(DatePickerDialog.Version.VERSION_1);
                dpd.show(getFragmentManager(), "Datepickerdialog");



            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date.findFocus();
            }
        });

            /*   Button Click on Submit
                 Checks if the field are filled correctly or not
            */

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ExpenseClass.this, "A Toast was made", Toast.LENGTH_SHORT).show();
                name = itemname.getText().toString();
                if(name.length()==0) {
                    itemname.setError("This Cannot be Empty");
                    return;
                }

                 dat=date.getText().toString();
                if(dat.length()==0) {
                    date.setError("This Cannot be Empty");
                    return;
                }

                 qty=itemqty.getText().toString();
                if(qty.length()==0) {
                    itemqty.setError("This Cannot be Empty");
                    return;
                }
                 quantity=Integer.valueOf(qty);

                 price=itemprice.getText().toString();
                if(price.length()==0) {
                    itemprice.setError("This Cannot be Empty");
                    return;
                }
                 pricee=Integer.valueOf(price);

                //Adding the values to Shared Prefrences
                SharedPreferences preferences=getSharedPreferences(str,MODE_APPEND);
                SharedPreferences.Editor editor=preferences.edit();

                String val= preferences.getString("Name","");
                val=val+name+"\n";
                editor.putString("Name",val);

                 val= preferences.getString("Date","");
                val=val+dat+"\n";
                editor.putString("Date",val);

                 val= preferences.getString("Quantity","");
                val=val+qty+"\n";
                editor.putString("Quantity",val);

                 val= preferences.getString("Price","");
                val=val+price+"\n";
                editor.putString("Price",val);

                editor.commit();
                Toast.makeText(ExpenseClass.this, preferences.getString("Date",""), Toast.LENGTH_SHORT).show();

                //Making a list out of all Strings-Same can be done for the rest of the columns
                String itr=preferences.getString("Date","");
                ArrayList<String>alldates=new ArrayList<String>();
                for(int i=0; i<itr.length();)
                {
                    int j=i;
                    while(j<itr.length()&&itr.charAt(j)!='\n')
                        j++;
                    alldates.add(itr.substring(i,j));
                    i=j+1;

                }

                //Log.d("lala", String.valueOf(Integer.valueOf(alldates.size())));
                for(int j=0; j<alldates.size(); j++)
                {
                    Log.d("lala",alldates.get(j));
                }
               // Toast.makeText(ExpenseClass.this, String.valueOf(quantity+pricee), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String time = dayOfMonth+"/"+monthOfYear+"/"+year;
        date.setText(time);
    }
}
