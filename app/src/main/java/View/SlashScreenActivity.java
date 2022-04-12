package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import hcmute.phamvohonglam19110154.foodyapplication.R;

public class SlashScreenActivity extends AppCompatActivity {

    // Declare Variables
    TextView txtVersion, txtLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_flashscreen);

        // Bindings
        txtVersion = findViewById(R.id.txtVersion);
        txtLoading =  findViewById(R.id.txtLoading);

        //Function to move SlashScreen to Login

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent iLogin = new Intent(SlashScreenActivity.this, LoginActivity.class);
                    startActivity(iLogin);
                }
            }, 2000);


    }
}