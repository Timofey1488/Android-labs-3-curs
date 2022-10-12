package com.example.converter.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.converter.databinding.FragmentCurrencyBinding
import com.example.converter.databinding.FragmentLengthBinding


class CurrencyFragment : Fragment() {
    lateinit var binding: FragmentCurrencyBinding
    private val dataModel: DataModel by activityViewModels()
    lateinit var editTextBefore: EditText
    lateinit var editTextAfter: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrencyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        editTextBefore = binding.PrevText
        editTextBefore.setShowSoftInputOnFocus(false)
        editTextAfter = binding.AfterText
        editTextAfter.setShowSoftInputOnFocus(false)

        dataModel.message.observe(viewLifecycleOwner){
            binding.PrevText.append(it)
        }
        dataModel.delete.observe(viewLifecycleOwner){
            binding.PrevText.setText(it)
        }
        dataModel.messageTemp.observe(viewLifecycleOwner){
            binding.PrevText.append(it)
        }
        dataModel.paste.observe(viewLifecycleOwner){
            binding.AfterText.setText(it)
        }
        dataModel.spinBeforeSet.observe(viewLifecycleOwner){
            binding.SpinnerBefore.setSelection(it.toInt())
        }
        dataModel.spinAfterSet.observe(viewLifecycleOwner){
            binding.SpinnerAfter.setSelection(it.toInt())
        }

        dataModel.proButton.observe(viewLifecycleOwner) {
            if(it == "true") {
                binding.apply {

                    binding.CopyButtonBefore.visibility = View.VISIBLE
                    binding.CopyButtonAfter.visibility = View.VISIBLE
                    binding.PasteButtonBefore.visibility = View.VISIBLE
                    binding.SwapButton.visibility = View.VISIBLE
                }
            }
            else
            {
                binding.apply {

                    binding.CopyButtonBefore.visibility = View.INVISIBLE
                    binding.CopyButtonAfter.visibility = View.INVISIBLE
                    binding.PasteButtonBefore.visibility = View.INVISIBLE
                    binding.SwapButton.visibility = View.INVISIBLE
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CurrencyFragment()
    }
}