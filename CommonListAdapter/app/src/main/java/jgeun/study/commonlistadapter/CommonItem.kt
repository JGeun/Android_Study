package jgeun.study.commonlistadapter

import jgeun.study.commonlistadapter.my.ViewObject

/* 모든 유형의 Item은 viewType, viewObject 형태로 제공됩니다 */
data class CommonItem(
    val viewType: String,
    val viewObject: ViewObject
)