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
 * Created by bykj003 on 2016/10/26.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.Holder> {

    private Context mContext;

    private List<VideoEntity> mVideoList;

    private LayoutInflater mInflater;

    public VideoAdapter(Context context, List<VideoEntity> videoList) {
        this.mContext = context;
        this.mVideoList = videoList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<VideoEntity> videoList) {
        mVideoList.clear();
        if (videoList != null) {
            mVideoList.addAll(videoList);
        }
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (holder != null) {
            VideoEntity videoEntity = mVideoList.get(position);
            if (videoEntity != null) {
                holder.mVideoView.setUp(videoEntity.url, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
                holder.mTvVideoName.setText(videoEntity.name);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
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
