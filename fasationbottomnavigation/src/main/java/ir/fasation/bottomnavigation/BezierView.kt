package ir.fasation.bottomnavigation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat

@SuppressLint("ViewConstructor")
internal class BezierView @JvmOverloads constructor(context: Context, backgroundColor: Int = 0) : RelativeLayout(context) {

    //region Declare Constants
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path: Path = Path()
    private var backgroundColor: Int? = backgroundColor
    //endregion Declare Constants

    //region Declare Variables
    private var bezierWidth = 0
    private var bezierHeight = 0
    private var startX = 0
    private var startY = 0
    private var startPointX = 0
    private var startPointY = 0
    private var isLinear = false
    //endregion Declare Variables

    //region Constructor
    init {
        paint.strokeWidth = 0f
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
    }
    //endregion Constructor

    //region Callbacks
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setBackgroundColor(ContextCompat.getColor(context, R.color.fasation_bottom_navigation_background_color))
    }

    override fun onDraw(canvas: Canvas) {

        val offsetX = (bezierWidth * 0.05).toInt()
        val offsetY = 0

        paint.color = backgroundColor!!
        path.reset()

        startPointX = startX - 2 * offsetX
        startPointY = startY + bezierHeight

        val endPointX = startX + bezierWidth + 2 * offsetX
        val endPointY = startY + bezierHeight

        path.moveTo(startPointX.toFloat(), startPointY.toFloat())

        if (!isLinear) {

            val x1 = startX + bezierWidth / 4 - 3 * offsetX
            val y1 = startY + bezierHeight
            val x2 = startX + bezierWidth / 4 - 4 * offsetX
            val y2 = startY + offsetY

            val x3 = startX + bezierWidth / 2
            val y3 = startY + offsetY

            val x4 = (startX.toDouble() + bezierWidth / 4.0 * 3 + (4 * offsetX).toDouble()).toInt()
            val y4 = startY + offsetY
            val x5 = startX + bezierWidth * 3 / 4 + 4 * offsetX
            val y5 = startY + bezierHeight

            path.cubicTo(x1.toFloat(), y1.toFloat(), x2.toFloat(), y2.toFloat(), x3.toFloat(), y3.toFloat())
            path.cubicTo(x4.toFloat(), y4.toFloat(), x5.toFloat(), y5.toFloat(), endPointX.toFloat(), endPointY.toFloat())
        }

        canvas.drawPath(path, paint)
    }

    /**
     * Change bezier view background color
     *
     * @param backgroundColor Target color
     */
    override fun setBackgroundColor(backgroundColor: Int) {
        this.backgroundColor = backgroundColor
        invalidate()
    }
    //endregion Callbacks

    //region Declare Methods
    /**
     * Build bezier view with given width and height
     *
     * @param bezierWidth  Given width
     * @param bezierHeight Given height
     * @param isLinear     True, if curves are not needed
     */
    fun build(bezierWidth: Int, bezierHeight: Int, isLinear: Boolean) {
        this.bezierWidth = bezierWidth
        this.bezierHeight = bezierHeight
        this.isLinear = isLinear
    }
    //endregion Declare Methods

    //region Setters & Getters
    fun setWidth(width: Int) {
        bezierWidth = width
    }

    fun setHeight(height: Int) {
        bezierHeight = height
    }

    fun setStartX(xPosition: Int) {
        startX = xPosition
    }

    fun setStartY(yPosition: Int) {
        startY = yPosition
    }
    //endregion Setters & Getters
}

