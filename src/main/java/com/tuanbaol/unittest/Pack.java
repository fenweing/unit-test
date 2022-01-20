package com.tuanbaol.unittest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE})
public @interface Pack {
    int num();
}
