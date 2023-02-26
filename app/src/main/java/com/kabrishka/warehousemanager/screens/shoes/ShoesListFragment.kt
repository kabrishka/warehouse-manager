package com.kabrishka.warehousemanager.screens.shoes

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kabrishka.warehousemanager.MainActivityViewModel
import com.kabrishka.warehousemanager.R
import com.kabrishka.warehousemanager.adapter.ShoesAdapter
import com.kabrishka.warehousemanager.databinding.FragmentShoesListBinding
import com.kabrishka.warehousemanager.model.ShoesViewModel

class ShoesListFragment: Fragment(R.layout.fragment_shoes_list) {
    private var _binding: FragmentShoesListBinding? = null
    private val binding get() = _binding!!

    private val shoesViewModel: ShoesViewModel by activityViewModels()
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoesListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (shoesViewModel.shoes.value == null) {
            shoesViewModel.initShoes()
        }

        recyclerView = binding.shoesRecyclerView
        recyclerView.adapter = shoesViewModel.shoes.value?.let { ShoesAdapter(it) }

        binding.addShoesButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoesListFragment_to_addShoesFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                try {
                    logout()
                } catch (e: NullPointerException) {
                    Toast.makeText(requireContext(),"${e.message}", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun logout() {
        mainActivityViewModel.logout()
        findNavController().navigate(R.id.action_shoesListFragment_to_signInFragment)
    }
}