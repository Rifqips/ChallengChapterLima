package com.rifqipadisiliwangi.challengchapterlima.fragment

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
import androidx.recyclerview.widget.GridLayoutManager
import com.rifqipadisiliwangi.challengchapterlima.R
import com.rifqipadisiliwangi.challengchapterlima.adapter.MovieAdapter
import com.rifqipadisiliwangi.challengchapterlima.databinding.FragmentHomeBinding
import com.rifqipadisiliwangi.challengchapterlima.viewmodel.ViewModelUser

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var adapter: MovieAdapter
    lateinit var sp : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivUser.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        setUpMovie()
    }

    private fun setUpMovie(){
        val viewModel = ViewModelProvider(requireActivity()).get(ViewModelUser::class.java)
        viewModel.getMovie().observe(viewLifecycleOwner, Observer {
            viewModel.loading.observe(viewLifecycleOwner, Observer {
                when(it){
                    true -> binding.homeProgressBar.visibility = View.VISIBLE
                    false -> binding.homeProgressBar.visibility = View.GONE
                }
            })

            if (it != null){
                binding.rvFilm.layoutManager = GridLayoutManager(context,2)
                adapter = MovieAdapter(it.results)
                binding.rvFilm.adapter = adapter
            }else {
                Toast.makeText(requireActivity(),"Data Tidak Tampil",Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.callApiMovie()
    }
}
