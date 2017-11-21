package com.example.override.listviewpersonalizado;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.override.listviewpersonalizado.Base.BDHelper;

public class Agregar extends AppCompatActivity {
    //BDHelper MyDB;
    EditText etNombre, etDescripcion, etPrecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        //MyDB = new BDHelper(this);
        etNombre = (EditText) findViewById(R.id.etProducto);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.GuardarProducto) {
            /*Cursor rs = MyDB.getData(etNombre.getText().toString());
            if (rs.moveToFirst()) {
                Toast.makeText(getApplicationContext(), "Repetido", Toast.LENGTH_SHORT).show();
                if (!rs.isClosed()) {
                    rs.close();
                }
            } else {
                MyDB.insertContact(etNombre.getText().toString(), etDescripcion.getText().toString(),
                        etPrecio.getText().toString());
                finish();
            }*/
        }
        if (item.getItemId() == R.id.Cancelar) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
