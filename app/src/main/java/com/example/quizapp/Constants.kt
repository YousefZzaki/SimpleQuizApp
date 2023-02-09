package com.example.quizapp

object Constants {

    const val USER_NAME = "user_name"
    const val CORRECT_ANSWERS = "correct_answers"
    const val TOTAL_QUESTIONS = "total_questions"


    fun getQuestions(): ArrayList<Question> {

        val questions = ArrayList<Question>()

        val q1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Germany", "Canada",
            "Argentina", "Lebanon", 1
        )
        questions.add(q1)

        val q2 = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Fiji", "India",
            "Australia", "USA", 3
        )
        questions.add(q2)

        val q3 = Question(
            3,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "United Arab Emirates", "Saudi Arabia",
            "Iraq", "Kuwait", 4
        )
        questions.add(q3)

        //Q4
        val q4 = Question(
            4,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Brazil", "Denmark",
            "Peru", "Argentina", 1
        )
        questions.add(q4)


        //Q5
        val q5 = Question(
            5,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Australia", "New zealand",
            "United Kingdom", "USA", 2
        )
        questions.add(q5)


        //Q6
        val q6 = Question(
            6,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "UAE", "Somalia Land",
            "Fiji", "Kuwait", 3
        )
        questions.add(q6)


        //Q7
        val q7 = Question(
            7,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina", "Peru",
            "Sudan", "Iran", 1
        )
        questions.add(q7)


        //Q8
        val q8 = Question(
            8,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Iran", "Iraq",
            "India", "Turkey", 3
        )
        questions.add(q8)


        //Q9
        val q9 = Question(
            9,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Germany", "Armenia",
            "Belgium", "South Africa", 3
        )
        questions.add(q9)


        return questions
    }
}