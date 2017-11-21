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

import com.example.override.listviewpersonalizado.Base.ListData;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //BDHelper MyDB;
    ArrayList<ListData> arrayListData = new ArrayList<ListData>();
    String seleccionado;
    ListView lvDatos;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MyDB = new BDHelper(this);
        ListData list = new ListData();
        list.setNombre("Prueba");
        list.setDescripcion("Prueba");
        list.setPrecio("1");
        arrayListData.add(list);
        lvDatos = (ListView) findViewById(R.id.lvListaProductos);
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
        //arrayListData = MyDB.getListData();

        lvDatos.setAdapter(new viewAdapter(this));
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
        //MyDB.deleteContact(seleccionado);
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
            nameText.setText(getResources().getText(R.string.name)+" : "+arrayListData.get(position).getNombre());
            final TextView ageText = (TextView) convertView.findViewById(R.id.descripcionText);
            ageText.setText(getResources().getText(R.string.description)+" : "+arrayListData.get(position).getDescripcion());
            final Button edit = (Button) convertView.findViewById(R.id.bEditar);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bundle = new Bundle();
                    bundle.putString("nombre", arrayListData.get(pos).getNombre());
                    Intent intent = new Intent(getApplicationContext(), Editar.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            final Button delete = (Button) convertView.findViewById(R.id.bEliminar);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    seleccionado = arrayListData.get(pos).getNombre();
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

