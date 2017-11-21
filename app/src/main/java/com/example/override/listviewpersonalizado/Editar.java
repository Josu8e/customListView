package com.example.override.listviewpersonalizado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Editar extends AppCompatActivity {
    //BDHelper MyDB;
    EditText etNombre, etDescripcion, etPrecio;
    Bundle bundle;
    String oldName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
       // MyDB = new BDHelper(this);
        etNombre = (EditText) findViewById(R.id.etProducto);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        bundle = getIntent().getExtras();
        oldName = bundle.getString("nombre");
        /*Cursor rs = MyDB.getData(oldName);
        if(rs.moveToFirst()) {
            etNombre.setText(rs.getString(0));
            etDescripcion.setText(rs.getString(1));
            etPrecio.setText(rs.getString(2));
            if (!rs.isClosed()) {
                rs.close();
            }
        }else{
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
        }*/

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.GuardarProducto) {
           /* if (!MyDB.updateContact(oldName, etNombre.getText().toString(),
                    etDescripcion.getText().toString(),
                    etPrecio.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Repetido", Toast.LENGTH_SHORT).show();
            }*/
            finish();
        }
        if (item.getItemId() == R.id.Cancelar) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
