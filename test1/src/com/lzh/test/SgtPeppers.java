package com.lzh.test;
import org.springframework.stereotype.Component;
import org.junit.Test;
@Component
public class SgtPeppers implements CompactDisc {
    private String title = "title";
    private String artlist = "artlist";

    public void play(){
        System.out.println(title+" "+artlist);
    }
}
