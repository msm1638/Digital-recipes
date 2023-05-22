package com.example.project_test3

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import com.example.project_test3.databinding.FragmentReservation4Binding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentReservation4 : Fragment() {
    lateinit var binding: FragmentReservation4Binding
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
        binding = FragmentReservation4Binding.inflate(inflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sw1 = false
        var sw2 = false
        setFragmentResultListener("requestKey5") { key, bundle ->
            binding.textRes.text = bundle.getString("day")
            binding.text1.text = bundle.getString("menu")
            binding.text2.text = bundle.getString("price")
            binding.text3.text = bundle.getString("priceAll")
        }
        binding.button1.setOnClickListener {
            job.cancel()
            mainActivity.reservation5()
        }
        binding.EditText1.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1:Int, p2:Int, p3:Int){

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){

            }
            override fun afterTextChanged(p0: Editable?){
                sw1 = binding.EditText1.text.toString() != ""
                buttonState(sw1, sw2)
                Log.d("태그", ""+sw1)
            }
        })
        binding.EditText2.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1:Int, p2:Int, p3:Int){

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){

            }
            override fun afterTextChanged(p0: Editable?){
                sw2 = binding.EditText2.text.toString() != ""
                Log.d("태그", ""+sw2)
                buttonState(sw1, sw2)
            }
        })
        job.cancel()
        job = SupervisorJob()
        GlobalScope.launch(job){
            while(true){
                //예약 버튼 색깔 변경
                binding.EditText1.setBackgroundResource(R.drawable.border_all2)
                delay(1000)
                binding.EditText1.setBackgroundResource(R.drawable.border_all)
                delay(1000)
            }
        }
    }
    fun buttonState(sw1:Boolean, sw2:Boolean){
        if(sw1 and sw2){
            //프래그먼트 job.cancel()해야함
            binding.button1.isEnabled = true
            binding.button1.setBackgroundColor(Color.parseColor("#2AB849"))
            job.cancel()
            binding.EditText1.setBackgroundResource(R.drawable.border_all)
            binding.EditText2.setBackgroundResource(R.drawable.border_all)
            job = SupervisorJob()
            GlobalScope.launch(job){
                while(true){
                    //예약 버튼 색깔 변경
                    binding.button1.setStrokeColor(
                        ColorStateList.valueOf(Color.parseColor("#FFE91E63")))
                    delay(1000)
                    binding.button1.setStrokeColor(
                        ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
                    delay(1000)
                }
            }
        }
        else{
            binding.button1.isEnabled = false
            job.cancel()
            binding.EditText1.setBackgroundResource(R.drawable.border_all)
            binding.EditText2.setBackgroundResource(R.drawable.border_all)
            binding.button1.setBackgroundColor(Color.parseColor("#BCBCBC"))
            binding.button1.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
        }
        if(sw1 and !sw2){
            job.cancel()
            binding.EditText1.setBackgroundResource(R.drawable.border_all)
            binding.button1.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
            job = SupervisorJob()
            GlobalScope.launch(job){
                while(true){
                    //예약 버튼 색깔 변경
                    binding.EditText2.setBackgroundResource(R.drawable.border_all2)
                    delay(1000)
                    binding.EditText2.setBackgroundResource(R.drawable.border_all)
                    delay(1000)
                }
            }
        }
        else if(!sw1 and sw2){
            job.cancel()
            job = SupervisorJob()
            binding.EditText2.setBackgroundResource(R.drawable.border_all)
            binding.button1.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
            GlobalScope.launch(job){
                while(true){
                    //예약 버튼 색깔 변경
                    binding.EditText1.setBackgroundResource(R.drawable.border_all2)
                    delay(1000)
                    binding.EditText1.setBackgroundResource(R.drawable.border_all)
                    delay(1000)
                }
            }
        }
        else if(!sw1 and !sw2){
            job.cancel()
            job = SupervisorJob()
            GlobalScope.launch(job){
                while(true){
                    //예약 버튼 색깔 변경
                    binding.EditText1.setBackgroundResource(R.drawable.border_all2)
                    delay(1000)
                    binding.EditText1.setBackgroundResource(R.drawable.border_all)
                    delay(1000)
                }
            }
        }
    }
}
