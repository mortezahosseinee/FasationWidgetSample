package ir.fasation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.addOnBackStackChangedListener { if (supportFragmentManager.backStackEntryCount == 0) finish() }
        replaceFragment(this, MainFragment.newInstance(), R.anim.fade_in, R.anim.fade_out)
    }
}