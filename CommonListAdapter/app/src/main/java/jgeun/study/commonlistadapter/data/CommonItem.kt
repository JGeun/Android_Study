package jgeun.study.commonlistadapter.data

import jgeun.study.commonlistadapter.data.viewobject.ViewObject

/* 모든 유형의 Item은 viewType, viewObject 형태로 제공됩니다 */
data class CommonItem(
    val viewType: String,
    val viewObject: ViewObject
)