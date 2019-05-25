package interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InterceptorJdkProxy implements InvocationHandler
{
    private Object target;
    private String interceptorClass = null;

    public InterceptorJdkProxy(Object target,String interceptorClass)
    {
        this.target=target;
        this.interceptorClass=interceptorClass;
    }

    public static Object bind(Object target,String interceptorClass)
    {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new InterceptorJdkProxy(target,interceptorClass));
    }

    public Object invoke(Object proxy, Method method,Object[]args)throws  Throwable
    {
        if(interceptorClass==null)
            return method.invoke(target,args);
        Object result=null;

        Interceptor interceptor=(Interceptor)Class.forName(interceptorClass).newInstance();

        if(interceptor.before(proxy,target,method,args)){
            result=method.invoke(target,args);
        }else{
            interceptor.around(proxy,target,method,args);
        }

        interceptor.after(proxy,target,method,args);
        return result;
    }
}
