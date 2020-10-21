package com.example.bdtercerparcial;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.bdtercerparcial.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registracion extends AppCompatActivity {
    EditText campoNombre, campoApellido, campocorreo, campoTelefono, campoUsuario, campoContra, campoVerificacionContra;
    String nombre, apellido, correo, telefono, usuario, contra, contra2;
    Button registrarme, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracion);
        campoNombre = (EditText)findViewById(R.id.nombre);
        campoApellido = (EditText)findViewById(R.id.apellido);
        campocorreo = (EditText)findViewById(R.id.correo);
        campoTelefono = (EditText)findViewById(R.id.telefono);
        campoUsuario = (EditText)findViewById(R.id.username);
        campoContra = (EditText)findViewById(R.id.password);
        campoVerificacionContra = (EditText)findViewById(R.id.passwordRetype);

        registrarme = (Button) findViewById(R.id.btnRegistrarme);
        back = (Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Registracion.this, MainActivity.class);
                startActivity(in);
            }
        });

        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;
                nombre = campoNombre.getText().toString();
                apellido = campoApellido.getText().toString();
                correo = campocorreo.getText().toString();
                telefono = campoTelefono.getText().toString();
                usuario = campoUsuario.getText().toString();
                contra = campoContra.getText().toString();
                contra2 = campoVerificacionContra.getText().toString();
                String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:" +
                        "[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
                String contrasRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
                String telefonoRegex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
                String nombreRegex = "^[\\p{L} .'-]+$";
                String apellidoRegex = "^[\\p{L} .'-]+$";

                Pattern patternNombre = Pattern.compile(nombreRegex);
                Pattern patternApellido = Pattern.compile(apellidoRegex);
                Pattern patternCorreo = Pattern.compile(emailRegex);
                Pattern patterntelefono = Pattern.compile(telefonoRegex);
                Pattern patterncontras = Pattern.compile(contrasRegex);

                Matcher nombreMatcher = patternNombre.matcher(nombre);
                Matcher apellidoMatcher = patternApellido.matcher(apellido);
                Matcher correoMatcher = patternCorreo.matcher(correo);
                Matcher telefonoMatcher = patterntelefono.matcher(telefono);
                Matcher contraMatcher = patterncontras.matcher(contra);

                if (!nombreMatcher.matches()){
                    Toast.makeText(getApplicationContext(), "Ingrese un nombre valido", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
                if (!apellidoMatcher.matches()){
                    Toast.makeText(getApplicationContext(), "Ingrese un apellido valido", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
                if (!correoMatcher.matches()){
                    Toast.makeText(getApplicationContext(), "Ingrese un correo valido", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
                if (!telefonoMatcher.matches()){
                    Toast.makeText(getApplicationContext(), "Ingrese un telefono valido", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
                /*if (!contraMatcher.matches()){
                    Toast.makeText(getApplicationContext(), "Ingrese una contraseña valida, debe contener al menos 1 numero, 1 letra mayuscula," +
                            " 1 letra minuscula y un caracter especial", Toast.LENGTH_LONG).show();
                    flag = false;
                }*/
                //verificacion de contrasenas
                if (contra.compareTo(contra2)!=0){
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    flag = false;
                }

                if (nombre.trim().isEmpty() || correo.trim().isEmpty() || telefono.trim().isEmpty() ||contra.trim().isEmpty()||contra2.trim().isEmpty()){
                    Toast.makeText(Registracion.this, "Porfavor llenar todos los campos", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
                if(flag){
                    SQLiteHelper helper = new SQLiteHelper(Registracion.this,"bdUsuarios",null,1);
                    boolean bandera = helper.insertarRegistro(nombre, apellido, correo, telefono, usuario, contra);
                    ContentValues registro = new ContentValues();
                    registro.put(Utilidades.Campo_nombre, nombre);
                    registro.put(Utilidades.Campo_apellido, apellido);
                    registro.put(Utilidades.Campo_correo, correo);
                    registro.put(Utilidades.Campo_telefono, telefono);
                    registro.put(Utilidades.Campo_usuario, usuario);
                    registro.put(Utilidades.Campo_contrasena,contra);

                    if (bandera){
                        Toast.makeText(Registracion.this, "Registro completado ✓", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplication(), MainActivity.class);
                        startActivity(in);
                        helper.close();
                    }else{
                        Toast.makeText(Registracion.this, "hubo un error en su registro", Toast.LENGTH_SHORT).show();
                    }
                    helper.close();
                }
            }
        });
    }

}
