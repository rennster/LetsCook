package com.example.ren.projektmoblilne;

/**
 * Created by ren on 22-May-17.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ren.projektmoblilne.SQLite.DatabaseHelper;
import com.example.ren.projektmoblilne.model.ListDataActivity;
import com.example.ren.projektmoblilne.recepti.Recept;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_activity);
        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);


        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }



    public void AddData(List<Recept> listaRecepta) {
        String item = "";
        String item2 = "";
        String item3 = "";
        String item4= "";
        for (int i = 0; i < listaRecepta.size(); i++){
            item = listaRecepta.get(i).getPicUrl();
            item2 = listaRecepta.get(i).getRecept();
            item3 = listaRecepta.get(i).getTitle();
            item4 = listaRecepta.get(i).getUrl();
        }

    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
