package com.sevketbuyukdemir.ieltsprepapp.custom_views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.sevketbuyukdemir.ieltsprepapp.ListeningActivity
import com.sevketbuyukdemir.ieltsprepapp.R
import com.sevketbuyukdemir.ieltsprepapp.ReadingActivity


class FillBlankQuestionView : View {
    private lateinit var finalParent : ViewGroup
    var questionSection : Int = -1
    var questionNumber : Int = -1
    lateinit var questionText : String
    lateinit var testPart : String

    constructor(context: Context?) : super(context) {

    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){

    }

    fun setQuestionInformation(testPart : String,
                               questionSection : Int,
                               questionNumber : Int,
                               questionText : String){
        this.questionSection = questionSection
        this.questionNumber = questionNumber
        this.questionText = questionText
        this.testPart = testPart
    }

    private fun createQuestionView() : View{
        val questionView = LinearLayout(context)
        questionView.layoutParams = ViewGroup.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        questionView.orientation = LinearLayout.VERTICAL
        questionView.setBackgroundColor(Color.GRAY)

        val questionParts = questionText.split('^')

        val tvQuestionFirstPart = TextView(context)
        tvQuestionFirstPart.text = questionParts[0]
        tvQuestionFirstPart.textSize = 16f
        tvQuestionFirstPart.setTextColor(Color.BLUE)

        val blank = EditText(context)
        blank.hint = "blank"
        blank.textSize = 16f
        blank.setTextColor(Color.RED)

        val questionTextView = LinearLayout(context)
        questionTextView.layoutParams = ViewGroup.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        questionTextView.orientation = LinearLayout.VERTICAL
        questionTextView.setBackgroundColor(Color.GRAY)

        questionTextView.addView(tvQuestionFirstPart)
        questionTextView.addView(blank)

        if(questionParts.size == 2){
            val tvQuestionSecondPart = TextView(context)
            tvQuestionSecondPart.text = questionParts[1]
            tvQuestionSecondPart.textSize = 16f
            tvQuestionSecondPart.setTextColor(Color.BLUE)
            questionTextView.addView(tvQuestionSecondPart)
        }

        val submitBlank = Button(context)
        submitBlank.text = context.getString(R.string.custom_fill_blank_view_submit_blank_text)
        submitBlank.textSize = 16f
        submitBlank.setTextColor(Color.WHITE)
        submitBlank.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val text = blank.text.toString()
                // TODO delete logs
                if(testPart == "listening"){
                    Log.e("MultipleChoiceView", "Answer: Section-"+questionSection+"-Question-"+questionNumber+": "+ text)
                    if (questionSection == 1) {
                        ListeningActivity.firstSectionAnswers[questionNumber] = text
                    } else if (questionSection == 2) {
                        ListeningActivity.secondSectionAnswers[questionNumber] = text
                    } else if (questionSection == 3) {
                        ListeningActivity.thirdSectionAnswers[questionNumber] = text
                    } else if (questionSection == 4) {
                        ListeningActivity.forthSectionAnswers[questionNumber] = text
                    }
                } else if(testPart == "reading"){
                    Log.e("MultipleChoiceView", "Answer: Section-"+questionSection+"-Question-"+questionNumber+": "+ text)
                    if (questionSection == 1) {
                        ReadingActivity.firstSectionAnswers[questionNumber] = text
                    } else if (questionSection == 2) {
                        ReadingActivity.secondSectionAnswers[questionNumber] = text
                    } else if (questionSection == 3) {
                        ReadingActivity.thirdSectionAnswers[questionNumber] = text
                    }
                }
            }
        })

        questionView.removeAllViews()
        questionView.addView(questionTextView)
        questionView.addView(submitBlank)

        return questionView
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        finalParent = this.parent as ViewGroup
        // Remove all views is very important for prevent duplication for add items in RecyclerView
        finalParent.removeAllViews()
        finalParent.addView(createQuestionView())
    }
}