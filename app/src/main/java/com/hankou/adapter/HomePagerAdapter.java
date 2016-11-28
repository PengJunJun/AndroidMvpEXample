package com.hankou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hankou.R;
import com.hankou.home.model.HomeEntity;
import com.hankou.mine.model.UserEntity;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bykj003 on 2016/9/20.
 */
public class HomePagerAdapter extends RecyclerView.Adapter<HomePagerAdapter.Holder> {

    private Context mContext;

    private List<UserEntity> mList;

    private LayoutInflater mInflater;

    public HomePagerAdapter(Context mContext, List<UserEntity> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<UserEntity> list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_home_pager, parent,false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (holder != null) {
            UserEntity entity = mList.get(position);
            Glide.with(mContext).load(entity.avatar).into(holder.mIvUserAvatar);
            holder.mTvUserName.setText(entity.name);
            holder.mTvUserDesc.setText(entity.desc);
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            mList.size();
        }
        return mList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_user_avatar)
        public ImageView mIvUserAvatar;

        @BindView(R.id.tv_user_name)
        public TextView mTvUserName;

        @BindView(R.id.tv_user_desc)
        public TextView mTvUserDesc;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
