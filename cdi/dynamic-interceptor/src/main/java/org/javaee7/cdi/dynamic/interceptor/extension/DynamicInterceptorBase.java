package org.javaee7.cdi.dynamic.interceptor.extension;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.enterprise.inject.spi.Interceptor;
import jakarta.enterprise.inject.spi.PassivationCapable;

/**
 * Default implementation of the Interceptor interface with all the boring defaults
 * 
 * @author Arjan Tijms
 *
 */
public abstract class DynamicInterceptorBase<T> implements Interceptor<T>, PassivationCapable {
 
    @Override
    public Set<Annotation> getQualifiers() {
        return emptySet();
    }
 
    @Override
    public Class<? extends Annotation> getScope() {
        return Dependent.class;
    }
 
    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return emptySet();
    }
 
    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return emptySet();
    }
 
    @Override
    public boolean isAlternative() {
        return false;
    }
 
    @Override
    public String getName() {
        return null;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T create(CreationalContext<T> creationalContext) {
        try {
            return (T) getBeanClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating an instance of " + getBeanClass());
        }
    }
    
    @Override
    public Set<Type> getTypes() {
        return new HashSet<Type>(asList(getBeanClass(), Object.class));
    }
    
    @Override
    public void destroy(T instance, CreationalContext<T> creationalContext) {
        creationalContext.release();
    }
    
    @Override
    public String getId() {
        return toString();
    }
}