package com.example.project_test3

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.project_test3.databinding.FragmentKBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FragmentK : Fragment() {
    lateinit var binding: FragmentKBinding
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
        binding = FragmentKBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkBox1.setOnCheckedChangeListener { _, isCheked ->
            if (isCheked) {
                check(5)
            }
            else uncheck(5)
        }
        binding.checkBox2.setOnCheckedChangeListener { _, isCheked ->
            if (isCheked) {
                check(6)
            }
            else uncheck(6)
        }
        binding.checkBox3.setOnCheckedChangeListener { _, isCheked ->
            if (isCheked) {
                check(7)
            }
            else uncheck(7)
        }
        binding.checkBox4.setOnCheckedChangeListener { _, isCheked ->
            if (isCheked) {
                check(8)
            }
            else uncheck(8)
        }
    }
    fun check(i:Int){
        val bundle = Bundle()
        bundle.putString("key", "add")
        bundle.putString("number", "${i}")
        setFragmentResult("requestKey3", bundle)
        val bundle2 = Bundle()
        bundle2.putString("key", "true")
        setFragmentResult("guide8", bundle2)
    }
    fun uncheck(i:Int){
        val bundle = Bundle()
        bundle.putString("key", "remove")
        bundle.putString("number", "${i}")
        setFragmentResult("requestKey3", bundle)
    }
}