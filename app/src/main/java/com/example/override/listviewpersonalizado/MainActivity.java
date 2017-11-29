package com.example.override.listviewpersonalizado;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.override.listviewpersonalizado.Base.Constants;
import com.example.override.listviewpersonalizado.Base.ListData;
import com.example.override.listviewpersonalizado.retrofit.ServerApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    Constants constants = new Constants();

    ServerApi serverApi = constants.serverApi;

    ArrayList<ListData> arrayListData = new ArrayList<ListData>();
    String seleccionado;
    ListView lvDatos;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.guardar) {
            Intent intent = new Intent(getApplicationContext(), Agregar.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        arrayListData = new ArrayList<ListData>();
        final Call<ArrayList<ListData>> call = serverApi.getAllProducts();

        call.enqueue(new Callback<ArrayList<ListData>>() {
                         @Override
                         public void onResponse(Call<ArrayList<ListData>> call, Response<ArrayList<ListData>> response) {
                             if(response.code() == 200){
                                 try{
                                     System.out.println("tamaño: "+response.toString());
                                     System.out.println("tamaño: "+response.body().toString());
                                     for(int i=0;i<response.body().size();i++){
                                         ListData list = new ListData();
                                         list.set_Id(response.body().get(i).get_Id());
                                         list.setName(response.body().get(i).getName());
                                         list.setDescription(response.body().get(i).getDescription());
                                         list.setPrice(response.body().get(i).getPrice());
                                         arrayListData.add(list);
                                     }
                                     lvDatos = (ListView) findViewById(R.id.lvListaProductos);
                                     lvDatos.setAdapter(new viewAdapter(MainActivity.this));
                                 }catch (Exception e){
                                     Toast.makeText(MainActivity.this,"error: "+e.getMessage(),Toast.LENGTH_LONG).show();
                                 }
                             }else{
                                 Toast.makeText(MainActivity.this,"error: "+response.code()+" "+response.message(),Toast.LENGTH_LONG).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<ArrayList<ListData>> call, Throwable t) {
                             Toast.makeText(MainActivity.this,"No hay conexion a internet,error: "+t.getMessage(),Toast.LENGTH_SHORT).show();
                         }
                     }

        );



        super.onResume();
    }
    public void confirmar_eliminar() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Desea eliminar el producto ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                eliminar();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }
    public void eliminar(){
        Call<ListData> call = serverApi.delete(seleccionado);

        call.enqueue(new Callback<ListData>() {
            @Override
            public void onResponse(Call<ListData> call, Response<ListData> response) {
                if(response.code()==200) {
                    Toast.makeText(MainActivity.this, call.toString(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Producto no borrado, error: "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Producto no borrado, error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        onResume();
    }

    public class viewAdapter extends BaseAdapter{
        LayoutInflater mInflater;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.listitem,null);
            }
            final int pos = position;
            final TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
            nameText.setText(getResources().getText(R.string.name)+" : "+arrayListData.get(position).getName());
            final TextView ageText = (TextView) convertView.findViewById(R.id.descripcionText);
            ageText.setText(getResources().getText(R.string.description)+" : "+arrayListData.get(position).getDescription());
            final Button edit = (Button) convertView.findViewById(R.id.bEditar);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bundle = new Bundle();
                    bundle.putString("id", arrayListData.get(pos).get_Id());
                    bundle.putString("nombre",arrayListData.get(pos).getName());
                    bundle.putString("description",arrayListData.get(pos).getDescription());
                    bundle.putString("price",arrayListData.get(pos).getPrice());
                    Intent intent = new Intent(getApplicationContext(), Editar.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            final Button delete = (Button) convertView.findViewById(R.id.bEliminar);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    seleccionado = arrayListData.get(pos).get_Id();
                    confirmar_eliminar();
                }
            });
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return arrayListData.get(position);
        }

        public viewAdapter(Context context){
            mInflater = LayoutInflater.from(context);
        }
        public int getCount()
        {
            return arrayListData.size();
        }

    }
}

