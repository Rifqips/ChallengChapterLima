package com.rifqipadisiliwangi.challengchapterlima.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.rifqipadisiliwangi.challengchapterlima.R
import com.rifqipadisiliwangi.challengchapterlima.databinding.FragmentProfileBinding
import java.util.*

class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding
    lateinit var  sp : SharedPreferences
    var currLang = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivUser.setOnClickListener {
            Glide.with(requireActivity()).load("https://i.ibb.co/zJHYGBP/binarlogo.jpg")
                .circleCrop()
                .into(binding.ivUser)
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

        binding.tvUpdate.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_updateFragment)
        }

        binding.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        binding.swButton.setOnClickListener {
            setLocale("en")
        }
        binding.swButton1.setOnClickListener {
            setLocale("id")
        }

    }

    fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val resources = context?.resources
        val configuration = resources?.configuration
        configuration?.locale = locale
        configuration?.setLayoutDirection(locale)
        resources?.updateConfiguration(configuration, resources.displayMetrics)
    }


}