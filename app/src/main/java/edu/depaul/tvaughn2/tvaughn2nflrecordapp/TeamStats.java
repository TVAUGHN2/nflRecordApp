package edu.depaul.tvaughn2.tvaughn2nflrecordapp;

/**
 * Created by Travis on 10/23/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class TeamStats extends Activity{
    private static final String TAG = "TeamStats";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_stats);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            TextView name = (TextView) findViewById(R.id.text1);
            TextView description = (TextView) findViewById(R.id.text2);
            ImageView icon = (ImageView) findViewById(R.id.image);
            Team team = intent.getParcelableExtra("Team");
            name.setText(team.getName());
            description.setText(team.getLongDescription());
            icon.setImageResource(team.getIconResource(team.getType()));
        }
    }

    @Override
    public void finish() {
        Log.d(TAG, "finish()");

        setResult(RESULT_OK);
        super.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
