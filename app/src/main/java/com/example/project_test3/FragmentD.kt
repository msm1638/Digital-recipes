package com.example.project_test3

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.project_test3.databinding.FragmentDBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentD : Fragment(){
    lateinit var binding:FragmentDBinding
    lateinit var mainActivity:MainActivity
    private var job = SupervisorJob()
    private var job2 = SupervisorJob()
    private var sw = false
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity) mainActivity = context
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDBinding.inflate(inflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener("guide2") { key, bundle ->
            if(bundle.getString("key")=="true") {
                job.cancel()
                job = SupervisorJob()
                GlobalScope.launch(job){
                    while(true){
                        //예약 버튼 색깔 변경
                        binding.layoutRes.setBackgroundResource(R.drawable.border_all2)
                        delay(1000)
                        binding.layoutRes.setBackgroundResource(R.drawable.border_all)
                        delay(1000)
                    }
                }
            }
        }
        binding.buttonRes1.setOnClickListener{
            mainActivity.reservation()//예약 불러오기
            val bundle = Bundle()
            bundle.putString("key", "true")
            setFragmentResult("guide3", bundle)
        }
        binding.buttonRes2.setOnClickListener{
            mainActivity.reservation()//예약 불러오기
            val bundle = Bundle()
            bundle.putString("key", "true")
            setFragmentResult("guide3", bundle)
        }
        setFragmentResultListener("requestKey") { key, bundle ->
            getData(bundle.getString("data1"), bundle.getString("data2"))
            job.cancel()
            binding.layoutRes.setBackgroundResource(R.drawable.border_all)
            guide4()
            sw = true
            mainActivity.jobCancel()
        }
        binding.button3.setOnClickListener{
            mainActivity.reservation2()
            job2.cancel()
            binding.button3.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
            if(sw){
                val bundle = Bundle()
                bundle.putString("data1", "${binding.buttonRes1.text}")
                bundle.putString("data2", "${binding.buttonRes2.text}")
                setFragmentResult("requestKey2", bundle)
            }
        }




    }
    fun getData(data1:String?, data2:String?){
        binding.buttonRes1.text = data1
        binding.buttonRes2.text = data2
    }
    fun guide4(){
        job2.cancel()
        job2 = SupervisorJob()
        GlobalScope.launch(job2){
            while(true){
                //예약 버튼 색깔 변경
                binding.button3.setStrokeColor(
                    ColorStateList.valueOf(Color.parseColor("#FFE91E63")))
                delay(1000)
                binding.button3.setStrokeColor(
                    ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
                delay(1000)
            }
        }
    }
}