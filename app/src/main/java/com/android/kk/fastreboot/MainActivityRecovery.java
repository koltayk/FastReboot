package com.android.kk.fastreboot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivityRecovery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityReboot.cmd("su","reboot recovery");
    }
}