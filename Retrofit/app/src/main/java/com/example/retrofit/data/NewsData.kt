package com.example.retrofit.data

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class NewsData (
    @SerializedName("source")
    val source: Source,
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("content")
    val content: String
)


/*
*
* status": "ok",
"totalResults": 2066,
-"articles": [
-{
-"source": {
"id": "engadget",
"name": "Engadget"
},
"author": "Mat Smith",
"title": "The Morning After: The FDA could ban Juul’s e-cigarettes",
"description": "The Food and Drug Administration is preparing to stop Juul from selling e-cigarette products in the US, and the decision could come soon, according to a report from The Wall Street Journal.Along with other e-cigarette makers, Juul submitted its products to th…",
"url": "https://www.engadget.com/the-morning-after-the-fda-could-ban-juuls-e-cigarettes-111550212.html",
"urlToImage": "https://s.yimg.com/os/creatr-images/2019-09/9f6e79c0-d4d9-11e9-ad18-5ea40103b7e1",
"publishedAt": "2022-06-23T11:15:50Z",
"content": "The Food and Drug Administration is preparing to stop Juul from selling e-cigarette products in the US, and the decision could come soon, according to a report from The Wall Street Journal.\r\nAlong wi… [+3454 chars]"
},*/