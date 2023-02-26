package com.kabrishka.warehousemanager.screens.auth

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.kabrishka.warehousemanager.R
import com.kabrishka.warehousemanager.databinding.FragmentSignInBinding
import com.kabrishka.warehousemanager.model.Shoe

class SignInFragment: Fragment(R.layout.fragment_sign_in) {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {
            if (!checkErrorInputLayout()) findNavController().navigate(R.id.action_signInFragment_to_welcomeFragment)
        }

        binding.signUpButton.setOnClickListener {
            if (!checkErrorInputLayout()) findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun checkErrorInputLayout(): Boolean {
        if (binding.emailEditText.text.toString().isBlank()) {
            binding.emailEditText.error = "Введите почту"
            return true
        } else binding.emailEditText.error = null
        if (binding.pwdEditText.text.toString().isBlank()) {
            binding.pwdEditText.error = "Введите пароль"
            return true
        } else binding.emailEditText.error = null
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}