package com.localshorts.newsapplication.Model.NewsApiModels

class FirstModel {
    var status:String? = null
    var totalResults:Int?= 0
    var articles = ArrayList<ThirdModel>()
    constructor(status:String?,totalResults:Int?,articles:ArrayList<ThirdModel>){
        this.status = status
        this.totalResults =totalResults
        this.articles = articles
    }

}