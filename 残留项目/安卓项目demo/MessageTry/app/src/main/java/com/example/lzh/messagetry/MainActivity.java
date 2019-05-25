package com.example.lzh.messagetry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText inputText;

    private Button send;

    private RecyclerView msgRecyclerView;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsgs();
        inputText = (EditText)findViewById(R.id.input_text);
        send=(Button)findViewById(R.id.send);
        msgRecyclerView=(RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);

                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
            }
        });
    }

    public void initMsgs(){
        Msg mag1 = new Msg("Hello guy",Msg.TYPE_RECEIVED);
        msgList.add(mag1);
        Msg mag2 = new Msg("who r u ?",Msg.TYPE_SEND);
        msgList.add(mag2);
        Msg mag3 = new Msg("NICE TO TALKING TO U",Msg.TYPE_RECEIVED);
        msgList.add(mag3);

        msgList.add(mag1);
        msgList.add(mag2);
        msgList.add(mag3);
        msgList.add(mag1);
        msgList.add(mag2);
        msgList.add(mag3);
        msgList.add(mag1);
        msgList.add(mag2);
        msgList.add(mag3);
        msgList.add(mag1);
        msgList.add(mag2);
        msgList.add(mag3);
        msgList.add(mag1);
        msgList.add(mag2);
        msgList.add(mag3);
        msgList.add(mag1);
        msgList.add(mag2);
        msgList.add(mag3);
    }
}
