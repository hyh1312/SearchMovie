package com.example.myapplication;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager layoutManager;
    private int itemCount, lastPosition, lastItemCount;
    public abstract void onLoadMore();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            itemCount = layoutManager.getItemCount();
            lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
        } else {
            Log.e("OnLoadMoreListener", "The OnLoadMoreListener only support LinearLayoutManager");
            return;
        }

        if (lastItemCount != itemCount && lastPosition == itemCount - 1) {
            //lastItemCount 是为了防止加载数据后，位置仍然符合lastPosition == itemCount - 1，因此会继续加载
            lastItemCount = itemCount;
            this.onLoadMore();
        }
    }
}

