package ir.fasation.widget

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.JELLY_BEAN_MR1
import android.text.Editable
import android.text.InputType.*
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat.getColor
import ir.fasation.widget.LeftDrawableMode.*
import ir.fasation.widget.Position.LEFT
import ir.fasation.widget.Position.RIGHT
import ir.fasation.widget.RightDrawableMode.*
import ir.fasation.widget.Status.*
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
    internal var initialInputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_NORMAL
    //endregion Declare Variables

    //region Declare Objects
    internal var listener: FasationEditTextOnDrawableClickListener? = null
    //endregion Declare Objects

    //region Declare Views
    //endregion Declare Views

    //region Custom Attributes
    private var fasationEditTextSecurePasswordImageSrc = R.drawable.ic_secure
    private var fasationEditTextUnSecurePasswordImageSrc = R.drawable.ic_unsecure
    private var fasationEditTextClearActionImageSrc = R.drawable.ic_clear
    internal var fasationEditTextLeftImageSrc = -777
    internal var fasationEditTextRightImageSrc = -777
    internal var fasationEditTextMainText = ""
    internal var fasationEditTextMainTextFont = ""
    internal var fasationEditTextHeight = 14f //dp
    internal var fasationEditTextSize = 14f //sp
    internal var fasationEditTextColor = getColor(resources, android.R.color.black, context.theme)
    internal var fasationEditTextStatus = NORMAL
    private var fasationEditTextInputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_NORMAL
    internal var fasationEditTextNormalColor = getColor(resources, R.color.grey, context.theme)
    internal var fasationEditTextActiveColor = getColor(resources, R.color.grey, context.theme)
    internal var fasationEditTextValidColor = getColor(resources, android.R.color.holo_green_dark, context.theme)
    internal var fasationEditTextInvalidColor = getColor(resources, android.R.color.holo_red_dark, context.theme)
    internal var fasationEditTextBorderWidth = 1f //dp
    private var fasationEditTextClearActionPosition = LEFT
    internal var fasationEditTextDescriptionText = ""
    internal var fasationEditTextDescriptionTextColor = getColor(resources, android.R.color.holo_red_dark, context.theme)
    internal var fasationEditTextDescriptionTextSize = 14f //sp
    internal var fasationEditTextDescriptionTextFont = ""
    private var fasationEditTextRightDrawableSpace = true
    private var fasationEditTextLeftDrawableSpace = true
    private var fasationEditTextSingleLine = false
    private var fasationEditTextMaxLength = 1000
    internal var fasationEditTextMainHint = ""
    internal var fasationEditTextHintColor = getColor(resources, android.R.color.darker_gray, context.theme)
    //endregion Custom Attributes

    //region Constructor
    init {
        initMain(context)
    }
    //endregion Constructor

    //region Main Callbacks
    override fun onClick(view: View) {
        when (view) {
            img_fasation_edit_text_left -> {
                when (fasationEditTextStatus) {
                    ACTIVE -> {
                        if (isInitialTypeAnyPassword()) {
                            when (fasationEditTextClearActionPosition) {
                                LEFT -> {
                                    if (edt_fasation_edit_text_main.text.isNullOrEmpty()) {
                                        if (fasationEditTextLeftImageSrc != -777)
                                            listener?.onFasationEditTextLeftDrawableClick(this, LEFT_BASIC_DRAWABLE) //LEFT Drawable image clicked
                                    } else {
                                        edt_fasation_edit_text_main.text!!.clear() //Clear image clicked

                                        if (fasationEditTextLeftImageSrc != -777)
                                            setLeftDrawableImage(fasationEditTextLeftImageSrc)
                                        else
                                            showLeftDrawableImage(false)

                                        listener?.onFasationEditTextLeftDrawableClick(this, LEFT_CLEAR_ACTION_DRAWABLE)
                                    }
                                }
                                RIGHT -> passwordImageClicked(LEFT) //Password image clicked
                            }
                        } else {
                            when (fasationEditTextClearActionPosition) {
                                LEFT -> {
                                    if (edt_fasation_edit_text_main.text.isNullOrEmpty()) {
                                        if (fasationEditTextLeftImageSrc != -777)
                                            listener?.onFasationEditTextLeftDrawableClick(this, LEFT_BASIC_DRAWABLE) //LEFT Drawable image clicked
                                    } else {
                                        edt_fasation_edit_text_main.text!!.clear() //Clear image clicked

                                        if (fasationEditTextLeftImageSrc != -777)
                                            setLeftDrawableImage(fasationEditTextLeftImageSrc)
                                        else
                                            showLeftDrawableImage(false)

                                        listener?.onFasationEditTextLeftDrawableClick(this, LEFT_CLEAR_ACTION_DRAWABLE)
                                    }
                                }
                                RIGHT -> {
                                    if (fasationEditTextLeftImageSrc != -777)
                                        listener?.onFasationEditTextLeftDrawableClick(this, LEFT_BASIC_DRAWABLE) //LEFT Drawable image clicked
                                }
                            }
                        }
                    }
                    else -> {
                        if (isInitialTypeAnyPassword()) {
                            when (fasationEditTextClearActionPosition) {
                                LEFT -> {
                                    if (fasationEditTextLeftImageSrc != -777)
                                        listener?.onFasationEditTextLeftDrawableClick(this, LEFT_BASIC_DRAWABLE) //LEFT Drawable image clicked
                                }
                                RIGHT -> passwordImageClicked(LEFT) //Password image clicked
                            }
                        } else {
                            if (fasationEditTextLeftImageSrc != -777)
                                listener?.onFasationEditTextLeftDrawableClick(this, LEFT_BASIC_DRAWABLE) //LEFT Drawable image clicked
                        }
                    }
                }
            }
            img_fasation_edit_text_right -> {
                when (fasationEditTextStatus) {
                    ACTIVE -> {
                        if (isInitialTypeAnyPassword()) {
                            when (fasationEditTextClearActionPosition) {
                                LEFT -> passwordImageClicked(RIGHT) //Password image clicked
                                RIGHT -> {
                                    if (edt_fasation_edit_text_main.text.isNullOrEmpty()) {
                                        if (fasationEditTextRightImageSrc != -777)
                                            listener?.onFasationEditTextRightDrawableClick(this, RIGHT_BASIC_DRAWABLE) //RIGHT Drawable image clicked
                                    } else {
                                        edt_fasation_edit_text_main.text!!.clear() //Clear image clicked

                                        if (fasationEditTextRightImageSrc != -777)
                                            setRightDrawableImage(fasationEditTextRightImageSrc)
                                        else
                                            showRightDrawableImage(false)

                                        listener?.onFasationEditTextRightDrawableClick(this, RIGHT_CLEAR_ACTION_DRAWABLE)
                                    }
                                }
                            }
                        } else {
                            when (fasationEditTextClearActionPosition) {
                                LEFT -> {
                                    if (fasationEditTextRightImageSrc != -777)
                                        listener?.onFasationEditTextRightDrawableClick(this, RIGHT_BASIC_DRAWABLE) //RIGHT Drawable image clicked
                                    else
                                        showRightDrawableImage(false)
                                }
                                RIGHT -> {
                                    if (edt_fasation_edit_text_main.text.isNullOrEmpty()) {
                                        if (fasationEditTextRightImageSrc != -777)
                                            listener?.onFasationEditTextRightDrawableClick(this, RIGHT_BASIC_DRAWABLE) //RIGHT Drawable image clicked
                                        else
                                            showRightDrawableImage(false)
                                    } else {
                                        edt_fasation_edit_text_main.text!!.clear() //Clear image clicked

                                        if (fasationEditTextRightImageSrc != -777)
                                            setRightDrawableImage(fasationEditTextRightImageSrc)
                                        else
                                            showRightDrawableImage(false)

                                        listener?.onFasationEditTextRightDrawableClick(this, RIGHT_CLEAR_ACTION_DRAWABLE)
                                    }
                                }
                            }
                        }
                    }
                    else -> {
                        if (isInitialTypeAnyPassword()) {
                            when (fasationEditTextClearActionPosition) {
                                LEFT -> passwordImageClicked(RIGHT) //Password image clicked
                                RIGHT -> {
                                    if (fasationEditTextRightImageSrc != -777)
                                        listener?.onFasationEditTextRightDrawableClick(this, RIGHT_BASIC_DRAWABLE) //RIGHT Drawable image clicked
                                }
                            }
                        } else {
                            if (fasationEditTextRightImageSrc != -777)
                                listener?.onFasationEditTextRightDrawableClick(this, RIGHT_BASIC_DRAWABLE) //RIGHT Drawable image clicked
                        }
                    }
                }
            }
        }

        edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length)
    }
    //endregion Main Callbacks

    //region Private Methods
    private fun initMain(context: Context) {
        View.inflate(context, R.layout.fasation_edit_text, this)
        initAttributes()
        initViews()
    }

    private fun initAttributes() {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.FasationEditText, 0, 0)

            fasationEditTextSecurePasswordImageSrc =
                    typedArray.getResourceId(R.styleable.FasationEditText_secure_password_drawable, fasationEditTextSecurePasswordImageSrc)

            fasationEditTextUnSecurePasswordImageSrc =
                    typedArray.getResourceId(R.styleable.FasationEditText_un_secure_password_drawable, fasationEditTextUnSecurePasswordImageSrc)

            fasationEditTextClearActionImageSrc =
                    typedArray.getResourceId(R.styleable.FasationEditText_clear_action_drawable, fasationEditTextClearActionImageSrc)

            fasationEditTextRightImageSrc =
                    typedArray.getResourceId(R.styleable.FasationEditText_right_drawable, fasationEditTextRightImageSrc)

            fasationEditTextLeftImageSrc =
                    typedArray.getResourceId(R.styleable.FasationEditText_left_drawable, fasationEditTextLeftImageSrc)

            fasationEditTextInputType =
                    typedArray.getInteger(R.styleable.FasationEditText_android_inputType, fasationEditTextInputType)

            fasationEditTextMainText =
                    typedArray.getString(R.styleable.FasationEditText_android_text)
                            ?: fasationEditTextMainText

            fasationEditTextMainTextFont =
                    typedArray.getString(R.styleable.FasationEditText_text_font)
                            ?: fasationEditTextMainTextFont

            fasationEditTextSize =
                    typedArray.getDimension(R.styleable.FasationEditText_android_textSize, edt_fasation_edit_text_main.textSize)

            fasationEditTextColor =
                    typedArray.getColor(R.styleable.FasationEditText_text_color, fasationEditTextColor)

            fasationEditTextMainHint =
                    typedArray.getString(R.styleable.FasationEditText_android_hint)
                            ?: fasationEditTextMainHint

            fasationEditTextHintColor =
                    typedArray.getColor(R.styleable.FasationEditText_android_textColorHint, fasationEditTextHintColor)

            fasationEditTextHeight =
                    typedArray.getDimension(R.styleable.FasationEditText_text_height, fasationEditTextHeight)

            fasationEditTextStatus =
                    when (typedArray.getInteger(R.styleable.FasationEditText_status, 0)) {
                        0 -> NORMAL
                        1 -> ACTIVE
                        2 -> VALID
                        3 -> INVALID
                        else -> NORMAL
                    }

            fasationEditTextNormalColor =
                    typedArray.getColor(R.styleable.FasationEditText_normal_color, fasationEditTextNormalColor)

            fasationEditTextActiveColor =
                    typedArray.getColor(R.styleable.FasationEditText_active_color, fasationEditTextActiveColor)

            fasationEditTextValidColor =
                    typedArray.getColor(R.styleable.FasationEditText_valid_color, fasationEditTextValidColor)

            fasationEditTextInvalidColor =
                    typedArray.getColor(R.styleable.FasationEditText_invalid_color, fasationEditTextInvalidColor)

            fasationEditTextBorderWidth =
                    typedArray.getDimension(R.styleable.FasationEditText_border_width, fasationEditTextBorderWidth)

            fasationEditTextClearActionPosition =
                    when (typedArray.getInteger(R.styleable.FasationEditText_clear_action_position, 1)) {
                        0 -> LEFT
                        1 -> RIGHT
                        else -> RIGHT
                    }

            fasationEditTextDescriptionText =
                    typedArray.getString(R.styleable.FasationEditText_description_text)
                            ?: fasationEditTextDescriptionText

            fasationEditTextDescriptionTextColor =
                    typedArray.getColor(R.styleable.FasationEditText_description_text_color, fasationEditTextDescriptionTextColor)

            fasationEditTextDescriptionTextSize =
                    typedArray.getDimension(R.styleable.FasationEditText_description_text_size, fasationEditTextDescriptionTextSize)

            fasationEditTextDescriptionTextFont =
                    typedArray.getString(R.styleable.FasationEditText_description_text_font)
                            ?: fasationEditTextDescriptionTextFont

            fasationEditTextRightDrawableSpace =
                    typedArray.getBoolean(R.styleable.FasationEditText_right_drawable_space, fasationEditTextRightDrawableSpace)

            fasationEditTextLeftDrawableSpace =
                    typedArray.getBoolean(R.styleable.FasationEditText_left_drawable_space, fasationEditTextLeftDrawableSpace)

            fasationEditTextSingleLine =
                    typedArray.getBoolean(R.styleable.FasationEditText_android_singleLine, fasationEditTextSingleLine)

            fasationEditTextMaxLength =
                    typedArray.getInteger(R.styleable.FasationEditText_android_maxLength, fasationEditTextMaxLength)

            typedArray.recycle()
        }
    }

    private fun initViews() {
        setInputType(fasationEditTextInputType) //Set input type
        setStatus(fasationEditTextStatus) //Set edit text status
        setBorderWidth(fasationEditTextBorderWidth.toInt()) //Set main border width
        setText(fasationEditTextMainText) //Set main text content
        setTextSize(fasationEditTextSize) //Set main text size
        setTextColor(fasationEditTextColor) //Set main text color
        setTextFont(fasationEditTextMainTextFont) //Set main text font
        setTextHeight(fasationEditTextHeight) //Set main text height
        setHint(fasationEditTextMainHint) //Set main text hint
        setHintColor(fasationEditTextHintColor) //Set main text hint color
        setDescription(fasationEditTextDescriptionText) //Set description text
        setDescriptionSize(fasationEditTextDescriptionTextSize) //Set description text
        setDescriptionColor(fasationEditTextDescriptionTextColor) //Set description text
        setDescriptionFont(fasationEditTextDescriptionTextFont) //Set description text font
        setSingleLine(fasationEditTextSingleLine) //Set text max line
        setMaxLength(fasationEditTextMaxLength) //Set text max length

        if (isInitialTypeAnyPassword()) {
            setPasswordImage()
            when (fasationEditTextClearActionPosition) {
                LEFT -> {
                    if (fasationEditTextLeftDrawableSpace) {
                        if (fasationEditTextLeftImageSrc != -777) {
                            changeLeftDrawableImage(fasationEditTextLeftImageSrc)
                            showLeftDrawableImage(true)
                        }
                    } else removeDrawable(LEFT)
                }
                RIGHT -> {
                    if (fasationEditTextRightDrawableSpace) {
                        if (fasationEditTextRightImageSrc != -777) {
                            changeRightDrawableImage(fasationEditTextRightImageSrc)
                            showRightDrawableImage(true)
                        }
                    } else removeDrawable(RIGHT)
                }
            }
        } else {
            if (fasationEditTextLeftDrawableSpace) {
                if (fasationEditTextLeftImageSrc != -777) {
                    changeLeftDrawableImage(fasationEditTextLeftImageSrc)
                    showLeftDrawableImage(true)
                }
            } else removeDrawable(LEFT)

            if (fasationEditTextRightDrawableSpace) {
                if (fasationEditTextRightImageSrc != -777) {
                    changeRightDrawableImage(fasationEditTextRightImageSrc)
                    showRightDrawableImage(true)
                }
            } else removeDrawable(RIGHT)
        }

        img_fasation_edit_text_left!!.setOnClickListener(this) // Set left drawable CLickListener on this view
        img_fasation_edit_text_right!!.setOnClickListener(this) // Set right drawable CLickListener on this view

        edt_fasation_edit_text_main.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        if (fasationEditTextStatus == ACTIVE)
                            if (edt_fasation_edit_text_main.text.isNullOrEmpty()) {
                                when (fasationEditTextClearActionPosition) {
                                    LEFT -> {
                                        if (fasationEditTextLeftDrawableSpace) {
                                            if (fasationEditTextLeftImageSrc == -777)
                                                showLeftDrawableImage(false)
                                            else {
                                                setLeftDrawableImage(fasationEditTextLeftImageSrc)
                                                showLeftDrawableImage(true)
                                            }
                                        } else
                                            removeDrawable(LEFT)
                                    }
                                    RIGHT -> {
                                        if (fasationEditTextRightDrawableSpace) {
                                            if (fasationEditTextRightImageSrc == -777)
                                                showRightDrawableImage(true)
                                            else {
                                                setRightDrawableImage(fasationEditTextRightImageSrc)
                                                showRightDrawableImage(true)
                                            }
                                        } else
                                            removeDrawable(RIGHT)
                                    }
                                }
                            } else {
                                when (fasationEditTextClearActionPosition) {
                                    LEFT -> {
                                        changeLeftDrawableImage(fasationEditTextClearActionImageSrc)
                                        showLeftDrawableImage(true)
                                    }
                                    RIGHT -> {
                                        changeRightDrawableImage(fasationEditTextClearActionImageSrc)
                                        showRightDrawableImage(true)
                                    }
                                }
                            }
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }
                })
    }

    private fun setPasswordImage() {
        when (fasationEditTextClearActionPosition) {
            LEFT -> {
                changeRightDrawableImage(fasationEditTextSecurePasswordImageSrc)
                showRightDrawableImage(true)
            }
            RIGHT -> {
                changeLeftDrawableImage(fasationEditTextSecurePasswordImageSrc)
                showLeftDrawableImage(true)
            }
        }
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @return A float value to represent px equivalent to dp depending on device density
     */
    internal fun convertDpToPx(dp: Float): Int {
        return (dp * context!!.resources.displayMetrics.density).toInt()
    }

    internal fun convertPxToSp(px: Float): Float {
        return px / resources.displayMetrics.scaledDensity
    }

    private fun passwordImageClicked(position: Position) {
        if (showPassword) {
            edt_fasation_edit_text_main.inputType = TYPE_CLASS_TEXT or initialInputType
            edt_fasation_edit_text_main.transformationMethod = PasswordTransformationMethod.getInstance()

            when (position) {
                LEFT -> {
                    changeLeftDrawableImage(fasationEditTextSecurePasswordImageSrc)
                    listener?.onFasationEditTextLeftDrawableClick(this, LEFT_HIDE_PASSWORD_DRAWABLE)
                }
                RIGHT -> {
                    changeRightDrawableImage(fasationEditTextSecurePasswordImageSrc)
                    listener?.onFasationEditTextRightDrawableClick(this, RIGHT_HIDE_PASSWORD_DRAWABLE)
                }
            }
        } else {
            if (initialInputType == TYPE_CLASS_TEXT or TYPE_NUMBER_VARIATION_PASSWORD)
                edt_fasation_edit_text_main.inputType = TYPE_CLASS_TEXT or TYPE_NUMBER_VARIATION_NORMAL
            else
                edt_fasation_edit_text_main.inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_NORMAL

            when (position) {
                LEFT -> {
                    changeLeftDrawableImage(fasationEditTextUnSecurePasswordImageSrc)
                    listener?.onFasationEditTextLeftDrawableClick(this, LEFT_SHOW_PASSWORD_DRAWABLE)
                }
                RIGHT -> {
                    changeRightDrawableImage(fasationEditTextUnSecurePasswordImageSrc)
                    listener?.onFasationEditTextRightDrawableClick(this, RIGHT_SHOW_PASSWORD_DRAWABLE)
                }
            }
        }

        showPassword = !showPassword

        setTextFont(fasationEditTextMainTextFont)
    }

    private fun isInitialTypeAnyPassword(): Boolean {
        return initialInputType == (TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD) ||
                initialInputType == (TYPE_CLASS_TEXT or TYPE_NUMBER_VARIATION_PASSWORD) ||
                initialInputType == (TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) ||
                initialInputType == (TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_WEB_PASSWORD)
    }

    private fun changeLeftDrawableImage(resourceId: Int) {
        img_fasation_edit_text_left!!.setImageResource(resourceId)
    }

    private fun changeRightDrawableImage(resourceId: Int) {
        img_fasation_edit_text_right!!.setImageResource(resourceId)
    }

    private fun removeDrawable(position: Position) {
        val params = edt_fasation_edit_text_main.layoutParams as LayoutParams

        when (position) {
            LEFT -> {
                img_fasation_edit_text_left.visibility = GONE

                params.leftMargin = convertDpToPx(16f)

                if (SDK_INT >= JELLY_BEAN_MR1)
                    params.marginStart = convertDpToPx(16f)
            }
            RIGHT -> {
                img_fasation_edit_text_right.visibility = GONE

                params.rightMargin = convertDpToPx(16f)

                if (SDK_INT >= JELLY_BEAN_MR1)
                    params.marginEnd = convertDpToPx(16f)
            }
        }

        edt_fasation_edit_text_main.layoutParams = params
        edt_fasation_edit_text_main.requestLayout()
    }
    //endregion Private Methods
}