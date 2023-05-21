package com.example.project_test3

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.project_test3.databinding.FragmentJBinding
import com.example.project_test3.databinding.FragmentReservation2Binding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FragmentJ : Fragment() {
    lateinit var binding: FragmentJBinding
    lateinit var mainActivity: MainActivity
    private var job = SupervisorJob()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) mainActivity = context
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("guide6") { key, bundle ->
            if(bundle.getString("key")=="true") {
                GlobalScope.launch(job){
                    while(true){
                        //예약 버튼 색깔 변경
                        binding.checkBox1.setBackgroundResource(R.drawable.border_all2)
                        delay(1000)
                        binding.checkBox1.setBackgroundResource(R.drawable.border_all)
                        delay(1000)
                    }
                }
            }
        }
        binding.checkBox1.setOnCheckedChangeListener { _, isCheked ->
            if (isCheked) {
                check(1)
            }
            else uncheck(1)
        }
        binding.checkBox2.setOnCheckedChangeListener { _, isCheked ->
            if (isCheked) {
                check(2)
            }
            else uncheck(2)
        }
        binding.checkBox3.setOnCheckedChangeListener { _, isCheked ->
            if (isCheked) {
                check(3)
            }
            else uncheck(3)
        }
        binding.checkBox4.setOnCheckedChangeListener { _, isCheked ->
            if (isCheked) {
                check(4)
            }
            else uncheck(4)
        }
    }
    fun check(i:Int){
        val bundle = Bundle()
        bundle.putString("key", "add")
        bundle.putString("number", "${i}")
        setFragmentResult("requestKey3", bundle)
    }
    fun uncheck(i:Int){
        val bundle = Bundle()
        bundle.putString("key", "remove")
        bundle.putString("number", "${i}")
        setFragmentResult("requestKey3", bundle)
    }
}