package com.ruhlan.lafyuuapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.ruhlan.lafyuuapplication.R
import com.ruhlan.lafyuuapplication.adapter.ProductAdapter
import com.ruhlan.lafyuuapplication.databinding.FragmentSearchBinding
import com.ruhlan.lafyuuapplication.model.ProductResponse
import com.ruhlan.lafyuuapplication.service.ApiClientMaker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val adapter = ProductAdapter()
    private val api = ApiClientMaker.api


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchProductRv.adapter = adapter
        getAllProducts()
        searchProduct()
        sortProductsByName()

        adapter.onClick = {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
                    it
                )
            )
        }
    }

    private fun sortProductsByName() {
        binding.sortButton.setOnClickListener {
            binding.loadingLyt.visibility = View.VISIBLE
            api.sortProducts(
                sortBy = "price",
                order = "asc"
            ).enqueue(object : Callback<ProductResponse> {
                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            binding.loadingLyt.visibility = View.GONE
                            if (it.total == 0) {
                                binding.productNotFoundLyt.visibility = View.VISIBLE
                                binding.searchProductRv.visibility = View.GONE
                            } else {
                                binding.productNotFoundLyt.visibility = View.GONE
                                binding.searchProductRv.visibility = View.VISIBLE
                                adapter.updateList(it.products!!)
                            }
                        }
                    } else {
                        Toast.makeText(requireContext(),"Unknown error",Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    Toast.makeText(requireContext(),t.toString(),Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun getAllProducts() {
        binding.loadingLyt.visibility = View.VISIBLE
        api.getAllProducts().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        binding.loadingLyt.visibility = View.GONE
                        binding.searchProductRv.visibility = View.VISIBLE
                        adapter.updateList(it.products!!)
                    }
                } else {
                    Toast.makeText(requireContext(), "Error occured", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun searchProduct() {
        binding.searchEditText.addTextChangedListener {
            binding.loadingLyt.visibility = View.VISIBLE
            val query = it.toString()

            api.searchProduct(
                searchQuery = query
            ).enqueue(object : Callback<ProductResponse> {
                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { data ->
                            binding.loadingLyt.visibility = View.GONE
                            if (data.total == 0) {
                                //Product not found
                                binding.productNotFoundLyt.visibility = View.VISIBLE
                                binding.searchProductRv.visibility = View.GONE
                            } else {
                                binding.productNotFoundLyt.visibility = View.GONE
                                binding.searchProductRv.visibility = View.VISIBLE
                                adapter.updateList(data.products!!)
                            }
                        }
                    } else {
                        Toast.makeText(requireContext(), "Error occured", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}