package com.example.bdtercerparcial.ui.productos;

import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bdtercerparcial.CustomAdapter;
import com.example.bdtercerparcial.R;
import com.example.bdtercerparcial.Utilidades;

import java.util.HashMap;


public class ProductosFragment extends Fragment {
    ListView lista;
    View roota;
    private HashMap items = new HashMap<Integer, String>();
    private ProductosViewModel mViewModel;

    public static ProductosFragment newInstance() {
        return new ProductosFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        roota =  inflater.inflate(R.layout.fragment_productos, container, false);
        lista = roota.findViewById(R.id.lstProductos);
        final String[] costos = getResources().getStringArray(R.array.costoProductos);
        final String[] productos = getResources().getStringArray(R.array.productos);

        for (int i =0; i<productos.length;i++){
            items.put(i, productos[i]);
        }
        CustomAdapter adapter = new CustomAdapter(getContext(), 0, items);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Se agrego: "+productos[position]+" ball", Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        Utilidades.Num_0 ++;
                        break;
                    case 1:
                        Utilidades.Num_1 ++;
                        break;
                    case 2:
                        Utilidades.Num_2 ++;
                        break;
                    case 3:
                        Utilidades.Num_3 ++;
                        break;
                    case 4:
                        Utilidades.Num_4 ++;
                        break;
                    case 5:
                        Utilidades.Num_5 ++;
                        break;
                    case 6:
                        Utilidades.Num_6 ++;
                        break;
                    case 7:
                        Utilidades.Num_7 ++;
                        break;
                    case 8:
                        Utilidades.Num_8 ++;
                        break;
                }
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "$"+costos[position], Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        return roota;
    }
}
