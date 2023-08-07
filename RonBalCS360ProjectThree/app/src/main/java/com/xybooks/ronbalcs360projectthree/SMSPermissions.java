package com.xybooks.ronbalcs360projectthree;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

public class SMSPermissions {

    public class MainActivity extends AppCompatActivity {

        private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.sms_permissions);

            //check if permission is not granted
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {


                }
                else {
                    //a popup will appear asking for permission
                    ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                }
            }



        }//onCreate

        //after getting permission request result
        //@Override
        public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults) {

            //check request code
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                    //check whether the grantResult length is greater than 0
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Access Granted", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(this, "Denied", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

    }
}
