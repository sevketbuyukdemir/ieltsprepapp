package com.sevketbuyukdemir.ieltsprepapp.custom_views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.get
import com.sevketbuyukdemir.ieltsprepapp.ListeningActivity
import com.sevketbuyukdemir.ieltsprepapp.ReadingActivity

class MultipleChoiceQuestionView : View, RadioGroup.OnCheckedChangeListener {
    private lateinit var finalParent : ViewGroup
    var questionSection : Int = -1
    var questionNumber : Int = -1
    lateinit var questionText : String
    lateinit var answers: Array<String>
    lateinit var testPart : String

    constructor(context: Context?) : super(context){
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
    }

    fun setQuestionInformation(testPart : String,
                               questionSection : Int,
                               questionNumber : Int,
                               questionText : String,
                               answers: Array<String>){
        this.questionSection = questionSection
        this.questionNumber = questionNumber
        this.questionText = questionText
        this.answers = answers
        this.testPart = testPart
    }

    private fun createQuestionView() : View{
        val questionView = LinearLayout(context)
        questionView.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        questionView.orientation = LinearLayout.VERTICAL
        questionView.setBackgroundColor(Color.GRAY)

        val tvQuestion = TextView(context)
        tvQuestion.text = questionText
        tvQuestion.textSize = 16f
        tvQuestion.setTextColor(Color.BLUE)

        val radioGroup = RadioGroup(context)

        for ((idCounter, answer) in answers.withIndex()) {
            val radioButton = RadioButton(context)
            radioButton.id = idCounter
            radioButton.text = answer
            radioButton.textSize = 12f
            radioButton.setTextColor(Color.DKGRAY)
            radioGroup.addView(radioButton)
        }

        radioGroup.setOnCheckedChangeListener(this)

        questionView.removeAllViews()
        questionView.addView(tvQuestion)
        questionView.addView(radioGroup)

        return questionView
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        finalParent = this.parent as ViewGroup
        // Remove all views is very important for prevent duplication for add items in RecyclerView
        finalParent.removeAllViews()
        finalParent.addView(createQuestionView())
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val radioButton = (group?.get(checkedId)) as RadioButton
        val text = radioButton.text
        // TODO delete logs
        if(testPart == "listening"){
            Log.e("MultipleChoiceView", "Answer: Section-"+questionSection+"-Question-"+questionNumber+": "+(text as String))
            if(questionSection == 1) {
                ListeningActivity.firstSectionAnswers[questionNumber] = text
            } else if (questionSection == 2)  {
                ListeningActivity.secondSectionAnswers[questionNumber] = text
            } else if (questionSection == 3)  {
                ListeningActivity.thirdSectionAnswers[questionNumber] = text
            } else if (questionSection == 4)  {
                ListeningActivity.forthSectionAnswers[questionNumber] = text
            }
        } else if(testPart == "reading"){
            Log.e("MultipleChoiceView", "Answer: Section-"+questionSection+"-Question-"+questionNumber+": "+(text as String))
            if (questionSection == 1) {
                ReadingActivity.firstSectionAnswers[questionNumber] = text
            } else if (questionSection == 2) {
                ReadingActivity.secondSectionAnswers[questionNumber] = text
            } else if (questionSection == 3) {
                ReadingActivity.thirdSectionAnswers[questionNumber] = text
            }
        }
    }

}