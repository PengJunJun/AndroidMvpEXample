package com.hankou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hankou.R;
import com.hankou.home.model.VideoEntity;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by bykj003 on 2016/11/9.
 */

public class TestVideoAdapter extends BasePagerRecyclerAdapter<List<VideoEntity>>{

    public TestVideoAdapter(Context context,List<VideoEntity> data){
        mContext = context;
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder itemHolder = (Holder) holder;
        if(itemHolder != null){
            VideoEntity videoEntity = (VideoEntity) getItem(position);
            if (videoEntity != null) {
                itemHolder.mVideoView.setUp(videoEntity.url, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
                itemHolder.mTvVideoName.setText(videoEntity.name);
            }
        }
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.videoView)
        public JCVideoPlayerStandard mVideoView;

        @BindView(R.id.tv_video_name)
        public TextView mTvVideoName;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
