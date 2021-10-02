package com.example.trainingpart.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.trainingpart.Constants
import com.example.trainingpart.R

class FragmentFirst : Fragment(R.layout.fragment_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFirstFragment = view.findViewById<Button>(R.id.btnFrag1)

        btnFirstFragment.setOnClickListener{

            val editText = view.findViewById<EditText>(R.id.etFrag1)
            val text1 = editText.text.toString()

            val bundle =Bundle()
            bundle.putString(Constants.Pref.fragment1, text1)

            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_firstFragment_to_secondFragment, bundle)

        }

    }

}