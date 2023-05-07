package com.example.project_test3

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_test3.databinding.FragmentReservationBinding

class FragmentReservation : Fragment(){
    lateinit var binding: FragmentReservationBinding
    lateinit var mainActivity:MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity) mainActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationBinding.inflate(inflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonRes1.setOnClickListener{
//            mainActivity.reservation()//예약 함수 불러오기
//        }
//        binding.buttonRes2.setOnClickListener{
//            mainActivity.reservation()//예약 함수 불러오기
//        }

    }
}