package com.example.lzh.applicationtest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        switch(requestCode){
            case 1:
                if(resultCode==RESULT_OK)  {
                    String returnedData = data.getStringExtra("data_return");
                    System.out.print(returnedData);
                    Log.d("FirstActivty",returnedData);
                }
                break;
            default:
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"you clicked Add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"you clicked Remove",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
