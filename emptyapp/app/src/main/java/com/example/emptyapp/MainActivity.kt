package com.example.emptyapp
//package jp.co.casareal.fuel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText


import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class MainActivity : AppCompatActivity() {
    val EXTRA_MESSAGE: String = "com.example.emptyapp.MESSAGE"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /* Sendボタン押下時 */
    fun sendMessage(view: View) {
        val intent: Intent = Intent(this@MainActivity,
                DisplayMessageActivity::class.java)
        val editText: EditText = findViewById(R.id.editText) as EditText
        val message: String = editText.text.toString()
        val args: Array<String> = arrayOf("green", "red", "blue")
        main(args)
        //val result = getText("https://bsvnodeapi.herokuapp.com/generateaddress/test")
        //println(result)
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivity(intent)
    }

//    fun getText(url: String): String {
//        val (_, _, result) = url.httpGet().responseString()
//
//        return when (result) {
//            is Result.Failure -> {
//                println("failed")
//                val ex = result.getException()
//                println(ex.toString())
//                ex.toString()
//            }
//            is Result.Success -> {
//                result.get()
//            }
//        }
//    }

    fun main(args: Array<String>) {

        val httpAsync = "https://httpbin.org/get"
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println("failed request")
                        println(ex)
                    }
                    is Result.Success -> {
                        val data = result.get()
                        println(data)
                    }
                }
            }

        httpAsync.join()
    }

//    fun main(args: Array<String>) {
//
//        // 非同期処理
//        val baseUrl = "https://bsvnodeapi.herokuapp.com"
//        val generateAddressTest = baseUrl + "/generateaddress/test"
//        "https://bsvnodeapi.herokuapp.com/generateaddress/test".httpGet().response { request, response, result ->
//            when (result) {
//                is Result.Success -> {
//                    // レスポンスボディを表示
//                    println("非同期処理の結果：" + String(response.data))
//                }
//                is Result.Failure -> {
//                    println("通信に失敗しました。")
//                }
//            }
//        }
//
//        // 同期処理
//        val triple = "https://bsvnodeapi.herokuapp.com/generateaddress/test".httpGet().response()
//        // レスポンスボディを表示
//        println("同期処理の結果：" + String(triple.second.data))
//
//        val triple1 = "https://www.casareal.co.jp/".httpGet().response()
//        // レスポンスボディを表示
//        println("同期処理の結果：" + String(triple1.second.data))
//    }
}
