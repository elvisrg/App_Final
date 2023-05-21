package AppFinal.elvis.app_final;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import AppFinal.elvis.app_final.adaptadores.ListaContactosAdapter;
import AppFinal.elvis.app_final.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreoElectronico, txtmascota, txtraza, txtfecha;
    Button btnsave;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtmascota = findViewById(R.id.txtmascota);
        txtraza = findViewById(R.id.txtraza);
        txtfecha = findViewById(R.id.txtfecha);
        btnsave = findViewById(R.id.btnGuarda);
        View parentLayout = findViewById(android.R.id.content);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateNull()) {
                    DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                    long id = dbContactos.insertaContacto(txtNombre.getText().toString(),
                            txtTelefono.getText().toString(),
                            txtCorreoElectronico.getText().toString(),
                            txtmascota.getText().toString(),
                            txtraza.getText().toString(),
                            txtfecha.getText().toString());

                    if (id > 0) {

                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                        lista();
                        finish();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(parentLayout, "Todos los campos son requeridos.", Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.purple_200))
                        .show();
                }
            }
        });
    }

    public boolean validateNull(){

        boolean result = true;
        if(txtNombre.getText().toString().matches("") || txtTelefono.getText().toString().matches("") ||
                txtCorreoElectronico.getText().toString().matches("")
        || txtmascota.getText().toString().matches("")
        || txtraza.getText().toString().matches("")
        || txtfecha.getText().toString().matches("") ){
            result =  false;
        }else{
            result = true;
        }

        return result;
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
        txtmascota.setText("");
        txtraza.setText("");
        txtfecha.setText("");
    }
}