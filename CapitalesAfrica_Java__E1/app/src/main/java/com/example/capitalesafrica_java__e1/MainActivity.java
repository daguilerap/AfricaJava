package com.example.capitalesafrica_java__e1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Data.CapAfri;

@RequiresApi(api = Build.VERSION_CODES.R)
public class MainActivity extends AppCompatActivity {
    HashMap<String,String> mapa = new HashMap();
    HashMap<String,String> mapa2 = new HashMap();
    Conexion cone = new Conexion(this);
    RadioButton resp1, resp2, resp3, resp4;
    ArrayList <RadioButton> rd = new ArrayList<>();
    String definitiva="";

    int i=0;
    boolean puesta =false;

    int puntuacion=0;
    String seleccionado="";
    int vuelta=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapa.put("Angola", "Luanda");
        mapa.put("Argelia", "Argel");
        mapa.put("Benín", "Porto Novo");
        mapa.put("Botsuana", "Gaborone");
        mapa.put("Burkina Faso", "Uagadugú");
        mapa.put("Burundi", "Bujumbura");
        mapa.put("Cabo Verde", "Praia");
        mapa.put("Camerún", "Yaundé");
        mapa.put("Chad", "Yamena");
        mapa.put("Comoras", "Moroni");
        mapa2 = cone.GetUsers();


        comenzar();
        Button boton = (Button)findViewById(R.id.button);
        boton.setVisibility(View.GONE);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarDatos();
            }
        });
    }

    public void insertarDatos(){
        cone.insertar(mapa);
        //cone.DeleteUser();

    }

    public void comenzar() {

        cone.Conexion();
        ArrayList<String> respuestas = new ArrayList<>();
        for (Map.Entry<String, String> entry : mapa2.entrySet()) {
            respuestas.add(entry.getValue());
            System.out.println(entry.getValue());
        }

        resp1 = (RadioButton) findViewById(R.id.respuesta1);
        resp2 = (RadioButton) findViewById(R.id.respuesta2);
        resp3 = (RadioButton) findViewById(R.id.respuesta3);
        resp4 = (RadioButton) findViewById(R.id.respuesta4);

        rd.add(resp1);
        rd.add(resp2);
        rd.add(resp3);
        rd.add(resp4);


        RadioGroup rdgp = (RadioGroup) findViewById(R.id.rdgp);
        resp1.setVisibility(View.GONE);
        resp2.setVisibility(View.GONE);
        resp3.setVisibility(View.GONE);
        resp4.setVisibility(View.GONE);
        rdgp.clearCheck();





        Button btn_otropais = (Button)findViewById(R.id.btn_otropais);
        btn_otropais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puesta=false;
                TextView enunbot = (TextView)findViewById(R.id.enunbot);
                TextView paises = (TextView)findViewById(R.id.paises);
                enunbot.setText("Mostrar otro País");
                resp1.setVisibility(View.VISIBLE);
                resp2.setVisibility(View.VISIBLE);
                resp3.setVisibility(View.VISIBLE);
                resp4.setVisibility(View.VISIBLE);


                if (resp1.isChecked()){
                    seleccionado= (String) resp1.getText();
                    if(seleccionado.equals(definitiva)){
                        Toast.makeText(getApplicationContext(),"CORRECTO",
                                Toast.LENGTH_SHORT).show();
                        puntuacion++;

                        rdgp.clearCheck();
                    }
                    rdgp.clearCheck();
                }

                if (resp2.isChecked()==true){
                    seleccionado= (String) resp2.getText();
                    if(seleccionado.equals(definitiva)){
                        Toast.makeText(getApplicationContext(),"CORRECTO",
                                Toast.LENGTH_SHORT).show();
                        puntuacion++;
                        rdgp.clearCheck();
                    }
                    rdgp.clearCheck();
                }

                if (resp3.isChecked()==true){
                    seleccionado= (String) resp3.getText();
                    if(seleccionado.equals(definitiva)){
                        Toast.makeText(getApplicationContext(),"CORRECTO",
                                Toast.LENGTH_SHORT).show();
                        puntuacion++;
                        rdgp.clearCheck();
                    }
                    rdgp.clearCheck();
                }

                if (resp4.isChecked()==true){
                    seleccionado= (String) resp4.getText();
                    if(seleccionado.equals(definitiva)){
                        Toast.makeText(getApplicationContext(),"CORRECTO",
                                Toast.LENGTH_SHORT).show();
                        puntuacion++;
                        rdgp.clearCheck();
                    }
                    rdgp.clearCheck();
                }

                Random random = new Random();
                if (mapa2.size()>0) {
                List<String> Keys = new ArrayList<String>(mapa2.keySet());
                String randomepais = Keys.get(random.nextInt(Keys.size()));
                String respuestacorrecta = mapa2.get(randomepais);
                if(definitiva!=null){
                    definitiva=respuestacorrecta;
                }


                paises.setText(randomepais);


                Collections.shuffle(respuestas);

                for (int i=0;i<rd.size();i++){
                    rd.get(i).setText(respuestas.get(i));
                }
                for (int i = 0; i <rd.size() ; i++) {
                    if (rd.get(i).getText().equals(respuestacorrecta)){
                        puesta=true;
                    }
                }

                if(puesta==false){
                    rd.get(random.nextInt(3)).setText(respuestacorrecta);

                }



                    mapa2.remove(randomepais);
                }else {

                    Toast.makeText(getApplicationContext(),"Has acertado " +puntuacion + " has fallado " +(mapa.size()-puntuacion)+ " de un total de " +mapa.size(),
                            Toast.LENGTH_SHORT).show();
                    Intent i =  new Intent(MainActivity.this,Activity2.class);
                    i.putExtra("aciertos", puntuacion);
                    i.putExtra("tamaño", mapa.size());
                    startActivity(i);

                }




            }
        });



    }
}