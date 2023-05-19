package com.example.motion.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.motion.R
import com.example.motion.adapter.ImgAdapter
import com.example.motion.adapter.ImgClickItem
import com.example.motion.databinding.FragmentFirstBinding
import com.example.motion.model.ImageModel
import com.example.motion.utility.ImgList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val adapter = ImgAdapter(ImgList.listOf(),requireContext(),object:ImgClickItem{
            override fun onItemClick(imageModel: ImageModel, imgview: ImageView) {
                val imgUrl = imageModel.imgUrl
                val transitionName = "imgView_$imgUrl" // Assign a unique transition name based on the image URL
                imgview.transitionName = transitionName
                val directions = FirstFragmentDirections.actionFirstFragmentToSecondFragment(imgUrl)
                val extras = FragmentNavigatorExtras(imgview to transitionName)
                findNavController().navigate(directions,extras)
            }

        })
        binding.recyclerView.layoutManager= GridLayoutManager(requireContext(),3)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}