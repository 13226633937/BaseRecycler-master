package com.demo.baserecyclerviewadapterhelper.adapter;

import android.content.Context;

import com.demo.baserecyclerviewadapterhelper.R;
import com.demo.baserecyclerviewadapterhelper.entity.MultipleItem;
import com.chanven.lib.cptr.recyclerview.BaseMultiItemQuickAdapter;
import com.chanven.lib.cptr.recyclerview.BaseViewHolder;


import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        addItemType(MultipleItem.TEXT, R.layout.item_text_view);
        addItemType(MultipleItem.IMG, R.layout.item_image_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
                helper.setText(R.id.tv, item.getContent());
                break;
            case MultipleItem.IMG:
                // set img data
                break;
        }
    }

}
