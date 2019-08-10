package ir.fasation.edittext

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.JELLY_BEAN_MR1
import android.text.Editable
import android.text.InputFilter
import android.text.InputType.*
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.Gravity.CENTER_VERTICAL
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat.getColor
import ir.fasation.edittext.LeftDrawableMode.*
import ir.fasation.edittext.Position.LEFT
import ir.fasation.edittext.Position.RIGHT
import ir.fasation.edittext.RightDrawableMode.*
import ir.fasation.edittext.Status.*
import kotlinx.android.synthetic.main.fasation_edit_text.view.*

class FasationEditText @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr),
    View.OnClickListener {

    companion object {
        //region Declare Constants
        //endregion Declare Constants
    }

    //region Declare Variables
    private var hidePassword = true
    private var initialInputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_NORMAL
    //endregion Declare Variables

    //region Declare Objects
    private var listener: FasationEditTextListener? = null
    //endregion Declare Objects

    //region Declare Views
    //endregion Declare Views

    //region Custom Attributes
    private var fasationEditTextSecurePasswordImageSrc = R.drawable.ic_secure
    private var fasationEditTextUnSecurePasswordImageSrc = R.drawable.ic_unsecure
    private var fasationEditTextClearActionImageSrc = R.drawable.ic_clear
    internal var fasationEditTextLeftImageSrc = -777
    internal var fasationEditTextRightImageSrc = -777
    private var fasationEditTextMainText = ""
    private var fasationEditTextMainTextFont = ""
    private var fasationEditTextHeight = 14f //dp
    private var fasationEditTextGravity = CENTER_VERTICAL //dp
    private var fasationEditTextSize = 14f //sp
    private var fasationEditTextColor = getColor(resources, android.R.color.black, context.theme)
    internal var fasationEditTextStatus = NORMAL
    private var fasationEditTextInputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_NORMAL
    private var fasationEditTextNormalColor = getColor(resources, R.color.grey, context.theme)
    private var fasationEditTextActiveColor = getColor(resources, R.color.grey, context.theme)
    private var fasationEditTextValidColor =
        getColor(resources, android.R.color.holo_green_dark, context.theme)
    private var fasationEditTextInvalidColor =
        getColor(resources, android.R.color.holo_red_dark, context.theme)
    private var fasationEditTextBorderWidth = 1f //dp
    private var fasationEditTextClearActionPosition = LEFT
    private var fasationEditTextDescriptionText = ""
    private var fasationEditTextDescriptionTextColor =
        getColor(resources, android.R.color.holo_red_dark, context.theme)
    private var fasationEditTextDescriptionTextSize = 14f //sp
    private var fasationEditTextDescriptionTextFont = ""
    private var fasationEditTextRightDrawableSpace = true
    private var fasationEditTextLeftDrawableSpace = true
    private var fasationEditTextSingleLine = false
    private var fasationEditTextMaxLength = 1000
    private var fasationEditTextMaxLines = 100
    private var fasationEditTextMainHint = ""
    private var fasationEditTextHintColor =
        getColor(resources, android.R.color.darker_gray, context.theme)
    private var fasationEditTextImeOptions = 0
    //endregion Custom Attributes

    //region Constructor
    init {
        initMain(context)
    }
    //endregion Constructor

    //region Main Callbacks
    override fun onClick(view: View) {
        when (view) {
            this -> edt_fasation_edit_text_main.requestFocus()
            img_fasation_edit_text_left -> {
                when (fasationEditTextStatus) {
                    ACTIVE -> {
                        if (isInitialTypeAnyPassword()) {
                            when (fasationEditTextClearActionPosition) {
                                LEFT -> {
                                    if (edt_fasation_edit_text_main.text.isNullOrEmpty()) {
                                        if (fasationEditTextLeftImageSrc != -777)
                                            listener?.onFasationEditTextLeftDrawableClick(
                                                this,
                                                LEFT_BASIC_DRAWABLE
                                            ) //LEFT Drawable image clicked
                                    } else {
                                        edt_fasation_edit_text_main.text!!.clear() //Clear image clicked

                                        if (fasationEditTextLeftImageSrc != -777)
                                            setLeftDrawableImage(fasationEditTextLeftImageSrc)
                                        else
                                            showLeftDrawableImage(false)

                                        listener?.onFasationEditTextLeftDrawableClick(
                                            this,
                                            LEFT_CLEAR_ACTION_DRAWABLE
                                        )
                                    }
                                }
                                RIGHT -> passwordImageClicked(LEFT) //Password image clicked
                            }
                        } else {
                            when (fasationEditTextClearActionPosition) {
                                LEFT -> {
                                    if (edt_fasation_edit_text_main.text.isNullOrEmpty()) {
                                        if (fasationEditTextLeftImageSrc != -777)
                                            listener?.onFasationEditTextLeftDrawableClick(
                                                this,
                                                LEFT_BASIC_DRAWABLE
                                            ) //LEFT Drawable image clicked
                                    } else {
                                        edt_fasation_edit_text_main.text!!.clear() //Clear image clicked

                                        if (fasationEditTextLeftImageSrc != -777)
                                            setLeftDrawableImage(fasationEditTextLeftImageSrc)
                                        else
                                            showLeftDrawableImage(false)

                                        listener?.onFasationEditTextLeftDrawableClick(
                                            this,
                                            LEFT_CLEAR_ACTION_DRAWABLE
                                        )
                                    }
                                }
                                RIGHT -> {
                                    if (fasationEditTextLeftImageSrc != -777)
                                        listener?.onFasationEditTextLeftDrawableClick(
                                            this,
                                            LEFT_BASIC_DRAWABLE
                                        ) //LEFT Drawable image clicked
                                }
                            }
                        }
                    }
                    else -> {
                        if (isInitialTypeAnyPassword()) {
                            when (fasationEditTextClearActionPosition) {
                                LEFT -> {
                                    if (fasationEditTextLeftImageSrc != -777)
                                        listener?.onFasationEditTextLeftDrawableClick(
                                            this,
                                            LEFT_BASIC_DRAWABLE
                                        ) //LEFT Drawable image clicked
                                }
                                RIGHT -> passwordImageClicked(LEFT) //Password image clicked
                            }
                        } else {
                            if (fasationEditTextLeftImageSrc != -777)
                                listener?.onFasationEditTextLeftDrawableClick(
                                    this,
                                    LEFT_BASIC_DRAWABLE
                                ) //LEFT Drawable image clicked
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
                                            listener?.onFasationEditTextRightDrawableClick(
                                                this,
                                                RIGHT_BASIC_DRAWABLE
                                            ) //RIGHT Drawable image clicked
                                    } else {
                                        edt_fasation_edit_text_main.text!!.clear() //Clear image clicked

                                        if (fasationEditTextRightImageSrc != -777)
                                            setRightDrawableImage(fasationEditTextRightImageSrc)
                                        else
                                            showRightDrawableImage(false)

                                        listener?.onFasationEditTextRightDrawableClick(
                                            this,
                                            RIGHT_CLEAR_ACTION_DRAWABLE
                                        )
                                    }
                                }
                            }
                        } else {
                            when (fasationEditTextClearActionPosition) {
                                LEFT -> {
                                    if (fasationEditTextRightImageSrc != -777)
                                        listener?.onFasationEditTextRightDrawableClick(
                                            this,
                                            RIGHT_BASIC_DRAWABLE
                                        ) //RIGHT Drawable image clicked
                                    else
                                        showRightDrawableImage(false)
                                }
                                RIGHT -> {
                                    if (edt_fasation_edit_text_main.text.isNullOrEmpty()) {
                                        if (fasationEditTextRightImageSrc != -777)
                                            listener?.onFasationEditTextRightDrawableClick(
                                                this,
                                                RIGHT_BASIC_DRAWABLE
                                            ) //RIGHT Drawable image clicked
                                        else
                                            showRightDrawableImage(false)
                                    } else {
                                        edt_fasation_edit_text_main.text!!.clear() //Clear image clicked

                                        if (fasationEditTextRightImageSrc != -777)
                                            setRightDrawableImage(fasationEditTextRightImageSrc)
                                        else
                                            showRightDrawableImage(false)

                                        listener?.onFasationEditTextRightDrawableClick(
                                            this,
                                            RIGHT_CLEAR_ACTION_DRAWABLE
                                        )
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
                                        listener?.onFasationEditTextRightDrawableClick(
                                            this,
                                            RIGHT_BASIC_DRAWABLE
                                        ) //RIGHT Drawable image clicked
                                }
                            }
                        } else {
                            if (fasationEditTextRightImageSrc != -777)
                                listener?.onFasationEditTextRightDrawableClick(
                                    this,
                                    RIGHT_BASIC_DRAWABLE
                                ) //RIGHT Drawable image clicked
                        }
                    }
                }
            }
        }

        setCorrectCursorPlace()
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
                typedArray.getResourceId(
                    R.styleable.FasationEditText_secure_password_drawable,
                    fasationEditTextSecurePasswordImageSrc
                )

            fasationEditTextUnSecurePasswordImageSrc =
                typedArray.getResourceId(
                    R.styleable.FasationEditText_un_secure_password_drawable,
                    fasationEditTextUnSecurePasswordImageSrc
                )

            fasationEditTextClearActionImageSrc =
                typedArray.getResourceId(
                    R.styleable.FasationEditText_clear_action_drawable,
                    fasationEditTextClearActionImageSrc
                )

            fasationEditTextRightImageSrc =
                typedArray.getResourceId(
                    R.styleable.FasationEditText_right_drawable,
                    fasationEditTextRightImageSrc
                )

            fasationEditTextLeftImageSrc =
                typedArray.getResourceId(
                    R.styleable.FasationEditText_left_drawable,
                    fasationEditTextLeftImageSrc
                )

            fasationEditTextInputType =
                typedArray.getInteger(
                    R.styleable.FasationEditText_android_inputType,
                    fasationEditTextInputType
                )

            fasationEditTextMainText =
                typedArray.getString(R.styleable.FasationEditText_android_text)
                    ?: fasationEditTextMainText

            fasationEditTextMainTextFont =
                typedArray.getString(R.styleable.FasationEditText_text_font)
                    ?: fasationEditTextMainTextFont

            fasationEditTextSize =
                typedArray.getDimension(
                    R.styleable.FasationEditText_android_textSize,
                    edt_fasation_edit_text_main.textSize
                )

            fasationEditTextColor =
                typedArray.getColor(R.styleable.FasationEditText_text_color, fasationEditTextColor)

            fasationEditTextMainHint =
                typedArray.getString(R.styleable.FasationEditText_android_hint)
                    ?: fasationEditTextMainHint

            fasationEditTextHintColor =
                typedArray.getColor(
                    R.styleable.FasationEditText_android_textColorHint,
                    fasationEditTextHintColor
                )

            fasationEditTextHeight =
                typedArray.getDimension(
                    R.styleable.FasationEditText_text_height,
                    fasationEditTextHeight
                )

            fasationEditTextGravity =
                typedArray.getInt(
                    R.styleable.FasationEditText_text_gravity,
                    fasationEditTextGravity
                )

            fasationEditTextStatus =
                when (typedArray.getInteger(R.styleable.FasationEditText_status, 0)) {
                    0 -> NORMAL
                    1 -> ACTIVE
                    2 -> VALID
                    3 -> INVALID
                    else -> NORMAL
                }

            fasationEditTextNormalColor =
                typedArray.getColor(
                    R.styleable.FasationEditText_normal_color,
                    fasationEditTextNormalColor
                )

            fasationEditTextActiveColor =
                typedArray.getColor(
                    R.styleable.FasationEditText_active_color,
                    fasationEditTextActiveColor
                )

            fasationEditTextValidColor =
                typedArray.getColor(
                    R.styleable.FasationEditText_valid_color,
                    fasationEditTextValidColor
                )

            fasationEditTextInvalidColor =
                typedArray.getColor(
                    R.styleable.FasationEditText_invalid_color,
                    fasationEditTextInvalidColor
                )

            fasationEditTextBorderWidth =
                typedArray.getDimension(
                    R.styleable.FasationEditText_border_width,
                    fasationEditTextBorderWidth
                )

            fasationEditTextClearActionPosition =
                when (typedArray.getInteger(
                    R.styleable.FasationEditText_clear_action_position,
                    1
                )) {
                    0 -> LEFT
                    1 -> RIGHT
                    else -> RIGHT
                }

            fasationEditTextImeOptions =
                typedArray.getInteger(
                    R.styleable.FasationEditText_android_imeOptions,
                    fasationEditTextImeOptions
                )

            fasationEditTextDescriptionText =
                typedArray.getString(R.styleable.FasationEditText_description_text)
                    ?: fasationEditTextDescriptionText

            fasationEditTextDescriptionTextColor =
                typedArray.getColor(
                    R.styleable.FasationEditText_description_text_color,
                    fasationEditTextDescriptionTextColor
                )

            fasationEditTextDescriptionTextSize =
                typedArray.getDimension(
                    R.styleable.FasationEditText_description_text_size,
                    fasationEditTextDescriptionTextSize
                )

            fasationEditTextDescriptionTextFont =
                typedArray.getString(R.styleable.FasationEditText_description_text_font)
                    ?: fasationEditTextDescriptionTextFont

            fasationEditTextRightDrawableSpace =
                typedArray.getBoolean(
                    R.styleable.FasationEditText_right_drawable_space,
                    fasationEditTextRightDrawableSpace
                )

            fasationEditTextLeftDrawableSpace =
                typedArray.getBoolean(
                    R.styleable.FasationEditText_left_drawable_space,
                    fasationEditTextLeftDrawableSpace
                )

            fasationEditTextSingleLine =
                typedArray.getBoolean(
                    R.styleable.FasationEditText_android_singleLine,
                    fasationEditTextSingleLine
                )

            fasationEditTextMaxLength =
                typedArray.getInteger(
                    R.styleable.FasationEditText_android_maxLength,
                    fasationEditTextMaxLength
                )

            fasationEditTextMaxLines =
                typedArray.getInteger(
                    R.styleable.FasationEditText_android_maxLines,
                    fasationEditTextMaxLines
                )

            typedArray.recycle()
        }
    }

    private fun initViews() {
        setFocusChangeListenerForEditText()
        setInputType(fasationEditTextInputType) //Set input type
        setStatus(fasationEditTextStatus) //Set edit text status
        setBorderWidth(fasationEditTextBorderWidth.toInt()) //Set main border width
        setText(fasationEditTextMainText) //Set main text content
        setTextSize(fasationEditTextSize) //Set main text size
        setTextColor(fasationEditTextColor) //Set main text color
        setTextFont(fasationEditTextMainTextFont) //Set main text font
        setTextHeight(fasationEditTextHeight) //Set main text height
        setTextGravity(fasationEditTextGravity) //Set main text gravity
        setHint(fasationEditTextMainHint) //Set main text hint
        setHintColor(fasationEditTextHintColor) //Set main text hint color
        setDescription(fasationEditTextDescriptionText) //Set description text
        setDescriptionSize(fasationEditTextDescriptionTextSize) //Set description text
        setDescriptionColor(fasationEditTextDescriptionTextColor) //Set description text
        setDescriptionFont(fasationEditTextDescriptionTextFont) //Set description text font
        setSingleLine(fasationEditTextSingleLine) //Set text max line
        setMaxLength(fasationEditTextMaxLength) //Set text max length
        setMaxLine(fasationEditTextMaxLines) //Set text max line
        setImeOptions(fasationEditTextImeOptions) //Set keyboard action as imeOptions

        if (isInitialTypeAnyPassword()) {
            setPasswordImage()
            handlePasswordInputType()

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

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })

        //Need these lines because hint goes wrong place in some input type, so do this just after length is one, just once
//        edt_fasation_edit_text_main.setOnFocusChangeListener { _, hasFocus ->
//            if (isInitialTypeNumberOrPhone())
//                if (hasFocus) {
//                    if (edt_fasation_edit_text_main.text.isNullOrEmpty()) {
//                        edt_fasation_edit_text_main.hint = ""
//                        edt_fasation_edit_text_main.inputType = initialInputType
//                        edt_fasation_edit_text_main.isActivated = true
//                        edt_fasation_edit_text_main.isPressed = true
//                    }
//                } else {
//                    if (edt_fasation_edit_text_main.text.isNullOrEmpty())
//                        edt_fasation_edit_text_main.inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_NORMAL
//                    setHint(fasationEditTextMainHint) //Set main text hint
//                }
//        }
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
    private fun convertDpToPx(dp: Float): Int {
        return (dp * context!!.resources.displayMetrics.density).toInt()
    }

    private fun convertPxToSp(px: Float): Float {
        return px / resources.displayMetrics.scaledDensity
    }

    private fun passwordImageClicked(position: Position) {
        if (hidePassword) {
            when (position) {
                LEFT -> {
                    changeLeftDrawableImage(fasationEditTextSecurePasswordImageSrc)
                    listener?.onFasationEditTextLeftDrawableClick(this, LEFT_HIDE_PASSWORD_DRAWABLE)
                }
                RIGHT -> {
                    changeRightDrawableImage(fasationEditTextSecurePasswordImageSrc)
                    listener?.onFasationEditTextRightDrawableClick(
                        this,
                        RIGHT_HIDE_PASSWORD_DRAWABLE
                    )
                }
            }
        } else {
            when (position) {
                LEFT -> {
                    changeLeftDrawableImage(fasationEditTextUnSecurePasswordImageSrc)
                    listener?.onFasationEditTextLeftDrawableClick(this, LEFT_SHOW_PASSWORD_DRAWABLE)
                }
                RIGHT -> {
                    changeRightDrawableImage(fasationEditTextUnSecurePasswordImageSrc)
                    listener?.onFasationEditTextRightDrawableClick(
                        this,
                        RIGHT_SHOW_PASSWORD_DRAWABLE
                    )
                }
            }
        }

        handlePasswordInputType()
    }

    private fun handlePasswordInputType() {
        if (edt_fasation_edit_text_main.text.isNullOrEmpty()) {
            if (hidePassword) {
                edt_fasation_edit_text_main.inputType = TYPE_CLASS_TEXT or initialInputType
                edt_fasation_edit_text_main.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            } else {
                if (initialInputType == TYPE_CLASS_TEXT or TYPE_NUMBER_VARIATION_PASSWORD)
                    edt_fasation_edit_text_main.inputType =
                        TYPE_CLASS_TEXT or TYPE_NUMBER_VARIATION_NORMAL
                else
                    edt_fasation_edit_text_main.inputType =
                        TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_NORMAL
            }

            hidePassword = !hidePassword

            setTextFont(fasationEditTextMainTextFont)
        }
    }

    private fun isInitialTypeAnyPassword(): Boolean {
        return initialInputType == (TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD) ||
                initialInputType == (TYPE_CLASS_TEXT or TYPE_NUMBER_VARIATION_PASSWORD) ||
                initialInputType == (TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) ||
                initialInputType == (TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_WEB_PASSWORD)
    }

//    private fun isInitialTypeNumberOrPhone(): Boolean {
//        return initialInputType == (TYPE_CLASS_TEXT or TYPE_NUMBER_FLAG_DECIMAL) ||
//                initialInputType == (TYPE_CLASS_TEXT or TYPE_NUMBER_FLAG_SIGNED) ||
//                initialInputType == (TYPE_CLASS_TEXT or TYPE_NUMBER_VARIATION_NORMAL) ||
//                initialInputType == (TYPE_CLASS_TEXT or TYPE_NUMBER_VARIATION_PASSWORD) ||
//                initialInputType == (TYPE_CLASS_TEXT or TYPE_CLASS_PHONE)
//    }

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

    private fun setFocusChangeListenerForEditText() {
        edt_fasation_edit_text_main.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            listener?.onFasationEditTextFocusChanged(this@FasationEditText, hasFocus)
        }
    }
    //endregion Private Methods

    //region Public Methods
    fun showLeftDrawableImage(show: Boolean) {
        img_fasation_edit_text_left.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    fun setInputType(inputType: Int) {
        initialInputType = inputType

//        if (edt_fasation_edit_text_main.isFocused)
        edt_fasation_edit_text_main.inputType = inputType
    }

    fun setStatus(status: Status) {
        when (status) {
            NORMAL -> setBorderColor(fasationEditTextNormalColor)
            ACTIVE -> setBorderColor(fasationEditTextActiveColor)
            VALID -> setBorderColor(fasationEditTextValidColor)
            INVALID -> setBorderColor(fasationEditTextInvalidColor)
        }
    }

    /**
     * @param color: send [androidx.core.content.res.getColor] as color param and DO NOT send id of color
     */
    fun setBorderColor(color: Int) {
        val drawable = AppCompatResources.getDrawable(
            context,
            R.drawable.rounded_corner_background
        ) as GradientDrawable
        drawable.setStroke(fasationEditTextBorderWidth.toInt(), color)
        invalidateDrawable(drawable)
        ctl_fasation_edit_text_main.background = drawable
        invalidate()
    }

    /**
     * @param color: send [androidx.core.content.res.getColor] as color param and DO NOT send id of color
     */
    fun setWholeBorderColor(color: Int) {
        ctl_fasation_edit_text_main.background = null

        val drawable = AppCompatResources.getDrawable(
            context,
            R.drawable.rounded_corner_background
        ) as GradientDrawable
        drawable.setStroke(fasationEditTextBorderWidth.toInt(), color)
        invalidateDrawable(drawable)
        this.background = drawable

        invalidate()
    }

    fun setBorderWidth(borderWidth: Int) {
        fasationEditTextBorderWidth = borderWidth.toFloat()

        val drawable = ctl_fasation_edit_text_main.background as GradientDrawable
        val tempColor = when (fasationEditTextStatus) {
            NORMAL -> fasationEditTextNormalColor
            ACTIVE -> fasationEditTextActiveColor
            VALID -> fasationEditTextValidColor
            INVALID -> fasationEditTextInvalidColor
        }
        drawable.setStroke(borderWidth, tempColor)
    }

    fun setText(text: String) {
        fasationEditTextMainText = text
        edt_fasation_edit_text_main.setText(fasationEditTextMainText) //Set main text content

        if (fasationEditTextStatus == ACTIVE && !edt_fasation_edit_text_main.text.isNullOrEmpty())
            when (fasationEditTextClearActionPosition) {
                LEFT -> showLeftDrawableImage(true)
                RIGHT -> showRightDrawableImage(true)
            }

        setCorrectCursorPlace()
    }

    fun getText(): String {
        return edt_fasation_edit_text_main.text.toString()
    }

    fun setTextSize(size: Float) {
        fasationEditTextSize = size
        edt_fasation_edit_text_main.textSize =
            convertPxToSp(fasationEditTextSize) // Set main text size
    }

    fun setTextColor(color: Int) {
        fasationEditTextColor = color
        edt_fasation_edit_text_main.setTextColor(fasationEditTextColor) //Set main text color
    }

    fun setTextFont(font: String) {
        if (font.isNotEmpty()) { //Set main text font
            fasationEditTextMainTextFont = font
            edt_fasation_edit_text_main.typeface =
                Typeface.createFromAsset(context.assets, fasationEditTextMainTextFont)
        }
    }

    fun setTextHeight(height: Float) {
        fasationEditTextHeight = height

        if (fasationEditTextHeight >= convertDpToPx(14f)) { //Set main edit text height
            val params = edt_fasation_edit_text_main.layoutParams
            params.height = fasationEditTextHeight.toInt()
            edt_fasation_edit_text_main.layoutParams = params
            edt_fasation_edit_text_main.requestLayout()
        }
    }

    private fun setTextGravity(gravity: Int) {
        edt_fasation_edit_text_main.gravity = gravity
    }

    fun setHint(hint: String) {
        fasationEditTextMainHint = hint
        edt_fasation_edit_text_main.hint = fasationEditTextMainHint //Set main text hint
    }

    fun setHintColor(color: Int) {
        fasationEditTextHintColor = color
        edt_fasation_edit_text_main.setHintTextColor(fasationEditTextHintColor) //Set main text hint color
    }

    fun setDescription(message: String) {
        fasationEditTextDescriptionText = message
        txv_fasation_edit_text_description.text =
            fasationEditTextDescriptionText //Set description content
        txv_fasation_edit_text_description.visibility = View.VISIBLE
    }

    fun setDescriptionSize(size: Float) {
        fasationEditTextDescriptionTextSize = size
        txv_fasation_edit_text_description.textSize =
            convertPxToSp(fasationEditTextDescriptionTextSize) // Set description text size
        clearDescription()
    }

    fun setDescriptionColor(color: Int) {
        fasationEditTextDescriptionTextColor = color
        txv_fasation_edit_text_description.setTextColor(fasationEditTextDescriptionTextColor) //Set description text color
        clearDescription()
    }

    fun setDescriptionFont(font: String) {
        if (font.isNotEmpty()) { //Set description font
            fasationEditTextDescriptionTextFont = font
            txv_fasation_edit_text_description.typeface =
                Typeface.createFromAsset(context.assets, fasationEditTextDescriptionTextFont)
            clearDescription()
        }
    }

    fun clearDescription() {
        txv_fasation_edit_text_description.text = null
        txv_fasation_edit_text_description.visibility = View.GONE
    }

    fun setLeftDrawableImage(resourceId: Int) {
        fasationEditTextLeftImageSrc = resourceId
        img_fasation_edit_text_left.setImageResource(resourceId)
    }

    fun setRightDrawableImage(resourceId: Int) {
        fasationEditTextRightImageSrc = resourceId
        img_fasation_edit_text_right.setImageResource(resourceId)
    }

    fun showRightDrawableImage(show: Boolean) {
        img_fasation_edit_text_right.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    @Deprecated(
        level = DeprecationLevel.ERROR,
        message = "We are going to replace with setListener(YOUR_LISTENER)",
        replaceWith = ReplaceWith(
            expression = "YOUR_VIEW.setListener(this)",
            imports = ["ir.fasation.edittext.setListener"]
        )
    )
    fun setOnDrawableClickListener(listener: FasationEditTextListener) {
        this.listener = listener
    }

    fun setListener(listener: FasationEditTextListener) {
        this.listener = listener
    }

    fun setSingleLine(singleLine: Boolean) {
        edt_fasation_edit_text_main.setSingleLine(singleLine)
    }

    fun setMaxLength(maxLength: Int) {
        edt_fasation_edit_text_main.filters =
            arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

    fun getEditText(): EditText? {
        return edt_fasation_edit_text_main
    }

    fun addBottomView(
        customView: View?,
        width: Int?,
        height: Int?,
        dividerColor: Int?,
        dividerHeight: Float?
    ) {
        if (customView == null)
            throw NullPointerException("customView must not be null.")

        if (customView.id == -1)
            throw NullPointerException("customView must have an Id.")

        var view: View?

        //if custom View exists, remove it first
        view = main_view.findViewById(R.id.view_fasation_edit_text_bottom_divider)
        if (view != null) {
            val position =
                main_view.indexOfChild(main_view.findViewById<View>(R.id.view_fasation_edit_text_bottom_divider))

            main_view.removeViewAt(position + 1)
        } else {
            //add divider View if not exists
            view = View(context)
            view.id = R.id.view_fasation_edit_text_bottom_divider
            main_view.addView(view)

            view.setBackgroundColor(
                dividerColor
                    ?: getColor(resources, R.color.default_bottom_view_divider_color, context.theme)
            )

            val params = view.layoutParams as ConstraintLayout.LayoutParams
            params.width = 0
            params.height = convertDpToPx(dividerHeight ?: 1f)

            params.setMargins(
                convertDpToPx(16f),
                convertDpToPx(8f),
                convertDpToPx(16f),
                convertDpToPx(8f)
            )
            view.layoutParams = params
            view.requestLayout()
        }

        //add custom View
        main_view.addView(customView)

        val params = customView.layoutParams as ConstraintLayout.LayoutParams
        params.width = width ?: 0
        params.height = height ?: ViewGroup.LayoutParams.WRAP_CONTENT

        params.setMargins(
            convertDpToPx(8f),
            convertDpToPx(8f),
            convertDpToPx(8f),
            convertDpToPx(8f)
        )
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

    fun clearBottomView() {
        if (main_view.findViewById<View>(R.id.view_fasation_edit_text_bottom_divider) != null) {
            val position =
                main_view.indexOfChild(main_view.findViewById<View>(R.id.view_fasation_edit_text_bottom_divider))

            main_view.removeViewAt(position)
            main_view.removeViewAt(position)
        }
    }

    fun getBottomViewDividerId(): Int {
        return R.id.view_fasation_edit_text_bottom_divider
    }

    fun setImeOptions(fasationEditTextImeOptions: Int) {
        this.fasationEditTextImeOptions = fasationEditTextImeOptions
        edt_fasation_edit_text_main.imeOptions = fasationEditTextImeOptions
    }

    fun setMaxLine(fasationEditTextMaxLine: Int) {
        this.fasationEditTextMaxLines = fasationEditTextMaxLine
        edt_fasation_edit_text_main.maxLines = fasationEditTextMaxLine
    }

    fun setCorrectCursorPlace() {
        edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length)
    }

    fun setEditTextEnabled(enable: Boolean) {
        edt_fasation_edit_text_main.isEnabled = enable
        img_fasation_edit_text_left.isEnabled = enable
        img_fasation_edit_text_right.isEnabled = enable
    }
    //endregion Public Methods
}