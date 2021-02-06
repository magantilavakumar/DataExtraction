package com.data.extraction.aspect;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(ElementType.METHOD)
/**
 * @author magantilavakumar
 *
 */
public @interface Logit {

}
