package com.example.ayush.expensemanagerninjas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayush on 10/2/17.
 */

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<ListObject> arrayList;
    private static LayoutInflater inflater=null;

    public ListAdapter(Context context, ArrayList<ListObject> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Holder
    {
        TextView name;
        TextView date;
        TextView quantity;
        TextView price;
        ImageView img;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();
        View rowview;
        rowview= inflater.inflate(R.layout.list_item,null);
        holder.date= (TextView) rowview.findViewById(R.id.ExpenseDate);
        holder.img= (ImageView) rowview.findViewById(R.id.ListItemImage);
        holder.name= (TextView) rowview.findViewById(R.id.ExpenseName);
        holder.quantity= (TextView) rowview.findViewById(R.id.ExpenseQuantity);
        holder.price= (TextView) rowview.findViewById(R.id.ExpensePrice);


        holder.name.setText(arrayList.get(position).getName());
        holder.quantity.setText(arrayList.get(position).getQuantity());
        holder.price.setText(arrayList.get(position).getPrice());

            if(arrayList.get(position).getType().equals("Utilities"))
        {
            holder.img.setImageResource(R.drawable.ic_beer);
        }
        if(arrayList.get(position).getType().equals("Food"))
            holder.img.setImageResource(R.drawable.ic_milk);
        if(arrayList.get(position).getType().equals("Miscellaneous"))
            holder.img.setImageResource(R.drawable.ic_beer);
        if(arrayList.get(position).getType().equals("Travel"))
            holder.img.setImageResource(R.drawable.ic_taxi);


        try {

            holder.date.setText(arrayList.get(position).Datestr);
        }
        catch (Exception e)
        {
            Log.d("TAG",e.toString());
            holder.date.setText("22/11/1997");
        }
        return rowview;

    }
}
