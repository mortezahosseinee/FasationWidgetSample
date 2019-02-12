package fasation.bottom.navigation

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

internal class ResizeAnimation internal constructor(private var view: View, private val targetWidth: Int, private val targetHeight: Int) : Animation() {

    //region Declare Constants
    private val startWidth: Int = view.width
    private val startHeight: Int = view.height
    //endregion Declare Constants

    //region Callbacks
    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {

        val newWidth = (startWidth + (targetWidth - startWidth) * interpolatedTime).toInt()
        val newHeight = (startHeight + (targetHeight - startHeight) * interpolatedTime).toInt()

        view.layoutParams.width = newWidth
        view.layoutParams.height = newHeight

        view.requestLayout()
    }

    override fun willChangeBounds(): Boolean {
        return true
    }
    //endregion Callbacks
}
