package com.example.m_projet_gare_de_train;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView ls; // Déclaration d'une ListView
    HashMap<String, String> map; // Déclaration d'un HashMap
    ArrayList<HashMap<String, String>> values; // Déclaration de la variable values
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ls = findViewById(R.id.lst); // Récupération de la ListView dans la vue

        values = new ArrayList<>(); // Initialisation d'une ArrayList pour stocker les données

        // Crer l'objet map
        map = new HashMap<String, String>();
        //map.put(KEY, VALUE);
        map.put("titre", "Tunis");
        map.put("description", "Gares");
        map.put("NoAdr", "Barcelone centre-ville");
        map.put("img", String.valueOf(R.drawable.tunis));
        values.add(map);

        map = new HashMap<String, String>();
        map.put("titre", "Nabeul");
        map.put("description", "Gares");
        map.put("NoAdr", "Nabeul centre-ville");
        map.put("img", String.valueOf(R.drawable.nabeul));
        values.add(map);

        map = new HashMap<String, String>();
        map.put("titre", "Sousse");
        map.put("description", "Gares");
        map.put("NoAdr", "Bab jdid centre-ville");
        map.put("img", String.valueOf(R.drawable.sousse_bab_jedid));
        values.add(map);

        map = new HashMap<String, String>();
        map.put("titre", "Monastir");
        map.put("description", "Gares");
        map.put("NoAdr", "Monastir centre-ville");
        map.put("img", String.valueOf(R.drawable.monastir));
        values.add(map);

        map = new HashMap<String, String>();
        map.put("titre", "Mahdia");
        map.put("description", "Gares");
        map.put("NoAdr", "Mahdia centre-ville");
        map.put("img", String.valueOf(R.drawable.mahdia));
        values.add(map);

        map = new HashMap<String, String>();
        map.put("titre", "Sfax");
        map.put("description", "Gares");
        map.put("NoAdr", "Sfax centre-ville");
        map.put("img", String.valueOf(R.drawable.sfax));
        values.add(map);


        // Créer un nouvel adaptateur SimpleAdapter avec les données 'values' et l'associer à la vue ListView 'ls'
        SimpleAdapter a = new SimpleAdapter(MainActivity.this, values, R.layout.item,
                new String[]{"titre", "description", "img"},
                new int[]{R.id.nom, R.id.des, R.id.img});
        ls.setAdapter(a);

        // Définir un écouteur d'événements pour la sélection d'un élément dans la ListView 'ls'
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer les détails spécifiques de la gare sélectionnée à partir de la liste values(ArrayList)
                // les stocker dans la variable gare, ce qui nous permettra de les utiliser ultérieurement
                HashMap<String, String> gare = values.get(position);

                // Créer une intention pour démarrer l'activité GareActivity
                Intent i = new Intent(MainActivity.this, GareActivity.class);
                i.putExtra("nom", gare.get("titre"));
                i.putExtra("adresse", gare.get("NoAdr"));

                // Démarrer l'activité GareActivity
                startActivity(i);
            }

        });


        // Question 4 "Long Click Listener"
        ls.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                return false;
            }
        });

        registerForContextMenu(ls);

        ls.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0, 1, 0, "SNCFT");
                menu.add(0, 2, 0, "Informations de la gare");
            }
        });
    } // Fin On create


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()){
            case 1 :
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sncft.com.tn/"));
                startActivity(browserIntent);
                break;

            case 2 :
                HashMap<String, String> gare = values.get(position);

                Intent gareIntent = new Intent(MainActivity.this, GareActivity.class);
                gareIntent.putExtra("nom", gare.get("titre"));
                gareIntent.putExtra("adresse", gare.get("NoAdr"));
                startActivity(gareIntent);
                return true;
        }
        return super.onContextItemSelected(item);
    }


}