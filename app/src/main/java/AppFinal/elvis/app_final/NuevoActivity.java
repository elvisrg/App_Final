package AppFinal.elvis.app_final;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import AppFinal.elvis.app_final.adaptadores.ListaContactosAdapter;
import AppFinal.elvis.app_final.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreoElectronico;
    Button btnsave;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        btnsave = findViewById(R.id.btnGuarda);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                long id = dbContactos.insertaContacto(txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreoElectronico.getText().toString());

                if (id > 0){
                    Toast.makeText(NuevoActivity.this,"REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                    lista();
                    finish();
                }else {
                    Toast.makeText(NuevoActivity.this,"ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }
}