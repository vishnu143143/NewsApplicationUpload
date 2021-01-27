package com.vishnu.quizzapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestion : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var seletedOptionPosition = 0
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.optionOne -> {
                seletedOptionView(optionOne, 1)
            }
            R.id.optionTwo -> {
                seletedOptionView(optionTwo, 2)
            }
            R.id.optionThree -> {
                seletedOptionView(optionThree, 3)
            }
            R.id.optionFour -> {
                seletedOptionView(optionFour, 4)
            }
            R.id.submitButton -> {
                if (seletedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> setQuestions()
                        else -> Toast.makeText(this, "completed", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    var question = mQuestionList!!.get(mCurrentPosition-1)
                    if(question.correctAnswer != seletedOptionPosition){
                        answerView(seletedOptionPosition,R.drawable.wrong)


                    }
                    answerView(question.correctAnswer,R.drawable.correct)
                    if(mCurrentPosition== mQuestionList!!.size){
                        submitButton.text="FINISH"
                    }
                    else{
                        submitButton.text="GO TO NEXT QUESTION"
                    }
                   seletedOptionPosition=0


                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        Log.i("Question Size", "${Constant.getQuestions().size}")
        //2d0b6b6109632c1ce7a17ca6cff8b2d1
        mQuestionList = Constant.getQuestions()
        setQuestions()
        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        submitButton.setOnClickListener(this)

    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> optionOne.background = ContextCompat.getDrawable(this, drawableView)
            2 -> optionTwo.background = ContextCompat.getDrawable(this, drawableView)
            3 -> optionThree.background = ContextCompat.getDrawable(this, drawableView)
            4 -> optionFour.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    private fun setQuestions() {
        var questionSingle: Question = mQuestionList!![mCurrentPosition - 1]

        defaultVal()
        if(mCurrentPosition== mQuestionList!!.size){
            submitButton.text="FINISH"

        }
        else{
            submitButton.text="SUBMIT"

        }
        progressBar.progress = mCurrentPosition
        tvProgressBar.text = "$mCurrentPosition" + "/" + progressBar.max
        questionText.text = questionSingle.question
        optionOne.text = questionSingle.optionOne
        optionTwo.text = questionSingle.optionTwo
        optionThree.text = questionSingle.optionThree
        optionFour.text = questionSingle.optionFour
        imageFlag.setImageResource(questionSingle.image)
    }

    private fun defaultVal() {
        var options = ArrayList<TextView>()
        options.add(0, optionOne)
        options.add(1, optionTwo)
        options.add(2, optionThree)
        options.add(3, optionFour)
        for (option in options) {
            option.setTextColor(Color.parseColor("#9013FE"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.shaperectangle)
        }

    }

    private fun seletedOptionView(tv: TextView, selectedOption: Int) {
        defaultVal()
        seletedOptionPosition = selectedOption
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.colorpressed)

    }
}
