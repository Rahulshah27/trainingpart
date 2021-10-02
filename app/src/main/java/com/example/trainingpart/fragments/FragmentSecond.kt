package com.example.trainingpart.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.trainingpart.Constants
import com.example.trainingpart.R



class FragmentSecond : Fragment(R.layout.fragment_second) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSecondFragment = view.findViewById<Button>(R.id.btnFrag2)
        val textView = view.findViewById<TextView>(R.id.tvFrag2)
        val args = this.arguments
        val text1 = args?.getString(Constants.Pref.fragment1)

        btnSecondFragment.setOnClickListener{
            val editText = view.findViewById<EditText>(R.id.etFrag2)
            val text2 = editText.text.toString()

            val bundle =Bundle()
            bundle.putString(Constants.Pref.fragment2, text2)
            bundle.putString(Constants.Pref.fragment1, text1)

            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_secondFragment_to_thirdFragment, bundle)

        }

        textView.text = text1
    }


}