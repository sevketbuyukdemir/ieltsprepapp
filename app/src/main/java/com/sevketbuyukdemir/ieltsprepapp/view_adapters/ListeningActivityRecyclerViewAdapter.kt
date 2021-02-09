package com.sevketbuyukdemir.ieltsprepapp.view_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sevketbuyukdemir.ieltsprepapp.R
import com.sevketbuyukdemir.ieltsprepapp.custom_views.FillBlankQuestionView
import com.sevketbuyukdemir.ieltsprepapp.custom_views.MultipleChoiceQuestionView
import com.sevketbuyukdemir.ieltsprepapp.utils.Question

class ListeningActivityRecyclerViewAdapter(var sectionNumber : Int,
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
            holder.multipleChoiceQuestionView.setQuestionInformation("listening",
                sectionNumber,
                position,
                question.questionText,
                question.answers)
        } else {
            holder as FillInTheBlankViewHolder
            holder.fillBlankQuestionView.setQuestionInformation("listening",
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