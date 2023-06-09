package com.example.project_test3

import android.animation.ObjectAnimator
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.project_test3.databinding.ActivityMainBinding
import com.example.project_test3.databinding.FragmentDBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay


class MainActivity : AppCompatActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    private var job = SupervisorJob()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 1. 페이지 데이터를 로드
        val list = listOf(FragmentA(), FragmentB(), FragmentC(), FragmentD(), FragmentE()
            , FragmentF(), FragmentG(), FragmentH(), FragmentI())
        // 2. 아답터를 생성
        val pagerAdatper = FragmentPagerAdapter(list, this)
        // 3. 아답터와 뷰페이저 연결
        binding.viewPager.adapter = pagerAdatper
        // 4. 탭 메뉴의 개수만큼 제목을 목록으로 생성
        val titles = listOf("홈","소식","가격","스타일리스트","시술","리뷰","사진","지도","주변")
        // 5. 탭 레이아웃과 뷰페이저 연결(smoothScrool false -> 전환 애니메이션 제거)
        TabLayoutMediator(binding.tabLayout, binding.viewPager, false, false){tab, position ->
            tab.text = titles.get(position)
        }.attach()

        // 끝에 도달했을 때 도형 같은 표시 없애기
        binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        // 뷰페이저 기능 해제
        binding.viewPager.setUserInputEnabled(false);
        // 헤더 뷰 고정
        binding.mainScrollView.run{
            header = binding.headerView
        }
        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //탭 선택 시 화면 이동
                binding.mainScrollView.scrollTo(0,binding.viewPager.top-binding.headerView.height)
            }
        })
        // 예약 버튼 누를 시 화면 이동
        binding.button1.setOnClickListener{
            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(3))
            binding.mainScrollView.scrollTo(0,binding.viewPager.top-binding.headerView.height)
            job.cancel()
            binding.button1
                .setStrokeColor(ColorStateList.valueOf(Color.parseColor("#4CAF50")))
            val bundle = Bundle()
            bundle.putString("key", "true")
            supportFragmentManager.setFragmentResult("guide2", bundle)
        }
        job.cancel()
        job = SupervisorJob()
        GlobalScope.launch(job){
            while(true){
                //예약 버튼 색깔 변경
                binding.button1
                    .setStrokeColor(ColorStateList.valueOf(Color.parseColor("#FFE91E63")))
                delay(1000)
                binding.button1
                    .setStrokeColor(ColorStateList.valueOf(Color.parseColor("#4CAF50")))
                delay(1000)
            }
        }
    }
    fun reservation(){
        val bottomSheet = FragmentReservation()
        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
    }
    fun reservation2(){
        val transaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
                .add(R.id.frameLayout, FragmentReservation2())
        transaction.addToBackStack(null).commitAllowingStateLoss()
    }
    fun reservation3(){
        val transaction: FragmentTransaction = supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, FragmentReservation3())
        transaction.addToBackStack(null).commitAllowingStateLoss()
    }
    fun reservation4(){
        val transaction: FragmentTransaction = supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, FragmentReservation4())
        transaction.addToBackStack(null).commitAllowingStateLoss()
    }
    fun reservation5(){
        val builder = AlertDialog.Builder(this)
            .setTitle("예약 성공!")
            .setMessage("임의 예약을 완료했습니다. 실전 예약에 도전해보세요!")
            .setPositiveButton("확인", DialogInterface.OnClickListener{dialog, which->
                Toast.makeText(this, "종료",
                Toast.LENGTH_LONG).show()
            })
        builder.show()
    }
    fun jobCancel(){
        job.cancel()
        binding.button1
            .setStrokeColor(ColorStateList.valueOf(Color.parseColor("#4CAF50")))
    }
}

class FragmentPagerAdapter(val fragmentList:List<Fragment>, fragmentActivity: FragmentActivity)
                                                    : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = fragmentList.size
    override fun createFragment(position: Int) = fragmentList.get(position)

}