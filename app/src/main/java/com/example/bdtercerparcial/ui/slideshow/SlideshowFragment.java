package com.example.bdtercerparcial.ui.slideshow;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    SQLiteHelper helper;
    View rootaes;
    ListView listasr;
    Button btnVaciar, btnComprar, btnIniciar;
    ArrayList <String> listaCompras = new ArrayList<>();
    ArrayList <Integer> precioTotal = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootaes = inflater.inflate(R.layout.fragment_slideshow, container, false);
        btnIniciar = rootaes.findViewById(R.id.btnIniciar);
        listasr = rootaes.findViewById(R.id.lstMain);
        btnVaciar = rootaes.findViewById(R.id.btnEmpty);
        btnComprar = rootaes.findViewById(R.id.btnPurchase);
        btnIniciar.setVisibility(View.INVISIBLE);
        if (Utilidades.Usuario_Actual!="Null"){
            Utilidades.precio_final = 0;

            fillCart();
        }else{
            btnIniciar.setVisibility(View.VISIBLE);
            listasr.setVisibility(View.INVISIBLE);
            btnVaciar.setVisibility(View.INVISIBLE);
            btnComprar.setVisibility(View.INVISIBLE);
        }
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utilidades.precio_final==0){
                    Toast.makeText(getContext(), "No existen productos en el carrito", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        helper = new SQLiteHelper(getContext(), "bdUsuarios", null, 1);
                        Cursor cursor = helper.idConsult(Utilidades.Usuario_Actual);
                        cursor.moveToFirst();
                        Utilidades.idUsuario = cursor.getInt(0);
                        double totally = ((Utilidades.precio_final*.16)+Utilidades.precio_final);
                        try{
                            String compra, sub, total;
                            compra = String.valueOf(Utilidades.idUsuario);
                            sub = String.valueOf(Utilidades.precio_final);
                            total =  String.valueOf(totally);
                            boolean bandera = helper.insertNote(compra,sub,total);
                            if (bandera)
                                Toast.makeText(getContext(), "Compra Registrada ✓", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getContext(), "Hubo un error en su compra", Toast.LENGTH_SHORT).show();
                        }catch(Exception e){
                            Toast.makeText(getContext(), "Hubo un error: "+e, Toast.LENGTH_SHORT).show();
                        }
                        ContentValues reg = new ContentValues();
                        reg.put(Utilidades.Nota_compra_id, Utilidades.idUsuario);
                        reg.put(Utilidades.Nota_subtotal, Utilidades.precio_final);
                        reg.put(Utilidades.Nota_total,(((Utilidades.precio_final*.16)+Utilidades.precio_final)));
                        /*
                        if (bandera){
                            Toast.makeText(getContext(), "Compra Registrada ✓", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Hubo un error en el registro de la compra", Toast.LENGTH_LONG).show();
                        }*/
                        helper.close();
                    }catch (Exception e){
                        Toast.makeText(getContext(), "Error "+e, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilidades.Num_0=0;
                Utilidades.Num_1=0;
                Utilidades.Num_2=0;
                Utilidades.Num_3=0;
                Utilidades.Num_4=0;
                Utilidades.Num_5=0;
                Utilidades.Num_6=0;
                Utilidades.Num_7=0;
                Utilidades.Num_8=0;
                Utilidades.precio_final=0;
                listaCompras.clear();
                precioTotal.clear();
                fillCart();
            }
        });
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), MainActivity.class);
                startActivity(in);
            }
        });
        return rootaes;
    }

    public void fillCart(){
        String[]temp = getResources().getStringArray(R.array.costoProductos);
        String[]productos = getResources().getStringArray(R.array.productos);
        if (Utilidades.Num_0!=0){
            listaCompras.add(productos[0]+": "+Utilidades.Num_0+
                    " Total: "+(Integer.parseInt(temp[0])*Utilidades.Num_0));
            precioTotal.add((Integer.parseInt(temp[0])*Utilidades.Num_0));
        }
        if (Utilidades.Num_1!=0){
            listaCompras.add(productos[1]+": "+Utilidades.Num_1+
                    " Total: "+(Integer.parseInt(temp[1])*Utilidades.Num_1));
            precioTotal.add((Integer.parseInt(temp[1])*Utilidades.Num_1));
        }
        if (Utilidades.Num_2!=0){
            listaCompras.add(productos[2]+": "+Utilidades.Num_2+
                    " Total: "+(Integer.parseInt(temp[2])*Utilidades.Num_2));
            precioTotal.add((Integer.parseInt(temp[2])*Utilidades.Num_2));
        }
        if (Utilidades.Num_3!=0){
            listaCompras.add(productos[3]+": "+Utilidades.Num_3+
                    " Total: "+(Integer.parseInt(temp[3])*Utilidades.Num_3));
            precioTotal.add((Integer.parseInt(temp[3])*Utilidades.Num_3));
        }
        if (Utilidades.Num_4!=0){
            listaCompras.add(productos[4]+": "+Utilidades.Num_4+
                    " Total: "+(Integer.parseInt(temp[4])*Utilidades.Num_4));
            precioTotal.add((Integer.parseInt(temp[4])*Utilidades.Num_4));
        }
        if (Utilidades.Num_5!=0){
            listaCompras.add(productos[5]+": "+Utilidades.Num_5+
                    " Total: "+(Integer.parseInt(temp[5])*Utilidades.Num_5));
            precioTotal.add((Integer.parseInt(temp[5])*Utilidades.Num_5));
        }
        if (Utilidades.Num_6!=0){
            listaCompras.add(productos[6]+": "+Utilidades.Num_6+
                    " Total: "+(Integer.parseInt(temp[6])*Utilidades.Num_6));
            precioTotal.add((Integer.parseInt(temp[6])*Utilidades.Num_6));
        }
        if (Utilidades.Num_7!=0){
            listaCompras.add(productos[7]+": "+Utilidades.Num_7+
                    " Total: "+(Integer.parseInt(temp[7])*Utilidades.Num_7));
            precioTotal.add((Integer.parseInt(temp[7])*Utilidades.Num_7));
        }
        if (Utilidades.Num_8!=0){
            listaCompras.add(productos[8]+": "+Utilidades.Num_8+
                    " Total: "+(Integer.parseInt(temp[8])*Utilidades.Num_8));
            precioTotal.add((Integer.parseInt(temp[8])*Utilidades.Num_8));
        }
        for (int i =0;i<precioTotal.size();i++){
            Utilidades.precio_final = Utilidades.precio_final + precioTotal.get(i);
        }
        listaCompras.add("Precio total: "+Utilidades.precio_final);
        listaCompras.add("Precio total + iva: "+((Utilidades.precio_final*.16)+Utilidades.precio_final));
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, listaCompras);
        listasr.setAdapter(adapter);
    }
}
