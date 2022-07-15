package com.example.org.orgd3if0074tubes1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.org.orgd3if0074tubes1.R
import com.example.org.orgd3if0074tubes1.databinding.AboutFragmentBinding

class AboutFragment : Fragment(){

    private lateinit var binding : AboutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.about_fragment, container,false)
    }
}