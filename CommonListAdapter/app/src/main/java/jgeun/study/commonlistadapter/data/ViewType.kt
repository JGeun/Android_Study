package jgeun.study.commonlistadapter.data

/* 새로운 뷰타입이 생길 때마다 업데이트 해야 합니다 */
enum class ViewType(viewType: String) {
    ONE_LINE_TEXT("ONE_LINE_TEXT"),
    TWO_LINE_TEXT("TWO_LINE_TEXT"),
    ONE_IMAGE("ONE_IMAGE")
}