package ninja.ibtehaz.fishersmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class marketplace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        Intent i = getIntent();
        String []array = i.getStringArrayExtra("Product");


        TextView product, description, amount, price;

        product = (TextView)findViewById(R.id.product2);
        description = (TextView)findViewById(R.id.description2);
        amount = (TextView)findViewById(R.id.amount2);
        price = (TextView)findViewById(R.id.price2);

        if(array != null) {
            product.setText(array[0]);
            description.setText(array[1]);
            amount.setText(array[2]);
            price.setText(array[3]);
        }



    }
}
