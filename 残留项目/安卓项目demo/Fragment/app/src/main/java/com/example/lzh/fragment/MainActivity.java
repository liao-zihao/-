package com.example.lzh.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Button button=(Button)findViewById(R.id.button);
        //button.setOnClickListener(this);
        //replaceFragment(new RightFragment());
    }

//    public void onClick(View v){
//        switch(v.getId()){
//            case R.id.button:
//                replaceFragment(new AnotherRightFragment());
//                break;
//            default:
//                break;
//        }
//    }

//    public void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transcation = fragmentManager.beginTransaction();
//        transcation.replace(R.id.right_layout,fragment);
//        transcation.addToBackStack(null);
//        transcation.commit();
//    }
}
