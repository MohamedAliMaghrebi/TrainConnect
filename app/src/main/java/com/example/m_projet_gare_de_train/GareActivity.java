package com.example.m_projet_gare_de_train;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class GareActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;

    TextView AdresseRec;
    TextView NomRec;

    @Override
    //bundle conteneur de l'intent
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gare);

        NomRec = findViewById(R.id.textViewNom);
        AdresseRec = findViewById(R.id.textViewAdresse);

        // Récupérer les données de l'intent
        Bundle extras = getIntent().getExtras();
        String nomGare = extras.getString("nom");
        String adresseGare = extras.getString("adresse");

        // Afficher les données dans les TextViews
        NomRec.setText(nomGare);
        AdresseRec.setText(adresseGare);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Quit");
        menu.add(0, 2, 0, "CALL SNCFT");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                finish();
                break;
            case 2:
                // Vérifie d'abord si la permission CALL_PHONE a été accordée à l'application en utilisant ContextCompat.checkSelfPermission()
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //PERMISSION_GRANTED est une constante statique de la classe PackageManager
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_PERMISSION);
                } else {
                    // Si la permission est déjà accordée, passer l'appel téléphonique
                    performCall();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PHONE_PERMISSION) {
            //tableau grantResults & le premier résultat est égal à PackageManager.PERMISSION_GRANTED
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // La permission a été accordée, passer l'appel téléphonique
                performCall();
            } else {
                // La permission a été refusée, afficher un message d'erreur ou prendre une autre action appropriée
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void performCall() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:71334444"));
        startActivity(intent);
    }
}
