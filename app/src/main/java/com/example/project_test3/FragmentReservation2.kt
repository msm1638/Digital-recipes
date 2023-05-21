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
import com.example.project_test3.databinding.FragmentDBinding
import com.example.project_test3.databinding.FragmentReservation2Binding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class FragmentReservation2 : Fragment() {
    lateinit var binding: FragmentReservation2Binding
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
        binding = FragmentReservation2Binding.inflate(inflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sw1 = false
        var sw2 = false
        var sw3 = false
        var price = 0
        var arr = ArrayList<HashMap<String, Any>>()
        var map1 = HashMap<String, Any>()
        map1.put("name","CUT")
        map1.put("price",9900)
        var map2 = HashMap<String, Any>()
        map2.put("name","클렌징CUT")
        map2.put("price",14900)
        var map3 = HashMap<String, Any>()
        map3.put("name","스켈프CUT")
        map3.put("price",19900)
        var map4 = HashMap<String, Any>()
        map4.put("name","CUT+다운펌")
        map4.put("price",16900)
        var map5 = HashMap<String, Any>()
        map5.put("name","일반펌 베이직")
        map5.put("price",19900)
        var map6 = HashMap<String, Any>()
        map6.put("name","일반펌 고급")
        map6.put("price",29900)
        var map7 = HashMap<String, Any>()
        map7.put("name","일반펌 프리미엄")
        map7.put("price",39900)
        var map8 = HashMap<String, Any>()
        map8.put("name","셋팅펌 베이직")
        map8.put("price",29900)
        var map9 = HashMap<String, Any>()
        map9.put("name","뿌리염색 베이직")
        map9.put("price",15900)
        var map10 = HashMap<String, Any>()
        map10.put("name","뿌리염색 고급")
        map10.put("price",25900)
        var map11 = HashMap<String, Any>()
        map11.put("name","뿌리염색 프리미엄")
        map11.put("price",35900)
        var map12 = HashMap<String, Any>()
        map12.put("name","전체염색 베이직")
        map12.put("price",19900)
        var map13 = HashMap<String, Any>()
        map13.put("name","케라틴")
        map13.put("price",14900)
        var map14 = HashMap<String, Any>()
        map14.put("name","리페어")
        map14.put("price",24900)

        // 1. 페이지 데이터를 로드
        val list = listOf(FragmentJ(), FragmentK(), FragmentL(), FragmentM())
        // 2. 아답터를 생성
        val pagerAdatper = FragmentPagerAdapter(list, mainActivity)
        // 3. 아답터와 뷰페이저 연결
        binding.viewPager.adapter = pagerAdatper
        // 4. 탭 메뉴의 개수만큼 제목을 목록으로 생성
        val titles = listOf("컷","펌","컬러","클리닉")
        // 5. 탭 레이아웃과 뷰페이저 연결(smoothScrool false -> 전환 애니메이션 제거)
        TabLayoutMediator(binding.tabLayout, binding.viewPager, false, false){tab, position ->
            tab.text = titles.get(position)
        }.attach()
        // 끝에 도달했을 때 도형 같은 표시 없애기
        binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        // 뷰페이저 기능 해제
        binding.viewPager.setUserInputEnabled(false);

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
        binding.button1.setOnClickListener {
            mainActivity.reservation3()
            job4.cancel()
            binding.button1.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
            val bundle = Bundle()
            var menu:String = ""
            var price:String = ""
            var priceAll:Int = 0
            for(i in 0..arr.size-1 step 1){
                menu += "${arr[i].get("name")}"
                price += "${arr[i].get("price")}원"
                priceAll += "${arr[i].get("price")}".toInt()
                if(i==arr.size-1) break
                menu += "\n"
                price += "\n"
            }
            Log.d("태그", ""+priceAll)
            bundle.putString("day", "${binding.calendartext.text} ${binding.timetext.text}")
            bundle.putString("menu", "${menu}")
            bundle.putString("price", "${price}")
            bundle.putString("priceAll", "${priceAll}원")
            setFragmentResult("requestKey4", bundle)
        }

        setFragmentResultListener("requestKey3") { key, bundle ->
            if(bundle.getString("key") == "add"){
                when(bundle.getString("number")){
                    "1" -> arr.add(map1)
                    "2" -> arr.add(map2)
                    "3" -> arr.add(map3)
                    "4" -> arr.add(map4)
                    "5" -> arr.add(map5)
                    "6" -> arr.add(map6)
                    "7" -> arr.add(map7)
                    "8" -> arr.add(map8)
                    "9" -> arr.add(map9)
                    "10" -> arr.add(map10)
                    "11" -> arr.add(map11)
                    "12" -> arr.add(map12)
                    "13" -> arr.add(map13)
                    "14" -> arr.add(map14)
                }
                sw3=true
                //Log.d("태그", ""+arr+sw3)
            }
            else if(bundle.getString("key") == "remove"){
                when(bundle.getString("number")){
                    "1" -> arr.remove(map1)
                    "2" -> arr.remove(map2)
                    "3" -> arr.remove(map3)
                    "4" -> arr.remove(map4)
                    "5" -> arr.remove(map5)
                    "6" -> arr.remove(map6)
                    "7" -> arr.remove(map7)
                    "8" -> arr.remove(map8)
                    "9" -> arr.remove(map9)
                    "10" -> arr.remove(map10)
                    "11" -> arr.remove(map11)
                    "12" -> arr.remove(map12)
                    "13" -> arr.remove(map13)
                    "14" -> arr.remove(map14)
                }
                if(arr.size == 0) {
                    sw3 = false
                    val bundle = Bundle()
                    bundle.putString("key", "true")
                    setFragmentResult("guide7", bundle)
                }
                //Log.d("태그", ""+arr+sw3)
            }
            buttonState(sw1, sw2, sw3)
        }
        setFragmentResultListener("requestKey2") { key, bundle ->
            binding.calendartext.text = bundle.getString("data1")
            binding.layoutDetail01.visibility = View.GONE
            binding.layoutBtn01.animate().apply {
                duration = 300
                rotation(0f)
            }
            binding.timetext.text = bundle.getString("data2")
            sw1 = true
            sw2 = true
            job.cancel()
            binding.layoutDetail01.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
            val bundle = Bundle()
            bundle.putString("key", "true")
            setFragmentResult("guide6", bundle)
        }
        //캘린더
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
                guide5()
            }
        })
        binding.radioButton1.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                sw2 = true
                binding.radioGroup2.clearCheck()
                binding.timetext.text = binding.radioButton1.text
                timeState()
            }
        }
        binding.radioButton2.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                sw2 = true
                binding.radioGroup2.clearCheck()
                binding.timetext.text = binding.radioButton2.text
                timeState()
            }
        }
        binding.radioButton3.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                sw2 = true
                binding.radioGroup2.clearCheck()
                binding.timetext.text = binding.radioButton3.text
                timeState()
            }
        }
        binding.radioButton4.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                sw2 = true
                binding.radioGroup2.clearCheck()
                binding.timetext.text = binding.radioButton4.text
                timeState()
            }
        }
        binding.radioButton5.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                sw2 = true
                binding.radioGroup1.clearCheck()
                binding.timetext.text = binding.radioButton5.text
                timeState()
            }
        }
        binding.radioButton6.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                sw2 = true
                binding.radioGroup1.clearCheck()
                binding.timetext.text = binding.radioButton6.text
                timeState()
            }
        }
        binding.radioButton7.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                sw2 = true
                binding.radioGroup1.clearCheck()
                binding.timetext.text = binding.radioButton7.text
                timeState()
            }
        }
        binding.radioButton8.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked) {
                sw2 = true
                binding.radioGroup1.clearCheck()
                binding.timetext.text = binding.radioButton8.text
                timeState()
            }
        }
    }
    fun timeState(){
        binding.layoutDetail02.visibility = View.GONE
        binding.layoutBtn01.animate().apply {
            duration = 300
            rotation(0f)
        }
        job2.cancel()
        binding.layoutDetail02.setStrokeColor(
            ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
        val bundle = Bundle()
        bundle.putString("key", "true")
        setFragmentResult("guide6", bundle)
    }
    fun buttonState(sw1:Boolean, sw2:Boolean, sw3:Boolean){
        if(sw1==sw2==sw3==true){
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
        else if(sw1==sw2==true and sw3==false) {
            binding.button1.isEnabled = false
            job4.cancel()
            binding.button1.setBackgroundColor(Color.parseColor("#BCBCBC"))
            binding.button1.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
        }

    }
    fun guide5(){
        job.cancel()
        binding.layoutDetail01.setStrokeColor(
            ColorStateList.valueOf(Color.parseColor("#D7D7D7")))
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
