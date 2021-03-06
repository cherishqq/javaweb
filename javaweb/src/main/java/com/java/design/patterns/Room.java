package com.java.design.patterns;

/*
 * AbstractFactory
 */
public abstract  class Room  {
    public abstract Wall makeWall();
    public abstract Door makeDoor();
}