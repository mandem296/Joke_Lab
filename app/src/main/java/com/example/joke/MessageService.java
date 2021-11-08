package com.example.joke;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MessageService extends IntentService {

    //Declare a constant KEY to pass a message from the Main Activity to the service

    public static final String EXTRA_MESSAGE="MESSAGE";
    //private Handler handler;
    private static final int NOTIFICATION_ID=1;

 //   @Override
   // public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
       // handler=new Handler();
       // return super.onStartCommand(intent, flags, startId);
   // }

    public MessageService() {

        super("MessageService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {

        //This method contains the code that we want to run when the service receives an intent. Do not delete it.
        synchronized (this) {

            //Synchronized() method is a Java code which allows us to lock a particular block of code from access by other threads
            try {

                //wait for 10 seconds
                wait(10000);
            } catch (InterruptedException error) {
                error.printStackTrace();
            }
            //try.. catch is a Java syntax that allows us to perform code actions on the try block, and catch error exceptions in the catch block, hence making us able to trace the line of code which has errors when debugging.

        }

        String text=intent.getStringExtra(EXTRA_MESSAGE);

        //Call the ShowText method
        showText(text);

    }

    private void showText(final String text){

        Log.v("DelayedMessageService","What is the secret of comedy?:" +text);


        //Creating an explicit intent
        Intent intent=new Intent(this,MainActivity.class);

        //Task Stack Builder
        TaskStackBuilder stackBuilder=TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);

        //Getting pending intent from the TaskStackBuilder
        PendingIntent pendingIntent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification=new Notification.Builder(this)

                //This displays a small notification icon..in this case the mipmap called ic_joke_round
        .setSmallIcon(R.mipmap.ic_joke_round)

                //Set the title as your application name
        .setContentTitle(getString(R.string.app_name))

                //Set the content text
        .setContentText(text)

                //Make the notification disappear when clicked
        .setAutoCancel(true)

                //Give it a maximum priority to allow peeking
        .setPriority(Notification.PRIORITY_MAX)

                //Set it to vibrate to get a larger heads-up notification
        .setDefaults(Notification.DEFAULT_VIBRATE)

                //Open main activity on clicking the notification
        .setContentIntent(pendingIntent)
        .build();

        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        //Issue the notification
        notificationManager.notify(NOTIFICATION_ID,notification);


        //The above line of code logs a piece of text so that we can see it in the logcat

        //post the Toast code to the main thread using the handler post method


       // handler.post(new Runnable() {
         //   @Override
          //  public void run() {
               // Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
          //  }
       // });



    }
        }


