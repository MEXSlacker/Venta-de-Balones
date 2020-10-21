package com.example.bdtercerparcial.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bdtercerparcial.R;
import com.example.bdtercerparcial.Registracion;
import com.example.bdtercerparcial.SQLiteHelper;
import com.example.bdtercerparcial.Utilidades;
import com.example.bdtercerparcial.ui.productos.ProductosFragment;

import org.w3c.dom.Text;
public class HomeFragment extends Fragment {

    Button btnLogin, btnRegistros;
    EditText txtUsuario, txtContra;
    TextView welcome, user, pass;



    View root;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        btnLogin = root.findViewById(R.id.btnLoginHome);
        btnRegistros = root.findViewById(R.id.btnRegistrarHome);
        welcome = root.findViewById(R.id.text_home);
        user = root.findViewById(R.id.tvUser);
        pass = root.findViewById(R.id.tvPass);
        txtContra = root.findViewById(R.id.passwordField);
        txtUsuario = root.findViewById(R.id.userField);


        if (Utilidades.Usuario_Actual != "Null") {
            txtUsuario.setVisibility(View.INVISIBLE);
            user.setVisibility(View.INVISIBLE);
            pass.setVisibility(View.INVISIBLE);
            txtContra.setVisibility(View.INVISIBLE);
            btnLogin.setVisibility(View.INVISIBLE);
            btnRegistros.setVisibility(View.INVISIBLE);
            welcome.setText("Bienvenido " + Utilidades.Usuario_Actual);
        } else {
            btnRegistros.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getActivity(), Registracion.class);
                    startActivity(in);
                }
            });

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtContra = root.findViewById(R.id.passwordField);
                    txtUsuario = root.findViewById(R.id.userField);

                    SQLiteHelper helper = new SQLiteHelper(getContext(), "bdUsuarios", null, 1);
                    Cursor cursor = helper.ConsultarUsuPas(txtUsuario.getText().toString(), txtContra.getText().toString());
                    if (cursor.getCount() > 0) {
                        Utilidades.Usuario_Actual = txtUsuario.getText().toString();
                        user.setVisibility(View.INVISIBLE);
                        pass.setVisibility(View.INVISIBLE);
                        txtUsuario.setVisibility(View.INVISIBLE);
                        txtContra.setVisibility(View.INVISIBLE);
                        btnLogin.setVisibility(View.INVISIBLE);
                        btnRegistros.setVisibility(View.INVISIBLE);
                        welcome.setText("Bienvenido " + Utilidades.Usuario_Actual);
                    } else {
                        Toast.makeText(getContext(), "Usuario y/o contra incorrectos", Toast.LENGTH_SHORT).show();
                        txtUsuario.setText("");
                        txtContra.setText("");
                        txtUsuario.findFocus();
                    }
                }
            });

        }
        return root;
    }
}


//txtUsuario.setVisibility(View.INVISIBLE);
//                            txtContra.setVisibility(View.INVISIBLE);
//                            btnLogin.setVisibility(View.INVISIBLE);
//                            btnRegistros.setVisibility(View.INVISIBLE);
