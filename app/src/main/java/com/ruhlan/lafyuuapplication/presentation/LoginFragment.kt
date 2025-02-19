package com.ruhlan.lafyuuapplication.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ruhlan.lafyuuapplication.databinding.FragmentLoginBinding
import com.ruhlan.lafyuuapplication.model.UserInformationRequestBody
import com.ruhlan.lafyuuapplication.model.UserInformationResponse
import com.ruhlan.lafyuuapplication.service.ApiClientMaker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val service = ApiClientMaker.api

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginUserWithCredentials()
    }

    private fun loginUserWithCredentials() {

        binding.cardView.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", requireContext().packageName, null)
            intent.setData(uri)
            startActivity(intent)
        }

        binding.buttonSignIn.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            service.loginUser(
                userInformationRequestBody = UserInformationRequestBody(
                    email = email,
                    password = password
                )
            ).enqueue(object : Callback<UserInformationResponse> {
                override fun onResponse(
                    call: Call<UserInformationResponse>,
                    response: Response<UserInformationResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                        }
                    } else {
                        Toast.makeText(requireContext(), "Error occured", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserInformationResponse>, t: Throwable) {
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
