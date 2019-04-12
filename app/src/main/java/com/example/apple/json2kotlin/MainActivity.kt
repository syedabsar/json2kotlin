package com.example.apple.json2kotlin

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        table_main.layoutManager = LinearLayoutManager(this)

        //TODO:- Binding the adapter
//        table_main.adapter = FruitViewAdapter()

        //TODO:- Task
        val url = "http://10.0.2.2:80/json2kotlin/fruits.json"
        Task().execute(url)
    }

    //TODO:- URLConnection for http get request.
    inner class Task:AsyncTask<String,String,String>(){

        override fun doInBackground(vararg url: String): String? {

            var jsonString:String? = null
            val urlConnection = URL(url.first()).openConnection() as HttpURLConnection

            try {

                urlConnection.connect()
                jsonString = urlConnection.inputStream.bufferedReader().readText()

            }catch (e:Exception){
                print(e.printStackTrace())
            }finally {
                urlConnection.disconnect()
            }

            return  jsonString
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            if(result != null){

                //TODO:- Parse JSON objects from JSON String by using Plain Kotlin maping
//                parseByPlainKotlinMapping(result)

                //TODO:- Parse JSON objects from JSON String by using Gson
                parseByUsingGJson(result)
            }
        }

    }

    //TODO:- Bind values from json string to cells by using of www.json2kotlin.com


    private  fun parseByPlainKotlinMapping(fromString: String){

        val jsonArray = JSONObject(fromString).getJSONArray("fruits")

        var list = ArrayList<Fruits>()

        for (x in 0 until jsonArray.length()){

            val jsonObject = jsonArray.getJSONObject(x)

            val fruitName = jsonObject.getString("name")
            val fruitUses = jsonObject.getString("uses")

            list.add(Fruits(fruitName,fruitUses))

        }

        //TODO:- Binding cell_fruit to table_main by using Adapter class

        table_main.adapter = FruitViewAdapter(list)
    }


    private  fun parseByUsingGJson(fromString:String){


        //TODO:- Binding cell_fruit to table_main by using Adapter class

        val topic = Gson().fromJson(fromString, Json4Kotlin_Base::class.java)

        table_main.adapter = FruitViewAdapter(topic.fruits)

    }
}
