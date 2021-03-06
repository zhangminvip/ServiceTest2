package com.example.servicetest;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends IntentService {

    private DownloadBinder mBinder =  new DownloadBinder();

    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService","startDownload excuted");
        }

        public int getProgress(){
            Log.d("MyService","get Progress executed");
            return 0;
        }
    }



    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        Log.d("MainActivity","Thread id is"+Thread.currentThread().getId());
    }


    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("MainActivity","我是张敏");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)

    {

        Log.d("MainActivity","onStartCommand");
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy(){
        Log.d("MainActivity","onDestroy");
        super.onDestroy();
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        Log.d("MainActivity","onbind");
        return mBinder;
    }
}
