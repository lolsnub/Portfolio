package database.c347.soi.rp.edu.sg.portfolio.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import database.c347.soi.rp.edu.sg.portfolio.Model.GridModel;
import database.c347.soi.rp.edu.sg.portfolio.Model.PostModel;
import database.c347.soi.rp.edu.sg.portfolio.Model.TitleModel;
import database.c347.soi.rp.edu.sg.portfolio.R;

public class RecyclerviewFirstActivityTitle extends RecyclerView.Adapter<RecyclerviewFirstActivityTitle.MyViewHolder> {

    private Context mContext;
    private ArrayList<TitleModel> mData;


    public RecyclerviewFirstActivityTitle(Context mContext, ArrayList<TitleModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_activity_01_01, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final TitleModel titleModel = mData.get(position);
        String title = titleModel.getTitle();
        ArrayList<GridModel> alGridModel = titleModel.getArrayListGrid();
        ArrayList<PostModel> alPostModel = titleModel.getArrayListPost();

        holder.tvTitle.setText(title);
        RecyclerviewFirstActivityGrid recyclerViewFirstActivityGrid = new RecyclerviewFirstActivityGrid(mContext, alGridModel);
        RecyclerviewFirstActivityPost recyclerviewFirstActivityPost = new RecyclerviewFirstActivityPost(mContext, alPostModel);

        if(titleModel.getTitle().equals("Photos")){
            holder.rvDifferentList.setHasFixedSize(true);
            holder.rvDifferentList.setLayoutManager(new GridLayoutManager(mContext, 3));
            holder.rvDifferentList.setAdapter(recyclerViewFirstActivityGrid);
        }

        if(titleModel.getTitle().equals("Reviews")){
            holder.rvDifferentList.setHasFixedSize(true);
            holder.rvDifferentList.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            holder.rvDifferentList.setAdapter(recyclerviewFirstActivityPost);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        RecyclerView rvDifferentList;
        public MyViewHolder(View itemView){
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            rvDifferentList = (RecyclerView) itemView.findViewById(R.id.rvDifferentList);
        }
    }
}
