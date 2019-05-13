package ir.fasation.main

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun replaceFragment(activity: AppCompatActivity, fragment: Fragment?, @AnimatorRes @AnimRes enter: Int, @AnimatorRes @AnimRes exit: Int) {
    if (fragment == null) return

    activity.supportFragmentManager.inTransaction {
        addToBackStack(fragment::class.simpleName)
        setCustomAnimations(enter, exit)
        replace(R.id.frm_main, fragment)
    }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}