package ir.fasation.edittext

import android.graphics.Typeface.createFromAsset
import android.graphics.drawable.GradientDrawable
import android.text.InputFilter
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat.getColor
import kotlinx.android.synthetic.main.fasation_edit_text.view.*

fun FasationEditText.showLeftDrawableImage(show: Boolean) {
    img_fasation_edit_text_left.visibility = if (show) View.VISIBLE else View.INVISIBLE
}

fun FasationEditText.setInputType(inputType: Int) {
    initialInputType = inputType

    if (edt_fasation_edit_text_main.text.isNotEmpty())
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

/**
 * @param color: send [androidx.core.content.res.getColor] as color param and DO NOT send id of color
 */
fun FasationEditText.setBorderColor(color: Int) {
    val drawable = AppCompatResources.getDrawable(context, R.drawable.rounded_corner_background) as GradientDrawable
    drawable.setStroke(fasationEditTextBorderWidth.toInt(), color)
    invalidateDrawable(drawable)
    ctl_fasation_edit_text_main.background = drawable
    invalidate()
}

/**
 * @param color: send [androidx.core.content.res.getColor] as color param and DO NOT send id of color
 */
fun FasationEditText.setWholeBorderColor(color: Int) {
    ctl_fasation_edit_text_main.background = null

    val drawable = AppCompatResources.getDrawable(context, R.drawable.rounded_corner_background) as GradientDrawable
    drawable.setStroke(fasationEditTextBorderWidth.toInt(), color)
    invalidateDrawable(drawable)
    this.background = drawable

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
    setCorrectCursorPlace()
}

fun FasationEditText.getText() : String {
    return edt_fasation_edit_text_main.text.toString()
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
    clearDescription()
}

fun FasationEditText.setDescriptionColor(color: Int) {
    fasationEditTextDescriptionTextColor = color
    txv_fasation_edit_text_description.setTextColor(fasationEditTextDescriptionTextColor) //Set description text color
    clearDescription()
}

fun FasationEditText.setDescriptionFont(font: String) {
    if (font.isNotEmpty()) { //Set description font
        fasationEditTextDescriptionTextFont = font
        txv_fasation_edit_text_description.typeface = createFromAsset(context.assets, fasationEditTextDescriptionTextFont)
        clearDescription()
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

fun FasationEditText.getEditText(): EditText? {
    return edt_fasation_edit_text_main
}

fun FasationEditText.addBottomView(customView: View?, width: Int?, height: Int?, dividerColor: Int?, dividerHeight: Float?) {
    if (customView == null)
        throw NullPointerException("customView must not be null.")

    if (customView.id == -1)
        throw NullPointerException("customView must have an Id.")

    var view: View?

    //if custom View exists, remove it first
    view = main_view.findViewById(R.id.view_fasation_edit_text_bottom_divider)
    if (view != null) {
        val position = main_view.indexOfChild(main_view.findViewById<View>(R.id.view_fasation_edit_text_bottom_divider))

        main_view.removeViewAt(position + 1)
    } else {
        //add divider View if not exists
        view = View(context)
        view.id = R.id.view_fasation_edit_text_bottom_divider
        main_view.addView(view)

        view.setBackgroundColor(dividerColor
                ?: getColor(resources, R.color.default_bottom_view_divider_color, context.theme))

        val params = view.layoutParams as ConstraintLayout.LayoutParams
        params.width = 0
        params.height = convertDpToPx(dividerHeight ?: 1f)

        params.setMargins(convertDpToPx(16f), convertDpToPx(8f), convertDpToPx(16f), convertDpToPx(8f))
        view.layoutParams = params
        view.requestLayout()
    }

    //add custom View
    main_view.addView(customView)

    val params = customView.layoutParams as ConstraintLayout.LayoutParams
    params.width = width ?: 0
    params.height = height ?: WRAP_CONTENT

    params.setMargins(convertDpToPx(8f), convertDpToPx(8f), convertDpToPx(8f), convertDpToPx(8f))
    customView.layoutParams = params
    customView.requestLayout()

    val constraintSet = ConstraintSet()
    constraintSet.clone(main_view)

    //view constraints
    constraintSet.connect(
            view.id,
            ConstraintSet.TOP,
            R.id.txv_fasation_edit_text_description,
            ConstraintSet.BOTTOM
    )
    constraintSet.connect(
            view.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
    )
    constraintSet.connect(
            view.id,
            ConstraintSet.END,
            ConstraintSet.PARENT_ID,
            ConstraintSet.END
    )

    //customView constraints
    constraintSet.connect(
            customView.id,
            ConstraintSet.TOP,
            view.id,
            ConstraintSet.BOTTOM
    )
    constraintSet.connect(
            customView.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
    )
    constraintSet.connect(
            customView.id,
            ConstraintSet.END,
            ConstraintSet.PARENT_ID,
            ConstraintSet.END
    )
    constraintSet.connect(
            customView.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM
    )

    //apply constraint to main view
    constraintSet.applyTo(main_view)
}

fun FasationEditText.clearBottomView() {
    if (main_view.findViewById<View>(R.id.view_fasation_edit_text_bottom_divider) != null) {
        val position =  main_view.indexOfChild(main_view.findViewById<View>(R.id.view_fasation_edit_text_bottom_divider))

        main_view.removeViewAt(position)
        main_view.removeViewAt(position)
    }
}

fun FasationEditText.getBottomViewDividerId(): Int {
    return R.id.view_fasation_edit_text_bottom_divider
}

fun FasationEditText.setImeOptions(fasationEditTextImeOptions: Int) {
    this.fasationEditTextImeOptions = fasationEditTextImeOptions
    edt_fasation_edit_text_main.imeOptions = fasationEditTextImeOptions
}

fun FasationEditText.setMaxLine(fasationEditTextMaxLine: Int) {
    this.fasationEditTextMaxLines = fasationEditTextMaxLine
    edt_fasation_edit_text_main.maxLines = fasationEditTextMaxLine
}

fun FasationEditText.setCorrectCursorPlace() {
    edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length)
}

fun FasationEditText.setEditTextEnabled(enable: Boolean) {
    edt_fasation_edit_text_main.isEnabled = enable
}