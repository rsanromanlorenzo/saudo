package com.example.robert.interaccion_elementos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ROBERT on 29/10/2014.
 */
public class Salutation extends Activity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.salutation);
        //recibo el intent con el par key/value establecido en MyActivity
        //utilizo el metodo getExtras()
        String saludo = getIntent().getExtras().getString("salutation");
        TextView vista = (TextView) findViewById(R.id.SalutationtextView);
        vista.setText(saludo);

        //SI LO HE HECHO CON EL BUNDLE=>lo recibiria de esta manera
        /*
        //Creamos un bundle para recibir la info del intent que obtengamos con un set
        Bundle bundle = getIntent().getExtras();
        String saludo=bundle.getString("salutation");
                TextView vista=(TextView)findViewById(R.id.SalutationtextView);
        vista.setText(saludo);
         */


    }

}

