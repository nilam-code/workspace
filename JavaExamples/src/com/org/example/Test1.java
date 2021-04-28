package com.org.example;

/*
 * class A { void m1() { System.out.println("In m1 A"); } } class B extends A {
 * void m1() { System.out.println("In m1 B"); } void m2() {
 * System.out.println("In m2 B"); } } public class Test1 {
 * 
 * public static void main(String[] args) { A a=new B(); a.m2(); } }
 */

class A
{
    void m1() throws ArrayIndexOutOfBoundsException
    {
        System.out.println("In m1 A");
    }
}
class B extends A
{
    void m1() throws IndexOutOfBoundsException
    {
        System.out.println("In m1 B");
    }
}
public class Test1 {
 
    public static void main(String[] args) {
        A a=new B();
        a.m1();
    }
}