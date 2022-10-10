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


class CurrencyFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentCurrencyBinding
    lateinit var editTextBefore: EditText
    lateinit var editTextAfter: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        dataModel.copy.value = ""
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
        super.onViewCreated(view, savedInstanceState)

//
        dataModel.message.observe(activity as LifecycleOwner){
            binding.PrevText.append(it)
        }
        dataModel.delete.observe(activity as LifecycleOwner){
            binding.PrevText.setText(it)
        }
        dataModel.messageTemp.observe(activity as LifecycleOwner){
            binding.PrevText.append(it)
        }
        dataModel.paste.observe(activity as LifecycleOwner){
            binding.AfterText.setText(it)
        }
        dataModel.spinBeforeSet.observe(activity as LifecycleOwner){
            binding.SpinnerBefore.setSelection(it.toInt())
        }
        dataModel.spinAfterSet.observe(activity as LifecycleOwner){
            binding.SpinnerAfter.setSelection(it.toInt())
        }

        dataModel.proButton.observe(activity as LifecycleOwner) {
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



    }

    companion object {
        @JvmStatic
        fun newInstance() =CurrencyFragment()
    }
}