package com.lzh;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	public static void main(String[] args) throws Exception{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("beans.xml");
		Person p = ctx.getBean("person",Person.class);
		p.sayHi("nidie");
	}
}
