package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 2;
    @BindView(R.id.quantity_text_view)
    TextView quantityTextView;
    @BindView(R.id.order_summary_text_view)
    TextView orderSummaryTextView;

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
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
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
        quantity *= 5;
        return quantity;
    }

    /**
     *
     * @param orderTotal total price of order
     * @return order summary message
     */
    private String createOrderSummary(int orderTotal) {
        String priceMessage = "Name: Kaptain Kunal";
        priceMessage+="\nQuantity: " + quantity;
        priceMessage+="\nTotal: $" + orderTotal;
        priceMessage+="\nThank you!";
        return priceMessage;
    }
}