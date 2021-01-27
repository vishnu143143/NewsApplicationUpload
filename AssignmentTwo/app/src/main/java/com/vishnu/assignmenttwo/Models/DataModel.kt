package com.vishnu.assignmenttwo.Models

class DataModel {
    var link:String
    var nearby_restaurants:ArrayList<SecondModel>
    //    var data = ArrayList<SecondModel>()
//    constructor(data:ArrayList<SecondModel>){
//        this.data = data
//    }
    constructor(link:String, nearby_restaurants:ArrayList<SecondModel>){
        this.link=link
        this.nearby_restaurants=nearby_restaurants
    }
}