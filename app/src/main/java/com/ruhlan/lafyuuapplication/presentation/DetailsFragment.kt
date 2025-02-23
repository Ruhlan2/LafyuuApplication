package com.ruhlan.lafyuuapplication.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ruhlan.lafyuuapplication.R
import com.ruhlan.lafyuuapplication.databinding.FragmentDetailsBinding
import com.ruhlan.lafyuuapplication.model.Product
import com.ruhlan.lafyuuapplication.service.ApiClientMaker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()
    private val api = ApiClientMaker.api

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        val data = args.id

        Log.e("TAG", "getData: $data" )

        api.getSingleProduct(
            id = data
        ).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        binding.productTitle.text = it.title
                        binding.prodcutDesc.text = it.description
                        binding.productPrice.text = "${it.price} $"
                        Glide.with(requireContext()).load(it.images?.get(0))
                            .into(binding.productImage)
                        binding.buttonBack.setOnClickListener {
                            findNavController().popBackStack()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(),"Error occured",Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(requireContext(),t.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}