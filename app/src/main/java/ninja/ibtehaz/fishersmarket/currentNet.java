package ninja.ibtehaz.fishersmarket;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class currentNet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_net);

        //JATKA

        VideoView view = (VideoView)findViewById(R.id.vv01);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.currentnet;
        view.setVideoURI(Uri.parse(path));
        view.start();
    }
}
