package database.c347.soi.rp.edu.sg.portfolio.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import database.c347.soi.rp.edu.sg.portfolio.Model.PostModel;
import database.c347.soi.rp.edu.sg.portfolio.R;

public class RecyclerviewFirstActivityPost extends RecyclerView.Adapter<RecyclerviewFirstActivityPost.MyViewHolder> {

    private Context mContext;
    private ArrayList<PostModel> mData;

    public RecyclerviewFirstActivityPost(Context mContext, ArrayList<PostModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_activity_01_03, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ivTrialIcon.setImageResource(mData.get(position).getImgName());
        holder.tvName.setText(mData.get(position).getName());
        holder.tvPost.setText(mData.get(position).getPost());
        holder.tvTimestamp.setText(mData.get(position).getTimestamp());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView ivTrialIcon;
        TextView tvName,tvTimestamp,tvPost;
        public MyViewHolder(View itemView){
            super(itemView);
            ivTrialIcon = (ImageView) itemView.findViewById(R.id.ivTrialIcon);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTimestamp = (TextView) itemView.findViewById(R.id.tvTimestamp);
            tvPost = (TextView) itemView.findViewById(R.id.tvPost);
        }
    }
}
