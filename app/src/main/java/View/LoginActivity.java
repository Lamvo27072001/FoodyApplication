package View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.FacebookSdk;

import hcmute.phamvohonglam19110154.foodyapplication.R;

public class LoginActivity extends AppCompatActivity {

    // Declare Variables
    EditText txtUserNam, txtPassword;
    Button btnLogin;
    String tk = "admin";
    String mk = "admin";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        // Bindings
        txtUserNam = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        //functions
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUserNam.getText().toString().equals(tk) && txtPassword.getText().toString().equals(mk))
                {
                    startActivity(new Intent(LoginActivity.this, MainPageActivity.class));
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Your username or password is not correct!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
