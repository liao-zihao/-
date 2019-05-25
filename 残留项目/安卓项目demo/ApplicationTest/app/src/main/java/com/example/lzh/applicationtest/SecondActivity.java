package com.example.lzh.applicationtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return","Hello firstactivity");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("data_return","Hello fic");
        setResult(RESULT_OK,intent);
        finish();
    }
}
