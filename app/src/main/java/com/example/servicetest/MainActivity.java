package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button Start;

    private Button Stop;

    private Button mBind;

    private Button mUnbind;

    private MyService.DownloadBinder mDownloadBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (MyService.DownloadBinder)service;
            mDownloadBinder.startDownload();
            mDownloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }


    private void initView(){

        Start = (Button)findViewById(R.id.start_service);
        Stop = (Button)findViewById(R.id.stop_service);
        mBind = (Button)findViewById(R.id.bind_service);
        mUnbind = (Button)findViewById(R.id.unbind_service);

    }


    private void initEvent(){

        Start.setOnClickListener(this);
        Stop.setOnClickListener(this);
        mBind.setOnClickListener(this);
        mUnbind.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this,MyService.class);
                startService(startIntent);
                Log.d("MainActivity","lll");
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,mConnection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(mConnection);
                break;
            default:
                break;

        }
    }
}
