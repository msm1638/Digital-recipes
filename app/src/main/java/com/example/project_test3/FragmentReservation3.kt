package com.example.project_test3

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import com.example.project_test3.databinding.FragmentReservation3Binding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentReservation3 : Fragment() {
    lateinit var binding: FragmentReservation3Binding
    lateinit var mainActivity:MainActivity
    private var job = SupervisorJob()
    private var job2 = SupervisorJob()
    private var job3 = SupervisorJob()
    private var job4 = SupervisorJob()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity) mainActivity = context
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservation3Binding.inflate(inflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sw1 = false
        var sw2 = false
        setFragmentResultListener("requestKey4") { key, bundle ->
            binding.textRes.text = "원장 성민\n" + bundle.getString("day")
            binding.text1.text = bundle.getString("menu")
            binding.text2.text = bundle.getString("price")
            binding.text3.text = bundle.getString("priceAll")
        }
        binding.checkBox1.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                sw1 = true
                binding.checkBox2.isChecked = false
            }
            else{
                sw1 = false
            }
            buttonState(sw1, sw2)
        }
        binding.checkBox2.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                sw2 = true
                binding.checkBox1.isChecked = false
            }
            else{
                sw2 = false
            }
            buttonState(sw1, sw2)
        }
    }
    fun buttonState(sw1:Boolean, sw2:Boolean){
        if(sw1 or sw2){
            //프래그먼트 job.cancel()해야함
            binding.button1.isEnabled = true
            binding.button1.setBackgroundColor(Color.parseColor("#2AB849"))
            job4.cancel()
            job4 = SupervisorJob()
            GlobalScope.launch(job4){
                while(true){
                    //예약 버튼 색깔 변경
                    binding.button1.setStrokeColor(
                        ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
                    delay(1000)
                    binding.button1.setStrokeColor(
                        ColorStateList.valueOf(Color.parseColor("#FFE91E63")))
                    delay(1000)
                }
            }
        }
        else {
            binding.button1.isEnabled = false
            job4.cancel()
            binding.button1.setBackgroundColor(Color.parseColor("#BCBCBC"))
            binding.button1.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
        }
    }
}
