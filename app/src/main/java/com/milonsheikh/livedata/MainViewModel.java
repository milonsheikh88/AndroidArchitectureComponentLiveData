package com.milonsheikh.livedata;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class MainViewModel extends ViewModel {
    private CountDownTimer timer;
    private MutableLiveData<Integer> secondValue= new MutableLiveData<>();

    public long startTime;

    public void startCountDown() {
        System.out.println("=== Start count down ===> ");
        timer = new CountDownTimer(startTime*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                long s = millisUntilFinished / 1000;
                System.out.println("Second Finished===> "+s);
                if(s <1){
                    onFinish();
                }else {
                    secondValue.setValue((int) s);
                }
            }
            public void onFinish() {
                secondValue.setValue(0);
                timer.cancel();
            }
        }.start();
    }

    public MutableLiveData<Integer> returnSecond() {
        return secondValue;
     }

    public void stopCountDown() {
        System.out.println("=== Stop count down ===");
        timer.cancel();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        System.out.println("=== ViewModel Destroyed ===");
    }

}
