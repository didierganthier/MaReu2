package com.example.myrecyclerview2;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Mes_ReunionAdapter.OnEditListener {

     RecyclerView mRecyclerView;;
     Mes_ReunionAdapter monAdapter;

    ArrayList<Liste_Reunion> mesReunionArrayList;
    private EditText nameReunion, liste_emailReunion, sujetReunion, salleReunion, heureReunion;
    private Button add;
    ImageView buttonAddFloat;
    int t1Hour, t1Minute;


    AlertDialog alertDialog;

    ImageView mimage_Delete;
    AlertDialog.Builder builder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.maListe);

        mesReunionArrayList = new ArrayList<>();

        liste_emailReunion = findViewById(R.id.liste_emailReunion);
        nameReunion = findViewById(R.id.nameReunion);
        sujetReunion = findViewById(R.id.AjoutReunion_editText_sujet);
        salleReunion = findViewById(R.id.AjoutReunion_editText_salle);
        heureReunion = findViewById(R.id.AjoutReunion_timeText_hour);
        buttonAddFloat = findViewById(R.id.newAdd);



        mimage_Delete =findViewById(R.id.image_Delete);








        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        buttonAddFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_ajout_reunion, null);
                EditText nameReunion = (EditText) mView.findViewById(R.id.nameReunion);
                EditText     liste_emailReunion = (EditText) mView.findViewById(R.id.liste_emailReunion);
                EditText   sujetReunion = (EditText) mView.findViewById(R.id.AjoutReunion_editText_sujet);
                Button add = (Button) mView.findViewById(R.id.AjoutReunion_button_ajouterNouvelleReunion);





                EditText heureReunion = (EditText) mView.findViewById(R.id.AjoutReunion_timeText_hour);
                heureReunion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0,t1Hour,t1Minute);
                                heureReunion.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12,0,false);

                    }
                });

                Spinner  salleReunion = (Spinner) mView.findViewById(R.id.spinner_salle);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.LesSalles));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                salleReunion.setAdapter(adapter);





                add.setOnClickListener(view1 -> {
                    String strNameReunion="",strListe_emailReunion="" , strHeureReunion = "";
                    if (nameReunion.getText()!= null){
                        strNameReunion= nameReunion.getText().toString();
                    }
                    if (strNameReunion.equals("")){
                        Toast.makeText(MainActivity.this,"Inserer le numero de la Reunion/Exemple: Reunion 1",Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (liste_emailReunion.getText()!= null){
                        strListe_emailReunion= liste_emailReunion.getText().toString();
                    }
                    if (strListe_emailReunion.equals("")){
                        Toast.makeText(MainActivity.this,"Inserer les emails des participants en les separant par des virgules.",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (heureReunion.getText()!= null){
                        strHeureReunion= heureReunion.getText().toString();
                    }
                    if (strHeureReunion.equals("")){
                        Toast.makeText(MainActivity.this,"Inserer le numero de la Reunion/Exemple: Reunion A",Toast.LENGTH_LONG).show();
                        return;
                    }
                    ajouter(strNameReunion,strListe_emailReunion,strHeureReunion);

                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }

        });











    }

    public void ajouter(String strNameReunion, String strListe_emailReunion, String strheureReunion){
     Liste_Reunion obj = new Liste_Reunion();
     obj.setNameReunion(strNameReunion);
     obj.setListe_emailReunion(strListe_emailReunion);
     obj.setHeureReunion(strheureReunion);


     mesReunionArrayList.add(obj);

     monAdapter = new Mes_ReunionAdapter(this, mesReunionArrayList, this::onEditClick);
     mRecyclerView.setAdapter(monAdapter);



}



    @Override
    public void onEditClick(Liste_Reunion listCurrentData, int currentPosition) {
        AlertDialog.Builder builderObj = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_image_edit, null);
        builderObj.setCancelable(false);
        builderObj.setView(view);

        EditText nameReunion2 = view.findViewById(R.id.nameReunion);
        EditText liste_emailReunion2 = view.findViewById(R.id.liste_emailReunion);
        Button button_edit = view.findViewById(R.id.button_edit);

        nameReunion2.setText(listCurrentData.getNameReunion());
        liste_emailReunion2.setText(listCurrentData.getListe_emailReunion());

        ImageView cancelAlD =view.findViewById(R.id.image_cancel);

        alertDialog = builderObj.create();
        alertDialog.show();



        button_edit.setOnClickListener(view1 -> {
            String strNameReunion="",strListe_emailReunion="";
            if (nameReunion2.getText()!= null){
                strNameReunion= nameReunion2.getText().toString();
            }
            if (strNameReunion.equals("")){
                Toast.makeText(this,"Inserer le numero de la Reunion/Exemple: Reunion 1",Toast.LENGTH_LONG).show();
                return;
            }

            if (liste_emailReunion2.getText()!= null){
                strListe_emailReunion= liste_emailReunion2.getText().toString();
            }
            if (strListe_emailReunion.equals("")){
                Toast.makeText(this,"Inserer les emails des participants en les separant par des virgules.",Toast.LENGTH_LONG).show();
                return;
            }
            modifier(strNameReunion,strListe_emailReunion, currentPosition);
        });




        cancelAlD.setOnClickListener(view1 -> {
            alertDialog.cancel();
        });
    }

public void modifier(String strNameReunion, String strListe_emailReunion, int currentPosition){
    Liste_Reunion obj = new Liste_Reunion();
    obj.setNameReunion(strNameReunion);
    obj.setListe_emailReunion(strListe_emailReunion);


    monAdapter.editData(obj,currentPosition);
    alertDialog.cancel();
}



}
