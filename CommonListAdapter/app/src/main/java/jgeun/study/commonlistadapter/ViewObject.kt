package jgeun.study.commonlistadapter.my

/* viewType 종류에 따라 Object를 생성해줘야 합니다 */
sealed class ViewObject {

    data class OneImageViewObject(
        val imageVO: ImageVO
    ) : ViewObject()

    data class OneLineTextViewObject(
        val contents: String
    ) : ViewObject()

    data class TwoLineTextViewObject(
        val title: String,
        val contents: String
    ) : ViewObject()

}

data class ImageVO(
    val url: String,
    val width: Int,
    val height: Int
)
