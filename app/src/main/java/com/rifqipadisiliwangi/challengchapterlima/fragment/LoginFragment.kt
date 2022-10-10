package com.rifqipadisiliwangi.challengchapterlima.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rifqipadisiliwangi.challengchapterlima.R
import com.rifqipadisiliwangi.challengchapterlima.databinding.FragmentLoginBinding
import com.rifqipadisiliwangi.challengchapterlima.databinding.FragmentSplashBinding
import com.rifqipadisiliwangi.challengchapterlima.model.user.ResponUserItem
import com.rifqipadisiliwangi.challengchapterlima.service.user.RetrofitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("datauser",
        Context.MODE_PRIVATE)


        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registFragment)
        }

        binding.btnLogin.setOnClickListener {
            userLogin()
        }

    }

    fun userLogin(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val saveUser = sharedPreferences.edit()

        RetrofitUser.instance.getAllUser()
            .enqueue(object : Callback<List<ResponUserItem>>{
                override fun onResponse(
                    call: Call<List<ResponUserItem>>,
                    response: Response<List<ResponUserItem>>
                ) {
                    if (response.isSuccessful){
                        Log.d("Login","onResponse: ${response.body()}")
                        val userList = response.body()?.filter {
                            it.email == email && it.password ==password
                        } as List<ResponUserItem>
                        if (!userList.indices.isEmpty()){
                            val user = userList.first {
                                it.email == email && it.password ==password
                            }
                            Log.d("Login","onResponse: $user")
                            saveUser.putString("name", user.username )
                            saveUser.putString("name", user.email )
                            saveUser.putString("name", user.password )
                            saveUser.apply()
//                            startActivity(Intent(context,))
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        }else{
                            Log.d("Login Activity","onRespon : Gagal")
                            Toast.makeText(context,"Login gagal",Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<List<ResponUserItem>>, t: Throwable) {
                    Toast.makeText(context,"Gagal Login",Toast.LENGTH_SHORT).show()
                }

            })
    }
}