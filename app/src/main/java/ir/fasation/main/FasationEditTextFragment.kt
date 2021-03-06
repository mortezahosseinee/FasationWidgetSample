package ir.fasation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ir.fasation.edittext.*
import kotlinx.android.synthetic.main.fragment_fasation_edit_text.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FasationEditTextFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FasationEditTextFragment : Fragment(), FasationEditTextListener {

    override fun onFasationEditTextFocusChanged(v: View?, hasFocus: Boolean) {
        makeText(context, "$hasFocus", LENGTH_LONG).show()
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fasation_edit_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fasation_text_view_normal_non_pass.setListener(this)
        fasation_text_view_normal_non_pass.setText("gjknio")

//        fasation_text_view_active_non_pass.setText("سلام چطوری")
//        fasation_text_view_active_non_pass.setEditTextEnabled(false)

//        val view = RecyclerView(context!!)
//        view.id = R.id.view_fasation_edit_text_bottom_divider_me
//        fasation_text_view_valid_pass_right_drawable.addBottomView(view, null, null, null, null)

//        view.setBackgroundResource(R.drawable.bg_vertical_divider)
//
//        val params = view.layoutParams as ConstraintLayout.LayoutParams
//        params.width = 0
//        params.height = convertDpToPx(100f)
//
//        params.setMargins(convertDpToPx(8f), convertDpToPx(16f), convertDpToPx(8f), convertDpToPx(8f))
//        view.layoutParams = params
//        view.requestLayout()
//
//        fasation_text_view_valid_pass_right_drawable.setWholeBorderColor(getColor(resources, android.R.color.holo_orange_light, context!!.theme))

//        fasation_text_view_normal_non_pass.addBottomView(View(context))
//        fasation_text_view_normal_non_pass.clearBottomView()

//        fasation_text_view_normal_non_pass.setTextFont("font/font_persian_default.ttf")


//        fasation_text_view_valid_non_pass_right_drawable.setOnDrawableClickListener(this)
//        fasation_text_view_valid_non_pass_non_right_drawable.setOnDrawableClickListener(this)
//        fasation_text_view_valid_pass_right_drawable.setOnDrawableClickListener(this)
//        fasation_text_view_valid_non_pass_left_drawable.setOnDrawableClickListener(this)
//        fasation_text_view_valid_right_pass_left_drawable.setOnDrawableClickListener(this)
//        fasation_text_view_valid_left_pass.setOnDrawableClickListener(this)
//        fasation_text_view_valid_right_pass_left_drawable2.setOnDrawableClickListener(this)
    }

    override fun onFasationEditTextLeftDrawableClick(v: View, mode: LeftDrawableMode) {
        when (v) {
//            fasation_text_view_valid_non_pass_right_drawable -> makeText(context, "This is left drawable. 1\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_non_pass_non_right_drawable -> makeText(context, "This is left drawable. 2\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_pass_right_drawable -> makeText(context, "This is left drawable. 3\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_non_pass_left_drawable -> makeText(context, "This is left drawable. 4\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_right_pass_left_drawable -> makeText(context, "This is left drawable. 5\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_left_pass -> makeText(context, "This is left drawable. 6\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_right_pass_left_drawable2 -> makeText(context, "This is left drawable. 7\n$mode", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFasationEditTextRightDrawableClick(v: View, mode: RightDrawableMode) {
        when (v) {
//            fasation_text_view_valid_non_pass_right_drawable ->  makeText(context, "This is right drawable. 1\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_non_pass_non_right_drawable ->  makeText(context, "This is right drawable. 2\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_pass_right_drawable ->  makeText(context, "This is right drawable. 3\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_non_pass_left_drawable ->  makeText(context, "This is right drawable. 4\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_right_pass_left_drawable ->  makeText(context, "This is right drawable. 5\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_left_pass ->  makeText(context, "This is right drawable. 6\n$mode", Toast.LENGTH_SHORT).show()
//            fasation_text_view_valid_right_pass_left_drawable2 ->  makeText(context, "This is right drawable. 7\n$mode", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FasationEditText.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FasationEditTextFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }

        fun newInstance() = FasationEditTextFragment()
    }

    fun convertDpToPx(dp: Float): Int {
        return (dp * context!!.resources.displayMetrics.density).toInt()
    }
}