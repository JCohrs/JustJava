package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    // VARIABLES
    private int quantity = 2;
    private int price;

    // VIEWS
    @BindView(R.id.quantity_text_view)
    TextView quantityTextView;
    @BindView(R.id.order_summary_text_view)
    TextView orderSummaryTextView;
    @BindView(R.id.whipped_checkbox)
    CheckBox whippedCheckBox;
    @BindView(R.id.chocolate_checkbox)
    CheckBox chocolateCheckBox;

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
        int total = calculatePrice();
        boolean hasWhippedCream = whippedCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        String priceMessage = createOrderSummary(total, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int qnty) {
        quantityTextView.setText(String.valueOf(qnty));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        orderSummaryTextView.setText(message);
    }

    /**
     * This method increments quantity
     */
    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method decrements quantity
     */
    public void decrement(View view) {
        quantity--;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        price = quantity * 5;
        return price;
    }

    /**
     * @param orderTotal      total price of order
     * @param addWhippedCream whether the user wants whipped cream or not
     * @param addChocolate    whether the user wants chocolate or not
     * @return order summary message
     */
    private String createOrderSummary(int orderTotal, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: Kaptain Kunal";
        priceMessage += "\nAdded whipped cream? " + addWhippedCream;
        priceMessage += "\nAdded chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + orderTotal;
        priceMessage += "\nThank you!";
        return priceMessage;
    }
}