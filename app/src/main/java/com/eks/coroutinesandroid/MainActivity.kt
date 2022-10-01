package com.eks.coroutinesandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.DummyText)
        val button: Button = findViewById(R.id.btnStartActivity)

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

        /* ***Part 4***
        // blocks the thread, using delay blocks the thread here
        // useful in testing with JUnit for accessing suspend functions
        Log.d(TAG,"Before runBlocking")
        runBlocking {
            Log.d(TAG,"While runBlocking")
            delay(1000L)
            Log.d(TAG,"End runBlocking")
        }
        Log.d(TAG,"After runBlocking")
        */

        /* ***Part 5***
        val job:Job = GlobalScope.launch(Dispatchers.Default) {
            repeat(6){
                Log.d(TAG, "Coroutine is working...")
                delay(100L)
            }
        }
        runBlocking {
            job.join() // blocks thread until coroutine is finished
            job.cancel()
            // cancels the job, can add cancellation exception,
            // sometimes coroutines are too busy with calcs that they don't check for cancellation, can use "if(isActive) to check manually
            // better to use withTimeout(){} to cancel properly
            Log.d(TAG, "Main thread is continuing...")
        }

         */

        /* ***Part 6***
        // TOO SLOW
        //        GlobalScope.launch(Dispatchers.IO) {
        //            val time = measureTimeMillis {
        //                val req1 = fakeNetworkCall1()
        //                val req2 = fakeNetworkCall2()
        //                Log.d(TAG,"Answer 1 is $req1")
        //                Log.d(TAG,"Answer 2 is $req2")
        //            }
        //            Log.d(TAG,"Req took $time")
        //        }
        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val answer2 = async { fakeNetworkCall1() }
                val answer1 = async { fakeNetworkCall2() }
                Log.d(TAG, "Answer 1 is ${answer1.await()}") // waits for the deferred to arrive
                Log.d(TAG, "Answer 2 is ${answer2.await()}")
            }
            Log.d(TAG, "Req took $time ms")
        }*/

        /* ***Part 7***
        // lifeCycleScope and viewModelScope are the ones used most instead of GlobalScope

        // What happens here is that using GlobalScope the main activity is destroyed but the coroutine is still running the loop
        // To slove this we use lifecycleScope (linked to activities) / viewModel (linked to viewModel)
        button.setOnClickListener {
            lifecycleScope.launch {
                while (true) {
                    delay(1000L)
                    Log.d(TAG, "running loop")
                }
            }
            GlobalScope.launch {
                delay(3000L)
                Intent(this@MainActivity, SecondActivity::class.java).also {
                    startActivity(it)
                    finish()
                    Log.d(TAG, "start activity")
                }
            }
        }*/

    }
}

suspend fun fakeNetworkCall1(): String {
    delay(3000L)
    return "Network call 1 done"
}

suspend fun fakeNetworkCall2(): String {
    delay(3000L)
    return "Network call 2 done"
}