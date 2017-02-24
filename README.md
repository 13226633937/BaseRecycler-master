# BaseRecycler-master 
Android部件与拉刷新视图,并支持loadMore ListView,RecyclerView,GridView和SwipeRefreshLayout.

在[CommonPullToRefresh ](https://github.com/Chanven/CommonPullToRefresh，感谢作者

在[BaseRecyclerViewAdapterHelper   这个库主要是Adapter的支持不是RecyclerView ](https://github.com/CymChad/BaseRecyclerViewAdapterHelper，感谢作者

结合了BaseRecyclerViewAdapterHelper的Adapter的强大支持和CommonPullToRefresh对下拉刷新加载更多的灵活使用



* 支持CommonPullToRefresh中的所有刷新下拉效果
* 支持所有BaseRecyclerViewAdapterHelper中的RecyclerView功能
* 支持自定义header以及footer


<div> <img src='https://raw.githubusercontent.com/Chanven/CommonPullToRefresh/master/raw/main.png' width='270px'/> </div>

####Demo个别效果截图
 <div> <img src='https://github.com/13226633937/BaseRecycler-master/tree/master/image/01.gif' width="270px"/></div>

 <div> <img src='https://github.com/13226633937/BaseRecycler-master/tree/master/image/02.gif' width='270px'/> </div>

<div> <img src='https://github.com/13226633937/BaseRecycler-master/tree/master/image/03.gif' width='270px'/> </div>


####使用截图
 <div> <img src='https://github.com/13226633937/BaseRecycler-master/tree/master/image/04.gif' width="270px"/></div>

 <div> <img src='https://github.com/13226633937/BaseRecycler-master/tree/master/image/05.gif' width='270px'/> </div>




#### 处理GridLayoutManager问题
在RecyclerAdapterWithHF中重写onAttachedToRecyclerView，onViewAttachedToWindow

 @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                int viewType = getItemViewType(position);
                if (viewType==TYPE_HEADER&&mHeaders.get(position) != null) {
                    return layoutManager.getSpanCount();
                } else if (viewType==TYPE_FOOTER&&mFooters.get(position - getItemCountHF() - mHeaders.size()) != null) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null)
                    return oldLookup.getSpanSize(position);
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(T holder) {
        mAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeader(position) || isFooter(position))
        {
            WrapperUtils.setFullSpan(holder);
        }
    }


## 常见问题
轮播图跟滑动冲突  重写onScroll即可  参考CusPtrClassicFrameLayout.class




 具体可参考Demo

 也可以下载上面两位作者的代码Demo修改相关代码整合
 

