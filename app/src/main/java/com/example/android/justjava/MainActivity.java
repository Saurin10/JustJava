package com.example.android.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
        CheckBox whippedCreamStatus = findViewById(R.id.whippedCreamCheckBox);
        boolean hasWhippedCream = whippedCreamStatus.isChecked();
        boolean hasChocolate = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String printMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, userName);
        //displayMessage(printMessage);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "saurinhdave@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Test Android Intent");
        intent.putExtra(Intent.EXTRA_TEXT, printMessage);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method will calculate the total price.
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5;
        if(hasWhippedCream) {
            basePrice += 1;
        }
        if(hasChocolate) {
            basePrice += 2;
        }
        return (quantity * basePrice);
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
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the price on screen.
     */
    public void increment(View view) {
        if (quantity == 10) {
            quantity = 10;
        }
        else {
            quantity = quantity + 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method decrements the price on screen.
     */
    public void decrement(View view) {
        if(quantity == 1) {
            quantity = 1;
        }
        else {
            quantity -= 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}