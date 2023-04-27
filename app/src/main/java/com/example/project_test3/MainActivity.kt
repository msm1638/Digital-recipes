package com.example.project_test3

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.project_test3.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay


class MainActivity : AppCompatActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //안녕하세요
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
                binding.mainScrollView.scrollTo(0,binding.viewPager.top)
            }
        })
        binding.viewPager
        GlobalScope.launch{
            while(true){
                binding.button1
                    .setStrokeColor(ColorStateList.valueOf(Color.parseColor("#FFE91E63")))
                delay(1000)
                binding.button1
                    .setStrokeColor(ColorStateList.valueOf(Color.parseColor("#4CAF50")))
                delay(1000)
            }
        }
    }
}

class FragmentPagerAdapter(val fragmentList:List<Fragment>, fragmentActivity: FragmentActivity)
                                                    : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = fragmentList.size
    override fun createFragment(position: Int) = fragmentList.get(position)

}