package ui.bindings

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import ui.image.GlideApp

/** Android databindings for [ImageView]*/
class ImageViewBindings {

  companion object {

    /**
     * Asynchronously load the image into the [ImageView]
     *
     * In addition to loading the image, also resize it as per the view dimensions to avoid
     * excessive memory usage.
     */
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, imageUrl: String) {

        /**
         * To know the view's width, height after it has been measured, we need load image in view
         * .post {} since messages in the event queue are processed after the layout pass
         */
        view.post {
          GlideApp
              .with(view)
              .load(imageUrl)
              //.placeholder(R.drawable.someSVG)
              //.fallback(R.drawable.someSVG)
              .into(view)
        }
    }

    /**
     * Asynchronously load the image, transform it as a circle and load into the [ImageView]
     *
     * In addition to loading the image, also resize it as per the view dimensions to avoid
     * excessive memory usage.
     */
    @JvmStatic
    @BindingAdapter("setCircularImage")
    fun setCircularImage(view: ImageView, imageUrl: String) {

      /**
       * To know the view's width, height after it has been measured, we need load image in view
       * .post {} since messages in the event queue are processed after the layout pass
       */
      view.post {
        GlideApp
            .with(view)
            .load(imageUrl)
            //.placeholder(R.drawable.someSVG)
            //.fallback(R.drawable.someSVG)
            .apply(RequestOptions.circleCropTransform())
            .into(view)
      }
    }
  }
}
