package ninja.ibtehaz.fishersmarket;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by monkey on 4/7/16.
 */


public class MyReceiver extends BroadcastReceiver
{
    static int countPowerOff=0;
    private Activity activity=null;


    public MyReceiver (Activity activity)
    {
        this.activity=activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, "power button clicked", Toast.LENGTH_LONG)
                .show();

//        TextView tv = (TextView)activity.findViewById(R.id.tv_distress);
//
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
        {
            countPowerOff++;
        }
        else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
        {
            countPowerOff++;
        }

        if(countPowerOff >= 5)
        {
            Toast.makeText(context, "Where Did you go?", Toast.LENGTH_LONG)
                    .show();

            SharedPreferences prefs = activity.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

            String address = prefs.getString("Address", "");
            String local = prefs.getString("District", "");
            String knwon = prefs.getString("Known", "");
            String lat = prefs.getString("Latitude", "");
            String lon = prefs.getString("Longitude", "");


            if(address.equals("") && local. equals("") && knwon.equals(""))
            {
                Toast.makeText(activity.getApplicationContext(), "Cannot collect address", Toast.LENGTH_LONG).show();
            }
            else
            {
                //Send SMS here
                String text = "Distress Message : " + '\n' +
                        " Address : "+address + '\n' +
                        " Locality : "+ local + '\n' +
                        " Known Area Name : "+knwon + '\n' +
                        " [ Latitude : "+lat + " Longitude : "+lon +" ] ";


                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("8801960276977", null, text, null, null);
            }

        }


//        tv.setText(countPowerOff);
    }
}
