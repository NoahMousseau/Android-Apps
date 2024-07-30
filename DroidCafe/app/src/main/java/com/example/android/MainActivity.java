package com.example.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.android.droidcafe.extra.MESSAGE";
    private String mOrderMessage;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        int shoppingCartIconId = getResources().getIdentifier("ic_shopping_cart", "drawable", getPackageName());
        fab.setImageResource(shoppingCartIconId);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
            startActivity(intent);
        });

        PreferenceManager.setDefaultValues(this, R.xml.header_preferences, false);
        PreferenceManager.setDefaultValues(this, R.xml.messages_preferences, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_account, false);

        // Load preferences and display them in a Toast message
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String marketPref = sharedPref.getString("market", "-1");
        String deliveryMethodPref = sharedPref.getString("delivery_method", "same_day");

        displayToast("Market: " + marketPref + ", Delivery Method: " + deliveryMethodPref);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu, adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar item clicks here, the action bar will automatically handle clicks on the Home/Up button
        //as long as you specify a parent activity in AndroidManifest.xml
        int itemId = item.getItemId();

        if (itemId == R.id.action_order) {
            displayToast(getString(R.string.action_order_message));
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
            startActivity(intent);
        } else if (itemId == R.id.action_status) {
            displayToast(getString(R.string.action_status_message));
        } else if (itemId == R.id.action_favorites) {
            displayToast(getString(R.string.action_favorites_message));
        } else if (itemId == R.id.action_contact) {
            displayToast(getString(R.string.action_contact_message));
        } else if (itemId == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        } else {
            //Left empty
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a message that the donut image was clicked.
     */
    public void showDonutOrder(View view) {
        mOrderMessage = getString(R.string.donut_order_message);
        displayToast(mOrderMessage);
    }
    /**
     * Shows a message that the ice cream sandwich image was clicked.
     */
    public void showIceCreamOrder(View view) {
        mOrderMessage = getString(R.string.ice_cream_order_message);
        displayToast(mOrderMessage);
    }
    /**
     * Shows a message that the froyo image was clicked.
     */
    public void showFroyoOrder(View view) {
        mOrderMessage = getString(R.string.froyo_order_message);
        displayToast(mOrderMessage);
    }
}