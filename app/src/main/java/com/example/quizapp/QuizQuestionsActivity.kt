package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var userName: String? = null
    private var correctAnswer: Int = 0

    private var currentPosition: Int = 1
    private var questionsList: ArrayList<Question>? = null
    private var selectedOptionPosition: Int = 0

    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var progressBar: ProgressBar? = null
    private var tvProgressBar: TextView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        userName = intent.getStringExtra(Constants.USER_NAME)

        tvQuestion = findViewById(R.id.tvQuestion)
        ivImage = findViewById(R.id.ivImage)
        progressBar = findViewById(R.id.progress_bar)
        tvProgressBar = findViewById(R.id.tvProgressBar)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)


        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)


        questionsList = Constants.getQuestions()
        setQuestions()

    }

    @SuppressLint("SetTextI18n")
    private fun setQuestions() {

        val question: Question = questionsList!![currentPosition - 1]

        defaultOptionsView()

        tvQuestion?.text = question.question
        ivImage?.setImageResource(question.image)
        progressBar?.progress = currentPosition
        tvProgressBar?.text = "${currentPosition}/${progressBar?.max}"
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if (currentPosition == questionsList!!.size)
            btnSubmit?.text = "FINISH"
        else
            btnSubmit?.text = "SUBMIT"
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView?>()

        options.add(0, tvOptionOne)
        options.add(1, tvOptionTwo)
        options.add(2, tvOptionThree)
        options.add(3, tvOptionFour)

        for (option in options) {
            option?.setTextColor(Color.parseColor("#7A8089"))
            option?.typeface = Typeface.DEFAULT
            option?.background = ContextCompat.getDrawable(
                this,
                R.drawable.options_border
            )
        }
    }

    /**
     * A function to set the view of selected option view.
     */
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        selectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border
        )
    }

    private fun answerView(answer: Int, view: Int) {
        when (answer) {
            1 ->
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    view
                )

            2 ->
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    view
                )

            3 ->
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    view
                )

            4 ->
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    view
                )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(tv: View?) {

        when (tv?.id) {

            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }
            }

            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }

            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }
            }

            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.btn_submit -> {

                if (selectedOptionPosition == 0) {
                    currentPosition++

                    when {

                        currentPosition <= questionsList!!.size -> {
                            setQuestions()

                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, userName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {

                    val question = questionsList?.get(currentPosition - 1)

                    if (question!!.correctAnswer != selectedOptionPosition) {
                        answerView(selectedOptionPosition, R.drawable.wrong_option_border)
                    } else
                        correctAnswer++

                    answerView(question.correctAnswer, R.drawable.correct_option_border)

                    if (currentPosition == questionsList?.size)
                        btnSubmit?.text = "FINISH"
                    else
                        btnSubmit?.text = "GO TO NEXT QUESTION"

                    selectedOptionPosition = 0

                }
            }
        }
    }
}