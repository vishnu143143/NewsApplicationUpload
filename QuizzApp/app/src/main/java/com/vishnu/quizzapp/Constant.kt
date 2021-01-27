package com.vishnu.quizzapp

object Constant{
    fun getQuestions() : ArrayList<Question>{
val questionsList= ArrayList<Question>()
var ques=Question(1,"Which Country do you belong?",R.drawable.ic_flag_of_argentina,

    "Argetina","Austrila","HongKong","China",1)
        questionsList.add(ques)
         ques=Question(1,"Which mother do you belong?",R.drawable.ic_flag_of_argentina,

            "Argetina","Austrila","HongKong","China",1)
        questionsList.add(ques)

        ques=Question(1,"Which dad do you belong?",R.drawable.ic_flag_of_argentina,

            "Argetina","Austrila","HongKong","China",1)
        questionsList.add(ques)
        ques=Question(1,"Which Country do you belong?",R.drawable.ic_flag_of_argentina,

            "Argetina","Austrila","HongKong","China",1)
        questionsList.add(ques)
        ques=Question(1,"Which lanch do you belong?",R.drawable.ic_flag_of_argentina,

            "Argetina","Austrila","HongKong","China",1)
        questionsList.add(ques)

        return  questionsList


    }
}