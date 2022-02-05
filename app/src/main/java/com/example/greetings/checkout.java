package com.example.greetings;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Savepoint;

public class checkout extends AppCompatActivity {
    public static final String key1 = "TOTAL";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.checkout_page);

        Intent intent = getIntent();
        String totalAmount = intent.getStringExtra(checkout.key1);
        if (! totalAmount.isEmpty() ) {
            TextView tAmmount = (TextView) findViewById(R.id.totalCost);
            tAmmount.setText(totalAmount);
        }

        Button closeBtn = (Button) findViewById(R.id.submitButton);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.checkout_page));
    }

    //Checks to see if textfield values are correct
    public void submit(View view) {
        EditText cardNumber = findViewById(R.id.cardnumber);
        EditText expiraryDate = findViewById(R.id.expirarydate);

        String cnumberString = cardNumber.getText().toString();
        String exdateString = expiraryDate.getText().toString();

        if (!(cnumberString.length() <= 8 && !cnumberString.isEmpty())) {
            finish();
            System.exit(0);

        }else if (!(exdateString.isEmpty())) {
            finish();
            System.exit(0);

        }else
            Toast.makeText(checkout.this, "Your card number may be too short or your date is incorrect please try again", Toast.LENGTH_LONG).show();
        finish();
        System.exit(0);

    }
}
