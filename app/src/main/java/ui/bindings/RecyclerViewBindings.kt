package ui.bindings

import android.content.Context
import android.databinding.BindingAdapter
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.support.v7.widget.RecyclerView.ViewHolder


/** Android databindings for [RecyclerView]*/
class RecyclerViewBindings {

  companion object {

    @JvmStatic
    @BindingAdapter("adapter")
    fun onBindAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<ViewHolder>) {
      recyclerView.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("itemDecoration")
    fun onItemDecoration(recyclerView: RecyclerView, itemDecoration: ItemDecoration) {
      recyclerView.addItemDecoration(itemDecoration)
    }

    @JvmStatic
    fun verticalLinear(context: Context) : RecyclerView.LayoutManager {
      return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    @JvmStatic
    fun verticalGrid(context: Context, columnCount: Int) : RecyclerView.LayoutManager {
      return GridLayoutManager(context, columnCount, GridLayoutManager.VERTICAL, false)
    }

    @JvmStatic
    fun dividerItemDecorationVertical(context: Context): RecyclerView.ItemDecoration {
      return DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    }
  }
}
