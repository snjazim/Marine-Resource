package ninja.ibtehaz.fishersmarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time_catch_fish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_catch_fish);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        TextView fish_law = (TextView)findViewById(R.id.fish_law_tv);
        String finalText = "১ 'বাংলাদেশ মৎস সম্পদ' হিসাবে মিঠা অঞ্চল এবং সামুদ্রিক অঞ্চল আইন , ১৯৭৪ (১৯৭৪ এর XXVI ) ," +
                " এবং অন্য কোন সামুদ্রিক জলের যার উপর রয়েছে , অথবা আছে বলে দাবি করে , অধিক্ষেত্র অধীন সরকার কর্তৃক ঘোষিত " +
                "জলসীমা এবং বাংলাদেশের অর্থনৈতিক জোন ব্যবস্থাপনা , সংরক্ষণ ও সামুদ্রিক জীবন সম্পদ উন্নয়নের আইনের অধীন।";
        String finalText2 = '\n' + "২ 'পরিচালক' ধারা 4 এর অধীন নিযুক্ত ব্যক্তি।";
        String finalText3 = '\n' + "৩ 'মাছ' মানে জলজ প্রাণী , যা কিনা পুকুর বা, " +
                "এবং কোন শেলফিস , কাঁকড়া , কচ্ছপ বা জলজ স্তন্যপায়ী , এবং অন্তর্ভুক্ত তরুণ , ভাজা , ডিম এবং ডিম উহার";
        String finalText4 = '\n' + "৪. 'মৎস চাষ' ব্যবস্থাপনার উদ্দেশ্যে একটি একক হিসাবে মাছ এক বা একাধিক সংরক্ষন।";
        String finalText5 = '\n' + "৫ 'মাছ ধরা' মানে গ্রহণ বা কোন পদ্ধতিতে মাছ হত্যা, এতে মাছ হত্যা প্রচেষ্টার অন্তর্ভুক্ত হইবে।";




        fish_law.setText(finalText + finalText2 + finalText3 + finalText4 + finalText5);

        //TODO - if time, set Location based notification system HERE
    }
}
