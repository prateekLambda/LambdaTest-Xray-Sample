package com.lambdatest.xray;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A Custom Annotation to inject additional information into a TestNG Test
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Xray {

    String requirement() default "";

    String test() default "";

    String labels() default "";

}