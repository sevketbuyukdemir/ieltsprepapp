package com.sevketbuyukdemir.ieltsprepapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sevketbuyukdemir.ieltsprepapp.Fragments.WritingFragment
import com.sevketbuyukdemir.ieltsprepapp.view_adapters.WritingActivityViewPagerAdapter

class WritingActivity : AppCompatActivity() {
    // Global objects for UI elements
    private lateinit var writingTabLayout : TabLayout
    private lateinit var writingViewpager : ViewPager

    // View pager and Fragments
    private lateinit var writingActivityViewPagerAdapter: WritingActivityViewPagerAdapter
    private lateinit var writingFragmentFirst: WritingFragment
    private lateinit var writingFragmentFirstResponse: WritingFragment
    private lateinit var writingFragmentFirstResponseFeedback: WritingFragment
    private lateinit var writingFragmentSecond: WritingFragment
    private lateinit var writingFragmentSecondResponse: WritingFragment
    private lateinit var writingFragmentSecondResponseFeedback: WritingFragment

    // Data sources for fragments
    private val writingTextFragmentFirstDataSource = ArrayList<String>()
    private val writingTextFragmentFirstResponseDataSource = ArrayList<String>()
    private val writingTextFragmentFirstResponseFeedbackDataSource = ArrayList<String>()
    private val writingTextFragmentSecondDataSource = ArrayList<String>()
    private val writingTextFragmentSecondResponseDataSource = ArrayList<String>()
    private val writingTextFragmentSecondResponseFeedbackDataSource = ArrayList<String>()
    private fun init(){
        writingTabLayout = findViewById(R.id.writing_tab_layout)
        writingViewpager = findViewById(R.id.writing_viewpager)
        fillDataSources()
        initiateFragments()
        setAndInitiateViewPagerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)
        init()
    }

    private fun fillDataSources(){
        // First fragment
        writingTextFragmentFirstDataSource.add(getString(R.string.writing_first_warning))
        writingTextFragmentFirstDataSource.add(getString(R.string.writing_first_title))
        writingTextFragmentFirstDataSource.add(getString(R.string.writing_first_text_first))
        // Second fragment response
        writingTextFragmentFirstResponseDataSource.add(getString(R.string.writing_second_warning))
        writingTextFragmentFirstResponseDataSource.add(getString(R.string.writing_second_title))
        writingTextFragmentFirstResponseDataSource.add(getString(R.string.writing_second_text_first))
        writingTextFragmentFirstResponseDataSource.add(getString(R.string.writing_second_text_second))
        writingTextFragmentFirstResponseDataSource.add(getString(R.string.writing_second_text_third))
        // Third fragment response feedback
        writingTextFragmentFirstResponseFeedbackDataSource.add(getString(R.string.writing_third_warning))
        writingTextFragmentFirstResponseFeedbackDataSource.add(getString(R.string.writing_third_title))
        writingTextFragmentFirstResponseFeedbackDataSource.add(getString(R.string.writing_third_text_first))
        writingTextFragmentFirstResponseFeedbackDataSource.add(getString(R.string.writing_third_text_second))
        writingTextFragmentFirstResponseFeedbackDataSource.add(getString(R.string.writing_third_text_third))
        writingTextFragmentFirstResponseFeedbackDataSource.add(getString(R.string.writing_third_text_forth))
        // Forth fragment
        writingTextFragmentSecondDataSource.add(getString(R.string.writing_forth_warning))
        writingTextFragmentSecondDataSource.add(getString(R.string.writing_forth_title))
        writingTextFragmentSecondDataSource.add(getString(R.string.writing_forth_text_first))
        // Fifth fragment response
        writingTextFragmentSecondResponseDataSource.add(getString(R.string.writing_fifth_warning))
        writingTextFragmentSecondResponseDataSource.add(getString(R.string.writing_fifth_title))
        writingTextFragmentSecondResponseDataSource.add(getString(R.string.writing_fifth_text_first))
        writingTextFragmentSecondResponseDataSource.add(getString(R.string.writing_fifth_text_second))
        writingTextFragmentSecondResponseDataSource.add(getString(R.string.writing_fifth_text_third))
        writingTextFragmentSecondResponseDataSource.add(getString(R.string.writing_fifth_text_forth))
        // Sixth fragment response feedback
        writingTextFragmentSecondResponseFeedbackDataSource.add(getString(R.string.writing_sixth_warning))
        writingTextFragmentSecondResponseFeedbackDataSource.add(getString(R.string.writing_sixth_title))
        writingTextFragmentSecondResponseFeedbackDataSource.add(getString(R.string.writing_sixth_text_first))
        writingTextFragmentSecondResponseFeedbackDataSource.add(getString(R.string.writing_sixth_text_second))
        writingTextFragmentSecondResponseFeedbackDataSource.add(getString(R.string.writing_sixth_text_third))
        writingTextFragmentSecondResponseFeedbackDataSource.add(getString(R.string.writing_sixth_text_forth))
    }

    private fun initiateFragments(){
        writingFragmentFirst = WritingFragment(1,writingTextFragmentFirstDataSource)
        writingFragmentFirstResponse = WritingFragment(2, writingTextFragmentFirstResponseDataSource)
        writingFragmentFirstResponseFeedback = WritingFragment(3, writingTextFragmentFirstResponseFeedbackDataSource)
        writingFragmentSecond = WritingFragment(4, writingTextFragmentSecondDataSource)
        writingFragmentSecondResponse = WritingFragment(5, writingTextFragmentSecondResponseDataSource)
        writingFragmentSecondResponseFeedback = WritingFragment(6, writingTextFragmentSecondResponseFeedbackDataSource)
    }

    private fun setAndInitiateViewPagerAdapter(){
        writingActivityViewPagerAdapter = WritingActivityViewPagerAdapter(supportFragmentManager)
        writingActivityViewPagerAdapter.addFragment(writingFragmentFirst, "TASK 1")
        writingActivityViewPagerAdapter.addFragment(writingFragmentFirstResponse, "RESPONSE")
        writingActivityViewPagerAdapter.addFragment(writingFragmentFirstResponseFeedback, "FEEDBACK")
        writingActivityViewPagerAdapter.addFragment(writingFragmentSecond, "TASK 2")
        writingActivityViewPagerAdapter.addFragment(writingFragmentSecondResponse, "RESPONSE")
        writingActivityViewPagerAdapter.addFragment(writingFragmentSecondResponseFeedback, "FEEDBACK")
        writingViewpager.adapter = writingActivityViewPagerAdapter
        writingTabLayout.setupWithViewPager(writingViewpager)
    }
}