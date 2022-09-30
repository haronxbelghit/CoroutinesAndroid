package com.eks.coroutinesandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val TAG = "MainActivity"

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

    }
}