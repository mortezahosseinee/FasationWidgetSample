package ir.fasation.widget.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import ir.fasation.widget.LeftDrawableMode
import ir.fasation.widget.FasationEditTextOnDrawableClickListener
import ir.fasation.widget.RightDrawableMode
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
class FasationEditTextFragment : Fragment(), FasationEditTextOnDrawableClickListener {

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

        fasation_text_view_valid_non_pass_left_drawable.setOnDrawableClickListener(this)
    }

    override fun onFasationEditTextLeftDrawableClick(lefT_CLEAR_ACTION_DRAWABLE: LeftDrawableMode) {
        makeText(context, "This is left drawable.", Toast.LENGTH_SHORT).show()
    }

    override fun onFasationEditTextRightDrawableClick(righT_HIDE_PASSWORD_DRAWABLE: RightDrawableMode) {
        makeText(context, "This is right drawable.", Toast.LENGTH_SHORT).show()
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
}