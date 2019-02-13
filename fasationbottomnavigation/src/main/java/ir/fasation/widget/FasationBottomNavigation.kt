package ir.fasation.widget

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import com.devs.vectorchildfinder.VectorChildFinder
import kotlinx.android.synthetic.main.fasation_bottom_navigation.view.*

class FasationBottomNavigation @JvmOverloads constructor(context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr),
        View.OnClickListener {

    companion object {
        //region Declare Constants
        private val BIGGER_SIZE = true
        private val SMALLER_SIZE = false

        private val defaultItemIconSize = 24 //dp
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
    private var newSelectedIndex = 2
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

    private var defaultItemSelectedStatus = false

    private var firstItemSolidStatus = false
    private var secondItemSolidStatus = false
    private var thirdItemSolidStatus = false
    private var fourthItemSolidStatus = false
    private var fifthItemSolidStatus = false
    //endregion Declare Variables

    //region Declare Objects
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

    private var centerContent: BezierView? = null
    private var listener: FasationBottomNavigationItemsClickListener? = null
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

    private var fasationBottomNavigationIconActiveColor = ContextCompat.getColor(context, R.color.fasation_bottom_navigation_active_item_icon_color)
    private var fasationBottomNavigationIconInactiveColor = ContextCompat.getColor(context, R.color.fasation_bottom_navigation_inactive_item_icon_color)

    private var fasationBottomNavigationDefaultSelectedItemIndex = 2

    private var fasationBottomNavigationBackgroundColor = ContextCompat.getColor(context, R.color.fasation_bottom_navigation_background_color)
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
        if (index != -1 && !isItemSolid(index)) {
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
                    typedArray.getColor(R.styleable.FasationBottomNavigation_icon_active_color, resources.getColor(R.color.fasation_bottom_navigation_active_item_icon_color))
            fasationBottomNavigationIconInactiveColor =
                    typedArray.getColor(R.styleable.FasationBottomNavigation_icon_inactive_color, resources.getColor(R.color.fasation_bottom_navigation_inactive_item_icon_color))

            fasationBottomNavigationDefaultSelectedItemIndex =
                    typedArray.getInteger(R.styleable.FasationBottomNavigation_default_selected_item_index, 2)

            fasationBottomNavigationBackgroundColor =
                    typedArray.getColor(R.styleable.FasationBottomNavigation_background_color, resources.getColor(R.color.fasation_bottom_navigation_background_color))

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

        image_navigation_items_first!!.setImageDrawable(ContextCompat.getDrawable(context, fasationBottomNavigationFirstImageSrc))
        image_navigation_items_second!!.setImageDrawable(ContextCompat.getDrawable(context, fasationBottomNavigationSecondImageSrc))
        image_navigation_items_third!!.setImageDrawable(ContextCompat.getDrawable(context, fasationBottomNavigationThirdImageSrc))
        image_navigation_items_fourth!!.setImageDrawable(ContextCompat.getDrawable(context, fasationBottomNavigationFourthImageSrc))
        image_navigation_items_fifth!!.setImageDrawable(ContextCompat.getDrawable(context, fasationBottomNavigationFifthImageSrc))

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
            if (!isItemSolid(newSelectedIndex))
                lastSelectedIndex = newSelectedIndex

            newSelectedIndex = selectedItemIndex

            if (!isItemSolid(newSelectedIndex)) {
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

            val mLayoutParams = lastSelectedParentView!!.layoutParams as ConstraintLayout.LayoutParams
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

            val mLayoutParams = newSelectedParentView!!.layoutParams as ConstraintLayout.LayoutParams
            moveSelectedItemAnimator = ValueAnimator.ofInt(mLayoutParams.bottomMargin, convertDpToPx(getSelectedItemOffsetBasedIndex(newSelectedIndex)))
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
        val xNewPosition = (selectedItemHorizontallyOffset + newSelectedIndex.toDouble() * relative_layout_empty!!.width.toDouble() * centerMainWidth / 5).toFloat()

        moveSelectedItemBackgroundAnimator = ObjectAnimator.ofFloat(image_navigation_background_selected_item, View.TRANSLATION_X, xNewPosition - xCurrentPosition)
        moveSelectedItemBackgroundAnimator!!.duration = 200
    }

    private fun setImageSizeAnimation(view: View?, duration: Int, finalSizeStatus: Boolean) {
        if (finalSizeStatus == SMALLER_SIZE) {
            lastSelectedViewReSizeAnimation = ResizeAnimation(view!!,
                    convertDpToPx(getIconWidthBasedIndex(lastSelectedIndex).toFloat()), convertDpToPx(getIconHeightBasedIndex(lastSelectedIndex).toFloat()))
            lastSelectedViewReSizeAnimation!!.duration = duration.toLong()
        } else if (finalSizeStatus == BIGGER_SIZE) {
            newSelectedViewReSizeAnimation = ResizeAnimation(view!!,
                    (convertDpToPx(getIconWidthBasedIndex(newSelectedIndex).toFloat()) * imageBiggerScale).toInt(), (convertDpToPx(getIconHeightBasedIndex(newSelectedIndex).toFloat()) * imageBiggerScale).toInt())
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
        when (index) {
            0 -> return constraint_layout_navigation_items_first
            1 -> return constraint_layout_navigation_items_second
            2 -> return constraint_layout_navigation_items_third
            3 -> return constraint_layout_navigation_items_fourth
            4 -> return constraint_layout_navigation_items_fifth
            else -> return null
        }
    }

    private fun getBadgeImageViewBasedIndex(index: Int): View? {
        when (index) {
            0 -> return image_badge_navigation_items_first
            1 -> return image_badge_navigation_items_second
            2 -> return image_badge_navigation_items_third
            3 -> return image_badge_navigation_items_fourth
            4 -> return image_badge_navigation_items_fifth
            else -> return null
        }
    }

    private fun getIconWidthBasedIndex(index: Int): Int {
        when (index) {
            0 -> return fasationBottomNavigationFirstItemIconWidth
            1 -> return fasationBottomNavigationSecondItemIconWidth
            2 -> return fasationBottomNavigationFourthItemIconWidth
            3 -> return fasationBottomNavigationThirdItemIconWidth
            4 -> return fasationBottomNavigationFifthItemIconWidth
            else -> return -1
        }
    }

    private fun getIconHeightBasedIndex(index: Int): Int {
        when (index) {
            0 -> return fasationBottomNavigationFirstItemIconHeight
            1 -> return fasationBottomNavigationSecondItemIconHeight
            2 -> return fasationBottomNavigationFourthItemIconHeight
            3 -> return fasationBottomNavigationThirdItemIconHeight
            4 -> return fasationBottomNavigationFifthItemIconHeight
            else -> return -1
        }
    }

    private fun getSelectedItemOffsetBasedIndex(index: Int): Float {
        when (index) {
            0 -> return firstItemOffset
            1 -> return secondItemOffset
            2 -> return thirdItemOffset
            3 -> return fourthItemOffset
            4 -> return fifthItemOffset
            else -> return -1f
        }
    }

    private fun getImageViewViewBasedIndex(index: Int): ImageView? {
        when (index) {
            0 -> return image_navigation_items_first
            1 -> return image_navigation_items_second
            2 -> return image_navigation_items_third
            3 -> return image_navigation_items_fourth
            4 -> return image_navigation_items_fifth
            else -> return null
        }
    }

    private fun getDrawableIdBasedIndex(index: Int): Int {
        when (index) {
            0 -> return fasationBottomNavigationFirstImageSrc
            1 -> return fasationBottomNavigationSecondImageSrc
            2 -> return fasationBottomNavigationThirdImageSrc
            3 -> return fasationBottomNavigationFourthImageSrc
            4 -> return fasationBottomNavigationFifthImageSrc
            else -> return R.drawable.ic_default
        }
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

    private fun setItemsIconColor() {

        var vector: VectorChildFinder

        if (lastSelectedIndex != -1) {
            vector = VectorChildFinder(context!!, getDrawableIdBasedIndex(lastSelectedIndex), lastSelectedImageView!!)
            vector.findPathByName("main_path").fillColor = fasationBottomNavigationIconInactiveColor
        }

        vector = VectorChildFinder(context!!, getDrawableIdBasedIndex(newSelectedIndex), newSelectedImageView!!)
        vector.findPathByName("main_path").fillColor = fasationBottomNavigationIconActiveColor
    }

    private fun buildBezierView(): BezierView {
        val bezierView = BezierView(context, ContextCompat.getColor(context!!, R.color.fasation_bottom_navigation_background_color))
        bezierView.build(bezierWidth, bezierHeight, false)
        return bezierView
    }

    private fun initDefaultItem(defaultSelectedItemPosition: Int) {
        newSelectedIndex = defaultSelectedItemPosition
        prepareSelectItemAnimation()
        setItemsIconColor()
        runAnimationOnClickItem()
        selectedItemHorizontallyOffset = (image_navigation_background_selected_item!!.left - 2.0 * relative_layout_empty!!.width.toDouble() * centerMainWidth / 5).toInt()

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
    //endregion Declare Methods

    //region public methods
    fun changeBackgroundColor() {
        centerContent?.setBackgroundColor(fasationBottomNavigationBackgroundColor)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            (relative_layout_empty.background as GradientDrawable).setColor(fasationBottomNavigationBackgroundColor)
    }

    fun changeSelectedItemBackgroundColor() {
//        val layers = getResources().getDrawable(R.drawable.background_selected_item) as LayerDrawable
//        val gradientDrawable = ContextCompat.getDrawable(context, R.drawable.rounded_corner_front) as GradientDrawable
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) gradientDrawable .setColor(fasationBottomNavigationBackgroundColor)
//        relative_layout_empty.setBackgroundDrawable(gradientDrawable)
//
//        centerContent?.setBackgroundColor(fasationBottomNavigationBackgroundColor)
//
//        layers.setDrawableByLayerId(R.id.front_item, )
//        layers.addLayer()
    }

    fun setItemSolidStatus(index: Int, solidStatus: Boolean) {
        if (index == fasationBottomNavigationDefaultSelectedItemIndex || index < 0 || index > 4)
            return

        when (index) {
            0 -> firstItemSolidStatus = solidStatus
            1 -> secondItemSolidStatus = solidStatus
            2 -> thirdItemSolidStatus = solidStatus
            3 -> fourthItemSolidStatus = solidStatus
            4 -> fifthItemSolidStatus = solidStatus
        }
    }

    fun isItemSolid(index: Int): Boolean {
        if (index < 0 || index > 4)
            return false

        when (index) {
            0 -> return firstItemSolidStatus
            1 -> return secondItemSolidStatus
            2 -> return thirdItemSolidStatus
            3 -> return fourthItemSolidStatus
            4 -> return fifthItemSolidStatus
            else -> return false
        }
    }

    fun enableBadgeOnItem(badgeIndex: Int) {
        if (badgeIndex >= 0 && badgeIndex <= 4)
            getBadgeImageViewBasedIndex(badgeIndex)!!.visibility = View.VISIBLE
    }

    fun disableBadgeOnItem(badgeIndex: Int) {
        if (badgeIndex >= 0 && badgeIndex <= 4)
            getBadgeImageViewBasedIndex(badgeIndex)!!.visibility = View.GONE
    }

    fun onDestroy() {
        moveSelectedItemBackgroundAnimator!!.end()

        moveDeSelectedItemAnimator!!.end()

        moveSelectedItemAnimator!!.end()

        if (lastSelectedImageView!!.animation != null)
            lastSelectedImageView!!.animation.cancel()

        if (newSelectedImageView!!.animation != null)
            newSelectedImageView!!.animation.cancel()
    }

    fun setClickListener(listener: FasationBottomNavigationItemsClickListener) {
        this.listener = listener

        if (defaultItemSelectedStatus)
            this.listener!!.onFasationBottomNavigationItemClick(newSelectedIndex)
    }
    //endregion public methods
}
