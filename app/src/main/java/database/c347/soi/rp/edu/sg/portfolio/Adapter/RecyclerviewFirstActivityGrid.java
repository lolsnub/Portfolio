package database.c347.soi.rp.edu.sg.portfolio.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import database.c347.soi.rp.edu.sg.portfolio.Model.GridModel;
import database.c347.soi.rp.edu.sg.portfolio.R;

public class RecyclerviewFirstActivityGrid extends RecyclerView.Adapter<RecyclerviewFirstActivityGrid.MyViewHolder> {

    private Context mContext;
    private ArrayList<GridModel> mData;

    public RecyclerviewFirstActivityGrid(Context context, ArrayList<GridModel> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_activity_01_02, parent,false);
        return new RecyclerviewFirstActivityGrid.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        String imgURL = "https://static.zerochan.net/Nakamura.Sumikage.full.1947241.jpg";
        GridModel gridModel = mData.get(position);
//        holder.ivListImg.setImageResource(gridModel.getImg());
        Picasso.get().setIndicatorsEnabled(true);
        Picasso.get().load(gridModel.getImgURL()).into(holder.ivListImg);
        if(gridModel == mData.get(getItemCount() -1)){
            holder.tvDisplayMore.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView ivListImg;
        TextView tvDisplayMore;
        public MyViewHolder(View itemView){
            super(itemView);
            ivListImg = (ImageView) itemView.findViewById(R.id.ivListImg);
            tvDisplayMore = (TextView) itemView.findViewById(R.id.tvDisplayMore);
        }
    }
}
