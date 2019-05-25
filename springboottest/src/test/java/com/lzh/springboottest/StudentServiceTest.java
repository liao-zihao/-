package com.lzh.springboottest;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lzh.springboottest.bean.Student;
import com.lzh.springboottest.service.SchoolService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
	
	// 注入Service
	@Autowired
    private SchoolService studentService;

    @Test
    public void findOne() throws Exception {
    	 Student stu = studentService.selectByKey(1);
         Assert.assertThat(stu.getName(),CoreMatchers.is("孙悟空"));
    }
}
