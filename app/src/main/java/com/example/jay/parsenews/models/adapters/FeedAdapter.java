package com.example.jay.parsenews.models.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jay.parsenews.R;
import com.example.jay.parsenews.controllers.Controller;
import com.example.jay.parsenews.models.pojo.Items;
import com.example.jay.parsenews.models.pojo.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jay on 5/7/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.Holder> {

    public static String TAG = FeedAdapter.class.getSimpleName();
    private List<Result> mList;
    private Controller.FeedCallbackListener mListener;

    public FeedAdapter(Controller.FeedCallbackListener listener,
                         List<Result> list) {
        this.mListener = listener;
        mList = list;
    }

    public void addItem(Result result) {
        //List<Result> list = mItems.getResult();
        mList.add(result);
        notifyDataSetChanged();
    }

    public void addItems(List<Result> results) {
        //List<Result> mResults = mItems.getResult();
        mList.addAll(results);
        notifyDataSetChanged();
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        //List<Result> mResultList = mItems.getResult();
        Result mResult = mList.get(position);
        holder.mTitle.setText(mResult.getTitle());
        holder.mType.setText(mResult.getType());
        holder.mLink.setText(mResult.getLink());
        holder.mPublishAt.setText(""+ mResult.getPublished_at());

        Picasso.with(holder.itemView.getContext())
                .load(mResult.getMain_image().getOriginal_url())
                .centerCrop()
                .fit()
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        public TextView mTitle;

        @BindView(R.id.type)
        public TextView mType;

        @BindView(R.id.link)
        public TextView mLink;

        @BindView(R.id.publish_at)
        public TextView mPublishAt;

        @BindView(R.id.image)
        public ImageView mImage;

        @BindView(R.id.cont_item_root)
        public CardView mContainer;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition());
        }
    }
}
