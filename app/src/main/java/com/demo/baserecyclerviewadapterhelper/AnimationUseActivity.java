package com.demo.baserecyclerviewadapterhelper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.demo.baserecyclerviewadapterhelper.adapter.QuickAdapter;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.BaseQuickAdapter;
import com.chanven.lib.cptr.recyclerview.BaseViewHolder;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.chanven.lib.cptr.recyclerview.listener.OnItemChildClickListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class AnimationUseActivity extends Activity {
    private RecyclerView mRecyclerView;
    private QuickAdapter mQuickAdapter;
    PtrClassicFrameLayout ptrClassicFrameLayout;
    private RecyclerAdapterWithHF<BaseViewHolder> mAdapter;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_use);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) this.findViewById(R.id.test_recycler_view_frame);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        initMenu();
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

    private void initAdapter() {
        mQuickAdapter = new QuickAdapter();
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                Toast.makeText(AnimationUseActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }
        });


        mAdapter = new RecyclerAdapterWithHF<BaseViewHolder>(mQuickAdapter);
        View headView = this.getLayoutInflater().inflate(R.layout.tweet, ptrClassicFrameLayout, false);//头视图
        View headView2 = this.getLayoutInflater().inflate(R.layout.tweet, ptrClassicFrameLayout, false);//头视图
        mQuickAdapter.addHeaderView(headView);
        mQuickAdapter.addFooterView(headView2);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initMenu() {
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("AlphaIn", "ScaleIn", "SlideInBottom", "SlideInLeft", "SlideInRight", "Custom");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                switch (position) {

                }
                mRecyclerView.setAdapter(mQuickAdapter);
            }
        });
        MaterialSpinner spinnerFirstOnly = (MaterialSpinner) findViewById(R.id.spinner_first_only);
        spinnerFirstOnly.setItems("isFirstOnly(true)", "isFirstOnly(false)");
        spinnerFirstOnly.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                switch (position) {
                    case 0:
                        mQuickAdapter.isFirstOnly(true);
                        break;
                    case 1:
                        mQuickAdapter.isFirstOnly(false);
                        break;
                    default:
                        break;
                }
                mQuickAdapter.notifyDataSetChanged();
            }
        });
    }

}
