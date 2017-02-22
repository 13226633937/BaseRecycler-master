package com.demo.baserecyclerviewadapterhelper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.demo.baserecyclerviewadapterhelper.adapter.ExpandableItemAdapter;
import com.demo.baserecyclerviewadapterhelper.entity.Level0Item;
import com.demo.baserecyclerviewadapterhelper.entity.Level1Item;
import com.demo.baserecyclerviewadapterhelper.entity.Person;
import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.BaseViewHolder;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.chanven.lib.cptr.recyclerview.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableUseActivity extends Activity {
    RecyclerView mRecyclerView;
    private RecyclerAdapterWithHF<BaseViewHolder> mAdapter;
    PtrClassicFrameLayout ptrClassicFrameLayout;
    Handler handler = new Handler();


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_item_use);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) this.findViewById(R.id.test_recycler_view_frame);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<MultiItemEntity> list = generateData();
        ExpandableItemAdapter adapter = new ExpandableItemAdapter(list);
       // adapter.expandAll(3, true);


        mAdapter = new RecyclerAdapterWithHF<BaseViewHolder>(adapter);
        View headView = this.getLayoutInflater().inflate(R.layout.tweet, ptrClassicFrameLayout, false);//头视图
        View headView2 = this.getLayoutInflater().inflate(R.layout.tweet, ptrClassicFrameLayout, false);//头视图
        adapter.addHeaderView(headView);
        adapter.addFooterView(headView2);
        mRecyclerView.setAdapter(mAdapter);


        RE();
    }




    public  void RE(){
        ptrClassicFrameLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                ptrClassicFrameLayout.autoRefresh(true);
            }
        }, 150);

        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLoadMoreEnable(true);
                    }
                }, 1500);
            }
        });

        ptrClassicFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void loadMore() {
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        ptrClassicFrameLayout.loadMoreComplete(true);
                    }
                }, 1000);
            }
        });

    }
    private ArrayList<MultiItemEntity> generateData() {
        int lv0Count = 9;
        int lv1Count = 3;
        int personCount = 5;

        String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce"};
        Random random = new Random();

        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            Level0Item lv0 = new Level0Item("This is " + i + "th item in Level 0", "subtitle of " + i);
            for (int j = 0; j < lv1Count; j++) {
                Level1Item lv1 = new Level1Item("Level 1 item: " + j, "(no animation)");
                for (int k = 0; k < personCount; k++) {
                    lv1.addSubItem(new Person(nameList[k], random.nextInt(40)));
                }
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        return res;
    }
}
