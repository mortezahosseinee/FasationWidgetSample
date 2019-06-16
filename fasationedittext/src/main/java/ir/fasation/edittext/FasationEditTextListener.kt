package ir.fasation.edittext

import android.view.View

interface FasationEditTextListener {
    fun onFasationEditTextLeftDrawableClick(v: View, mode: LeftDrawableMode)

    fun onFasationEditTextRightDrawableClick(v: View, mode: RightDrawableMode)

    fun onFasationEditTextFocusChanged(v: View?, hasFocus: Boolean)
}