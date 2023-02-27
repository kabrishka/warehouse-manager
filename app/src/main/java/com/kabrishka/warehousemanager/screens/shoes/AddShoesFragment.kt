package com.kabrishka.warehousemanager.screens.shoes

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kabrishka.warehousemanager.R
import com.kabrishka.warehousemanager.databinding.FragmentAddShoesBinding
import com.kabrishka.warehousemanager.databinding.FragmentShoesListBinding
import com.kabrishka.warehousemanager.model.Shoe
import com.kabrishka.warehousemanager.model.ShoesViewModel
import java.lang.NullPointerException

class AddShoesFragment: Fragment(R.layout.fragment_add_shoes) {
    private lateinit var binding: FragmentAddShoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddShoesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_shoe_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_done -> {
                try {
                    onDoneButtonPressed()
                } catch (e: NullPointerException) {
                    Toast.makeText(requireContext(),"${e.message}", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onDoneButtonPressed() {
        val shoe = createShoe()

        if (checkErrorInputLayout(shoe)) return

        val direction  = AddShoesFragmentDirections.actionAddShoesFragmentToShoesListFragment(shoe)
        findNavController().navigate(direction)
    }

    private fun checkErrorInputLayout(shoe: Shoe): Boolean {
        if (shoe.name.isBlank()) {
            binding.nameInputLayout.error = "Введите название ботинка"
            return true
        } else binding.nameInputLayout.error = null
        if (shoe.vendorCode.isBlank()) {
            binding.codeInputLayout.error = "Введите артикул ботинка"
            return true
        } else binding.codeInputLayout.error = null
        if (shoe.brand.isBlank()) {
            binding.brandInputLayout.error = "Введите марку ботинка"
            return true
        } else binding.brandInputLayout.error = null
        if (shoe.size.isBlank()) {
            binding.sizeInputLayout.error = "Введите размер ботинка"
            return true
        } else binding.sizeInputLayout.error = null
        return false
    }

    private fun createShoe(): Shoe {
        val name = binding.nameEditText.text.toString()
        val code = binding.codeEditText.text.toString()
        val brand = binding.brandEditText.text.toString()
        val size = binding.sizeEditText.text.toString()

        Log.d("MyApp","Name: $name Code: $code Brand: $brand Size: $size")
        return Shoe(name, code, brand, size)
    }
}