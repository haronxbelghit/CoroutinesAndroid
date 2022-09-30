package com.eks.coroutinesandroid

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.DummyText) as TextView

        /*  ***Part 1***


        // GlobalScope means this Coroutine will live as long as the
        // app does
        GlobalScope.launch {
            delay(3000L) // this is like sleep of a thread, except it only pauses the work of the coroutine worker not the entire thread
            Log.d(TAG, "Coroutine saying hello from thread ${Thread.currentThread().name}") //Coroutine saying hello from thread DefaultDispatcher-worker-2
        }
        Log.d(TAG, "Hello from thread ${Thread.currentThread().name}") //Hello from thread main

         */

        /* ***Part 2***
        GlobalScope.launch {
            delay(1000L) // this is a suspend func so it can only be executed into another suspend function or a coroutine
            val fakeNetworkCallAnswer = fakeNetworkCall()
            val fakeNetworkCallAnswer2 = fakeNetworkCall2()
            // The answer to log is delayed by 6ms cuz the 2 delays add up cuw they are ran inside the same coroutine
            Log.d(TAG, fakeNetworkCallAnswer)
            Log.d(TAG, fakeNetworkCallAnswer2)
        }

    }
    suspend fun fakeNetworkCall(): String {
        delay(3000L)
        return "Network call done"
    }
    suspend fun fakeNetworkCall2(): String {
        delay(3000L)
        return "Network call done"
    }
    */

        /* ***Part 3***
        //Dispatchers.main : Useful for Ui related stuff and cuz UI can only be changed from the main thread
        //Dispatchers.IO   : Useful for data related stuff: network ops, database ops, r/w on files
        //Dispatchers.Default   : Useful for complex and long calculations
        //Or we can start a new thread using newSingleThreadContext("Name")
        //Useful thing is that the context can be switched from inside the coroutine
        // For example, we shouldn't make Network requests from the main thread but we can't update the ui from outside the main thread, switching context is the solution

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Starting in thread ${Thread.currentThread().name}")
            val fakeNetworkCallAnswer = fakeNetworkCall()
            //after getting the "network" answer we can switch context
            withContext(Dispatchers.Main){
                Log.d(TAG, "Ui update thread ${Thread.currentThread().name}")
                textView.text = fakeNetworkCallAnswer
            }
        }
        */

    }

    suspend fun fakeNetworkCall(): String {
        delay(3000L)
        return "Network call done"
    }
}