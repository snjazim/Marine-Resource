package ninja.ibtehaz.fishersmarket;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class Little_fish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little_fish);

        //JATKA

        VideoView view = (VideoView)findViewById(R.id.vv01);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.littlefish;
        view.setVideoURI(Uri.parse(path));
        view.start();
    }
}
