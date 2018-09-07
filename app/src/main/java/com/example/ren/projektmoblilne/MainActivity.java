package com.example.ren.projektmoblilne;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ren.projektmoblilne.SQLite.DatabaseHelper;
import com.example.ren.projektmoblilne.model.ListDataActivity;
import com.example.ren.projektmoblilne.recepti.PrikazRecepta;
import com.example.ren.projektmoblilne.recepti.Recept;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button getBtn;
    private TextView result;
    private ScrollView mScrollView;
    private Button layout;
    private Button btnGetData;


    //dodano iz ListActivity
    private static final String TAG = "MainActivity";
    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;
    //**


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHelper = new DatabaseHelper(this);
        getBtn = (Button) findViewById(R.id.getBtn);
        btnViewData = (Button) findViewById(R.id.btnView);

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWebsite();
            }
        });


        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }


    private void getWebsite() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Recept> recepti = new ArrayList<Recept>();
                final List<String>urlSlike = new ArrayList<String>();
                final StringBuilder builder = new StringBuilder();

                try {
                    //Document doc = Jsoup.connect("https://www.coolinarika.com/recepti/cool-faktor/").get();
                    Document glavnaJela = Jsoup.connect("https://www.coolinarika.com/recepti/grupe-jela/glavna-jela/?order_by=cool-faktor").get();
                    //Document glavnaJela2 = Jsoup.connect("https://www.coolinarika.com/recepti/grupe-jela/glavna-jela/?order_by=cool-faktor").get();
                    //Document glavnaJela3 = Jsoup.connect("https://www.coolinarika.com/recepti/grupe-jela/glavna-jela/?page=4").get();


                    Elements naslovi = glavnaJela.select("h3[class=h4 title]");

                    for (Element u: naslovi) {
                        Element test = u.select("a").first();
                        builder.append(test.attr("abs:href")).append("\n\n").append(u.select("span").text()).append("\n\n");
                        Element img = u.select("img").first();

                        String url = test.attr("abs:href");
                        String naslov = u.select("span").text();
                        String recept = null;
                        String picUrl = img.attr("data-src");
                        Recept noviRecept = new Recept(url, naslov, picUrl, recept);
                        urlSlike.add(picUrl);
                        recepti.add(noviRecept);
                    }


                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDatabaseHelper.deleteAll(mDatabaseHelper.getWritableDatabase());

                        List<Recept> odabraniRecepti = randomRecepti(recepti);
                        AddData(odabraniRecepti);

                        Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                        startActivity(intent);

                    }

                });

                }
            }).start();

    }

    public List<Recept> randomRecepti(List<Recept> dohvaceniRecepti){

        List<Recept> filtriraniRecepti = new LinkedList<Recept>(dohvaceniRecepti);
        Collections.shuffle(filtriraniRecepti);
        return filtriraniRecepti.subList(0, 7);

    }


    public void AddData(List<Recept> listaRecepta) {
        String item = "Recept";
        String item2 = "";
        String item3 = "";
        String item4= "";
        for (int i = 0; i < listaRecepta.size(); i++){
            item = listaRecepta.get(i).getTitle();
            item2 = "R";
            item3 = listaRecepta.get(i).getPicUrl();
            item4 = listaRecepta.get(i).getUrl();
            boolean insertData = mDatabaseHelper.addData(item, item2, item3, item4);
            int z = 0;
            if (insertData) {
                if (z < 1){
                toastMessage("Recepti su pohranjeni");
                z++;}
            } else {
                if (!insertData) {
                    toastMessage("Recepti se nisu uspjeli pohraniti");
                }
            }
        }

    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public void dohvatiRecept() {
        final StringBuilder builderRecept = new StringBuilder();
        try {
            Document glavnaJela = Jsoup.connect("https://www.coolinarika.com/recept/moje-marokansko-pile/").get();

            Elements naslovi = glavnaJela.select("div[class=recipe_step_description]");

            for (Element u : naslovi) {
                Element test = u.select("p").first();
                builderRecept.append(test.text()).append("\n\n");
                System.out.println(builderRecept.toString());
            }
        } catch (IOException e) {
            builderRecept.append("Error: ").append(e.getMessage()).append("\n");
        }

    }

    private void getRecept() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Recept> recepti = new ArrayList<Recept>();
                final StringBuilder tekst = new StringBuilder();
                final StringBuilder builder2 = new StringBuilder();
                try {
                    Document glavnaJela = Jsoup.connect("https://www.coolinarika.com/recept/moje-marokansko-pile/").get();

                    Elements naslovi = glavnaJela.select("div[class=recipe_step_description]");

                    for (Element u : naslovi) {
                        Element test = u.select("p").first();
                        tekst.append(test.text()).append("\n\n");
                        System.out.println(tekst.toString());
                    }


                } catch (IOException e) {
                    tekst.append("Error: ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(tekst.toString());
                        result.append(tekst.toString());

                        Intent intent = new Intent(MainActivity.this, PrikazRecepta.class);
                        intent.putExtra("recept", tekst.toString());
                        startActivity(intent);

                    }

                });

            }
        }).start();

    }

}
