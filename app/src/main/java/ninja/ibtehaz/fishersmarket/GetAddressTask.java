package ninja.ibtehaz.fishersmarket;


import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by monkey on 4/6/16.
 */


public class GetAddressTask extends AsyncTask<String, Void, String[]> {

    private locationActivity activity;

    public GetAddressTask(locationActivity activity) {
        super();
        this.activity = activity;
    }
    @Override
    protected String[] doInBackground(String... params) {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(activity, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(Double.parseDouble(params[0]), Double.parseDouble(params[1]), 1);

            //get current Street name
            String address = addresses.get(0).getAddressLine(0);

            //get country
            String country = addresses.get(0).getCountryName();

            //get place Name
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            String []returnVal = new String[3];

            returnVal[0] = address;
            returnVal[1] = country;
            returnVal[2] = knownName;

            SharedPreferences sharedpreferences = activity.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);;
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("Address", address);
            editor.putString("District", addresses.get(0).getLocality());
            editor.putString("Known", knownName);
            editor.putString("Latitude",Double.parseDouble(params[0])+"");
            editor.putString("Longitude",Double.parseDouble(params[1])+"");
            editor.commit();


            return returnVal;

        } catch (IOException ex) {
            ex.printStackTrace();
            return new String[]{"IOE EXCEPTION"};

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return new String[]{"IllegalArgument Exception"};
        }
    }


    /**
     * When the task finishes, onPostExecute() call back data to Activity UI and displays the address.
     * @param address
     */

    protected void onPostExecute(String []address) {
        // Call back Data and Display the current address in the UI
        activity.callBackDataFromAsyncTask(address);
    }
}
