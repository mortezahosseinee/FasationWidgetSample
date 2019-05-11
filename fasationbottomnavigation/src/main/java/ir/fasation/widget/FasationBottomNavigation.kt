package ir.fasation.widget

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.core.content.res.ResourcesCompat.getDrawable
import com.devs.vectorchildfinder.VectorChildFinder
import kotlinx.android.synthetic.main.fasation_bottom_navigation.view.*

class FasationBottomNavigation @JvmOverloads constructor(context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr),
        View.OnClickListener {

    companion object {
        //region Declare Constants
        private const val BIGGER_SIZE = true
        private const val SMALLER_SIZE = false

        private const val defaultItemIconSize = 24 //dp
        //endregion Declare Constants
    }

    //region Declare Variables
    private var emptyRelativeLayoutHeight = 40 //dp
    private var imageBiggerScale = 100.0f / 80
    private var defaultItemPadding = 8 //dp
    private var defaultItemOffset = 0f  //dp
    private var selectedItemHorizontallyOffset: Int = 0

    private var drawableSelectedItemIndex = 2
    private var lastSelectedIndex = -1
    internal var newSelectedIndex = 2
    private var horizontallyOffset: Int = 0

    private var bezierWidth = 0
    private var bezierHeight = 0

    private var firstItemOffset = defaultItemOffset
    private var secondItemOffset = defaultItemOffset
    private var thirdItemOffset = defaultItemOffset
    private var fourthItemOffset = defaultItemOffset
    private var fifthItemOffset = defaultItemOffset

    private var centerMainWidth = 0.9f
    private var besideMainWidth = 0.05

    internal var defaultItemSelectedStatus = false

    internal var firstItemFloatingStatus = true
    internal var secondItemFloatingStatus = true
    internal var thirdItemFloatingStatus = true
    internal var fourthItemFloatingStatus = true
    internal var fifthItemFloatingStatus = true
    //endregion Declare Variables

    //region Declare Objects
    internal var listener: FasationBottomNavigationOnItemClickListener? = null

    private var moveSelectedItemBackgroundAnimator: ObjectAnimator? = null

    private var moveDeSelectedItemAnimator: ValueAnimator? = null
    private var moveSelectedItemAnimator: ValueAnimator? = null

    private var lastSelectedViewReSizeAnimation: Animation? = null
    private var newSelectedViewReSizeAnimation: Animation? = null
    //endregion Declare Objects

    //region Declare Views
    private var lastSelectedImageView: ImageView? = null
    private var newSelectedImageView: ImageView? = null

    private var lastSelectedParentView: View? = null
    private var newSelectedParentView: View? = null

    internal var centerContent: BezierView? = null
    //endregion Declare Views

    //region Custom Attributes
    private var fasationBottomNavigationFirstImageSrc = R.drawable.ic_default
    private var fasationBottomNavigationSecondImageSrc = R.drawable.ic_default
    private var fasationBottomNavigationThirdImageSrc = R.drawable.ic_default
    private var fasationBottomNavigationFourthImageSrc = R.drawable.ic_default
    private var fasationBottomNavigationFifthImageSrc = R.drawable.ic_default

    private var fasationBottomNavigationFirstItemIconWidth = defaultItemIconSize
    private var fasationBottomNavigationFirstItemIconHeight = defaultItemIconSize

    private var fasationBottomNavigationSecondItemIconWidth = defaultItemIconSize
    private var fasationBottomNavigationSecondItemIconHeight = defaultItemIconSize

    private var fasationBottomNavigationThirdItemIconWidth = defaultItemIconSize
    private var fasationBottomNavigationThirdItemIconHeight = defaultItemIconSize

    private var fasationBottomNavigationFourthItemIconWidth = defaultItemIconSize
    private var fasationBottomNavigationFourthItemIconHeight = defaultItemIconSize

    private var fasationBottomNavigationFifthItemIconWidth = defaultItemIconSize
    private var fasationBottomNavigationFifthItemIconHeight = defaultItemIconSize

    private var fasationBottomNavigationIconActiveColor = getColor(resources, R.color.fasation_bottom_navigation_active_item_icon_color, context.theme)
    private var fasationBottomNavigationIconInactiveColor = getColor(resources, R.color.fasation_bottom_navigation_inactive_item_icon_color, context.theme)

    internal var fasationBottomNavigationDefaultSelectedItemIndex = 2

    internal var fasationBottomNavigationBackgroundColor = getColor(resources, R.color.fasation_bottom_navigation_background_color, context.theme)
    //endregion Custom Attributes

    //region Constructor
    init {
        this.setWillNotDraw(false)
        initMain(context)
    }
    //endregion Constructor

    //region Main Callbacks
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (!defaultItemSelectedStatus)
            initDefaultItem(fasationBottomNavigationDefaultSelectedItemIndex)
        defaultItemSelectedStatus = true

        drawSelectedItemBackground(canvas, drawableSelectedItemIndex)
    }

    private fun drawSelectedItemBackground(canvas: Canvas, index: Int) {
        if (index != -1 && isItemFloating(index)) {
            centerContent!!.setStartX(horizontallyOffset + index * bezierWidth)
            centerContent!!.draw(canvas)
            prepareSelectedItemBackgroundAnimation()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.image_navigation_items_first, R.id.constraint_layout_navigation_items_first -> handleItemClick(0)
            R.id.image_navigation_items_second, R.id.constraint_layout_navigation_items_second -> handleItemClick(1)
            R.id.image_navigation_items_third, R.id.constraint_layout_navigation_items_third -> handleItemClick(2)
            R.id.image_navigation_items_fourth, R.id.constraint_layout_navigation_items_fourth -> handleItemClick(3)
            R.id.image_navigation_items_fifth, R.id.constraint_layout_navigation_items_fifth -> handleItemClick(4)
        }
    }
    //endregion Main Callbacks

    //region Declare Methods
    private fun initMain(context: Context) {
        View.inflate(context, R.layout.fasation_bottom_navigation, this)
        initAttributes()
        initViews()
    }

    private fun initAttributes() {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.FasationBottomNavigation, 0, 0)

            fasationBottomNavigationFirstImageSrc =
                    typedArray.getResourceId(R.styleable.FasationBottomNavigation_first_image_src, R.drawable.ic_default)

            fasationBottomNavigationSecondImageSrc =
                    typedArray.getResourceId(R.styleable.FasationBottomNavigation_second_image_src, R.drawable.ic_default)

            fasationBottomNavigationThirdImageSrc =
                    typedArray.getResourceId(R.styleable.FasationBottomNavigation_third_image_src, R.drawable.ic_default)

            fasationBottomNavigationFourthImageSrc =
                    typedArray.getResourceId(R.styleable.FasationBottomNavigation_fourth_image_src, R.drawable.ic_default)

            fasationBottomNavigationFifthImageSrc =
                    typedArray.getResourceId(R.styleable.FasationBottomNavigation_fifth_image_src, R.drawable.ic_default)

            fasationBottomNavigationFirstItemIconWidth =
                    typedArray.getInt(R.styleable.FasationBottomNavigation_first_item_icon_width, defaultItemIconSize)
            fasationBottomNavigationFirstItemIconHeight =
                    typedArray.getInt(R.styleable.FasationBottomNavigation_first_item_icon_height, defaultItemIconSize)

            fasationBottomNavigationSecondItemIconWidth =
                    typedArray.getInt(R.styleable.FasationBottomNavigation_second_item_icon_width, defaultItemIconSize)
            fasationBottomNavigationSecondItemIconHeight =
                    typedArray.getInt(R.styleable.FasationBottomNavigation_second_item_icon_height, defaultItemIconSize)

            fasationBottomNavigationFourthItemIconWidth =
                    typedArray.getInt(R.styleable.FasationBottomNavigation_third_item_icon_width, defaultItemIconSize)
            fasationBottomNavigationFourthItemIconHeight =
                    typedArray.getInt(R.styleable.FasationBottomNavigation_third_item_icon_height, defaultItemIconSize)

            fasationBottomNavigationThirdItemIconWidth =
                    typedArray.getInt(R.styleable.FasationBottomNavigation_fourth_item_icon_width, defaultItemIconSize)
            fasationBottomNavigationThirdItemIconHeight =
                    typedArray.getInt(R.styleable.FasationBottomNavigation_fourth_item_icon_height, defaultItemIconSize)

            fasationBottomNavigationFifthItemIconWidth =
                    typedArray.getInt(R.styleable.FasationBottomNavigation_fifth_item_icon_width, defaultItemIconSize)
            fasationBottomNavigationFifthItemIconHeight =
                    typedArray.getInt(R.styleable.FasationBottomNavigation_fifth_item_icon_height, defaultItemIconSize)

            fasationBottomNavigationIconActiveColor =
                    typedArray.getColor(R.styleable.FasationBottomNavigation_icon_active_color,
                            getColor(resources, R.color.fasation_bottom_navigation_active_item_icon_color, context.theme))
            fasationBottomNavigationIconInactiveColor =
                    typedArray.getColor(R.styleable.FasationBottomNavigation_icon_inactive_color,
                            getColor(resources, R.color.fasation_bottom_navigation_inactive_item_icon_color, context.theme))

            fasationBottomNavigationDefaultSelectedItemIndex =
                    typedArray.getInteger(R.styleable.FasationBottomNavigation_default_selected_item_index, 2)

            fasationBottomNavigationBackgroundColor =
                    typedArray.getColor(R.styleable.FasationBottomNavigation_background_color,
                            getColor(resources, R.color.fasation_bottom_navigation_background_color, context.theme))

            typedArray.recycle()
        }
    }

    private fun initViews() {
        centerContent = buildBezierView()

        image_navigation_items_first!!.setOnClickListener(this)
        image_navigation_items_second!!.setOnClickListener(this)
        image_navigation_items_third!!.setOnClickListener(this)
        image_navigation_items_fourth!!.setOnClickListener(this)
        image_navigation_items_fifth!!.setOnClickListener(this)

        constraint_layout_navigation_items_first!!.setOnClickListener(this)
        constraint_layout_navigation_items_second!!.setOnClickListener(this)
        constraint_layout_navigation_items_third!!.setOnClickListener(this)
        constraint_layout_navigation_items_fourth!!.setOnClickListener(this)
        constraint_layout_navigation_items_fifth!!.setOnClickListener(this)

        image_navigation_items_first!!.setImageDrawable(getDrawable(resources, fasationBottomNavigationFirstImageSrc, context.theme))
        image_navigation_items_second!!.setImageDrawable(getDrawable(resources, fasationBottomNavigationSecondImageSrc, context.theme))
        image_navigation_items_third!!.setImageDrawable(getDrawable(resources, fasationBottomNavigationThirdImageSrc, context.theme))
        image_navigation_items_fourth!!.setImageDrawable(getDrawable(resources, fasationBottomNavigationFourthImageSrc, context.theme))
        image_navigation_items_fifth!!.setImageDrawable(getDrawable(resources, fasationBottomNavigationFifthImageSrc, context.theme))

        firstItemOffset =
                emptyRelativeLayoutHeight.toFloat() - defaultItemPadding.toFloat() - fasationBottomNavigationFirstItemIconHeight * imageBiggerScale / 2

        secondItemOffset =
                emptyRelativeLayoutHeight.toFloat() - defaultItemPadding.toFloat() - fasationBottomNavigationSecondItemIconHeight * imageBiggerScale / 2

        thirdItemOffset =
                emptyRelativeLayoutHeight.toFloat() - defaultItemPadding.toFloat() - fasationBottomNavigationFourthItemIconHeight * imageBiggerScale / 2

        fourthItemOffset =
                emptyRelativeLayoutHeight.toFloat() - defaultItemPadding.toFloat() - fasationBottomNavigationThirdItemIconHeight * imageBiggerScale / 2

        fifthItemOffset =
                emptyRelativeLayoutHeight.toFloat() - defaultItemPadding.toFloat() - fasationBottomNavigationFifthItemIconHeight * imageBiggerScale / 2

        besideMainWidth = (1.0 - centerMainWidth) / 2.0

        VectorChildFinder(context!!, getDrawableIdBasedIndex(0), image_navigation_items_first)
                .findPathByName("main_path").fillColor = if (fasationBottomNavigationDefaultSelectedItemIndex == 0) fasationBottomNavigationIconActiveColor else fasationBottomNavigationIconInactiveColor
        VectorChildFinder(context!!, getDrawableIdBasedIndex(1), image_navigation_items_second)
                .findPathByName("main_path").fillColor = if (fasationBottomNavigationDefaultSelectedItemIndex == 1) fasationBottomNavigationIconActiveColor else fasationBottomNavigationIconInactiveColor
        VectorChildFinder(context!!, getDrawableIdBasedIndex(2), image_navigation_items_third)
                .findPathByName("main_path").fillColor = if (fasationBottomNavigationDefaultSelectedItemIndex == 2) fasationBottomNavigationIconActiveColor else fasationBottomNavigationIconInactiveColor
        VectorChildFinder(context!!, getDrawableIdBasedIndex(3), image_navigation_items_fourth)
                .findPathByName("main_path").fillColor = if (fasationBottomNavigationDefaultSelectedItemIndex == 3) fasationBottomNavigationIconActiveColor else fasationBottomNavigationIconInactiveColor
        VectorChildFinder(context!!, getDrawableIdBasedIndex(4), image_navigation_items_fifth)
                .findPathByName("main_path").fillColor = if (fasationBottomNavigationDefaultSelectedItemIndex == 4) fasationBottomNavigationIconActiveColor else fasationBottomNavigationIconInactiveColor

        changeBackgroundColor()
    }

    private fun handleItemClick(selectedItemIndex: Int) {
        if (newSelectedIndex != selectedItemIndex) {
            if (isItemFloating(newSelectedIndex))
                lastSelectedIndex = newSelectedIndex

            newSelectedIndex = selectedItemIndex

            if (isItemFloating(newSelectedIndex)) {
                drawableSelectedItemIndex = selectedItemIndex
                handleItemAnimations()
            }
        }

        listener?.onFasationBottomNavigationItemClick(newSelectedIndex)
    }

    private fun handleItemAnimations() {
        prepareDeSelectItemAnimation()
        prepareSelectItemAnimation()
        prepareSelectedItemBackgroundAnimation()
        setItemsIconColor()
        runAnimationOnClickItem()
    }

    private fun prepareDeSelectItemAnimation() {
        lastSelectedImageView = getImageViewViewBasedIndex(lastSelectedIndex)
        lastSelectedParentView = getParentViewBasedIndex(lastSelectedIndex)

        if (lastSelectedParentView != null) {
            if (moveDeSelectedItemAnimator != null && moveDeSelectedItemAnimator!!.isRunning)
                moveDeSelectedItemAnimator!!.end()

            val mLayoutParams = lastSelectedParentView!!.layoutParams as LayoutParams
            moveDeSelectedItemAnimator = ValueAnimator.ofInt(mLayoutParams.bottomMargin, convertDpToPx(defaultItemOffset))
            moveDeSelectedItemAnimator!!.addUpdateListener { valueAnimator ->
                mLayoutParams.bottomMargin = valueAnimator.animatedValue as Int
                lastSelectedParentView!!.requestLayout()
            }
            moveDeSelectedItemAnimator!!.duration = 500

            setImageSizeAnimation(lastSelectedImageView, 200, SMALLER_SIZE)
        }
    }

    private fun prepareSelectItemAnimation() {
        newSelectedImageView = getImageViewViewBasedIndex(newSelectedIndex)
        newSelectedParentView = getParentViewBasedIndex(newSelectedIndex)

        if (newSelectedParentView != null) {
            if (moveSelectedItemAnimator != null && moveSelectedItemAnimator!!.isRunning)
                moveSelectedItemAnimator!!.end()

            val mLayoutParams = newSelectedParentView!!.layoutParams as LayoutParams
            moveSelectedItemAnimator = ValueAnimator.ofInt(mLayoutParams.bottomMargin,
                    convertDpToPx(getSelectedItemOffsetBasedIndex(newSelectedIndex)))
            moveSelectedItemAnimator!!.addUpdateListener { valueAnimator ->
                mLayoutParams.bottomMargin = valueAnimator.animatedValue as Int
                newSelectedParentView!!.requestLayout()
            }
            moveSelectedItemAnimator!!.duration = 500

            setImageSizeAnimation(newSelectedImageView, 200, BIGGER_SIZE)
        }
    }

    private fun prepareSelectedItemBackgroundAnimation() {
        val xCurrentPosition = image_navigation_background_selected_item!!.left.toFloat()
        val xNewPosition = (selectedItemHorizontallyOffset + newSelectedIndex.toDouble() *
                relative_layout_empty!!.width.toDouble() * centerMainWidth / 5).toFloat()

        moveSelectedItemBackgroundAnimator = ObjectAnimator.ofFloat(
                image_navigation_background_selected_item,
                View.TRANSLATION_X, xNewPosition - xCurrentPosition)
        moveSelectedItemBackgroundAnimator!!.duration = 200
    }

    private fun setImageSizeAnimation(view: View?, duration: Int, finalSizeStatus: Boolean) {
        if (finalSizeStatus == SMALLER_SIZE) {
            lastSelectedViewReSizeAnimation = ResizeAnimation(view!!,
                    convertDpToPx(getIconWidthBasedIndex(lastSelectedIndex).toFloat()),
                    convertDpToPx(getIconHeightBasedIndex(lastSelectedIndex).toFloat()))
            lastSelectedViewReSizeAnimation!!.duration = duration.toLong()
        } else if (finalSizeStatus == BIGGER_SIZE) {
            newSelectedViewReSizeAnimation = ResizeAnimation(view!!,
                    (convertDpToPx(getIconWidthBasedIndex(newSelectedIndex).toFloat()) * imageBiggerScale).toInt(),
                    (convertDpToPx(getIconHeightBasedIndex(newSelectedIndex).toFloat()) * imageBiggerScale).toInt())
            newSelectedViewReSizeAnimation!!.duration = duration.toLong()
        }
    }

    private fun runAnimationOnClickItem() {
        moveSelectedItemBackgroundAnimator?.start()
        moveDeSelectedItemAnimator?.start()
        moveSelectedItemAnimator?.start()
        lastSelectedImageView?.startAnimation(lastSelectedViewReSizeAnimation)
        newSelectedImageView?.startAnimation(newSelectedViewReSizeAnimation)
    }

    private fun getParentViewBasedIndex(index: Int): View? {
        return when (index) {
            0 -> constraint_layout_navigation_items_first
            1 -> constraint_layout_navigation_items_second
            2 -> constraint_layout_navigation_items_third
            3 -> constraint_layout_navigation_items_fourth
            4 -> constraint_layout_navigation_items_fifth
            else -> null
        }
    }

    internal fun getBadgeImageViewBasedIndex(index: Int): View? {
        return when (index) {
            0 -> image_badge_navigation_items_first
            1 -> image_badge_navigation_items_second
            2 -> image_badge_navigation_items_third
            3 -> image_badge_navigation_items_fourth
            4 -> image_badge_navigation_items_fifth
            else -> null
        }
    }

    private fun getIconWidthBasedIndex(index: Int): Int {
        return when (index) {
            0 -> fasationBottomNavigationFirstItemIconWidth
            1 -> fasationBottomNavigationSecondItemIconWidth
            2 -> fasationBottomNavigationFourthItemIconWidth
            3 -> fasationBottomNavigationThirdItemIconWidth
            4 -> fasationBottomNavigationFifthItemIconWidth
            else -> -1
        }
    }

    private fun getIconHeightBasedIndex(index: Int): Int {
        return when (index) {
            0 -> fasationBottomNavigationFirstItemIconHeight
            1 -> fasationBottomNavigationSecondItemIconHeight
            2 -> fasationBottomNavigationFourthItemIconHeight
            3 -> fasationBottomNavigationThirdItemIconHeight
            4 -> fasationBottomNavigationFifthItemIconHeight
            else -> -1
        }
    }

    private fun getSelectedItemOffsetBasedIndex(index: Int): Float {
        return when (index) {
            0 -> firstItemOffset
            1 -> secondItemOffset
            2 -> thirdItemOffset
            3 -> fourthItemOffset
            4 -> fifthItemOffset
            else -> -1f
        }
    }

    private fun getImageViewViewBasedIndex(index: Int): ImageView? {
        return when (index) {
            0 -> image_navigation_items_first
            1 -> image_navigation_items_second
            2 -> image_navigation_items_third
            3 -> image_navigation_items_fourth
            4 -> image_navigation_items_fifth
            else -> null
        }
    }

    private fun getDrawableIdBasedIndex(index: Int): Int {
        return when (index) {
            0 -> fasationBottomNavigationFirstImageSrc
            1 -> fasationBottomNavigationSecondImageSrc
            2 -> fasationBottomNavigationThirdImageSrc
            3 -> fasationBottomNavigationFourthImageSrc
            4 -> fasationBottomNavigationFifthImageSrc
            else -> R.drawable.ic_default
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

    private fun setItemsIconColor() {

        var vector: VectorChildFinder

        if (lastSelectedIndex != -1) {
            vector = VectorChildFinder(context!!, getDrawableIdBasedIndex(lastSelectedIndex), lastSelectedImageView!!)
            vector.findPathByName("main_path")!!.fillColor = fasationBottomNavigationIconInactiveColor
        }

        vector = VectorChildFinder(context!!, getDrawableIdBasedIndex(newSelectedIndex), newSelectedImageView!!)
        vector.findPathByName("main_path")!!.fillColor = fasationBottomNavigationIconActiveColor
    }

    private fun buildBezierView(): BezierView {
        val bezierView = BezierView(context, getColor(resources, R.color.fasation_bottom_navigation_background_color, context.theme))
        bezierView.build(bezierWidth, bezierHeight, false)
        return bezierView
    }

    private fun initDefaultItem(defaultSelectedItemPosition: Int) {
        newSelectedIndex = defaultSelectedItemPosition
        prepareSelectItemAnimation()
        setItemsIconColor()
        runAnimationOnClickItem()
        selectedItemHorizontallyOffset = (image_navigation_background_selected_item!!.left - 2.0 *
                relative_layout_empty!!.width.toDouble() * centerMainWidth / 5).toInt()

        bezierWidth = (centerMainWidth * relative_layout_empty!!.width / 5).toInt()
        bezierHeight = height - relative_layout_empty!!.height - convertDpToPx(8f)

        centerContent!!.width = bezierWidth
        centerContent!!.height = bezierHeight
        centerContent!!.setStartY(convertDpToPx(8f))

        horizontallyOffset = (besideMainWidth * relative_layout_empty!!.width).toInt()
    }

    private fun changeSelectedItemBackgroundPosition() {
        val set = ConstraintSet()
        set.connect(image_navigation_background_selected_item.id, ConstraintSet.END, guide_line_third.id, ConstraintSet.START)
        set.connect(image_navigation_background_selected_item.id, ConstraintSet.START, guide_line_second.id, ConstraintSet.END)
        set.applyTo(main_view)
    }

    private fun onDestroy() {
        moveSelectedItemBackgroundAnimator!!.end()

        moveDeSelectedItemAnimator!!.end()

        moveSelectedItemAnimator!!.end()

        if (lastSelectedImageView!!.animation != null)
            lastSelectedImageView!!.animation.cancel()

        if (newSelectedImageView!!.animation != null)
            newSelectedImageView!!.animation.cancel()
    }
    //endregion Declare Methods
}