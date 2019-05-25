package interceptor;

import java.lang.reflect.Method;

public class MyInterceptor implements Interceptor{
    @Override
    public boolean before(Object proxy, Object target, Method method,Object[]args){
        System.err.println("反射方法前逻辑");
        return false;
    }
    @Override
    public void after(Object proxy,Object target,Method method,Object[]args)
    {
        System.err.println("反射方法后逻辑");
    }
    @Override
    public void around(Object proxy,Object target,Method method,Object[]args)
    {
        System.err.println("取代了方法");
    }
}
