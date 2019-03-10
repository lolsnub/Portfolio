package database.c347.soi.rp.edu.sg.portfolio.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.c347.soi.rp.edu.sg.portfolio.Adapter.ExpandableListAdapter;
import database.c347.soi.rp.edu.sg.portfolio.R;

public class MainActivity extends AppCompatActivity {

    ExpandableListView ELVlist;
    ExpandableListAdapter adapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listHash;
    int lastExpandedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ELVlist = (ExpandableListView) findViewById(R.id.ELVlist);
        initData();
        adapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        ELVlist.setAdapter(adapter);

        ELVlist.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    ELVlist.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        ELVlist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if(listHash.get(listDataHeader.get(i)).get(i1).equals("Profile View")){
                    nextActivity(FirstActivity.class, "Profile View");
                }
                return true;
            }
        });
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Portfolio");
        List<String> title1 = new ArrayList<>();

        title1.add("Profile View");

        listHash.put(listDataHeader.get(0), title1);

    }

    private void nextActivity(Class c, String title){
        Intent i = new Intent(MainActivity.this, c);
        i.putExtra("Title", title);
        startActivity(i);
    }
}
