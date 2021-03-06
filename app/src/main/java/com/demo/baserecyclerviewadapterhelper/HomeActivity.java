package com.demo.baserecyclerviewadapterhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.demo.baserecyclerviewadapterhelper.adapter.HomeAdapter;
import com.demo.baserecyclerviewadapterhelper.entity.HomeItem;
import com.chanven.lib.cptr.recyclerview.BaseQuickAdapter;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.chanven.lib.cptr.recyclerview.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HomeActivity extends Activity {
    private static final Class<?>[] ACTIVITY = {AnimationUseActivity.class, MultipleItemUseActivity.class,  ItemDragAndSwipeUseActivity.class,ExpandableUseActivity.class};
    private static final String[] TITLE = {"HeaderAndFooterAnimation Use", "MultipleItem Use", "ItemDragAndSwipe Use","ExpandableItem Activity",};
    private static final String[] COLOR_STR = {"#0dddb8","#0bd4c3","#03cdcd","#00b1c5","#04b2d1","#04b2d1","#04b2d1","#04b2d1", "#04b2d1","#04b2d1", "#04b2d1"};
    private ArrayList<HomeItem> mDataList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.home_item_view, mDataList);
//        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void SimpleOnItemClick(RecyclerAdapterWithHF adapter, View view, int position) {
//                Intent intent = new Intent(HomeActivity.this, ACTIVITY[position]);
//                startActivity(intent);
//            }
//        });

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HomeActivity.this, ACTIVITY[position]);
                startActivity(intent);
            }
        });

        RecyclerAdapterWithHF ff=new RecyclerAdapterWithHF(homeAdapter);

        mRecyclerView.setAdapter(ff);
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setActivity(ACTIVITY[i]);
            item.setColorStr(COLOR_STR[i]);
            mDataList.add(item);
        }
    }

}
