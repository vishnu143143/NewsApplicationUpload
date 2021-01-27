package com.localshorts.newsapplication.Model.NewsApiModels

class ThirdModel {
    var author:String? = null
    var title:String? = null
    var description:String? = null
    var urlToImage:String? = null
    var content:String? = null
    var url:String? = null
    constructor(author:String?,title:String?,description:String?,urlToImage:String?,content:String?,url: String?)
    {
        this.author = author
        this.title = title
        this.description = description
        this.urlToImage = urlToImage
        this.content = content
        this.url = url
    }

}