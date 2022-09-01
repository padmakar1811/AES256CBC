package com.padmakar.aes256cbc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    EditText editMobileNumber;
    KeyGenerator keyGenerator;
    SecretKey secretKey;
    byte[] IV = new byte[16];
    SecureRandom random;
    TextView output;

    //RSA key pair (public and private)
    private KeyPair rsaKey = null;

    //encrypted aes key and ivs combined
    private byte[] encryptedAESKey = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editMobileNumber=findViewById(R.id.editMobileNumber);
        output=findViewById(R.id.output);

        findViewById(R.id.buttonEncrypt).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(editMobileNumber.getText().toString().trim())){
                    String encrypted = null;
                    encrypted = AESUtil.encrypt(editMobileNumber.getText().toString().trim());
                    output.setText(encrypted);
                       // encrypted = CryptoHandler.encrypt(editMobileNumber.getText().toString().trim());


                    Log.e("out",output.getText().toString().trim());
                }
                else{
                    Toast.makeText(MainActivity.this,"Enter Number",Toast.LENGTH_LONG);
                }
            }
        });
        findViewById(R.id.buttonDecrypt).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(editMobileNumber.getText().toString().trim())){
                    try {
                        //String decrypt = decrypt(encrypt(editMobileNumber.getText().toString().getBytes(), secretKey, IV), secretKey, IV);
                        String encrypted = AESUtil.encrypt(editMobileNumber.getText().toString().trim());
                        String decrypted = AESUtil.decrypt(encrypted);
                        output.setText(decrypted);
                        Log.e("out",output.getText().toString().trim());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Enter Number",Toast.LENGTH_LONG);
                }
            }
        });
    }



}