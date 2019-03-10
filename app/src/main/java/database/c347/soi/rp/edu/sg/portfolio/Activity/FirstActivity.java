package database.c347.soi.rp.edu.sg.portfolio.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import database.c347.soi.rp.edu.sg.portfolio.Adapter.RecyclerviewFirstActivityTitle;
import database.c347.soi.rp.edu.sg.portfolio.Model.GridModel;
import database.c347.soi.rp.edu.sg.portfolio.Model.PostModel;
import database.c347.soi.rp.edu.sg.portfolio.Model.TitleModel;
import database.c347.soi.rp.edu.sg.portfolio.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class FirstActivity extends AppCompatActivity {

    RecyclerView rvList;
    ArrayList<TitleModel> alTitleList;
    RecyclerviewFirstActivityTitle adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        setContentView(R.layout.activity_first);
        setTitle(getIntent().getStringExtra("Title"));

        actionBar.hide();

        rvList = (RecyclerView) findViewById(R.id.rvList);

        alTitleList = new ArrayList<>();
//        alTitleList.add("Photos");
//        alTitleList.add("Reviews");

        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerviewFirstActivityTitle(this, alTitleList);
        rvList.setAdapter(adapter);
        setData();
    }

    private void setData() {
        String[] title = {"Photos", "Reviews"};

        String[] gridImg = {"https://firebasestorage.googleapis.com/v0/b/portfolio-f214c.appspot.com/o/Yukina%2Fy_image_01.png?alt=media&token=62406283-05b5-4bd9-85c4-bc4031bc4fdd",
        "https://firebasestorage.googleapis.com/v0/b/portfolio-f214c.appspot.com/o/Yukina%2Fy_image_02.png?alt=media&token=cb2fdce1-3a8e-45fe-9243-4562da6cbbd3",
        "https://firebasestorage.googleapis.com/v0/b/portfolio-f214c.appspot.com/o/Yukina%2Fy_image_03.png?alt=media&token=8989c19d-3076-4af9-900f-1a12d0871da0",
        "https://firebasestorage.googleapis.com/v0/b/portfolio-f214c.appspot.com/o/Yukina%2Fy_image_04.png?alt=media&token=9060eaed-7759-4248-a6b0-5eab47ebe769",
        "https://firebasestorage.googleapis.com/v0/b/portfolio-f214c.appspot.com/o/Yukina%2Fy_image_05.png?alt=media&token=7f2834cf-4c2d-438b-9fad-63d0c1cd8601",
        "https://firebasestorage.googleapis.com/v0/b/portfolio-f214c.appspot.com/o/Yukina%2Fy_image_06.png?alt=media&token=85115bdc-4585-40d8-82ba-5a18a8ebdeab"};

        for(int i=0; i<title.length; i++){
            TitleModel titleModel = new TitleModel();
            titleModel.setTitle(title[i]);
            if(title[i].equals("Photos")){
                ArrayList<GridModel> listModelArrayGrid = new ArrayList<>();

                for(int j=0; j<gridImg.length; j++){
                    GridModel gridModel = new GridModel();
                    gridModel.setImg(gridImg[j]);
                    listModelArrayGrid.add(gridModel);
                }
                titleModel.setArrayListGrid(listModelArrayGrid);
                alTitleList.add(titleModel);
            } else if(title[i].equals("Reviews")){
                ArrayList<PostModel> postModelArrayList = new ArrayList<>();
                postModelArrayList.add(new PostModel(R.drawable.sayo_image_01, "Sayo", "10.15am yesterday", "Quisque imperdiet nunc at massa dictum volupat. Etiam id orci ipsim. Integer id ex dignissim"));
                postModelArrayList.add(new PostModel(R.drawable.ako_image_02, "Ako", "just now", "Quisque imperdiet nunc at massa dictum volupat. Etiam id orci ipsim. Integer id ex dignissim \"Quisque imperdiet nunc at massa dictum volupat. Etiam id orci ipsim. Integer id ex dignissim\""));
                postModelArrayList.add(new PostModel(R.drawable.lisa_image_01, "Lisa", "just now", "Quisque imperdiet nunc at massa dictum volupat. Etiam id orci ipsim. Integer id ex dignissim"));
                titleModel.setArrayListPost(postModelArrayList);
                alTitleList.add(titleModel);
            }
        }
    }
}
