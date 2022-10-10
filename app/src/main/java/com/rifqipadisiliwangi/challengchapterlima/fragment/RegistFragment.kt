package com.rifqipadisiliwangi.challengchapterlima.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rifqipadisiliwangi.challengchapterlima.R
import com.rifqipadisiliwangi.challengchapterlima.databinding.FragmentLoginBinding
import com.rifqipadisiliwangi.challengchapterlima.databinding.FragmentRegistBinding
import com.rifqipadisiliwangi.challengchapterlima.viewmodel.ViewModelUser

class RegistFragment : Fragment() {

    lateinit var binding: FragmentRegistBinding
    lateinit var sp : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sp = requireActivity().getSharedPreferences("login_status",Context.MODE_PRIVATE)

        binding.btnRegist.setOnClickListener {
            userRegist()
        }
    }

    fun userRegist(){
        val username = binding.etUsername.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val cfPassword = binding.cfPassword.text.toString()

        if (password == cfPassword){
            addUser(username,email, password)
            findNavController().navigate(R.id.action_registFragment_to_loginFragment)
            Toast.makeText(context,"Akun Berhasil Terdaftar",Toast.LENGTH_SHORT).show()
        }else if (password != cfPassword){
            Toast.makeText(context,"Password Tidak Sama",Toast.LENGTH_SHORT).show()
        }
    }

    private fun addUser(username : String, email : String, password : String){
        val viewModel = ViewModelProvider(requireActivity())[ViewModelUser::class.java]
        viewModel.callPostUser(username, email , password)
        viewModel.postLiveDataUser().observe(requireActivity()){
            if (it != null){
                Toast.makeText(context,"Akun Berhasil Terdaftar", Toast.LENGTH_SHORT).show()
                val addReq = sp.edit()
                addReq.putString("username",username)
                addReq.putString("email",email)
                addReq.putString("password",password)
                addReq.apply()
            }
        }
    }
}