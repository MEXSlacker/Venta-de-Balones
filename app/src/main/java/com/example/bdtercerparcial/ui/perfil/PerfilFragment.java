package com.example.bdtercerparcial.ui.perfil;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bdtercerparcial.MainActivity;
import com.example.bdtercerparcial.R;
import com.example.bdtercerparcial.SQLiteHelper;
import com.example.bdtercerparcial.Utilidades;
import com.example.bdtercerparcial.ui.home.HomeFragment;

public class PerfilFragment extends Fragment {
    View rootas;
    TextView nombre, apellido, correo, telefono, usuario, contra;
    SQLiteHelper helper;
    Button salir, inicio;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootas = inflater.inflate(R.layout.fragment_perfil, container, false);
        nombre = rootas.findViewById(R.id.txtNombre);
        apellido = rootas.findViewById(R.id.txtApellido);
        correo = rootas.findViewById(R.id.txtCorreo);
        telefono = rootas.findViewById(R.id.txtTelefono);
        usuario = rootas.findViewById(R.id.txtUsuario);
        contra = rootas.findViewById(R.id.txtContra);
        salir = rootas.findViewById(R.id.btnSalirCuenta);
        inicio = rootas.findViewById(R.id.btnIniciaSesion);
        inicio.setVisibility(View.INVISIBLE);

        if (Utilidades.Usuario_Actual!="Null"){
            helper = new SQLiteHelper(getContext(), "bdUsuarios", null, 1);
            Cursor cursor = helper.allData(Utilidades.Usuario_Actual);
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                nombre.setText(cursor.getString(1));
                apellido.setText(cursor.getString(2));
                correo.setText(cursor.getString(3));
                telefono.setText(cursor.getString(4));
                usuario.setText(cursor.getString(5));
                contra.setText(cursor.getString(6));
                cursor.close();
            }else{
                Toast.makeText(getContext(), "No existen ese usuario", Toast.LENGTH_SHORT).show();
            }
        }

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilidades.Usuario_Actual = "Null";
                nombre.setVisibility(View.INVISIBLE);
                apellido.setVisibility(View.INVISIBLE);
                correo.setVisibility(View.INVISIBLE);
                telefono.setVisibility(View.INVISIBLE);
                usuario.setVisibility(View.INVISIBLE);
                contra.setVisibility(View.INVISIBLE);
                salir.setVisibility(View.INVISIBLE);
                inicio.setText("Inicia Sesion");
                inicio.setVisibility(View.VISIBLE);
            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), MainActivity.class);
                startActivity(in);
            }
        });
        return rootas;
    }
}
