package jgeun.study.commonlistadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import jgeun.study.commonlistadapter.databinding.ActivityMainBinding
import jgeun.study.commonlistadapter.my.ImageVO
import jgeun.study.commonlistadapter.my.ViewObject
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val dataSet = ArrayList<CommonItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val jsonString = assets.open("data.json").reader().readText()
        Log.d("JSON STR", jsonString)

        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("viewItems")
        Log.d("JSON STR", jsonArray.toString())

        for(i in 0 until jsonArray.length()) {

            val jsonObj = jsonArray.getJSONObject(i)

            val viewType = jsonObj.getString("viewType")
            val viewObject: JSONObject = jsonObj.getJSONObject("viewObject")

            if(viewType.equals("ONE_LINE_TEXT")) {
                val titleText = viewObject.getString("titleText")
                dataSet.add(CommonItem(viewType, ViewObject.OneLineTextViewObject(titleText)))
            } else if (viewType.equals("TWO_LINE_TEXT")) {
                val titleText = viewObject.getString("titleText")
                val descText = viewObject.getString("descText")
                dataSet.add(CommonItem(viewType, ViewObject.TwoLineTextViewObject(titleText, descText)))
            } else {
                val imageVO = viewObject.getJSONObject("imageVO")
                val url = imageVO.getString("url")
                val width = imageVO.getString("width")
                val height = imageVO.getString("height")
                dataSet.add(CommonItem(viewType, ViewObject.OneImageViewObject(ImageVO(url, width.toInt(), height.toInt()))))
            }
        }

        binding.rvCommon.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CommonListAdapter(dataSet)
        }
    }
}