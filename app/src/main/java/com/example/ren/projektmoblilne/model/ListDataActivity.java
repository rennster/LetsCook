package com.example.ren.projektmoblilne.model;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ren.projektmoblilne.R;
import com.example.ren.projektmoblilne.SQLite.DatabaseHelper;
import com.example.ren.projektmoblilne.recepti.PrikazRecepta;
import com.example.ren.projektmoblilne.recepti.Recept;

import java.util.ArrayList;

/**
 * Created by ren on 22-May-17.
 */

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    String daniTjedan[] = new String[]{
            "Ponedjeljak",
            "Utorak",
            "Srijeda",
            "Četvrtak",
            "Petak",
            "Subota",
            "Nedjelja"
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);




        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        Cursor data = mDatabaseHelper.getData();
        ArrayList<Recept> rcp = new ArrayList<Recept>();
        ArrayList<String> listData = new ArrayList<>();
        ArrayList<String> recUrl = new ArrayList<>();
        String web[] = new String[7];
        String imageId[] = new String[7];
        int br = 0;
        while(data.moveToNext()){
            String one = data.getString(1);
            String two = data.getString(2);
            String three = data.getString(3);
            String four  = data.getString(4);
            Recept rec = new Recept(one, two, three, four);
            rcp.add(rec);

            web[br] = two;
            imageId[br] = three;
            br ++;

            listData.add(data.getString(1));
            recUrl.add(data.getString(4));

        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);








        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = mDatabaseHelper.getItemID(name);
                Cursor url = mDatabaseHelper.getItemURL(name);
                Cursor pic = mDatabaseHelper.getItemPic(name);
                int itemID = -1;
                String urlRec = "";
                String picture = "";


                while(pic.moveToNext()){
                    picture = pic.getString(0);
                }

                while(url.moveToNext()){
                    urlRec = url.getString(0);
                }
                while(data.moveToNext()){
                    itemID = data.getInt(0);

                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);

                    Intent otvoriRecept = new Intent(ListDataActivity.this, PrikazRecepta.class);
                    otvoriRecept.putExtra("url",urlRec);
                    otvoriRecept.putExtra("pic",picture);
                    otvoriRecept.putExtra("naslov",name);
                    startActivity(otvoriRecept);


                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}