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


class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSubmit = view.findViewById<Button>(R.id.btnHomeFrag)
        val textView = view.findViewById<TextView>(R.id.tvHomeFrag)

        btnSubmit.setOnClickListener{

            val editText = view.findViewById<EditText>(R.id.etHomeFrag)
            val text = editText.text.toString()

            val bundle =Bundle()
            bundle.putString(Constants.BottomNav.home, text)

            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_navigation_home_to_navigation_search, bundle)

        }

        val args = this.arguments
        val recData = args?.getString(Constants.BottomNav.profile)
        if (recData.isNullOrEmpty()){
            textView.text = getString(R.string.title_home)}

        else {textView.text = recData}
    }

}