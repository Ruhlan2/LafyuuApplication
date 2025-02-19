package com.ruhlan.lafyuuapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ruhlan.lafyuuapplication.adapter.ProductAdapter
import com.ruhlan.lafyuuapplication.databinding.FragmentHomeBinding
import com.ruhlan.lafyuuapplication.model.ProductResponse
import com.ruhlan.lafyuuapplication.service.ApiClientMaker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val service = ApiClientMaker.api
    private val adapter = ProductAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productRv.adapter = adapter
        getProductsData()

        adapter.onClick = {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it))
        }
    }

    private fun getProductsData() {
        service.getAllProducts().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        binding.loadingBar.visibility = View.GONE
                        binding.productRv.visibility = View.VISIBLE
                        adapter.updateList(it.products!!)
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Error occured while loading data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}