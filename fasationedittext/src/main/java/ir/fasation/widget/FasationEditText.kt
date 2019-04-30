package ir.fasation.widget

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.JELLY_BEAN_MR1
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
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
    private var listener: FasationEditTextOnDrawableClickListener? = null
    //endregion Declare Objects

    //region Declare Views
    //endregion Declare Views

    //region Custom Attributes
    private var fasationEditTextSecurePasswordImageSrc = R.drawable.ic_secure
    private var fasationEditTextUnSecurePasswordImageSrc = R.drawable.ic_unsecure
    private var fasationEditTextClearActionImageSrc = R.drawable.ic_clear
    private var fasationEditTextLeftImageSrc = -777
    private var fasationEditTextRightImageSrc = -777
    private var fasationEditTextMainText = ""
    private var fasationEditTextHeight = 14f //dp
    private var fasationEditTextSize = 14f //sp
    private var fasationEditTextColor = ContextCompat.getColor(context, android.R.color.black)
    private var fasationEditTextStatus = 0
    private var fasationEditTextInputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
    private var fasationEditTextNormalColor = ContextCompat.getColor(context, R.color.grey)
    private var fasationEditTextActiveColor = ContextCompat.getColor(context, R.color.grey)
    private var fasationEditTextValidColor = ContextCompat.getColor(context, android.R.color.holo_green_dark)
    private var fasationEditTextInvalidColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
    private var fasationEditTextBorderWidth = 1f //dp
    private var fasationEditTextClearActionPosition = 1
    private var fasationEditTextDescriptionText = ""
    private var fasationEditTextDescriptionTextColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
    private var fasationEditTextDescriptionTextSize = 14f //sp
    private var fasationEditTextDescriptionTextFont = ""
    private var fasationEditTextRightDrawableSpace = true
    private var fasationEditTextLeftDrawableSpace = true
    private var fasationEditTextMainHint = ""
    private var fasationEditTextHintColor = ContextCompat.getColor(context, android.R.color.darker_gray)
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
                if (fasationEditTextStatus == 1) {
                    if (initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD) {
                        if (fasationEditTextClearActionPosition == 0) {
                            edt_fasation_edit_text_main.text!!.clear() //Clear image clicked
                            listener?.onFasationEditTextLeftDrawableClick(LeftDrawableMode.LEFT_CLEAR_ACTION_DRAWABLE)
                        } else {
                            passwordImageClicked(Position.Left) //Password image clicked
                        }
                    } else {
                        if (fasationEditTextClearActionPosition == 0) {
                            edt_fasation_edit_text_main.text!!.clear() //Clear image clicked
                            listener?.onFasationEditTextLeftDrawableClick(LeftDrawableMode.LEFT_CLEAR_ACTION_DRAWABLE)
                        } else {
                            if (fasationEditTextLeftImageSrc != -777)
                                listener?.onFasationEditTextLeftDrawableClick(LeftDrawableMode.LEFT_BASIC_DRAWABLE) //Left Drawable image clicked
                        }
                    }
                } else {
                    if (initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD) {
                        if (fasationEditTextClearActionPosition == 0) {
                            if (fasationEditTextLeftImageSrc != -777)
                                listener?.onFasationEditTextLeftDrawableClick(LeftDrawableMode.LEFT_BASIC_DRAWABLE) //Left Drawable image clicked
                        } else {
                            passwordImageClicked(Position.Left) //Password image clicked
                        }
                    } else {
                        if (fasationEditTextClearActionPosition == 0) {
                            if (fasationEditTextLeftImageSrc != -777)
                                listener?.onFasationEditTextLeftDrawableClick(LeftDrawableMode.LEFT_BASIC_DRAWABLE) //Left Drawable image clicked
                        } else {
                            if (fasationEditTextLeftImageSrc != -777)
                                listener?.onFasationEditTextLeftDrawableClick(LeftDrawableMode.LEFT_BASIC_DRAWABLE) //Left Drawable image clicked
                        }
                    }
                }

                edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length)

                //TODO
//                if (fasationEditTextStatus == 1) {
//                    if (fasationEditTextClearActionPosition == 0) {
//                        edt_fasation_edit_text_main.text!!.clear()
//                    } else {
//                        if (initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
//                                initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
//                                initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
//                                initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD)
//                            if (showPassword) {
//                                img_fasation_edit_text_left!!.setImageDrawable(getDrawable(context, fasationEditTextSecurePasswordImageSrc))
//                                edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or initialInputType
//                                edt_fasation_edit_text_main.transformationMethod = PasswordTransformationMethod.getInstance()
//                            } else {
//                                img_fasation_edit_text_left!!.setImageDrawable(getDrawable(context, fasationEditTextUnSecurePasswordImageSrc))
//
//                                if (initialInputType == InputType.TYPE_NUMBER_VARIATION_PASSWORD)
//                                    edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_NORMAL
//                                else
//                                    edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
//                            }
//
//                        showPassword = !showPassword
//                        edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length)
//                    }
//                } else {
//                    if (initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
//                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
//                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
//                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD)
//                        if (showPassword) {
//                            img_fasation_edit_text_left!!.setImageDrawable(getDrawable(context, fasationEditTextSecurePasswordImageSrc))
//                            edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or initialInputType
//                            edt_fasation_edit_text_main.transformationMethod = PasswordTransformationMethod.getInstance()
//                        } else {
//                            img_fasation_edit_text_left!!.setImageDrawable(getDrawable(context, fasationEditTextUnSecurePasswordImageSrc))
//
//                            if (initialInputType == InputType.TYPE_NUMBER_VARIATION_PASSWORD)
//                                edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_NORMAL
//                            else
//                                edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
//                        }
//
//                    showPassword = !showPassword
//                    edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length)
//                }
//
//                listener?.onFasationEditTextLeftDrawableClick()
            }
            R.id.img_fasation_edit_text_right -> {
                if (fasationEditTextStatus == 1) {
                    if (initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD) {
                        if (fasationEditTextClearActionPosition == 0) {
                            passwordImageClicked(Position.Right) //Password image clicked
                        } else {
                            edt_fasation_edit_text_main.text!!.clear() //Clear image clicked
                            listener?.onFasationEditTextRightDrawableClick(RightDrawableMode.RIGHT_CLEAR_ACTION_DRAWABLE)
                        }
                    } else {
                        if (fasationEditTextClearActionPosition == 0) {
                            if (fasationEditTextRightImageSrc != -777)
                                listener?.onFasationEditTextRightDrawableClick(RightDrawableMode.RIGHT_BASIC_DRAWABLE) //Right Drawable image clicked
                        } else {
                            edt_fasation_edit_text_main.text!!.clear() //Clear image clicked
                            listener?.onFasationEditTextRightDrawableClick(RightDrawableMode.RIGHT_CLEAR_ACTION_DRAWABLE)
                        }
                    }
                } else {
                    if (initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD) {
                        if (fasationEditTextClearActionPosition == 0) {
                            passwordImageClicked(Position.Right) //Password image clicked
                        } else {
                            if (fasationEditTextRightImageSrc != -777)
                                listener?.onFasationEditTextRightDrawableClick(RightDrawableMode.RIGHT_BASIC_DRAWABLE) //Right Drawable image clicked
                        }
                    } else {
                        if (fasationEditTextClearActionPosition == 0) {
                            if (fasationEditTextRightImageSrc != -777)
                                listener?.onFasationEditTextRightDrawableClick(RightDrawableMode.RIGHT_BASIC_DRAWABLE) //Right Drawable image clicked
                        } else {
                            if (fasationEditTextRightImageSrc != -777)
                                listener?.onFasationEditTextRightDrawableClick(RightDrawableMode.RIGHT_BASIC_DRAWABLE) //Right Drawable image clicked
                        }
                    }
                }

                edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length)

                //TODO
//                if (fasationEditTextStatus == 1) {
//                    if (fasationEditTextClearActionPosition == 1) {
//                        edt_fasation_edit_text_main.text!!.clear()
//                    } else {
//                        if (initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
//                                initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
//                                initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
//                                initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD)
//                            if (showPassword) {
//                                img_fasation_edit_text_right!!.setImageDrawable(getDrawable(context, fasationEditTextSecurePasswordImageSrc))
//                                edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or initialInputType
//                                edt_fasation_edit_text_main.transformationMethod = PasswordTransformationMethod.getInstance()
//                            } else {
//                                img_fasation_edit_text_right!!.setImageDrawable(getDrawable(context, fasationEditTextUnSecurePasswordImageSrc))
//
//                                if (initialInputType == InputType.TYPE_NUMBER_VARIATION_PASSWORD)
//                                    edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_NORMAL
//                                else
//                                    edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
//                            }
//
//                        showPassword = !showPassword
//                        edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length)
//                    }
//                } else {
//                    if (initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
//                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
//                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
//                            initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD) {
//                        if (showPassword) {
//                            img_fasation_edit_text_right!!.setImageDrawable(getDrawable(context, fasationEditTextSecurePasswordImageSrc))
//                            edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or initialInputType
//                            edt_fasation_edit_text_main.transformationMethod = PasswordTransformationMethod.getInstance()
//                        } else {
//                            img_fasation_edit_text_right!!.setImageDrawable(getDrawable(context, fasationEditTextUnSecurePasswordImageSrc))
//
//                            if (initialInputType == InputType.TYPE_NUMBER_VARIATION_PASSWORD)
//                                edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_NORMAL
//                            else
//                                edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
//                        }
//
//                        showPassword = !showPassword
//                    } else {
//
//                    }
//
//                    edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length)
//                }
//
//                listener?.onFasationEditTextRightDrawableClick(RightDrawableMode.RIGHT_HIDE_PASSWORD_DRAWABLE)
            }
        }
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

            fasationEditTextSize =
                    typedArray.getDimension(R.styleable.FasationEditText_text_size, edt_fasation_edit_text_main.textSize)

            fasationEditTextColor =
                    typedArray.getColor(R.styleable.FasationEditText_text_color, fasationEditTextColor)

            fasationEditTextMainHint =
                    typedArray.getString(R.styleable.FasationEditText_android_hint)
                            ?: fasationEditTextMainHint

            fasationEditTextHintColor =
                    typedArray.getColor(R.styleable.FasationEditText_hint_color, fasationEditTextHintColor)

            fasationEditTextHeight =
                    typedArray.getDimension(R.styleable.FasationEditText_text_height, fasationEditTextHeight)

            fasationEditTextStatus =
                    typedArray.getInteger(R.styleable.FasationEditText_status, fasationEditTextStatus)

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
                    typedArray.getInteger(R.styleable.FasationEditText_clear_action_position, fasationEditTextClearActionPosition)

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

            typedArray.recycle()
        }
    }

    private fun initViews() {
        initialInputType = fasationEditTextInputType
        edt_fasation_edit_text_main.inputType = fasationEditTextInputType

        img_fasation_edit_text_left.visibility = INVISIBLE
        img_fasation_edit_text_right.visibility = INVISIBLE

        if (!(initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ||
                        initialInputType == InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
                        initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
                        initialInputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD)) {
            if (fasationEditTextStatus == 1) {
                if (fasationEditTextClearActionPosition == 1) {
                    if (fasationEditTextLeftDrawableSpace) {
                        if (fasationEditTextLeftImageSrc == -777)
                            img_fasation_edit_text_left.visibility = INVISIBLE
                        else {
                            img_fasation_edit_text_left!!.setImageDrawable(getDrawable(context, fasationEditTextLeftImageSrc))
                            img_fasation_edit_text_left.visibility = VISIBLE
                        }
                    }
                }
                else
                    img_fasation_edit_text_right.visibility = INVISIBLE
            } else {
                if (fasationEditTextRightDrawableSpace) {
                    if (fasationEditTextRightImageSrc == -777)
                        img_fasation_edit_text_right.visibility = INVISIBLE
                    else {
                        img_fasation_edit_text_right!!.setImageDrawable(getDrawable(context, fasationEditTextRightImageSrc))
                        img_fasation_edit_text_right.visibility = VISIBLE
                    }
                }

                if (fasationEditTextLeftDrawableSpace) {
                    if (fasationEditTextLeftImageSrc == -777)
                        img_fasation_edit_text_left.visibility = INVISIBLE
                    else {
                        img_fasation_edit_text_left!!.setImageDrawable(getDrawable(context, fasationEditTextLeftImageSrc))
                        img_fasation_edit_text_left.visibility = VISIBLE
                    }
                }
            }
        } else {
            if (fasationEditTextClearActionPosition == 1) {
                img_fasation_edit_text_left!!.setImageDrawable(getDrawable(context, fasationEditTextSecurePasswordImageSrc)) //Set left image drawable
                img_fasation_edit_text_left.visibility = VISIBLE

                if (fasationEditTextStatus != 1)
                    if (fasationEditTextRightImageSrc == -777)
                        img_fasation_edit_text_right.visibility = INVISIBLE
                    else {
                        if (fasationEditTextRightDrawableSpace) {
                            img_fasation_edit_text_right!!.setImageDrawable(getDrawable(context, fasationEditTextRightImageSrc))
                            img_fasation_edit_text_right.visibility = VISIBLE
                        }
                    }
            } else {
                if (fasationEditTextStatus != 1) {
                    if (fasationEditTextLeftDrawableSpace) {
                        if (fasationEditTextLeftImageSrc == -777)
                            img_fasation_edit_text_left.visibility = INVISIBLE
                        else {
                            img_fasation_edit_text_left!!.setImageDrawable(getDrawable(context, fasationEditTextLeftImageSrc))
                            img_fasation_edit_text_left.visibility = VISIBLE
                        }
                    }
                }
            }
        }

        img_fasation_edit_text_left!!.setOnClickListener(this) // Set left drawable CLickListener on this view
        img_fasation_edit_text_right!!.setOnClickListener(this) // Set right drawable CLickListener on this view

        edt_fasation_edit_text_main.setText(fasationEditTextMainText)
        edt_fasation_edit_text_main.hint = fasationEditTextMainHint
        edt_fasation_edit_text_main.textSize = convertPxToSp(fasationEditTextSize)
        edt_fasation_edit_text_main.setTextColor(fasationEditTextColor)
        edt_fasation_edit_text_main.setHintTextColor(fasationEditTextHintColor)
        edt_fasation_edit_text_main.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (fasationEditTextStatus == 1)
                    if (!edt_fasation_edit_text_main.text.isNullOrEmpty()) {
                        if (fasationEditTextClearActionPosition == 0) {
                            img_fasation_edit_text_left.setImageResource(fasationEditTextClearActionImageSrc)
                            img_fasation_edit_text_left.visibility = VISIBLE
                        } else {
                            img_fasation_edit_text_right.setImageResource(fasationEditTextClearActionImageSrc)
                            img_fasation_edit_text_right.visibility = VISIBLE
                        }
                    } else {
                        if (fasationEditTextClearActionPosition == 0) {
                            if (fasationEditTextLeftDrawableSpace) {
                                if (fasationEditTextLeftImageSrc == -777)
                                    img_fasation_edit_text_left.visibility = INVISIBLE
                                else {
                                    img_fasation_edit_text_left!!.setImageDrawable(getDrawable(context, fasationEditTextLeftImageSrc))
                                    img_fasation_edit_text_left.visibility = VISIBLE
                                }
                            }
                        } else {
                            if (fasationEditTextRightDrawableSpace) {
                                if (fasationEditTextRightImageSrc == -777)
                                    img_fasation_edit_text_right.visibility = INVISIBLE
                                else {
                                    img_fasation_edit_text_right!!.setImageDrawable(getDrawable(context, fasationEditTextRightImageSrc))
                                    img_fasation_edit_text_right.visibility = VISIBLE
                                }
                            }
                        }
                    }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        if (fasationEditTextHeight >= convertDpToPx(14f)) {
            val params = edt_fasation_edit_text_main.layoutParams
            params.height = fasationEditTextHeight.toInt()
            edt_fasation_edit_text_main.layoutParams = params
            edt_fasation_edit_text_main.requestLayout()
        }

        if (fasationEditTextStatus == 1 && !edt_fasation_edit_text_main.text.isNullOrEmpty()) {
            if (fasationEditTextClearActionPosition == 0) {
                img_fasation_edit_text_left.setImageResource(fasationEditTextClearActionImageSrc)
                img_fasation_edit_text_left.visibility = VISIBLE
            } else {
                img_fasation_edit_text_right.setImageResource(fasationEditTextClearActionImageSrc)
                img_fasation_edit_text_right.visibility = VISIBLE
            }
        }

        updateEditTextStatus()

        if (fasationEditTextDescriptionTextFont.isNotEmpty())
            txv_fasation_edit_text_description.typeface = Typeface.createFromAsset(context.assets, fasationEditTextDescriptionTextFont)

        txv_fasation_edit_text_description.visibility = GONE
        txv_fasation_edit_text_description.text = null
        txv_fasation_edit_text_description.setTextColor(fasationEditTextDescriptionTextColor)

        if (fasationEditTextDescriptionTextSize >= 14f)
            txv_fasation_edit_text_description.textSize = convertPxToSp(fasationEditTextDescriptionTextSize)

        edt_fasation_edit_text_main.setSelection(edt_fasation_edit_text_main.text!!.length) //Set Cursor end of text

        setDescription(fasationEditTextDescriptionText)

        if (!fasationEditTextRightDrawableSpace) {
            img_fasation_edit_text_right.visibility = GONE

            val params = edt_fasation_edit_text_main.layoutParams as LayoutParams
            params.rightMargin = convertDpToPx(16f)

            if (SDK_INT >= JELLY_BEAN_MR1)
                params.marginEnd = convertDpToPx(16f)

            edt_fasation_edit_text_main.layoutParams = params
            edt_fasation_edit_text_main.requestLayout()
        }
    }

    private fun updateEditTextStatus() {
        when (fasationEditTextStatus) {
            0 -> setEditTextBorderColor(fasationEditTextNormalColor)
            1 -> setEditTextBorderColor(fasationEditTextActiveColor)
            2 -> setEditTextBorderColor(fasationEditTextValidColor)
            3 -> setEditTextBorderColor(fasationEditTextInvalidColor)
        }
    }

    fun setEditTextBorderColor(color: Int) {
        val drawable = getDrawable(context, R.drawable.rounded_corner_background) as GradientDrawable
        drawable.setStroke(fasationEditTextBorderWidth.toInt(), color)
        invalidateDrawable(drawable)
        ctl_fasation_edit_text_main.background = drawable
        invalidate()
    }

    fun setEditTextBorderWidth() {
        val drawable = ctl_fasation_edit_text_main.background as GradientDrawable
        val tempColor = when (fasationEditTextStatus) {
            0 -> fasationEditTextNormalColor
            1 -> fasationEditTextActiveColor
            2 -> fasationEditTextValidColor
            3 -> fasationEditTextInvalidColor
            else -> fasationEditTextNormalColor
        }
        drawable.setStroke(fasationEditTextBorderWidth.toInt(), tempColor)
    }

    fun setDescription(message: String) {
        if (fasationEditTextDescriptionText.isNotEmpty()) {
            txv_fasation_edit_text_description.text = message
            txv_fasation_edit_text_description.visibility = VISIBLE
        }
    }

    fun clearDescription() {
        txv_fasation_edit_text_description.text = null
        txv_fasation_edit_text_description.visibility = GONE
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

    private fun convertPxToSp(px: Float): Float {
        return px / resources.displayMetrics.scaledDensity
    }

    private fun passwordImageClicked(position: Position) {
        if (showPassword) {
            img_fasation_edit_text_left!!.setImageDrawable(getDrawable(context, fasationEditTextSecurePasswordImageSrc))
            edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or initialInputType
            edt_fasation_edit_text_main.transformationMethod = PasswordTransformationMethod.getInstance()

            if (position == Position.Right)//RIGHT
                listener?.onFasationEditTextRightDrawableClick(RightDrawableMode.RIGHT_HIDE_PASSWORD_DRAWABLE)
            else
                listener?.onFasationEditTextLeftDrawableClick(LeftDrawableMode.LEFT_HIDE_PASSWORD_DRAWABLE)
        } else {
            img_fasation_edit_text_left!!.setImageDrawable(getDrawable(context, fasationEditTextUnSecurePasswordImageSrc))

            if (initialInputType == InputType.TYPE_NUMBER_VARIATION_PASSWORD)
                edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_NORMAL
            else
                edt_fasation_edit_text_main.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL

            if (position == Position.Right)
                listener?.onFasationEditTextRightDrawableClick(RightDrawableMode.RIGHT_SHOW_PASSWORD_DRAWABLE)
            else
                listener?.onFasationEditTextLeftDrawableClick(LeftDrawableMode.LEFT_SHOW_PASSWORD_DRAWABLE)
        }

        showPassword = !showPassword
    }
    //endregion Private Methods

    //region Public Methods
    fun setOnDrawableClickListener(listener: FasationEditTextOnDrawableClickListener) {
        this.listener = listener
    }
    //endregion Public Methods
}
