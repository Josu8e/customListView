package com.example.override.listviewpersonalizado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.override.listviewpersonalizado.Base.Constants;
import com.example.override.listviewpersonalizado.Base.ListData;
import com.example.override.listviewpersonalizado.retrofit.ServerApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editar extends AppCompatActivity {
    //BDHelper MyDB;
    Constants constants = new Constants();
    ServerApi serverApi = constants.serverApi;
    EditText etNombre, etDescripcion, etPrecio;
    Bundle bundle;
    String Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        bundle = getIntent().getExtras();
        etNombre = (EditText) findViewById(R.id.etProducto);
        etNombre.setText(bundle.getString("nombre"));
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        etDescripcion.setText(bundle.getString("description"));
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etPrecio.setText(bundle.getString("price"));
        Id = bundle.getString("id");
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
            product.set_Id(Id);

            Call<ListData> call = serverApi.update(Id,product);

            call.enqueue(new Callback<ListData>() {
                @Override
                public void onResponse(Call<ListData> call, Response<ListData> response) {
                    if (response.code()==200) {
                        Toast.makeText(Editar.this, "Producto Modificado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(Editar.this,"Producto no modificado, error: "+response.code(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ListData> call, Throwable t) {
                    System.out.println("debug: "+ t.getMessage());
                    Toast.makeText(Editar.this,"Producto no modificado, error: "+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (item.getItemId() == R.id.Cancelar) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
