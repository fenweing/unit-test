//package com.tuanbaol.unittest;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//@Component
//public class MockitoBeansPostProcessor implements BeanFactoryPostProcessor {
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        UserMapper bean = beanFactory.getBean(UserMapper.class);
//        beanFactory.destroyBean(UserMapper.class);
//        beanFactory.registerResolvableDependency(UserMapper.class,new UserMapper());
//         bean = beanFactory.getBean(UserMapper.class);
////
////        Map<Class<?>, MockitoBeansTestExecutionListener.MockBeanWrapper> allMockBeans = MockitoBeansTestExecutionListener.resolvedAllMockBeans();
////        for (Map.Entry<Class<?>, MockitoBeansTestExecutionListener.MockBeanWrapper> mockBeanWrapperEntry : allMockBeans.entrySet()) {
////            beanFactory.registerResolvableDependency(mockBeanWrapperEntry.getKey(), mockBeanWrapperEntry.getValue().getMockObject());
////        }
//    }
//
//}
