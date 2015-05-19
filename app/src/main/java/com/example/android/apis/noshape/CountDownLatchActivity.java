/**
 * Copyright (c) 2010, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.noshape;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.apis.R;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public class CountDownLatchActivity extends Activity {
    private static final int MAX_TASK = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_layout);
        try {
            countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void countDown() throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(MAX_TASK);

        for (int i = 0; i < MAX_TASK; ++i) // create and start threads
            new Thread(new Worker(startSignal, doneSignal, "ID:" + String.valueOf(i))).start();

        startSignal.countDown();      // let all threads proceed
        doSomethingElse("FIRST");            // don't let run yet
        doSomethingElse("SECOND");
    }

    void countDown01() throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(MAX_TASK);
        Executor e = new Executor() {
            @Override
            public void execute(Runnable command) {

            }
        };

        for (int i = 0; i < MAX_TASK; ++i) // create and start threads
            e.execute(new WorkerRunnable(doneSignal, i));

        doneSignal.await();           // wait for all to finish
    }

    class WorkerRunnable implements Runnable {
        private final CountDownLatch doneSignal;
        private final int i;
        WorkerRunnable(CountDownLatch doneSignal, int i) {
            this.doneSignal = doneSignal;
            this.i = i;
        }
        public void run() {
            try {
                doWork(i);
            } catch (Exception ex) {} // return;
        }

        void doWork(int i) {
            Log.d("CountDownLatchActivity", " doWork " + i);
        }
    }


    private void doSomethingElse(String what) {
        Log.d("CountDownLatchActivity", " doSomethingElse " + what);
    }

    class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        private String tag;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal, String tag) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
            this.tag = tag;
        }

        public void run() {
            try {
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException ex) {
            }
        }

        void doWork() {
            Log.d("CountDownLatchActivity", " doWork " + tag);
        }
    }
}
