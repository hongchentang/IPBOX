package com.hcis.datas;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.lang.reflect.Method;

/**
 * @author zhw
 * @date 2019/12/25
 **/
public class DateSourceAspect {

    /**
     * 拦截目标方法，获取由@DateSource指定的数据源标识，设置到线程存储中以便切换数据源
     * @param point
     * @throws Exception
     */
    public void intercept(JoinPoint point) throws Exception{

        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();

        //默认使用目标类型的注解，如果没有使用则使用其实现接口的注解
        for(Class<?> clazz : target.getInterfaces()){
            resolveDataSource(clazz, signature.getMethod());
        }

        resolveDataSource(target, signature.getMethod());
    }

    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            //默认使用类型注解
            if(clazz.isAnnotationPresent(DataSource.class)){
                DataSource source = clazz.getAnnotation(DataSource.class);
                DbContextHolder.setDataSource(source.value());
            }

            //方法注解可以覆盖类型注解
            Method m = clazz.getMethod(method.getName(), types);
            if(m != null && m.isAnnotationPresent(DataSource.class)){
                DataSource source = m.getAnnotation(DataSource.class);
                DbContextHolder.setDataSource(source.value());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void after(){
        DbContextHolder.cleanDataSource();
    }

}
