package com.example.bdtercerparcial.ui.gallery;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bdtercerparcial.MainActivity;
import com.example.bdtercerparcial.R;
import com.example.bdtercerparcial.SQLiteHelper;
import com.example.bdtercerparcial.Utilidades;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    TextView txtCod, txtSubtotal, txtTotal;
    Spinner spinner;
    Button btnInicio;
    SQLiteHelper helper;
    ArrayList<String> lista = new ArrayList<>();
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_gallery, container, false);
        txtCod = root.findViewById(R.id.txtCodigoCompra);
        txtSubtotal = root.findViewById(R.id.txtSubtotal);
        txtTotal = root.findViewById(R.id.txtTotal);
        spinner = root.findViewById(R.id.spinner);
        btnInicio = root.findViewById(R.id.btnIniciolin);
        btnInicio.setVisibility(View.INVISIBLE);
        if (Utilidades.Usuario_Actual!="Null"){
            lista.add("Elegir Compra");
            lista.add("0");
            lista.add("1");
            lista.add("2");
            lista.add("3");
            lista.add("4");
            lista.add("5");
            lista.add("6");
            lista.add("7");
            lista.add("8");
            lista.add("9");
            lista.add("10");
            lista.add("11");
            lista.add("12");
            lista.add("13");
            lista.add("14");
            lista.add("15");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lista);
            spinner.setAdapter(adapter);
            helper = new SQLiteHelper(getContext(), "bdUsuarios", null, 1);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(spinner.getSelectedItem().toString()!="Elegir Compra"){
                        try{
                            Cursor cursor = helper.consultNotes(spinner.getSelectedItemPosition());
                            if (cursor.getCount()>0){
                                cursor.moveToFirst();
                                txtCod.setText(spinner.getSelectedItem().toString());
                                txtSubtotal.setText(cursor.getString(0));
                                txtTotal.setText(cursor.getString(1));
                                Utilidades.codigo_compra = String.valueOf(spinner.getSelectedItemPosition());
                            }
                        }catch (Exception e){
                            Toast.makeText(getContext(), "Hubo un error al consultar la compra "+e, Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    //do nothing
                }
            });

            btnInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getActivity(), MainActivity.class);
                    startActivity(in);
                }
            });
        }else{
            txtTotal.setVisibility(View.INVISIBLE);
            txtSubtotal.setVisibility(View.INVISIBLE);
            txtCod.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.INVISIBLE);
            btnInicio.setVisibility(View.VISIBLE);
        }
        return root;
    }
}
