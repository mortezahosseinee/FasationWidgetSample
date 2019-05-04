package ir.fasation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import ir.fasation.widget.DismissPosition.CENTER_BOTTOM
import ir.fasation.widget.DismissPosition.RIGHT_BOTTOM

class FasationBottomNavigation @JvmOverloads constructor(context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr) {
    companion object {
        //region Declare Constants
        //endregion Declare Constants
    }

    //region Declare Variables
    //endregion Declare Variables

    //region Declare Objects
    //endregion Declare Objects

    //region Declare Views
    //endregion Declare Views

    //region Custom Attributes
    private var fasationCustomDialogDismissPosition = CENTER_BOTTOM
    //endregion Custom Attributes

    //region Constructor
    init {
        this.setWillNotDraw(false)
        initMain(context)
    }
    //endregion Constructor

    //region Declare Methods
    private fun initMain(context: Context) {
        View.inflate(context, R.layout.fasation_custom_dialog, this)
        initAttributes()
        initViews()
    }

    private fun initAttributes() {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.FasationCustomDialog, 0, 0)

            fasationCustomDialogDismissPosition =
                    when (typedArray.getInteger(R.styleable.FasationCustomDialog_position, 0)) {
                        0 -> CENTER_BOTTOM
                        1 -> RIGHT_BOTTOM
                        2 -> RIGHT_BOTTOM
                        else -> CENTER_BOTTOM
                    }

            typedArray.recycle()
        }
    }

    private fun initViews() {
    }
    //endregion Declare Methods
}