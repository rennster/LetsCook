package com.example.ren.projektmoblilne.recepti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ren.projektmoblilne.MainActivity;
import com.example.ren.projektmoblilne.R;
import com.example.ren.projektmoblilne.SQLite.DatabaseHelper;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ren on 24-May-17.
 */

public class PrikazRecepta extends AppCompatActivity {
    private ImageView hrana;
    private TextView recept;
    private TextView t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prikaz_recepta);

        t2= (TextView) findViewById(R.id.text2);

        hrana = (ImageView)findViewById(R.id.slikaHrane);
        recept = (TextView)findViewById(R.id.tekstRecepta);
        recept.setMovementMethod(new ScrollingMovementMethod());

        Intent prethodniIntent = getIntent();
        Bundle b = prethodniIntent.getExtras();
        String pic = "";
        String naslov = (String)b.get("naslov");
        final String rec2 = (String)b.get("url");

        setTitle(naslov);

        if (b != null){
            String rec = (String)b.get("url");
            pic = (String)b.get("pic");

            getRecept(rec);
        }

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(rec2));
                startActivity(browser);
            }
        });


        Picasso.with(this).load(pic).placeholder(R.drawable.foods).fit().into(hrana);


    }

    private void getRecept(final String urlRecepta) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //final List<Recept> recepti = new ArrayList<Recept>();
                final StringBuilder tekst = new StringBuilder();

                try {
                    Document glavnaJela = Jsoup.connect(urlRecepta).get();

                    Elements naslovi = glavnaJela.select("div[class=recipe_step_description]");
                    Elements sastojci = glavnaJela.select("div[class=ingredient_list_wrap cf]");
                    Elements sastojci2 = glavnaJela.select("li[class=pt_l ingredient_item]");
                    Elements sastojciP = glavnaJela.select("span[class=fw_normal]");
                    tekst.append("SASTOJCI: \n");



                    for (Element el: sastojci){
                        //Element li = el.select("li[class=pt_l ingredient_item]").select("*");
                        Element li = el.select("*").first();
                        tekst.append(li.text()).append("\n\n\n");
                        //Element span = el.select("span[class=fw_normal]").first();
                        //tekst.append("SASTOJCI: \n").append(li.text()).append("\n");
                    }

                    int testZaTekst = 0;
                    for(Element b: naslovi){
                        if(b.hasText()){
                            tekst.append(b.text()).append("\n\n");
                            testZaTekst = 1;
                        }
                    }

                    for (Element u : naslovi) {
                        if (u.hasText() && testZaTekst == 0) {
                            Element test = u.select("p").first();
                            tekst.append(test.text()).append("\n\n");
                        }

                    }


                } catch (Exception e) {
                    //tekst.append("Error: ").append(e.getMessage()).append("\n");
                    tekst.append("\n Ispod slike na vrhu, nalazi se link kojim recept možete otvoriti na stranicama Coolinarike.");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            recept.setText(tekst.toString());
                        }catch (Exception e){
                            toastMessage("Oops, izgleda da nešto ne valja!");
                        }
                    }

                });

            }
        }).start();

    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }




}
