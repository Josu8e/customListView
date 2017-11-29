package com.example.override.listviewpersonalizado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.override.listviewpersonalizado.Base.Constants;
import com.example.override.listviewpersonalizado.Base.ListData;
import com.example.override.listviewpersonalizado.retrofit.ServerApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Agregar extends AppCompatActivity {
    Constants constants = new Constants();
    ServerApi serverApi = constants.serverApi;
    EditText etNombre, etDescripcion, etPrecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        Constants constants =  new Constants();
        ServerApi serverApi = constants.serverApi;
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
            ListData product = new ListData();
            product.setName(etNombre.getText().toString());
            product.setDescription(etDescripcion.getText().toString());
            product.setPrice(etPrecio.getText().toString());

            System.out.println(product.toString());

            Call<ListData> call = serverApi.create(product);
            call.enqueue(new Callback<ListData>() {
                @Override
                public void onResponse(Call<ListData> call, Response<ListData> response) {
                    if(response.code()==200) {
                        Toast.makeText(Agregar.this, "Producto Agregado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(Agregar.this,"no se pudo agregar el producto, error: "+response.code(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ListData> call, Throwable t) {
                    Toast.makeText(Agregar.this,"no se pudo agregar el producto, error: "+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }
        if (item.getItemId() == R.id.Cancelar) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
