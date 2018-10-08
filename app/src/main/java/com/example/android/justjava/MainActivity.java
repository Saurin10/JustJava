package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    //cupsOfCoffee Global Variable
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //int price = quantity*
        //Name
        String userName = ((EditText) findViewById(R.id.UserName)).getText().toString();
        //CheckBox
        CheckBox whippedCreamStatus = (CheckBox) findViewById(R.id.whippedCreamCheckBox);
        boolean hasWhippedCream = whippedCreamStatus.isChecked();
        int price = calculatePrice();
        boolean hasChocolate = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
        String printMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, userName);
        displayMessage(printMessage);
    }

    /**
     * This method will calculate the total price.
     */
    private int calculatePrice() {
        return (quantity * 5);
    }

    /**
     * This method will give you the order summary.
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate,
                                      String uName) {
        //boolean isChecked = ((CheckBox) findViewById(R.id.whippedCreamCheckBox)).isChecked();
        String name = "Name: " + uName;
        String whippedCream = "Add Whipped Cream? " + hasWhippedCream;
        String chocolate = "Add Chocolate? " + hasChocolate;
        String stringQuantity = "Quantity: " + quantity;
        String total = "Total: $" + price;
        return (name + "\n" + whippedCream + "\n" + chocolate + "\n" + stringQuantity + "\n" + total
                + "\nThank You!");
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the price on screen.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method decrements the price on screen.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}