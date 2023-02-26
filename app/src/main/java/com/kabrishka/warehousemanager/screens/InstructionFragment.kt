package com.kabrishka.warehousemanager.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kabrishka.warehousemanager.R
import com.kabrishka.warehousemanager.databinding.FragmentInstructionBinding
import com.kabrishka.warehousemanager.databinding.FragmentInstructionBinding.*

class InstructionFragment: Fragment(R.layout.fragment_instruction) {
    private lateinit var binding: FragmentInstructionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.openShoesListButton.setOnClickListener {
            findNavController().navigate(R.id.action_instructionFragment_to_shoesListFragment)
        }
    }
}