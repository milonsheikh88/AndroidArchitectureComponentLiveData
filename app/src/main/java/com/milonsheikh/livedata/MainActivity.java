package com.milonsheikh.livedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvNumber;
    private EditText etInput;
    private Button btnStart, btnStop;

    private LiveData<Integer> myCountdown;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumber=findViewById(R.id.tv_number);
        etInput=findViewById(R.id.et_input);
        btnStart=findViewById(R.id.button_1);
        btnStop=findViewById(R.id.button_2);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Observe the LiveData
        myCountdown=viewModel.returnSecond();
        myCountdown.observe(this,countDownObserver);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etInput.getText().toString().length()>0){
                    viewModel.startTime=(Long.valueOf(etInput.getText().toString()));
                    viewModel.startCountDown();
                }else{
                    Toast.makeText(MainActivity.this,
                            "Please enter count down number",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.stopCountDown();
            }
        });
    }

    private final Observer<Integer> countDownObserver = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable final Integer newValue) {
            // Update the UI
            tvNumber.setText(newValue.toString());
            System.out.println( "=== Data Updated in UI ===");
        }
    };
}