package com.demo.baserecyclerviewadapterhelper.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.demo.baserecyclerviewadapterhelper.R;
import com.demo.baserecyclerviewadapterhelper.data.DataServer;
import com.demo.baserecyclerviewadapterhelper.entity.Status;
import com.demo.baserecyclerviewadapterhelper.transform.GlideCircleTransform;
import com.chanven.lib.cptr.recyclerview.BaseQuickAdapter;
import com.chanven.lib.cptr.recyclerview.BaseViewHolder;


/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class QuickClickAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public QuickClickAdapter() {
        super( R.layout.item, DataServer.getSampleData(100));
    }

    public QuickClickAdapter(int dataSize) {
        super( R.layout.item, DataServer.getSampleData(dataSize));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
//        helper.getConvertView().setBackgroundResource(R.drawable.card_click);
        helper.setText(R.id.tweetName, item.getUserName())
                .setText(R.id.tweetText, item.getText())
                .setText(R.id.tweetDate, item.getCreatedAt())
                .setVisible(R.id.tweetRT, item.isRetweet())
                .addOnClickListener(R.id.tweetAvatar)
                .addOnClickListener(R.id.tweetName)
                .addOnLongClickListener(R.id.tweetText)
               ;
        Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.tweetAvatar));
    }


}
