package com.sevketbuyukdemir.ieltsprepapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sevketbuyukdemir.ieltsprepapp.Fragments.SpeakingFragment
import com.sevketbuyukdemir.ieltsprepapp.Fragments.WritingFragment
import com.sevketbuyukdemir.ieltsprepapp.view_adapters.SpeakingActivityViewPagerAdapter
import com.sevketbuyukdemir.ieltsprepapp.view_adapters.WritingActivityViewPagerAdapter

// todo
class SpeakingActivity : AppCompatActivity() {

    // Global objects for UI elements
    private lateinit var speakingTabLayout : TabLayout
    private lateinit var speakingViewpager : ViewPager

    // View pager and Fragments
    private lateinit var speakingActivityViewPagerAdapter: SpeakingActivityViewPagerAdapter
    private lateinit var speakingFragmentFirst: SpeakingFragment
    private lateinit var speakingFragmentSecond: SpeakingFragment
    private lateinit var speakingFragmentThird: SpeakingFragment

    // Data sources for fragments
    private val speakingFragmentFirstDataSource = ArrayList<String>()
    private val speakingFragmentSecondDataSource = ArrayList<String>()
    private val speakingFragmentThirdDataSource = ArrayList<String>()

    private fun init(){
        speakingTabLayout = findViewById(R.id.speaking_tab_layout)
        speakingViewpager = findViewById(R.id.speaking_viewpager)
        fillDataSources()
        initiateFragments()
        setAndInitiateViewPagerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaking)
        init()
    }

    private fun fillDataSources(){
        speakingFragmentFirstDataSource.add(getString(R.string.speaking_first_warning))
        speakingFragmentFirstDataSource.add(getString(R.string.speaking_first_title))
        speakingFragmentFirstDataSource.add(getString(R.string.speaking_first_text_first))
        speakingFragmentSecondDataSource.add(getString(R.string.speaking_second_warning))
        speakingFragmentSecondDataSource.add(getString(R.string.speaking_second_title))
        speakingFragmentSecondDataSource.add(getString(R.string.speaking_second_text_first))
        speakingFragmentThirdDataSource.add(getString(R.string.speaking_third_warning))
        speakingFragmentThirdDataSource.add(getString(R.string.speaking_third_title))
    }

    private fun initiateFragments(){
        speakingFragmentFirst = SpeakingFragment(1, speakingFragmentFirstDataSource)
        speakingFragmentSecond = SpeakingFragment(2, speakingFragmentSecondDataSource)
        speakingFragmentThird = SpeakingFragment(3, speakingFragmentThirdDataSource)
    }

    private fun setAndInitiateViewPagerAdapter(){
        speakingActivityViewPagerAdapter = SpeakingActivityViewPagerAdapter(supportFragmentManager)
        speakingActivityViewPagerAdapter.addFragment(speakingFragmentFirst, "PART 1")
        speakingActivityViewPagerAdapter.addFragment(speakingFragmentSecond, "PART 2")
        speakingActivityViewPagerAdapter.addFragment(speakingFragmentThird, "PART 3")
        speakingViewpager.adapter = speakingActivityViewPagerAdapter
        speakingTabLayout.setupWithViewPager(speakingViewpager)
    }
}