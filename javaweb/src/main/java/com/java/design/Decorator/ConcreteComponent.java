package com.java.design.Decorator;


import java.io.*;

public class ConcreteComponent implements Component {
    public ConcreteComponent() {
    }
    public void PrintString(String s) {
        System.out.println("Input String is:" + s);
    }
}