package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    // VARIABLES
    private int quantity = 2;

    // VIEWS
    @BindView(R.id.whipped_checkbox)
    CheckBox whippedCheckBox;
    @BindView(R.id.chocolate_checkbox)
    CheckBox chocolateCheckBox;
    @BindView(R.id.editTextName)
    EditText editTxtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        boolean hasWhippedCream = whippedCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int total = calculatePrice(hasWhippedCream, hasChocolate);
        String custName = editTxtName.getText().toString();
        emailOrderSummary(custName, total, hasWhippedCream, hasChocolate);
    }

    /**
     * This method increments quantity
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.max_coffee, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            quantity++;
        }
    }

    /**
     * This method decrements quantity
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.min_coffee, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            quantity--;
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @param whipped whether or not whipped cream is added
     * @param chocolate whether or not chocolate is added
     * @return total price
     */
    private int calculatePrice(boolean whipped, boolean chocolate) {
        int basePrice = 5;
        if (chocolate) {
            basePrice += 2;
        }
        if (whipped) {
            basePrice += 1;
        }
        return basePrice * quantity;
    }

    /**
     * @param orderTotal      total price of order
     * @param addWhippedCream whether the user wants whipped cream or not
     * @param addChocolate    whether the user wants chocolate or not
     */
    private void emailOrderSummary(String custName, int orderTotal, boolean addWhippedCream, boolean addChocolate) {
        String subject = getString(R.string.email_subject) + custName;
        String priceMessage = getString(R.string.order_name) + custName;
        priceMessage += "\n"+getString(R.string.added_whipped_cream) + addWhippedCream;
        priceMessage += "\n"+getString(R.string.added_chocolate) + addChocolate;
        priceMessage += "\n"+getString(R.string.order_quantity) + quantity;
        priceMessage += "\n"+getString(R.string.total_amount) + orderTotal;
        priceMessage += "\n"+getString(R.string.thank_you);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}