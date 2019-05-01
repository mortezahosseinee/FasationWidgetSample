package ir.fasation.widget

import android.view.View

interface FasationEditTextOnDrawableClickListener {
    fun onFasationEditTextLeftDrawableClick(v: View, mode: LeftDrawableMode)

    fun onFasationEditTextRightDrawableClick(v: View, mode: RightDrawableMode)
}