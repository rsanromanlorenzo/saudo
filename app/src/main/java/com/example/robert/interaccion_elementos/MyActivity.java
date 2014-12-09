package com.example.robert.interaccion_elementos;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //orden para que me guarde el estado de la aplicación
        super.onCreate(savedInstanceState);
        //Indico cual es el layout al que haré referancia (en este caso activity_my)
        setContentView(R.layout.activity_my);

        //Creo un button y lo vinculo con el boton hello del XML
        Button button = (Button) findViewById(R.id.hello);

        //Creo un evento para el boton que acabo de declarar
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // código a ejecutar cuando sea pulsado hello
                EditText text = (EditText) findViewById(R.id.mensaje1);
                // comprobar si existe nombre
                if ("".equals(text.getText().toString().trim())) {
                    //Si el texto está en blanco=> me mostrará una alerta. Puedo hacerlo de 2 maneras:
                    //1.mostrar dialogo
                    //showAlert();
                    //2.mostrar toast
                    showToast();
                    return;
                }
                //recogo el texto (el nombre) que está en el editText
                String enteredName = text.getText().toString();

                //Creo un String Salutation que me recogerá lo seleccionado en el radioGroup
                String salutation = null;

                // referencia al radioButton
                //Los distintos RadioButton los agrupo en un RadioGroup
                RadioGroup radio = (RadioGroup) findViewById(R.id.RadioGroup01);
                //Evaluo si está marcado el boton rdsr. (si no estuviese marcado este,
                //tendría que estar marcado el otro: rdsra
                if (R.id.rdsr == radio.getCheckedRadioButtonId()) {
                    //para señor
                    salutation = getResources().getString(R.string.saludoSr).toLowerCase();
                } else {
                    salutation = getResources().getString(R.string.saludoSra).toLowerCase();
                }
                //concateno el sr/sra con el nombre recogido en el enteredName
                salutation = getResources().getString(R.string.hello) + " " + salutation + " " + enteredName;

                // obtención de la hora y fecha
                //Si está marcado este checkbox el timeCheckBox cogerá la info de fecha y hora y la agregará al mensaje salutation

                CheckBox timeCheckBox = (CheckBox) findViewById(R.id.checkBox);
                if (timeCheckBox.isChecked()) {
                    DatePicker date = (DatePicker) findViewById(R.id.datePicker);
                    String dateToShow = date.getDayOfMonth() + "/" + (date.getMonth() + 1) + "/" + date.getYear();
                    TimePicker time = (TimePicker) findViewById(R.id.timePicker);
                    //Recojo la info del dataPicker y del time picker
                    dateToShow += " " + time.getCurrentHour() + ":" + time.getCurrentMinute();

                    //La información de fecha y hora se la ñado al saludo
                    salutation += " " + dateToShow;
                }
                // Salida del texto
                //Creo un intent para llamar a una nueva activity
                Intent intent = new Intent(MyActivity.this, Salutation.class);
                //Añado un par key/value (como un hashmap) para pasar info entre intents
                intent.putExtra("salutation", salutation);
                startActivity(intent);

                //OPCION B:utilizar un obxecto bundle para pasar info entre activities
                /*
                //Para pasar info entre las activitys utilizamos un objeto bundle
                Bundle bundle = new Bundle();

                //añadimos la info al objeto bundle => clave valor
                bundle.putString("salutation",salutation);

                //añadimos el objeto bundle a nuestro intent
                intent.putExtras(bundle);

                //iniciamos la actividad de nuestro intent
                startActivity(intent);
                */


            }
        });
        // Mostrar o no las fechas
        //Tengo un checkBox en el cual decido si muestro o no las fechas
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int visibility = isChecked ? View.VISIBLE : View.GONE;
                View view = findViewById(R.id.timePicker);
                view.setVisibility(visibility);
                view = findViewById(R.id.datePicker);
                view.setVisibility(visibility);
            }
        });


    }


    protected void showAlert() {
        //Indico aquí el mensaje que quiero que se muestre cuando no se indica ningún nombre
        CharSequence text = getResources().getString(R.string.noNameMsg);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(text);
        alert.setPositiveButton(android.R.string.ok, null);
        alert.show();
    }

    protected void showToast() {
        //El toast necesita guardar el contexto actual de la aplicacion. Tambien valdria con poner this.
        Context context = getApplicationContext();
        //indicamos el mensaje que se mostrará (guardado en un recurso string)
        CharSequence text = getResources().getString(R.string.noNameMsg);
        //indicamos la duracion del Toast
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}





