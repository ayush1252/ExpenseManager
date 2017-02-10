package com.example.ayush.expensemanagerninjas;

import android.content.Context;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by ayush on 9/2/17.
 */

public class ListObject implements Comparable<ListObject> {

    String Name;
    String Type;
    String Quantity;
    String  Price;
    public String Datestr;
    Date Dateobj;

    public ListObject(Context context, String name, String type, String quantity, String price, String datestr) {
        Name = name;
        Type = type;
        Quantity = quantity;
        Price = price;
        Datestr = datestr;

        DateFormat dateFormat=new SimpleDateFormat("dd/mm/yyyy");
        try {
            Dateobj=dateFormat.parse(Datestr);
        } catch (ParseException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getPrice() {
        return Price;
    }

    public Date getDateobj() {
        return Dateobj;
    }

    public String print()
    {
        return getName()+getType()+getQuantity()+getPrice()+getDateobj();
    }


    @Override
    public int compareTo(ListObject o) {
        return getDateobj().compareTo(o.getDateobj());
    }
}
