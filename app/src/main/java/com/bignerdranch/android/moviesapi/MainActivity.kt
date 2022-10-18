package com.bignerdranch.android.moviesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity()
{
    private lateinit var  tv: TextView
    private lateinit var  btn: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById(R.id.textView)
        btn = findViewById(R.id.button)

        val executor: ExecutorService = Executors.newSingleThreadExecutor()

        btn.setOnClickListener{
            tv.text = executor.submit(Callable {
                httpRequest("http://numbersapi.com/11/23/date")
            }).get()
        }
    }

    @Throws(IOException::class)

    fun httpRequest(strUrl: String): String
    {
        val url = URL(strUrl)

        val connection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"

        var data: Int = connection.inputStream.read()
        var str = ""

        while(data != -1)
        {
            str += data.toChar()
            data = connection.inputStream.read()
        }
        return str
    }
}