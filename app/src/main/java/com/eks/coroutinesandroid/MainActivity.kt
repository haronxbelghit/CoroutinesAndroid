package com.eks.coroutinesandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "MainActivity"
        // GlobalScope means this Coroutine will live as long as the
        // app does
        GlobalScope.launch {
            delay(3000L) // this is like sleep of a thread, except it only pauses the work of the coroutine not the entire thread
            Log.d(TAG, "Coroutine saying hello from thread ${Thread.currentThread().name}") //Coroutine saying hello from thread DefaultDispatcher-worker-2
        }
        Log.d(TAG, "Hello from thread ${Thread.currentThread().name}") //Hello from thread main
    }
}