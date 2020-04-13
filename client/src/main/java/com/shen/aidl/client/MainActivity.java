package com.shen.aidl.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import com.aidl.fuck.IFuckInterface;

public class MainActivity extends AppCompatActivity {
    private IFuckInterface myInterface = null;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView  = findViewById(R.id.xxx);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //跨进程，无法使用Intent（this，xx.class）方法，可以使用下面的，通过包名和完整路径类名创建Intent
                intent.setClassName("com.shen.aidl.server","com.shen.aidl.server.FuckService");
                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        myInterface = IFuckInterface.Stub.asInterface(iBinder);
                        try {
                            myInterface.setName("诸神黄昏");
                            String name = myInterface.getName();
                            textView.setText(name);
                        }catch (Exception e) {
                        }
                    }
                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {
                    }
                }, Context.BIND_AUTO_CREATE);
            }
        });
    }
}

