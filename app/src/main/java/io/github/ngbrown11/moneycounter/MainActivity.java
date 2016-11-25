package io.github.ngbrown11.moneycounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "io.github.ngbrown11.moneycounter.MESSAGE";
    public static double count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize count
        count = 0.00;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_reset) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendCount(View view) {

        Intent intent = new Intent(this, DisplayCountActivity.class);
        String amount = "";
        try {
            int[] idArray = {R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4,
                    R.id.editText5, R.id.editText6, R.id.editText7};
            double[] denominations = {0.25, 1.00, 5.00, 10.00, 20.00, 50.00, 100.00};

            for(int i = 0; i < idArray.length; i++) {
                EditText editText = (EditText) findViewById(idArray[i]);
                String moneyString = editText.getText().toString();
                double money = (Double.parseDouble(moneyString)) * denominations[i];
                count += money;
                editText.setText("");
            }

            amount = "" + count;
        } catch (NumberFormatException ex) {
            amount = "All fields must be filled before counting. Click RESET for a recount.";
        } finally {
            intent.putExtra(EXTRA_MESSAGE, amount);
            finish();
            startActivity(intent);
        }
    }
}
