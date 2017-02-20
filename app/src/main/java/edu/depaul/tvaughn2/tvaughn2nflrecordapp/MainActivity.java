package edu.depaul.tvaughn2.tvaughn2nflrecordapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends ListActivity {

    private static final String TAG = "MyActivity";

    private Team selectedTeam;
    private static final int STATS = 100; // request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new TeamAdapter(this));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "onListItemClick position=" + position + " id=" + id + " " + TEAMS[position].getName());
        selectedTeam = TEAMS[position];
        Intent intent = new Intent(MainActivity.this, TeamStats.class);
        intent.putExtra("Team", selectedTeam);
        startActivityForResult(intent, STATS);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        if (requestCode == STATS) {
            if (resultCode == RESULT_OK) {
                ((TeamAdapter) getListAdapter()).notifyDataSetChanged();
            }
        }
    }

    static class TeamAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Map<Team.Type, Bitmap> icons;

        TeamAdapter(Context context) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            icons = new HashMap<Team.Type, Bitmap>();
            for (Team.Type type : Team.Type.values()) {
                icons.put(type, BitmapFactory.decodeResource(context.getResources(),
                        Team.getIconResource(type)));
            }
        }

        public int getCount() {
            return TEAMS.length;
        }

        @Override
        public Object getItem(int i) {return TEAMS[i];}

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            View row = convertView;
            if (row == null) {
                row = inflater.inflate(R.layout.team_stats, parent, false);
                holder = new ViewHolder();
                holder.icon = (ImageView) row.findViewById(R.id.image);
                holder.name = (TextView) row.findViewById(R.id.text1);
                holder.description = (TextView) row.findViewById(R.id.text2);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            Team team = TEAMS[position];
            holder.name.setText(team.getName());
            holder.description.setText(team.getShortDescription());
            holder.icon.setImageBitmap(icons.get(team.getType()));
            return row;
        }

        static class ViewHolder {
            TextView name;
            TextView description;
            ImageView icon;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private static final Team[] TEAMS = {
            new Team("Minnesota Vikings",
                    Team.Type.NFC,
                    "Record:  5-0",
                    "\nWin/Loss%:  1\nPoints For:  119\nPoints Against:  63\nPoints Differential:  56\nMargin of Victory:  11.2\nStrength of Schedule:  -2.1\nSimple Rating System:  9.1\nOffensive SRS:  0.3\nDefensive SRS:  8.8"),

            new Team("New England Patriots",
                    Team.Type.AFC,
                    "Record:  5-1",
                    "\nWin/Loss%:  0.833\nPoints For:  149\nPoints Against:  91\nPoints Differential:  58\nMargin of Victory:  11.2\nStrength of Schedule:  -1\nSimple Rating System:  8.7\nOffensive SRS:  1.6\nDefensive SRS:  7.1"),

            new Team("Dallas Cowboys",
                    Team.Type.NFC,
                    "Record:  5-1",
                    "\nWin/Loss%:  0.833\nPoints For:  159\nPoints Against:  107\nPoints Differential:  52\nMargin of Victory:  11.2\nStrength of Schedule:  -2.4\nSimple Rating System:  6.3\nOffensive SRS:  2.4\nDefensive SRS:  4"),

            new Team("Seattle Seahawks",
                    Team.Type.NFC,
                    "Record:  4-1",
                    "\nWin/Loss%:  0.8\nPoints For:  105\nPoints Against:  78\nPoints Differential:  27\nMargin of Victory:  11.2\nStrength of Schedule:  -3.1\nSimple Rating System:  2.3\nOffensive SRS:  -5.3\nDefensive SRS:  7.6"),


            new Team("Buffalo Bills",
                    Team.Type.AFC,
                    "Record:  4-2",
                    "\nWin/Loss%:  0.667\nPoints For:  162\nPoints Against:  103\nPoints Differential:  59\nMargin of Victory:  11.2\nStrength of Schedule:  -0.2\nSimple Rating System:  9.6\nOffensive SRS:  5.1\nDefensive SRS:  4.6"),

            new Team("Pittsburgh Steelers",
                    Team.Type.AFC,
                    "Record:  4-2",
                    "\nWin/Loss%:  0.667\nPoints For:  154\nPoints Against:  123\nPoints Differential:  31\nMargin of Victory:  11.2\nStrength of Schedule:  0.3\nSimple Rating System:  5.5\nOffensive SRS:  3.7\nDefensive SRS:  1.8"),

            new Team("Houston Texans",
                    Team.Type.AFC,
                    "Record:  4-2",
                    "\nWin/Loss%:  0.667\nPoints For:  108\nPoints Against:  127\nPoints Differential:  -19\nMargin of Victory:  11.2\nStrength of Schedule:  -0.6\nSimple Rating System:  -3.8\nOffensive SRS:  -3.5\nDefensive SRS:  -0.3"),

            new Team("Denver Broncos",
                    Team.Type.AFC,
                    "Record:  4-2",
                    "\nWin/Loss%:  0.667\nPoints For:  140\nPoints Against:  108\nPoints Differential:  32\nMargin of Victory:  11.2\nStrength of Schedule:  -1.7\nSimple Rating System:  3.7\nOffensive SRS:  -3.8\nDefensive SRS:  7.5"),

            new Team("Oakland Raiders",
                    Team.Type.AFC,
                    "Record:  4-2",
                     "\nWin/Loss%:  0.667\nPoints For:  152\nPoints Against:  163\nPoints Differential:  -11\nMargin of Victory:  11.2\nStrength of Schedule:  0.3\nSimple Rating System:  -1.6\nOffensive SRS:  1.3\nDefensive SRS:  -2.9"),

            new Team("Washington Redskins",
                    Team.Type.NFC,
                    "Record:  4-2",
                     "\nWin/Loss%:  0.667\nPoints For:  142\nPoints Against:  142\nPoints Differential:  0\nMargin of Victory:  11.2\nStrength of Schedule:  1.2\nSimple Rating System:  1.2\nOffensive SRS:  2.4\nDefensive SRS:  -1.2"),

            new Team("Green Bay Packers",
                    Team.Type.NFC,
                    "Record:  4-2",
                     "\nWin/Loss%:  0.667\nPoints For:  140\nPoints Against:  123\nPoints Differential:  17\nMargin of Victory:  11.2\nStrength of Schedule:  -1.6\nSimple Rating System:  1.2\nOffensive SRS:  1\nDefensive SRS:  0.3"),

            new Team("Atlanta Falcons",
                    Team.Type.NFC,
                    "Record:  4-2",
                     "\nWin/Loss%:  0.667\nPoints For:  199\nPoints Against:  166\nPoints Differential:  33\nMargin of Victory:  11.2\nStrength of Schedule:  0\nSimple Rating System:  5.5\nOffensive SRS:  10.2\nDefensive SRS:  -4.7"),

            new Team("Kansas City Chiefs",
                    Team.Type.AFC,
                    "Record:  3-2",
                     "\nWin/Loss%:  0.6\nPoints For:  109\nPoints Against:  102\nPoints Differential:  7\nMargin of Victory:  11.2\nStrength of Schedule:  -0.4\nSimple Rating System:  1\nOffensive SRS:  -2.3\nDefensive SRS:  3.3"),

            new Team("Philadelphia Eagles",
                    Team.Type.NFC,
                    "Record:  3-2",
                     "\nWin/Loss%:  0.6\nPoints For:  135\nPoints Against:  78\nPoints Differential:  57\nMargin of Victory:  11.2\nStrength of Schedule:  -1.2\nSimple Rating System:  10.2\nOffensive SRS:  2.4\nDefensive SRS:  7.8"),

            new Team("Baltimore Ravens",
                    Team.Type.AFC,
                    "Record:  3-3",
                     "\nWin/Loss%:  0.5\nPoints For:  117\nPoints Against:  115\nPoints Differential:  2\nMargin of Victory:  11.2\nStrength of Schedule:  -1\nSimple Rating System:  -0.6\nOffensive SRS:  -4.6\nDefensive SRS:  4"),

            new Team("Tennessee Titans",
                    Team.Type.AFC,
                    "Record:  3-3",
                     "\nWin/Loss%:  0.5\nPoints For:  120\nPoints Against:  127\nPoints Differential:  -7\nMargin of Victory:  11.2\nStrength of Schedule:  -1.1\nSimple Rating System:  -2.2\nOffensive SRS:  -3.7\nDefensive SRS:  1.5"),

            new Team("New York Giants",
                    Team.Type.NFC,
                    "Record:  3-3",
                     "\nWin/Loss%:  0.5\nPoints For:  116\nPoints Against:  131\nPoints Differential:  -15\nMargin of Victory:  11.2\nStrength of Schedule:  2.4\nSimple Rating System:  -0.1\nOffensive SRS:  -2.2\nDefensive SRS:  2.1"),

            new Team("Detroit Lions",
                     Team.Type.NFC,
                     "Record:  3-3",
                     "\nWin/Loss%:  0.5\nPoints For:  150\nPoints Against:  153\nPoints Differential:  -3\nMargin of Victory:  11.2\nStrength of Schedule:  -1.1\nSimple Rating System:  -1.6\nOffensive SRS:  2.4\nDefensive SRS:  -4"),

            new Team("Los Angeles Rams",
                    Team.Type.NFC,
                    "Record:  3-3",
                     "\nWin/Loss%:  0.5\nPoints For:  110\nPoints Against:  137\nPoints Differential:  -27\nMargin of Victory:  11.2\nStrength of Schedule:  1.8\nSimple Rating System:  -2.7\nOffensive SRS:  -3.7\nDefensive SRS:  1"),

            new Team("Arizona Cardinals",
                    Team.Type.NFC,
                    "Record:  3-3",
                     "\nWin/Loss%:  0.5\nPoints For:  153\nPoints Against:  104\nPoints Differential:  49\nMargin of Victory:  11.2\nStrength of Schedule:  -1.9\nSimple Rating System:  6.3\nOffensive SRS:  1.7\nDefensive SRS:  4.6"),

            new Team("Jacksonville Jaguars",
                    Team.Type.AFC,
                    "Record:  2-3",
                     "\nWin/Loss%:  0.4\nPoints For:  101\nPoints Against:  127\nPoints Differential:  -26\nMargin of Victory:  11.2\nStrength of Schedule:  -3.1\nSimple Rating System:  -8.3\nOffensive SRS:  -4.6\nDefensive SRS:  -3.7"),

            new Team("New Orleans Saints",
                    Team.Type.NFC,
                    "Record:  2-3",
                     "\nWin/Loss%:  0.4\nPoints For:  155\nPoints Against:  168\nPoints Differential:  -13\nMargin of Victory:  11.2\nStrength of Schedule:  -0.2\nSimple Rating System:  -2.8\nOffensive SRS:  5.1\nDefensive SRS:  -7.9"),

            new Team("Tampa Bay Buccaneers",
                    Team.Type.NFC,
                    "Record:  2-3",
                     "\nWin/Loss%:  0.4\nPoints For:  94\nPoints Against:  142\nPoints Differential:  -48\nMargin of Victory:  11.2\nStrength of Schedule:  2.8\nSimple Rating System:  -6.8\nOffensive SRS:  -3\nDefensive SRS:  -3.8"),

            new Team("Miami Dolphins",
                    Team.Type.AFC,
                    "Record:  2-4",
                     "\nWin/Loss%:  0.333\nPoints For:  118\nPoints Against:  134\nPoints Differential:  -16\nMargin of Victory:  11.2\nStrength of Schedule:  0.9\nSimple Rating System:  -1.8\nOffensive SRS:  -1.4\nDefensive SRS:  -0.4"),

            new Team("Cincinnati Bengals",
                    Team.Type.AFC,
                    "Record:  2-4",
                     "\nWin/Loss%:  0.333\nPoints For:  109\nPoints Against:  145\nPoints Differential:  -36\nMargin of Victory:  11.2\nStrength of Schedule:  3.8\nSimple Rating System:  -2.2\nOffensive SRS:  -1.4\nDefensive SRS:  -0.8"),

            new Team("Indianapolis Colts",
                    Team.Type.AFC,
                    "Record:  2-4",
                     "\nWin/Loss%:  0.333\nPoints For:  160\nPoints Against:  174\nPoints Differential:  -14\nMargin of Victory:  11.2\nStrength of Schedule:  -2.9\nSimple Rating System:  -5.2\nOffensive SRS:  3.1\nDefensive SRS:  -8.3"),

            new Team("San Diego Chargers",
                    Team.Type.AFC,
                    "Record:  2-4",
                     "\nWin/Loss%:  0.333\nPoints For:  173\nPoints Against:  155\nPoints Differential:  18\nMargin of Victory:  11.2\nStrength of Schedule:  -2.2\nSimple Rating System:  0.8\nOffensive SRS:  4\nDefensive SRS:  -3.2"),

            new Team("New York Jets",
                    Team.Type.AFC,
                    "Record:  1-5",
                     "\nWin/Loss%:  0.167\nPoints For:  95\nPoints Against:  164\nPoints Differential:  -69\nMargin of Victory:  11.2\nStrength of Schedule:  5\nSimple Rating System:  -6.5\nOffensive SRS:  -2.9\nDefensive SRS:  -3.6"),

            new Team("Carolina Panthers",
                    Team.Type.NFC,
                    "Record:  1-5",
                     "\nWin/Loss%:  0.167\nPoints For:  161\nPoints Against:  176\nPoints Differential:  -15\nMargin of Victory:  11.2\nStrength of Schedule:  0.4\nSimple Rating System:  -2.1\nOffensive SRS:  2.7\nDefensive SRS:  -4.8"),

            new Team("San Francisco 49ers",
                    Team.Type.NFC,
                    "Record:  1-5",
                     "\nWin/Loss%:  0.167\nPoints For:  127\nPoints Against:  185\nPoints Differential:  -58\nMargin of Victory:  11.2\nStrength of Schedule:  3.3\nSimple Rating System:  -6.4\nOffensive SRS:  1.1\nDefensive SRS:  -7.5"),

            new Team("Chicago Bears",
                    Team.Type.NFC,
                    "Record:  1-6",
                     "\nWin/Loss%:  0.143\nPoints For:  111\nPoints Against:  169\nPoints Differential:  -58\nMargin of Victory:  11.2\nStrength of Schedule:  0.4\nSimple Rating System:  -7.9\nOffensive SRS:  -7.4\nDefensive SRS:  -0.6"),

            new Team("Cleveland Browns",
                    Team.Type.AFC,
                    "Record:  0-6",
                     "\nWin/Loss%:  0\nPoints For:  113\nPoints Against:  176\nPoints Differential:  -63\nMargin of Victory:  11.2\nStrength of Schedule:  3.8\nSimple Rating System:  -6.7\nOffensive SRS:  -0.3\nDefensive SRS:  -6.4"),



    };
}
