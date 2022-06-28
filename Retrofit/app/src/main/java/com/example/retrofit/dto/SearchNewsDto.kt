package com.example.retrofit.dto

import com.example.retrofit.data.NewsData
import com.google.gson.annotations.SerializedName

data class SearchNewsDto (
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: Array<NewsData>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SearchNewsDto

        if (status != other.status) return false
        if (totalResults != other.totalResults) return false
        if (!articles.contentEquals(other.articles)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + totalResults
        result = 31 * result + articles.contentHashCode()
        return result
    }
}


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