package com.sevketbuyukdemir.ieltsprepapp.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sevketbuyukdemir.ieltsprepapp.R
import com.sevketbuyukdemir.ieltsprepapp.ReadingActivity
import com.sevketbuyukdemir.ieltsprepapp.custom_views.FillBlankQuestionView
import com.sevketbuyukdemir.ieltsprepapp.custom_views.MultipleChoiceQuestionView
import com.sevketbuyukdemir.ieltsprepapp.utils.Question

class ReadingQuestionFragment(var sectionNumber : Int,
                              private val questionList: ArrayList<Question>) : Fragment(), View.OnClickListener  {

    lateinit var recyclerViewReadingQuestion : RecyclerView
    lateinit var readingQuestionFragmentRecyclerViewAdapter : ReadingQuestionFragmentRecyclerViewAdapter
    lateinit var btnReadingSubmit : Button
    private var submitted : Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reading_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view : View){
        btnReadingSubmit = view.findViewById(R.id.btn_reading_submit)
        btnReadingSubmit.setOnClickListener(this)
        // Bind RecyclerView with id
        recyclerViewReadingQuestion = view.findViewById(R.id.recycler_view_reading_question)
        // Set layout manager to RecyclerView
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerViewReadingQuestion.layoutManager = llm
        // RecyclerView adapter initiation
        readingQuestionFragmentRecyclerViewAdapter = context?.let {
                ReadingQuestionFragmentRecyclerViewAdapter(sectionNumber, it, questionList) }!!
        recyclerViewReadingQuestion.adapter = readingQuestionFragmentRecyclerViewAdapter
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.btn_reading_submit -> submit()
            }
        }
    }

    private fun submit(){
        if(!submitted) {
            btnReadingSubmit.text = getString(R.string.submit_button_text_submitted)
            ReadingActivity.submittedSectionCount++
            ReadingActivity.submitAllListeningSections()
            submitted = true
        }
    }

    class ReadingQuestionFragmentRecyclerViewAdapter(var sectionNumber : Int,
                                                       var context : Context,
                                                       private val questionList: ArrayList<Question>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemViewType(position: Int): Int {
            val question = questionList[position]
            val type = question.type
            return if(type){
                1
            } else {
                0
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if(viewType == 1){
                val inflater = LayoutInflater.from(context)
                val view = inflater.inflate(R.layout.recycler_view_multiple_choice_item, parent, false)
                MultipleChoiceViewHolder(view)
            } else {
                val inflater = LayoutInflater.from(context)
                val view = inflater.inflate(R.layout.recycler_view_fill_in_the_blank_item, parent, false)
                FillInTheBlankViewHolder(view)
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val holderType = holder.itemViewType
            val question = questionList[position]
            if(holderType == 1) {
                holder as MultipleChoiceViewHolder
                holder.multipleChoiceQuestionView.setQuestionInformation("reading",
                    sectionNumber,
                    position,
                    question.questionText,
                    question.answers)
            } else {
                holder as FillInTheBlankViewHolder
                holder.fillBlankQuestionView.setQuestionInformation("reading",
                    sectionNumber,
                    position,
                    question.questionText)
            }
        }

        override fun getItemCount(): Int {
            return if(questionList.size == 0){
                0
            } else {
                questionList.size
            }
        }

        class FillInTheBlankViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            val fillBlankQuestionView : FillBlankQuestionView =
                view.findViewById(R.id.FillBlankQuestionView)
        }

        class MultipleChoiceViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            val multipleChoiceQuestionView : MultipleChoiceQuestionView =
                view.findViewById(R.id.MultipleChoiceQuestionView)
        }
    }
}