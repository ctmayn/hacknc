package hacknc.com.poolit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    static String TAG = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnDoSomething= (Button)(findViewById(R.id.theBestButton));
        btnDoSomething. setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG, "Button was pressed.");
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; This adds items to the action bar if it's present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle action bar item clicks here. This action bar
        //will automatically handle clicks on the home/Up button
        //so long as ou specify a parent activity in AndroidManifestxml
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;

        }
        return super.onOptionsItemSelected(item);

    }
}
