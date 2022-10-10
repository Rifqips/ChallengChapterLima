package com.rifqipadisiliwangi.challengchapterlima.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rifqipadisiliwangi.challengchapterlima.R
import com.rifqipadisiliwangi.challengchapterlima.databinding.FragmentUpdateBinding
import com.rifqipadisiliwangi.challengchapterlima.viewmodel.ViewModelUser

class UpdateFragment : Fragment() {

    lateinit var binding : FragmentUpdateBinding
    lateinit var sp : SharedPreferences
    private var idUser = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentUpdateBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sp = requireActivity().getSharedPreferences("login_status",Context.MODE_PRIVATE)

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_profileFragment)
        }

        binding.btnUpdate.setOnClickListener {
            val saveId = sp.getString("id","")
            val name = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            updateDatauserVm(saveId!!, name, email, password)
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }
        getData()
    }

    fun getData(){
        val savedUsername = sp.getString("username", "nama")
        val savedEmail = sp.getString("email","email")
        val savedPassword = sp.getString("password","password")

        binding.etUsername.setText(savedUsername)
        binding.etEmail.setText(savedEmail)
        binding.etPassword.setText(savedPassword)
        binding.cfPassword.setText(savedPassword)
    }

    fun updateDatauserVm(id: String, username: String, email: String, password: String){
        val vm = ViewModelProvider(requireActivity()).get(ViewModelUser::class.java)
        vm.updateCallApiUser(id, username, email, password)
        vm.updateUser().observe(requireActivity(), Observer {
            if (it != null){
                Toast.makeText(requireActivity()," Update Succses",Toast.LENGTH_SHORT).show()

                val updateCk = sp.edit()
                updateCk.putString("username",username)
                updateCk.putString("email",email)
                updateCk.putString("password",password)
                updateCk.apply()
            }
        })
    }
}