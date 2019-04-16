package ir.fasation.widget

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fasation_edit_text.view.*

class FasationEditText @JvmOverloads constructor(context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr),
        View.OnClickListener {

    companion object {
        //region Declare Constants
        //endregion Declare Constants
    }

    //region Declare Variables
    private var showPassword = false
    private var initialInputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
    //endregion Declare Variables

    //region Declare Objects
    //endregion Declare Objects

    //region Declare Views
    //endregion Declare Views

    //region Custom Attributes
    private var fasationEditTextSecurePasswordImageSrc = R.drawable.ic_secure
    private var fasationEditTextUnSecurePasswordImageSrc = R.drawable.ic_unsecure
    private var fasationEditTextRightImageSrc = -777
    private var fasationEditTextMainText = ""
    private var fasationEditTextStatus = "normal"
    private var fasationEditTextInputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
    private var fasationEditTextNormalColor = ContextCompat.getColor(context, R.color.grey)
    private var fasationEditTextActiveColor = ContextCompat.getColor(context, R.color.grey)
    private var fasationEditTextValidColor = ContextCompat.getColor(context, R.color.green)
    private var fasationEditTextInvalidColor = ContextCompat.getColor(context, R.color.red)
    private var fasationEditTextBorderWidth = 1f //dp
    private var fasationEditTextClearActionPosition = 0
    //endregion Custom Attributes

    //region Constructor
    init {
        initMain(context)
    }
    //endregion Constructor

    //region Main Callbacks
    override fun onClick(view: View) {
        when (view.id) {
            R.id.img_fasation_edit_text_left -> {
                if (fasationEditTextStatus == "active" && !edt_fasation_edit_text_main.text.isNullOrBlank())
                    edt_fasation_edit_text_main.text!!.clear()
                else {
                    if (initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD)
                        if (showPassword) {
                            img_fasation_edit_text_left!!.setImageDrawable(ContextCompat.getDrawable(context, fasationEditTextSecurePasswordImageSrc))

                            edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or initialInputType
                            edt_fasation_edit_text_main.transformationMethod = PasswordTransformationMethod.getInstance()
                        } else {
                            img_fasation_edit_text_left!!.setImageDrawable(ContextCompat.getDrawable(context, fasationEditTextUnSecurePasswordImageSrc))

                            if (initialInputType == InputType.TYPE_NUMBER_VARIATION_PASSWORD)
                                edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_NORMAL
                            else
                                edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
                        }

                    showPassword = !showPassword
                    edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length)
                }
            }
        }
    }
    //endregion Main Callbacks

    //region Declare Methods
    private fun initMain(context: Context) {
        View.inflate(context, R.layout.fasation_edit_text, this)
        initAttributes()
        initViews()
    }

    private fun initAttributes() {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.FasationEditText, 0, 0)

            fasationEditTextSecurePasswordImageSrc =
                    typedArray.getResourceId(R.styleable.FasationEditText_secure_password_drawable, R.drawable.ic_secure)
            fasationEditTextUnSecurePasswordImageSrc =
                    typedArray.getResourceId(R.styleable.FasationEditText_un_secure_password_drawable, R.drawable.ic_unsecure)

            fasationEditTextRightImageSrc =
                    typedArray.getResourceId(R.styleable.FasationEditText_right_drawable, -777)

            fasationEditTextInputType =
                    typedArray.getInteger(R.styleable.FasationEditText_android_inputType, InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)

            fasationEditTextMainText =
                    typedArray.getString(R.styleable.FasationEditText_android_text) ?: ""

            fasationEditTextStatus =
                    typedArray.getString(R.styleable.FasationEditText_status) ?: "normal"

            fasationEditTextNormalColor =
                    typedArray.getColor(R.styleable.FasationEditText_normal_color,
                            ContextCompat.getColor(context, R.color.grey))

            fasationEditTextActiveColor =
                    typedArray.getColor(R.styleable.FasationEditText_active_color,
                            ContextCompat.getColor(context, R.color.grey))

            fasationEditTextValidColor =
                    typedArray.getColor(R.styleable.FasationEditText_valid_color,
                            ContextCompat.getColor(context, R.color.green))

            fasationEditTextInvalidColor =
                    typedArray.getColor(R.styleable.FasationEditText_invalid_color,
                            ContextCompat.getColor(context, R.color.red))

            fasationEditTextBorderWidth =
                    typedArray.getDimension(R.styleable.FasationEditText_border_width,
                            fasationEditTextBorderWidth)

            fasationEditTextClearActionPosition =
                    typedArray.getInteger(R.styleable.FasationEditText_clear_action_position,
                            fasationEditTextClearActionPosition)

            typedArray.recycle()
        }
    }

    //region Private Methods
    private fun initViews() {
        initialInputType = fasationEditTextInputType
        edt_fasation_edit_text_main.inputType = fasationEditTextInputType

        if (!(initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
                        initialInputType == InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
                        initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
                        initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD))
            img_fasation_edit_text_left.visibility = View.INVISIBLE
        else {
            img_fasation_edit_text_left!!.setImageDrawable(ContextCompat.getDrawable(context, fasationEditTextSecurePasswordImageSrc)) //Set left image drawable
            img_fasation_edit_text_left.visibility = View.VISIBLE
        }

        if (fasationEditTextRightImageSrc == -777)
            img_fasation_edit_text_right.visibility = View.INVISIBLE
        else {
            img_fasation_edit_text_right!!.setImageDrawable(ContextCompat.getDrawable(context, fasationEditTextRightImageSrc))
            img_fasation_edit_text_right.visibility = View.VISIBLE
        }

        img_fasation_edit_text_left!!.setOnClickListener(this) // Set left drawable CLickListener on this view
        edt_fasation_edit_text_main.setText(fasationEditTextMainText)
        edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length) //Set Cursor end of text
        edt_fasation_edit_text_main.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (fasationEditTextStatus == "active")
                    if (!edt_fasation_edit_text_main.text.isNullOrBlank() && !(initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
                                    initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
                                    initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
                                    initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD)) {
                        if (fasationEditTextClearActionPosition == 0) {
                            img_fasation_edit_text_left.setImageResource(R.drawable.ic_clear)
                            img_fasation_edit_text_left.visibility = View.VISIBLE
                            img_fasation_edit_text_left.setOnClickListener(this@FasationEditText)
                        } else {
                            img_fasation_edit_text_right.setImageResource(R.drawable.ic_clear)
                            img_fasation_edit_text_right.visibility = View.VISIBLE
                            img_fasation_edit_text_right.setOnClickListener(this@FasationEditText)
                        }
                    } else {
                        if (fasationEditTextClearActionPosition == 0)
                            img_fasation_edit_text_left.visibility = View.INVISIBLE
                        else
                            img_fasation_edit_text_right.visibility = View.INVISIBLE
                    }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        updateEditTextStatus()
    }

    private fun updateEditTextStatus() {
        when (fasationEditTextStatus) {
            "normal" -> setEditTextBorderColor(fasationEditTextNormalColor)
            "active" -> setEditTextBorderColor(fasationEditTextActiveColor)
            "valid" -> setEditTextBorderColor(fasationEditTextValidColor)
            "invalid" -> setEditTextBorderColor(fasationEditTextInvalidColor)
        }
    }

    private fun setEditTextBorderColor(color: Int) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.rounded_corner_background) as GradientDrawable
        drawable.setStroke(convertDpToPx(fasationEditTextBorderWidth), color)
        invalidateDrawable(drawable)
        this.background = drawable
        invalidate()
    }

    private fun setEditTextBorderWidth() {
        val drawable = background as GradientDrawable
        val tempColor = when (fasationEditTextStatus) {
            "normal" -> fasationEditTextNormalColor
            "active" -> fasationEditTextActiveColor
            "valid" -> fasationEditTextValidColor
            "invalid" -> fasationEditTextInvalidColor
            else -> fasationEditTextNormalColor
        }
        drawable.setStroke(convertDpToPx(fasationEditTextBorderWidth), tempColor)
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    private fun convertDpToPx(dp: Float): Int {
        return (dp * context!!.resources.displayMetrics.density).toInt()
    }
    //endregion Private Methods

    //region Public Methods
    //endregion Public Methods
}
