package com.ruhlan.lafyuuapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ruhlan.lafyuuapplication.databinding.FragmentProfileBinding
import com.ruhlan.lafyuuapplication.model.UserInformationModel
import com.ruhlan.lafyuuapplication.service.ApiClientMaker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val service = ApiClientMaker.api

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserData()
    }

    private fun getUserData() {
        service.getUserInformation(id = 3).enqueue(object : Callback<UserInformationModel> {
            override fun onResponse(
                call: Call<UserInformationModel>,
                response: Response<UserInformationModel>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        binding.userMail.text = it.email
                        binding.userName.text = it.username
                        Glide.with(requireContext()).load(it.image).into(binding.userImage)
                        binding.userNumber.text = it.phone
                        binding.birthDate.text = it.birthDate
                    }
                } else {
                    Toast.makeText(requireContext(), "Unknown error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserInformationModel>, t: Throwable) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}