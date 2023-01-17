package com.shadad.epm;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.internal.$Gson$Types;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;




public class ScansActivity extends AppCompatActivity {

Button btnScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        btnScan =  findViewById(R.id.btnScanQr);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scan();
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100)  {
            if(grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "allowed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Scan( ) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            String[] list =new String[]{Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this, list, 100);
        }
        ScanOptions options = new ScanOptions();
        options.setPrompt("Scan a barcode");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barcodeLoucher.launch(options);
    }


    private final ActivityResultLauncher<ScanOptions> barcodeLoucher =  registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() == null) {
            Toast.makeText(ScansActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
        } else {
//            Toast.makeText(ScansActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            String hasil = result.getContents();
            if(hasil.equals("st100")){
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(ScansActivity.this, "QR tidak Terdaftar", Toast.LENGTH_SHORT).show();
            }
        }
    });
}