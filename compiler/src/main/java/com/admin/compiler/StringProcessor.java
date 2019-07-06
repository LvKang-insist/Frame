package com.admin.compiler;

import com.admin.annotations.annotations.StaticString;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @author Lv
 * Created at 2019/7/2
 */
public class StringProcessor  extends AbstractProcessor {

    // Elements 代码程序的元素，例如 包，类，方法。
    private Elements elementUtils;
    // 处理 TypeMirror 的工具类，用于去类信息
    private Types typeUtils;
    // Filer 可以创建文件
    private Filer filer;
    // 错误处理工具
    private Messager messager;

    /**
     * 注解处理器的初始化
     * 一般在这里获取我们需要的工具类
     * @param processingEnv 提供工具类 Elements，types 和 filer
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        elementUtils = processingEnv.getElementUtils();
        typeUtils = processingEnv.getTypeUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    /**
     *  处理器实际 处理逻辑入口
     * @param annotations set
     * @param roundEnv 所有注解的集合
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        
        //如果没有其他处理器处理，则返回true
        return false;
    }

    /**
     * 指定 处理器是注册给那个注解的，返回指定支持的注解类集合，这是一个集合，意味着 支持多个类型的注解
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> sets = new LinkedHashSet<>();
        sets.add(StaticString.class.getCanonicalName());
        return sets;
    }

    /**
     * @return 指定Java版本，一般返回最新的版本即可
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
