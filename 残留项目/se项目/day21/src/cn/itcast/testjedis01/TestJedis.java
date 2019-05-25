package cn.itcast.testjedis01;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestJedis {
	public static void main(String[] args) {
		//testJedis01();
		testJedis02();
	}
	
	
	
	//通过JEDIS实例操作redis
	static void testJedis01(){
		//创建Jedis对象
		Jedis jedis=new Jedis("192.168.238.129",6379);
		//向redis发送命令
		jedis.set("name", "张三");
		//获取redis中name键对应的值
		String v=jedis.get("name");
		//输出数据
		System.out.println(v);
		//释放资源
		jedis.close();
	}
	
	//通过Jedis池操作redis
	static void testJedis02(){
		//创建连接池的配置对象
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMaxIdle(10);
		//创建连接池
		JedisPool pool=new JedisPool(config, "192.168.238.129", 6379);
		
		//声明Jedis实例
		Jedis jedis =null;
		
		try {
			//通过连接池获取Jedis实例
			jedis = pool.getResource();
			//向redis发送命令
			jedis.set("name", "smith");
			//获取redis中name键对应的值
			String v=jedis.get("name");
			//输出数据
			System.out.println(v);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			//释放资源
			if(null!=jedis){
				jedis.close();
				jedis=null;
			}
			if(null!=pool){
				pool.close();
				pool=null;
			}
			
		}
		
		
		
	}
	
	
}
