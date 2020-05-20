package com.example.emptyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        generateAddress()
        // Activity開始時にIntentを取得し、文字列をセットする
    }

    //https://www.yuulinux.tokyo/15220/
    data class GenerateAddress (
        var address: String,
        var privatekey_wif: String
    )

    fun generateAddress() {
        val httpAsync = "https://bsvnodeapi.herokuapp.com/generateaddress/test"
            .httpGet()
            .responseObject<MainActivity.GenerateAddress> { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println("failed request")
                        println(ex)
                        //val(generateAddress,err) = result
                    }
                    is Result.Success -> {
                        val(generateAddress, err) = result
                        println("generateaddress:${generateAddress}")

                        val intent: Intent = getIntent()
                        val textViewAddress: TextView = findViewById(R.id.textViewAddress)
                        val textViewPrivateKeyWif: TextView = findViewById(R.id.textViewPrivateKeyWif)
                        textViewAddress.setText(generateAddress?.address)
                        textViewPrivateKeyWif.setText(generateAddress?.privatekey_wif)
                        val editTextAddress: EditText = findViewById(R.id.editTextAddress) as EditText
                        editTextAddress.setText(generateAddress?.address)
                    }
                }
            }
    }
}
