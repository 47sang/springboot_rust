package com.example.demo.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author 周士钰
 * @date 2024/11/29 下午8:06
 */
public interface RustDll extends Library {
    RustDll INSTANCE = Native.load("rustdll", RustDll.class);

    int addNumbers(int a, int b);

    String processString(String name);
    String greet(String name);
}
