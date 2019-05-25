package com.example.lzh.fragmentbestpractice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsContentActivity extends AppCompatActivity {

    public static void actionStart(Context context,String newsTitle,String newsContent){
        /*得到信息，构造intent并启动活动*/
        Intent intent = new Intent(context,NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }

    /*这个好像是必写*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*设置显示的内容为news_content*/
        setContentView(R.layout.news_content);
        String newsTitle = getIntent().getStringExtra("news_Title");
        String newsContent=getIntent().getStringExtra("news_content");
        /*把数据传给碎片运作*/
        /*碎片好像只能在layout之上*/
        /*感觉很麻烦*/
        NewsContentFragment newsContentFragment=(NewsContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle,newsContent);
    }
}
