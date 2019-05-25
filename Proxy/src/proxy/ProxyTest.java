package proxy;

public class ProxyTest {
    public static void main(String[] args){
        testJdkProxy();
    }
    public static void testJdkProxy(){
        JdkProxy jdk = new JdkProxy();
        HelloWorld proxy = (HelloWorld)jdk.bind(new HelloWorldImp());
        proxy.sayHello();
    }
}
