package com.liuilin.JVM.OOM;

/**
 * @author liuqiang
 * @since 2021-08-10
 */
public class MetaspaceOOM {
    static class OOMTest{}

    public static void main(String[] args) {
        System.out.println("h1".hashCode());
    }
//    public static void main(String[] args) {
//        int i = 0;
//        try {
//            while (true) {
//                i++;
//                Enhancer enhancer = new Enhancer();
//                enhancer.setSuperclass(OOMTest.class);
//                enhancer.setUseCache(false);
//                enhancer.setCallback(new MethodInterceptor() {
//                    @Override
//                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                        return methodProxy.invokeSuper(o,args);
//                    }
//                });
//                enhancer.create();
//            }
//        } catch (Throwable e) {
//            // --- 多少次后发生了异常：540
//            // java.lang.OutOfMemoryError: Metaspace
//            System.out.println("--- 多少次后发生了异常："+i);
//            e.printStackTrace();
//        }
//    }
}
