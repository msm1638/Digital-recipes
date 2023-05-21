package com.example.project_test3

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings.Global.putString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.project_test3.databinding.FragmentDBinding
import com.example.project_test3.databinding.FragmentReservationBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentReservation : BottomSheetDialogFragment() {
    lateinit var binding:FragmentReservationBinding
    lateinit var mainActivity:MainActivity
    private var job = SupervisorJob()
    private var job2 = SupervisorJob()
    private var job3 = SupervisorJob()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity) mainActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sw1 = false
        var sw2 = false
        binding.layout01.setOnClickListener {
            if(binding.layoutDetail01.visibility == View.VISIBLE) {
                binding.layoutDetail01.visibility = View.GONE
                binding.layoutBtn01.animate().apply {
                    duration = 300
                    rotation(0f)
                }
            } else {
                binding.layoutDetail01.visibility = View.VISIBLE
                binding.layoutBtn01.animate().apply {
                    duration = 300
                    rotation(180f)
                }
            }
        }
        binding.layout02.setOnClickListener {
            if(binding.layoutDetail02.visibility == View.VISIBLE) {
                binding.layoutDetail02.visibility = View.GONE
                binding.layoutBtn02.animate().apply {
                    duration = 300
                    rotation(0f)
                }
            } else {
                binding.layoutDetail02.visibility = View.VISIBLE
                binding.layoutBtn02.animate().apply {
                    duration = 300
                    rotation(180f)
                }
            }
        }
        //캘린더

        binding.calendar.setOnDateChangeListener(object : CalendarView.OnDateChangeListener{
            override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
                sw1 = true
                binding.calendartext.text = "${p2}월 ${p3}일"
                binding.layoutDetail01.visibility = View.GONE
                binding.layoutBtn01.animate().apply {
                    duration = 300
                    rotation(0f)
                }
                binding.layoutDetail02.visibility = View.VISIBLE
                binding.layoutBtn02.animate().apply {
                    duration = 300
                    rotation(180f)
                }
                job.cancel()
                binding.layoutDetail01.setStrokeColor(
                    ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
                guide3()
            }
        })
        //시간
        binding.radioButton1.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                binding.radioGroup2.clearCheck()
                binding.timetext.text = binding.radioButton1.text
                sw2 = true
                buttonState(sw1, sw2)
            }
        }
        binding.radioButton2.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                binding.radioGroup2.clearCheck()
                binding.timetext.text = binding.radioButton2.text
                sw2 = true
                buttonState(sw1, sw2)
            }
        }
        binding.radioButton3.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                binding.radioGroup2.clearCheck()
                binding.timetext.text = binding.radioButton3.text
                sw2 = true
                buttonState(sw1, sw2)
            }
        }
        binding.radioButton4.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                binding.radioGroup2.clearCheck()
                binding.timetext.text = binding.radioButton4.text
                sw2 = true
                buttonState(sw1, sw2)
            }
        }
        binding.radioButton5.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                binding.radioGroup1.clearCheck()
                binding.timetext.text = binding.radioButton5.text
                sw2 = true
                buttonState(sw1, sw2)
            }
        }
        binding.radioButton6.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                binding.radioGroup1.clearCheck()
                binding.timetext.text = binding.radioButton6.text
                sw2 = true
                buttonState(sw1, sw2)
            }
        }
        binding.radioButton7.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                binding.radioGroup1.clearCheck()
                binding.timetext.text = binding.radioButton7.text
                sw2 = true
                buttonState(sw1, sw2)
            }
        }
        binding.radioButton8.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                binding.radioGroup1.clearCheck()
                binding.timetext.text = binding.radioButton8.text
                sw2 = true
                buttonState(sw1, sw2)
            }
        }
        binding.button1.setOnClickListener{
            dialog?.dismiss()
            job3.cancel()
            binding.layoutDetail02.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
        }
        binding.button2.setOnClickListener{
            dialog?.dismiss()
            val bundle = Bundle()
            bundle.putString("data1", "${binding.calendartext.text}")
            bundle.putString("data2", "${binding.timetext.text}")
            setFragmentResult("requestKey", bundle)
            job3.cancel()
            binding.layoutDetail02.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
        }
        setFragmentResultListener("guide3") { key, bundle ->
            if(bundle.getString("key")=="true") {
                job.cancel()
                job = SupervisorJob()
                GlobalScope.launch(job){
                    while(true){
                        //예약 버튼 색깔 변경
                        binding.layoutDetail01.setStrokeColor(
                            ColorStateList.valueOf(Color.parseColor("#FFE91E63")))
                        delay(1000)
                        binding.layoutDetail01.setStrokeColor(
                            ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
                        delay(1000)
                    }
                }
            }
        }
        // 팝업 생성 시 전체화면으로 띄우기
        val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        val behavior = BottomSheetBehavior.from<View>(bottomSheet!!)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        // 드래그해도 팝업이 종료되지 않도록
//        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
//                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
//                }
//            }
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
//        })
    }
    //버튼 상태 바꾸기
    fun buttonState(sw1:Boolean, sw2:Boolean){
        if(sw1==sw2==true){
            job2.cancel()
            binding.layoutDetail02.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
            binding.button2.isEnabled = true
            job3.cancel()
            job3 = SupervisorJob()
            GlobalScope.launch(job3){
                while(true){
                    //예약 버튼 색깔 변경
                    binding.button2.setStrokeColor(
                        ColorStateList.valueOf(Color.parseColor("#4CAF50")))
                    delay(1000)
                    binding.button2.setStrokeColor(
                        ColorStateList.valueOf(Color.parseColor("#FFE91E63")))
                    delay(1000)
                }
            }
        }
    }
    fun guide3(){
        job2.cancel()
        job2 = SupervisorJob()
        GlobalScope.launch(job2){
            while(true){
                //예약 버튼 색깔 변경
                binding.layoutDetail02.setStrokeColor(
                    ColorStateList.valueOf(Color.parseColor("#FFE91E63")))
                delay(1000)
                binding.layoutDetail02.setStrokeColor(
                    ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
                delay(1000)
            }
        }
    }
}