package ir.fasation.widget

import android.graphics.Typeface
import android.graphics.Typeface.createFromAsset
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import kotlinx.android.synthetic.main.fasation_edit_text.view.*
import android.text.InputFilter



fun FasationEditText.showLeftDrawableImage(show: Boolean) {
    img_fasation_edit_text_left.visibility = if (show) View.VISIBLE else View.INVISIBLE
}

fun FasationEditText.setInputType(inputType: Int) {
    initialInputType = inputType
    edt_fasation_edit_text_main.inputType = inputType
}

fun FasationEditText.setStatus(status: Status) {
    when (status) {
        Status.NORMAL -> setBorderColor(fasationEditTextNormalColor)
        Status.ACTIVE -> setBorderColor(fasationEditTextActiveColor)
        Status.VALID -> setBorderColor(fasationEditTextValidColor)
        Status.INVALID -> setBorderColor(fasationEditTextInvalidColor)
    }
}

fun FasationEditText.setBorderColor(color: Int) {
    val drawable = AppCompatResources.getDrawable(context, R.drawable.rounded_corner_background) as GradientDrawable
    drawable.setStroke(fasationEditTextBorderWidth.toInt(), color)
    invalidateDrawable(drawable)
    ctl_fasation_edit_text_main.background = drawable
    invalidate()
}

fun FasationEditText.setBorderWidth(borderWidth: Int) {
    fasationEditTextBorderWidth = borderWidth.toFloat()

    val drawable = ctl_fasation_edit_text_main.background as GradientDrawable
    val tempColor = when (fasationEditTextStatus) {
        Status.NORMAL -> fasationEditTextNormalColor
        Status.ACTIVE -> fasationEditTextActiveColor
        Status.VALID -> fasationEditTextValidColor
        Status.INVALID -> fasationEditTextInvalidColor
    }
    drawable.setStroke(borderWidth, tempColor)
}

fun FasationEditText.setText(text: String) {
    fasationEditTextMainText = text
    edt_fasation_edit_text_main.setText(fasationEditTextMainText) //Set main text content
}

fun FasationEditText.setTextSize(size: Float) {
    fasationEditTextSize = size
    edt_fasation_edit_text_main.textSize = convertPxToSp(fasationEditTextSize) // Set main text size
}

fun FasationEditText.setTextColor(color: Int) {
    fasationEditTextColor = color
    edt_fasation_edit_text_main.setTextColor(fasationEditTextColor) //Set main text color
}

fun FasationEditText.setTextFont(font: String) {
    if (font.isNotEmpty()) { //Set main text font
        fasationEditTextMainTextFont = font
        edt_fasation_edit_text_main.typeface = createFromAsset(context.assets, fasationEditTextMainTextFont)
    }
}

fun FasationEditText.setTextHeight(height: Float) {
    fasationEditTextHeight = height

    if (fasationEditTextHeight >= convertDpToPx(14f)) { //Set main edit text height
        val params = edt_fasation_edit_text_main.layoutParams
        params.height = fasationEditTextHeight.toInt()
        edt_fasation_edit_text_main.layoutParams = params
        edt_fasation_edit_text_main.requestLayout()
    }
}

fun FasationEditText.setHint(hint: String) {
    fasationEditTextMainHint = hint
    edt_fasation_edit_text_main.hint = fasationEditTextMainHint //Set main text hint
}

fun FasationEditText.setHintColor(color: Int) {
    fasationEditTextHintColor = color
    edt_fasation_edit_text_main.setHintTextColor(fasationEditTextHintColor) //Set main text hint color
}

fun FasationEditText.setDescription(message: String) {
    fasationEditTextDescriptionText = message
    txv_fasation_edit_text_description.text = fasationEditTextDescriptionText //Set description content
    txv_fasation_edit_text_description.visibility = View.VISIBLE
}

fun FasationEditText.setDescriptionSize(size: Float) {
    fasationEditTextDescriptionTextSize = size
    txv_fasation_edit_text_description.textSize = convertPxToSp(fasationEditTextDescriptionTextSize) // Set description text size
}

fun FasationEditText.setDescriptionColor(color: Int) {
    fasationEditTextDescriptionTextColor = color
    txv_fasation_edit_text_description.setTextColor(fasationEditTextDescriptionTextColor) //Set description text color
}

fun FasationEditText.setDescriptionFont(font: String) {
    if (font.isNotEmpty()) { //Set description font
        fasationEditTextDescriptionTextFont = font
        txv_fasation_edit_text_description.typeface = Typeface.createFromAsset(context.assets, fasationEditTextDescriptionTextFont)
    }
}

fun FasationEditText.clearDescription() {
    txv_fasation_edit_text_description.text = null
    txv_fasation_edit_text_description.visibility = View.GONE
}

fun FasationEditText.setLeftDrawableImage(resourceId: Int) {
    fasationEditTextLeftImageSrc = resourceId
    img_fasation_edit_text_left.setImageResource(resourceId)
}

fun FasationEditText.setRightDrawableImage(resourceId: Int) {
    fasationEditTextRightImageSrc = resourceId
    img_fasation_edit_text_right.setImageResource(resourceId)
}

fun FasationEditText.showRightDrawableImage(show: Boolean) {
    img_fasation_edit_text_right.visibility = if (show) View.VISIBLE else View.INVISIBLE
}

fun FasationEditText.setOnDrawableClickListener(listener: FasationEditTextOnDrawableClickListener) {
    this.listener = listener
}

fun FasationEditText.setSingleLine(singleLine: Boolean) {
    edt_fasation_edit_text_main.setSingleLine(singleLine)
}

fun FasationEditText.setMaxLength(maxLength: Int) {
    edt_fasation_edit_text_main.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
}