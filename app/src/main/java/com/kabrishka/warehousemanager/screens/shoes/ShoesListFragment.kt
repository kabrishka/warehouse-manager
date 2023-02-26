package com.kabrishka.warehousemanager.screens.shoes

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kabrishka.warehousemanager.MainActivityViewModel
import com.kabrishka.warehousemanager.R
import com.kabrishka.warehousemanager.databinding.FragmentShoesListBinding
import com.kabrishka.warehousemanager.model.Shoe
import com.kabrishka.warehousemanager.model.ShoesViewModel

class ShoesListFragment: Fragment(R.layout.fragment_shoes_list) {
    private lateinit var binding: FragmentShoesListBinding

    private val shoesViewModel: ShoesViewModel by viewModels()
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (shoesViewModel.shoes.value == null) {
            shoesViewModel.initShoes()
        }

        val currentBackStackEntry = findNavController().currentBackStackEntry
        val savedStateHandle = currentBackStackEntry?.savedStateHandle
        savedStateHandle?.getLiveData<Shoe>(RESULT_FROM_FRAGMENT)
            ?.observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    shoesViewModel.addShoes(result)
                }
            }

        shoesViewModel.shoes.value?.let { createList(it) }

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

    private fun logout() {
        mainActivityViewModel.logout()
        findNavController().navigate(R.id.action_shoesListFragment_to_signInFragment)
    }

    private fun createList(shoes: List<Shoe>) {
        val lparams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        for (shoe in shoes) {
            val tv = TextView(requireContext())
            tv.layoutParams = lparams
            tv.text = shoe.toString()
            binding.linearLayout.addView(tv)
        }
    }

    companion object {
        const val RESULT_FROM_FRAGMENT = "RESULT_FROM_FRAGMENT"
    }
}