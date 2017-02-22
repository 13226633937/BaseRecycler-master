package com.demo.baserecyclerviewadapterhelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.demo.baserecyclerviewadapterhelper.adapter.ItemClickAdapter;
import com.demo.baserecyclerviewadapterhelper.entity.ClickEntity;
import com.chanven.lib.cptr.recyclerview.BaseQuickAdapter;
import com.chanven.lib.cptr.recyclerview.listener.OnItemClickListener;


import java.util.ArrayList;
import java.util.List;

public class ItemClickActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ItemClickAdapter itemClickAdapter;
    private static final int PAGE_SIZE = 10;
    private static String TAG = "RecyclerClickItemActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_click);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Log.d(TAG, "SimpleOnItemClick: ");
            }
        });


    }

    private void initAdapter() {
        List<ClickEntity> data = new ArrayList<>();
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_CHILD_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_CHILD_VIEW));
        itemClickAdapter = new ItemClickAdapter(data);
        mRecyclerView.setAdapter(itemClickAdapter);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

}
