package com.tenkovskaya.clicker_cats.fragments.to_do_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tenkovskaya.clicker_cats.databinding.FragmentTaskViewModelBinding

class NewTask : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentTaskViewModelBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTaskViewModelBinding.inflate(inflater,container,false)
        return binding.root
    }


    private fun saveAction()
    {
        taskViewModel.name.value = binding.name.text.toString()
        taskViewModel.desc.value = binding.desc.text.toString()
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

}


