package database.c347.soi.rp.edu.sg.portfolio.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TitleModel {
    String title;
    ArrayList<GridModel> arrayListGrid;
    ArrayList<PostModel> arrayListPost;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<GridModel> getArrayListGrid() {
        return arrayListGrid;
    }

    public void setArrayListGrid(ArrayList<GridModel> arrayListGrid) {
        this.arrayListGrid = arrayListGrid;
    }

    public ArrayList<PostModel> getArrayListPost() {
        return arrayListPost;
    }

    public void setArrayListPost(ArrayList<PostModel> arrayListPost) {
        this.arrayListPost = arrayListPost;
    }
}
