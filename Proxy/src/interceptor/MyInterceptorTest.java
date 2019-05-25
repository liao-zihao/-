package interceptor;

public class MyInterceptorTest {
    public static void main(String[] args)
    {
        HelloWorld proxy = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImp(),
                "interceptor.MyInterceptor");
        proxy.sayHello();
    }
}
