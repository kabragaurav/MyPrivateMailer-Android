package com.example.myemailer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText emailid, sum, msg;
    Switch nightswitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailid = findViewById(R.id.to2);
        sum = findViewById(R.id.sub2);
        msg = findViewById(R.id.msg2);


        nightswitch = (Switch) findViewById(R.id.switch1);
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            nightswitch.setChecked(true);
        }
        nightswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
}


    public void sendit(View v) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        GMailSender sender = new GMailSender("gauravkabra240697@gmail.com",
                                "gauravkabraisgreat");
                        sender.sendMail(sum.getText().toString(), msg.getText().toString(),
                                "gauravkabra240697@gmail.com", emailid.getText().toString());
                    } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);
                    }
                }

            }).start();
        Toast.makeText(MainActivity.this, "Mail sent in case data is valid!", Toast.LENGTH_SHORT).show();
    }

}
