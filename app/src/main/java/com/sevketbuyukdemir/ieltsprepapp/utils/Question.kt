package com.sevketbuyukdemir.ieltsprepapp.utils

import android.os.Parcel
import android.os.Parcelable

class Question  : Parcelable {
    val MULTIPLE_CHOICE = true // 1
    val FILL_IN_THE_BLANK = false // 0

    var type = true
    lateinit var questionText : String
    lateinit var answers : Array<String>

    /**
     * Constructor for fill in the blank questions
     */
    constructor(type : Boolean?, question_text : String){
        if (type != null) {
            this.type = type
            this.questionText = question_text
        }
    }

    /**
     * Constructor for multiple choice questions
     */
    constructor(type : Boolean?, question_text : String, answers : Array<String>){
        if (type != null) {
            this.type = type
            this.questionText = question_text
            this.answers = answers
        }
    }

    /**
     * Parcelable constructor
     */
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString(),
        parcel.createStringArray() as Array<String>) {
        type =  foo(parcel.readByte())
        questionText = parcel.readString().toString()
        if(type){
            answers = parcel.createStringArray() as Array<String>
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (type) 1 else 0)
        parcel.writeString(questionText)
        if(type){
            parcel.writeStringArray(answers)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }

    private fun foo(b: Byte): Boolean {
        return when (b) {
            0.toByte() -> true
            else -> false
        }
    }
}