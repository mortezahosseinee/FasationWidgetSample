package ir.fasation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

class FasationTextView @JvmOverloads constructor(context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr),
        View.OnClickListener {

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
    //endregion Custom Attributes

    //region Constructor
    init {
        initMain(context)
    }
    //endregion Constructor

    //region Main Callbacks
    override fun onClick(view: View) {
        when (view.id) {
        }
    }
    //endregion Main Callbacks

    //region Declare Methods
    private fun initMain(context: Context) {
        View.inflate(context, R.layout.fasation_text_view, this)
        initAttributes()
        initViews()
    }

    private fun initAttributes() {
//        attrs?.let {
//            val typedArray = context.obtainStyledAttributes(it, R.styleable.FasationBottomNavigation, 0, 0)
//
//            fasationBottomNavigationFirstImageSrc =
//                    typedArray.getResourceId(R.styleable.FasationBottomNavigation_first_image_src, R.drawable.ic_default)
//
//            fasationBottomNavigationSecondImageSrc =
//                    typedArray.getResourceId(R.styleable.FasationBottomNavigation_second_image_src, R.drawable.ic_default)
//
//            fasationBottomNavigationThirdImageSrc =
//                    typedArray.getResourceId(R.styleable.FasationBottomNavigation_third_image_src, R.drawable.ic_default)
//
//            fasationBottomNavigationFourthImageSrc =
//                    typedArray.getResourceId(R.styleable.FasationBottomNavigation_fourth_image_src, R.drawable.ic_default)
//
//            fasationBottomNavigationFifthImageSrc =
//                    typedArray.getResourceId(R.styleable.FasationBottomNavigation_fifth_image_src, R.drawable.ic_default)
//
//            fasationBottomNavigationFirstItemIconWidth =
//                    typedArray.getInt(R.styleable.FasationBottomNavigation_first_item_icon_width, defaultItemIconSize)
//            fasationBottomNavigationFirstItemIconHeight =
//                    typedArray.getInt(R.styleable.FasationBottomNavigation_first_item_icon_height, defaultItemIconSize)
//
//            fasationBottomNavigationSecondItemIconWidth =
//                    typedArray.getInt(R.styleable.FasationBottomNavigation_second_item_icon_width, defaultItemIconSize)
//            fasationBottomNavigationSecondItemIconHeight =
//                    typedArray.getInt(R.styleable.FasationBottomNavigation_second_item_icon_height, defaultItemIconSize)
//
//            fasationBottomNavigationFourthItemIconWidth =
//                    typedArray.getInt(R.styleable.FasationBottomNavigation_third_item_icon_width, defaultItemIconSize)
//            fasationBottomNavigationFourthItemIconHeight =
//                    typedArray.getInt(R.styleable.FasationBottomNavigation_third_item_icon_height, defaultItemIconSize)
//
//            fasationBottomNavigationThirdItemIconWidth =
//                    typedArray.getInt(R.styleable.FasationBottomNavigation_fourth_item_icon_width, defaultItemIconSize)
//            fasationBottomNavigationThirdItemIconHeight =
//                    typedArray.getInt(R.styleable.FasationBottomNavigation_fourth_item_icon_height, defaultItemIconSize)
//
//            fasationBottomNavigationFifthItemIconWidth =
//                    typedArray.getInt(R.styleable.FasationBottomNavigation_fifth_item_icon_width, defaultItemIconSize)
//            fasationBottomNavigationFifthItemIconHeight =
//                    typedArray.getInt(R.styleable.FasationBottomNavigation_fifth_item_icon_height, defaultItemIconSize)
//
//            fasationBottomNavigationIconActiveColor =
//                    typedArray.getColor(R.styleable.FasationBottomNavigation_icon_active_color,
//                            ContextCompat.getColor(context, R.color.fasation_bottom_navigation_active_item_icon_color))
//            fasationBottomNavigationIconInactiveColor =
//                    typedArray.getColor(R.styleable.FasationBottomNavigation_icon_inactive_color,
//                            ContextCompat.getColor(context, R.color.fasation_bottom_navigation_inactive_item_icon_color))
//
//            fasationBottomNavigationDefaultSelectedItemIndex =
//                    typedArray.getInteger(R.styleable.FasationBottomNavigation_default_selected_item_index, 2)
//
//            fasationBottomNavigationBackgroundColor =
//                    typedArray.getColor(R.styleable.FasationBottomNavigation_background_color,
//                            ContextCompat.getColor(context, R.color.fasation_bottom_navigation_background_color))
//
//            typedArray.recycle()
    }

    private fun initViews() {
//        image_navigation_items_first!!.setOnClickListener(this)
//        image_navigation_items_second!!.setOnClickListener(this)
//        image_navigation_items_third!!.setOnClickListener(this)
//        image_navigation_items_fourth!!.setOnClickListener(this)
//        image_navigation_items_fifth!!.setOnClickListener(this)

//        image_navigation_items_first!!.setImageDrawable(ContextCompat.getDrawable(context, fasationBottomNavigationFirstImageSrc))
//        image_navigation_items_second!!.setImageDrawable(ContextCompat.getDrawable(context, fasationBottomNavigationSecondImageSrc))
//        image_navigation_items_third!!.setImageDrawable(ContextCompat.getDrawable(context, fasationBottomNavigationThirdImageSrc))
//        image_navigation_items_fourth!!.setImageDrawable(ContextCompat.getDrawable(context, fasationBottomNavigationFourthImageSrc))
//        image_navigation_items_fifth!!.setImageDrawable(ContextCompat.getDrawable(context, fasationBottomNavigationFifthImageSrc))
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

    //region Private Methods
    //endregion Private Methods

    //region Public Methods
    //endregion Public Methods
}
