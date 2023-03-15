package com.example.ltech.presentation.fragments.filterFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ltech.databinding.FragmentFilterBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    private var binding: FragmentFilterBottomSheetBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFilterBottomSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeFilterFragment()
    }

    private fun closeFilterFragment() {
        binding?.filterFragmentCloseButton?.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}