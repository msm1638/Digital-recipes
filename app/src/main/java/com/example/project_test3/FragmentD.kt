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
import androidx.fragment.app.setFragmentResultListener
import com.example.project_test3.databinding.FragmentDBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentD : Fragment(){
    lateinit var binding:FragmentDBinding
    lateinit var mainActivity:MainActivity
    val text1 = "예약 날짜"
    val text2 = "예약 시간"
    private val job = SupervisorJob()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity) mainActivity = context
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDBinding.inflate(inflater, container,false)
//        binding.buttonRes1.text = this.arguments?.getString("data1")
//        binding.buttonRes2.text = this.arguments?.getString("data2")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("requestKey") { key, bundle ->
            getData(bundle.getString("data1"), bundle.getString("data2"))
            job.cancel()
            binding.layoutRes.setBackgroundResource(R.drawable.border_all)
        }

        binding.buttonRes1.setOnClickListener{
            mainActivity.reservation()//예약 불러오기
        }
        binding.buttonRes2.setOnClickListener{
            mainActivity.reservation()//예약 불러오기
        }
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
    fun getData(data1:String?, data2:String?){
        binding.buttonRes1.text = data1
        binding.buttonRes2.text = data2
    }
}