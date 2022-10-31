package com.mahmoudashraf.core.view.pagination

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class EndlessRecyclerViewScrollListener : RecyclerView.OnScrollListener {
  // The minimum amount of items to have below your current scroll position
  // before loading more.
  private var visibleThreshold = 5

  // The total number of items in the dataset after the last load
  private var previousTotalItemCount = 0

  // True if we are still waiting for the last set of data to load.
  private var loading = true

  // Sets the starting page index
  private val startingPageIndex = 0
  var mLayoutManager: RecyclerView.LayoutManager

  constructor(layoutManager: LinearLayoutManager) {
    mLayoutManager = layoutManager
  }

  constructor(layoutManager: GridLayoutManager) {
    mLayoutManager = layoutManager
    visibleThreshold *= layoutManager.spanCount
  }

  constructor(layoutManager: StaggeredGridLayoutManager) {
    mLayoutManager = layoutManager
    visibleThreshold *= layoutManager.spanCount
  }

  private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
    var maxSize = 0
    for (i in lastVisibleItemPositions.indices) {
      if (i == 0) {
        maxSize = lastVisibleItemPositions[i]
      } else if (lastVisibleItemPositions[i] > maxSize) {
        maxSize = lastVisibleItemPositions[i]
      }
    }
    return maxSize
  }

  // This happens many times a second during a scroll, so be wary of the code you place here.
  // We are given a few useful parameters to help us work out if we need to load some more data,
  // but first we check if we are waiting for the previous load to finish.
  override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
    var lastVisibleItemPosition = 0
    val totalItemCount: Int = mLayoutManager.getItemCount()
    if (mLayoutManager is StaggeredGridLayoutManager) {
      val lastVisibleItemPositions: IntArray =
        (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
      // get maximum element within the list
      lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
    } else if (mLayoutManager is GridLayoutManager) {
      lastVisibleItemPosition =
        (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
    } else if (mLayoutManager is LinearLayoutManager) {
      lastVisibleItemPosition =
        (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
    }

    // If it’s still loading, we check to see if the dataset count has
    // changed, if so we conclude it has finished loading and update the current page
    // number and total item count.
    if (loading && totalItemCount > previousTotalItemCount) {
      loading = false
      previousTotalItemCount = totalItemCount
    }

    // If it isn’t currently loading, we check to see if we have breached
    // the visibleThreshold and need to reload more data.
    // If we do need to reload some more data, we execute onLoadMore to fetch the data.
    // threshold should reflect how many total columns there are too
    if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
      onLoadMore()
      loading = true
    }
  }

  // Defines the process for actually loading more data based on page
  abstract fun onLoadMore()
}

fun RecyclerView.setOnLoadMoreListener(onLoadMore: () -> Unit) {
  addOnScrollListener(object :
      EndlessRecyclerViewScrollListener(this.layoutManager as GridLayoutManager) {
      override fun onLoadMore() {
        onLoadMore()
      }
    })
}
