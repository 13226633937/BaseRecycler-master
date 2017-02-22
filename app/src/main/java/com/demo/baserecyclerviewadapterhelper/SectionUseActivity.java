package com.demo.baserecyclerviewadapterhelper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.demo.baserecyclerviewadapterhelper.adapter.SectionAdapter;
import com.demo.baserecyclerviewadapterhelper.data.DataServer;
import com.demo.baserecyclerviewadapterhelper.entity.MySection;
import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.BaseQuickAdapter;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.chanven.lib.cptr.recyclerview.listener.OnItemClickListener;


import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SectionUseActivity extends Activity {
    private RecyclerView mRecyclerView;
    private List<MySection> mData;
    Handler handler = new Handler();

    private RecyclerAdapterWithHF mAdapter;
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_uer);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) this.findViewById(R.id.test_recycler_view_frame);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mData = DataServer.getSampleData();
        SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.def_section_head, mData);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                MySection mySection = mData.get(position);
                if (mySection.isHeader)
                    Toast.makeText(SectionUseActivity.this, mySection.header, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SectionUseActivity.this, mySection.t.getName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(SectionUseActivity.this, "onItemChildClick" + position, Toast.LENGTH_LONG).show();
            }


        });

        mAdapter = new RecyclerAdapterWithHF(sectionAdapter);

        View headView = this.getLayoutInflater().inflate(R.layout.tweet, ptrClassicFrameLayout, false);//头视图
        sectionAdapter.addHeaderView(headView);


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
}
