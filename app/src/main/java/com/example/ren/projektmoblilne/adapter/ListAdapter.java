package com.example.ren.projektmoblilne.adapter;

/**
 * Created by ren on 24-May-17.
 *
 *
 *
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ren.projektmoblilne.R;
import com.example.ren.projektmoblilne.recepti.Recept;
import com.squareup.picasso.Picasso;

import java.util.List;
  /*
public class ListAdapter extends ArrayAdapter<Recept>{

    Context context;
    int layoutResId;

    Recept data[] = null;
    String days[] = new String[]{
            "Ponedjeljak",
            "Utorak",
            "Srijeda",
            "Četvrtak",
            "Petak",
            "Subota",
            "Nedjelja"




    public ListAdapter(Context context, int layoutResId, Recept[] data) {
        super(context, layoutResId, data);
        this.layoutResId = layoutResId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoryHolder holder = null;

        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResId, parent, false);

            holder = new HistoryHolder();
            holder.imageIcon = (ImageView)convertView.findViewById(R.id.icon);
            holder.textTitle = (TextView)convertView.findViewById(R.id.gameType);
            holder.textScore = (TextView)convertView.findViewById(R.id.score);

            convertView.setTag(holder);
        }
        else
        {
            holder = (HistoryHolder)convertView.getTag();
        }

        Recept recept = data[position];
        String day = days[position];
        holder.textScore.setText(day.toString());
        holder.textTitle.setText(recept.getTitle());
        Picasso.with(this.context).load(recept.getPicUrl()).into(holder.imageIcon);
        //holder.imageIcon.setImageResource(recept.getPicUrl());


        return convertView;
    }

    static class HistoryHolder
    {
        ImageView imageIcon;
        TextView textTitle;
        TextView textScore;
    }
}



public class ListAdapter extends ArrayAdapter<Recept> {

    int resource;
    String response;
    Context context;

    public ListAdapter(Context context, int resource, List<Recept> items) {
        super(context, resource, items);
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout contactsView;
        Recept contact = getItem(position);
        if(convertView==null) {
            contactsView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, contactsView, true);
        } else {
            contactsView = (LinearLayout) convertView;
        }
        TextView contactName =(TextView)contactsView.findViewById(R.id.txt);
        contactName.setTypeface(null, Typeface.BOLD);
        contactName.setTextSize(TypedValue.COMPLEX_UNIT_PX,18);
        TextView contactPhone = (TextView)contactsView.findViewById(R.id.contactPhone);
        contactPhone.setTextSize(TypedValue.COMPLEX_UNIT_PX,14);
        contactName.setText(contact.getName());
        contactPhone.setText(contact.getPhone());
        return contactsView;
    }
}

*/


