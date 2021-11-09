package com.lyl.testobject;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * * @Description
 * * @Author 刘亚林
 * * @CreateDate 2021/11/9
 * * @Version 1.0
 * * @Remark TODO
 **/
@Retention(RUNTIME)
@Target(FIELD)
public @interface TestListType {
    Class listType();
}
