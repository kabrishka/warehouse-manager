package com.kabrishka.warehousemanager.screens.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.kabrishka.warehousemanager.R
import com.kabrishka.warehousemanager.databinding.FragmentSignUpBinding

class SignUpFragment: Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {
            if (!checkErrorInputLayout()) findNavController().popBackStack(R.id.signInFragment, false)
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

}