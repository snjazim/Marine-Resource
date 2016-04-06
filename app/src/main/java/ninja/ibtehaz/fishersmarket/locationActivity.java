package ninja.ibtehaz.fishersmarket;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class locationActivity extends AppCompatActivity {

    private TextView locationText;
    private TextView addressText;
    private GoogleMap map;



    TextToSpeech textToSpeech;
    boolean createCircleOnce = true;
    boolean talkOnce = true;
    Circle mCircle;
    boolean markerOnce = true;

    int color = Color.RED;
    boolean colorOnce = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationText = (TextView) findViewById(R.id.location);
        addressText = (TextView) findViewById(R.id.address);


        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });


        //replace GOOGLE MAP fragment in this Activity
        replaceMapFragment();
    }





    private void drawCircle(LatLng point){

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();

        // Specifying the center of the circle
        circleOptions.center(point);

        // Radius of the circle - Meter
        circleOptions.radius(200);

        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);

        // Fill color of the circle
        circleOptions.fillColor(color);

        // Border width of the circle
        circleOptions.strokeWidth(2);

        // Adding the circle to the GoogleMap
        mCircle =  map.addCircle(circleOptions);



    }

    private void replaceMapFragment() {
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        // Enable Zoom
        map.getUiSettings().setZoomGesturesEnabled(true);

        //set Map TYPE
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        } else {
            map.setMyLocationEnabled(true);
        }


        //set "listener" for changing my location
        map.setOnMyLocationChangeListener(myLocationChangeListener());
    }


    private void text2Speech(LatLng location)
    {
        float[] distance = new float[2];

                        /*
                        Location.distanceBetween( mMarker.getPosition().latitude, mMarker.getPosition().longitude,
                                mCircle.getCenter().latitude, mCircle.getCenter().longitude, distance);
                                */

        if(talkOnce) {
            Location.distanceBetween(location.latitude, location.longitude,
                    mCircle.getCenter().latitude, mCircle.getCenter().longitude, distance);

            if (distance[0] > mCircle.getRadius()) {
                makeText(getBaseContext(), "Outside, distance from center: " + distance[0] + " radius: " + mCircle.getRadius(), LENGTH_LONG).show();
            } else {
                makeText(getBaseContext(), "Inside, distance from center: " + distance[0] + " radius: " + mCircle.getRadius(), LENGTH_LONG).show();
                textToSpeech.speak("Inside, distance from center: " + distance[0], TextToSpeech.QUEUE_FLUSH, null);

            }
            talkOnce = false;
        }
    }



    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener() {
        return new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

                if(markerOnce) {
                    Marker marker;
                    marker = map.addMarker(new MarkerOptions().position(loc));
                    markerOnce = false;
                }

                map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
                String toSpeak = "You are at [" + longitude + " ; " + latitude + " ]";

                locationText.setText(toSpeak);


                // Drawing circle on the map
                if(createCircleOnce) {
                    drawCircle(new LatLng(location.getLatitude(), location.getLongitude()));
                    createCircleOnce = false;
                }

                text2Speech(new LatLng(location.getLatitude(), location.getLongitude()));
                //get current address by invoke an AsyncTask object
                new GetAddressTask(locationActivity.this).execute(String.valueOf(latitude), String.valueOf(longitude));
            }
        };
    }

    public void callBackDataFromAsyncTask(String []address) {

        addressText.setText("Street: " + address[0] + " \n" + "\nCountry: " + address[1] +
                " \n" + "Place Name: " + address[2]);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
        if (id == R.id.action_color) {
            if(mCircle != null){
                mCircle.remove();
            }
            
            if(colorOnce) {
                colorOnce = false;
            }
            else
            {
                colorOnce = true;
                createCircleOnce = true;
                talkOnce = true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
