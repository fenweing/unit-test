package com.tuanbaol.unittest;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.util.Assert;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MockitoBeansTestExecutionListener extends AbstractTestExecutionListener {
    private List<Class> mockAnno = Arrays.asList(Mock.class, Spy.class);
    private List<Class> autowiredAnno = Arrays.asList(Autowired.class);

//
//    @Override
//    public void prepareTestInstance(TestContext testContext) throws Exception {
//        Field userMapper = testContext.getTestClass().getDeclaredField("userMapper");
//        userMapper.setAccessible(true);
//        Object o = userMapper.get(testContext.getTestInstance());
//        DefaultListableBeanFactory defaultListableBeanFactory = ((GenericWebApplicationContext) testContext.getApplicationContext()).getDefaultListableBeanFactory();
////        defaultListableBeanFactory.registerResolvableDependency(UserMapper.class, o);
//
//        UserMapper bean = defaultListableBeanFactory.getBean(UserMapper.class);
//        System.out.println(bean.getClass());
//        defaultListableBeanFactory.destroySingleton("userMapper");
//
//        bean = defaultListableBeanFactory.getBean(UserMapper.class);
//        System.out.println(bean.getClass());
//
//        defaultListableBeanFactory.registerSingleton("userMapper", o);
//        bean = defaultListableBeanFactory.getBean(UserMapper.class);
//        System.out.println(bean.getClass());
//    }
//
    private Map<Class, Object> mockObjectMap = new HashMap<>();
    private List<Object> autowiredInstance = new ArrayList<>();

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        Object testInstance = testContext.getTestInstance();
        Field[] declaredFields = testContext.getTestClass().getDeclaredFields();
        //将需要mock的对象创建出来
        for (Field field : declaredFields) {
            if (hasAnno(mockAnno,field)) {
                field.setAccessible(true);
                mockObjectMap.put(field.getType(), field.get(testInstance));
            }
            if (hasAnno(autowiredAnno,field)) {
                field.setAccessible(true);
                autowiredInstance.add(field.get(testInstance));
            }
        }
        if (mockObjectMap.size() > 0 && autowiredInstance.size() > 0) {
            autowiredInstance.forEach(instance -> {
                Class<?> instanceClass = instance.getClass();
                Field[] instanceDeclaredFields = instanceClass.getDeclaredFields();
                Arrays.asList(instanceDeclaredFields).forEach(instanceField->{
                    Class<?> type = instanceField.getType();
                    if (hasAnno(autowiredAnno, instanceField) && mockObjectMap.containsKey(type)) {
                        instanceField.setAccessible(true);
                        try {
                            instanceField.set(instance,mockObjectMap.get(type));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
            });
        }
    }

    public boolean hasAnno(List<Class> container, Field field) {
        Annotation[] annotations = field.getAnnotations();
        long mockCount = Arrays.asList(annotations).stream().filter(anno -> container.contains(anno.annotationType())).count();
        return mockCount > 0;
    }

//    @Override
//    public void beforeTestMethod(TestContext testContext) throws Exception {
//        Object testInstance = testContext.getTestInstance();
//        List<Field> fields = injectMockBeans.get(testContext.getTestClass());
//        if (fields != null) {
//            for (Field field : fields) {
//                field.setAccessible(true);
//                field.set(testInstance, mockBeans.get(field.getType()).getMockObject());
//            }
//        }
//    }

}
