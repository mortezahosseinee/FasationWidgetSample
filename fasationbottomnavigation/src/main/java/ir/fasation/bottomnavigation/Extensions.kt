package ir.fasation.bottomnavigation

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import kotlinx.android.synthetic.main.fasation_bottom_navigation.view.*

fun FasationBottomNavigation.changeBackgroundColor() {
    centerContent?.setBackgroundColor(fasationBottomNavigationBackgroundColor)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        (relative_layout_empty.background as GradientDrawable).setColor(fasationBottomNavigationBackgroundColor)
}

fun FasationBottomNavigation.changeSelectedItemBackgroundColor() {
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

fun FasationBottomNavigation.setItemFloatingStatus(index: Int, floatingStatus: Boolean) {
    if (index == fasationBottomNavigationDefaultSelectedItemIndex || index !in 0..4)
        return

    when (index) {
        0 -> firstItemFloatingStatus = floatingStatus
        1 -> secondItemFloatingStatus = floatingStatus
        2 -> thirdItemFloatingStatus = floatingStatus
        3 -> fourthItemFloatingStatus = floatingStatus
        4 -> fifthItemFloatingStatus = floatingStatus
    }
}

fun FasationBottomNavigation.isItemFloating(index: Int): Boolean {
    if (index < 0 || index > 4)
        return false

    return when (index) {
        0 -> firstItemFloatingStatus
        1 -> secondItemFloatingStatus
        2 -> thirdItemFloatingStatus
        3 -> fourthItemFloatingStatus
        4 -> fifthItemFloatingStatus
        else -> false
    }
}

fun FasationBottomNavigation.enableBadgeOnItem(badgeIndex: Int) {
    if (badgeIndex in 0..4)
        getBadgeImageViewBasedIndex(badgeIndex)!!.visibility = View.VISIBLE
}

fun FasationBottomNavigation.disableBadgeOnItem(badgeIndex: Int) {
    if (badgeIndex in 0..4)
        getBadgeImageViewBasedIndex(badgeIndex)!!.visibility = View.GONE
}

fun FasationBottomNavigation.setOnItemClickListener(listener: FasationBottomNavigationOnItemClickListener) {
    this.listener = listener

    if (defaultItemSelectedStatus)
        this.listener!!.onFasationBottomNavigationItemClick(newSelectedIndex)
}