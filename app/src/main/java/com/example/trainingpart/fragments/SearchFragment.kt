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


class SearchFragment :  Fragment(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSubmit = view.findViewById<Button>(R.id.btnSearchFrag)
        val textView = view.findViewById<TextView>(R.id.tvSearchFrag)

        btnSubmit.setOnClickListener{

            val editText = view.findViewById<EditText>(R.id.etSearchFrag)
            val text = editText.text.toString()

            val bundle =Bundle()
            bundle.putString(Constants.BottomNav.search, text)

            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_navigation_search_to_navigation_orders, bundle)

        }

        val args = this.arguments
        val recData = args?.getString(Constants.BottomNav.home)
        if (recData.isNullOrEmpty()){
            textView.text = getString(R.string.title_search)}

        else {textView.text = recData}
    }

}