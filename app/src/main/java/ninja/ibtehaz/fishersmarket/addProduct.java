package ninja.ibtehaz.fishersmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        EditText name, description, amount, price;

        Button add = (Button)findViewById(R.id.addProduct);

        name = (EditText)findViewById(R.id.name);
        description = (EditText)findViewById(R.id.description);
        amount = (EditText)findViewById(R.id.amount);
        price = (EditText)findViewById(R.id.price);

        String val1 = name.getText().toString();
        String val2 = description.getText().toString();
        String val3 = amount.getText().toString();
        String val4 = price.getText().toString();


        String []array = new String[4];
        if(val1.equals("") || val2.equals("") || val3.equals("") || val4.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Missing Information", Toast.LENGTH_LONG).show();
        }
        else {
            array[0] = val1;
            array[1] = val2;
            array[2] = val3;
            array[3] = val4;
        }

        final Intent i = new Intent(this, marketplace.class);
        i.putExtra("Product", array);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });



    }
}
