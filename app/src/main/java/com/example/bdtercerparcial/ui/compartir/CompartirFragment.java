package com.example.bdtercerparcial.ui.compartir;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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


public class CompartirFragment extends Fragment {
    View root;
    TextView txtCod, txtsub, txtto;
    Button btnInicio, btnCompartir;
    SQLiteHelper helper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_compartir, container, false);
        txtCod = root.findViewById(R.id.textViewCodigo);
        txtsub = root.findViewById(R.id.textViewSubtotal);
        txtto = root.findViewById(R.id.textViewTotal);
        btnInicio = root.findViewById(R.id.btnIniciolindo);
        btnCompartir = root.findViewById(R.id.btnCompartir);
        btnInicio.setVisibility(View.INVISIBLE);

        if (Utilidades.Usuario_Actual!="Null"){
            helper = new SQLiteHelper(getContext(), "bdUsuarios", null, 1);
            try{
                Cursor cursor = helper.consultNotes(Integer.parseInt(Utilidades.codigo_compra));
                if (cursor.getCount()>0){
                    cursor.moveToFirst();
                    txtCod.setText(Utilidades.codigo_compra);
                    txtsub.setText(cursor.getString(0));
                    txtto.setText(cursor.getString(1));
                }
            }catch (Exception e){
                Toast.makeText(getContext(), "Hubo un error al consulta compra "+e, Toast.LENGTH_SHORT).show();
            }
            btnCompartir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        SQLiteDatabase db = helper.getReadableDatabase();
                        String[] U = {Utilidades.Usuario_Actual};
                        Cursor cursor = db.rawQuery("select correo from usuarios where nombreUsuario =? ", U);
                        cursor.moveToFirst();
                        String correo = cursor.getString(0);
                        cursor.close();

                        Intent email = new Intent(Intent.ACTION_SEND);
                        String msg = "Gracias por su compra!\n" + "Codigo de compra: " + Utilidades.codigo_compra +
                                "\nSubtotal: " + txtsub.getText().toString() +
                                "\nTotal: " + txtto.getText().toString();
                        email.putExtra(Intent.EXTRA_EMAIL, correo);
                        email.putExtra(Intent.EXTRA_SUBJECT, "Compra en Beto Shop");
                        email.putExtra(Intent.EXTRA_TEXT, msg);
                        email.setType("message/rfc822");
                        startActivity(Intent.createChooser(email, "Compartir compra"));
                        Toast.makeText(getContext(), "Comparte por...", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getContext(), "Hubo un error al querer compartir "+e, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else{
            txtto.setVisibility(View.INVISIBLE);
            txtsub.setVisibility(View.INVISIBLE);
            txtCod.setVisibility(View.INVISIBLE);
            btnInicio.setVisibility(View.VISIBLE);
            btnInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getActivity(), MainActivity.class);
                    startActivity(in);
                }
            });
        }



        return root;
    }

}
