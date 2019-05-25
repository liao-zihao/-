package com.example.lzh.fragmentbestpractice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane;
    /*第一步：onCreateView*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        /*确定加载哪个模型（main里面）*/
        View view = inflater.inflate(R.layout.news_title_frag,container,false);
        /*再加载滚动模型*/
        RecyclerView newsTitleRecyclerView = (RecyclerView)view.findViewById(R.id.news_title_recycler_view);
        /*给一个manager*/
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        /*把manager交给滚动模型*/
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        /*加载一个适配器，包含显示的数据*/
        NewsAdapter adapter=new NewsAdapter(getNews());
        /*把数据给与显示*/
        newsTitleRecyclerView.setAdapter(adapter);
        /*返回显示*/
        return view;
    }

    private List<News> getNews(){
        List<News> newsList = new ArrayList<>();
        for(int i=1;i<=50;i++){
            News news= new News();
            news.setTitle("This is new title "+i);
            news.setContent(getRandomLengthContent("This is a content"+i));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String content){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i =0;i<length;i++){
            builder.append(content);
        }
        return builder.toString();
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane=true;
        }else isTwoPane=false;
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        /*显示的数据*/
        private List<News> mNewsList;
        /*viewholder必须有，里面有具体的显示方式*/
        class ViewHolder extends  RecyclerView.ViewHolder{
            TextView newsTitleText;

            public ViewHolder(View view){
                super(view);
                newsTitleText=(TextView)view.findViewById(R.id.news_title);
            }
        }

        public NewsAdapter(List<News> newsList){
            mNewsList=newsList;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            /*加载news_item的显示*/
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            /*交给viewholder*/
            final ViewHolder holder=new ViewHolder(view);
            /*点击事件发生器*/
            view.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    /*告知点击的位置*/
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if(isTwoPane){
                        /*开始不会显示，点击之后才会显示*/
                        NewsContentFragment newsContentFragment=(NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(),news.getContent());
                    }else{
                        /*启动显示内容的活动，并传递所需的信息*/
                        NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return holder;
        }
        /*固定写法，不懂什么意思*/
        public void onBindViewHolder(ViewHolder holder,int position){
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        public int getItemCount(){
            return mNewsList.size();
        }
    }
}
