package com.vishnu.assignmenttwo.Models

class ThirdModel {

    var apikey:String
    var name:String
    var cuisines:String
    var average_cost_for_two:String
    var featured_image:String
    constructor(apikey:String, name:String, cuisines:String, average_cost_for_two:String,featured_image:String){
        this.name=name
        this.apikey=apikey
        this.cuisines=cuisines
        this.average_cost_for_two=average_cost_for_two
        this.featured_image=featured_image
    }
}