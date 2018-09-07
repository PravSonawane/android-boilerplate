package ui.bindings

import android.databinding.BindingAdapter
import android.view.View

/** Android databindings for [View]*/
class ViewBindings {

  companion object {

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun onVisibility(view: View, isVisible: Boolean) {
      when(isVisible) {
        true -> view.visibility = View.VISIBLE
        else ->  view.visibility = View.GONE
      }
    }
  }
}