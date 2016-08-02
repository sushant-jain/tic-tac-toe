package com.sushant.zerokata;

import android.os.AsyncTask;
import android.os.SystemClock;

/**
 * Created by Sushant on 06-07-2016.
 */
public class Waiter extends AsyncTask<String,Integer,Integer> {
    public interface Listener{
        void myfunc();
    }
    Listener listener;

    public Waiter(Listener listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        long t=SystemClock.uptimeMillis();
        while (SystemClock.uptimeMillis()-t<700);
        return null;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        listener.myfunc();
    }
}
