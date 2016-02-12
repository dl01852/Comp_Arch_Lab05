package com.example.CSCI_5331_Spring2016_Lab05_DavidLewis;
/**
 * Created by David on 2/10/2016.
 * Half Adder simulates the combination of
 * AndGates, OrGates and NotGates.
 *
 * This Particular HalfAdder takes in two booleans(a,b)
 *
 *
 *
 */
public class HalfAdder {

    private OrGate o1;
    private AndGate a1;
    private AndGate a2;
    private NotGate n1;
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean s;
    private boolean t;


    public HalfAdder()
    {
        // initialize all the objects when the constructor is created.
        // the last thing i want is a null pointer error...
        o1 = new OrGate();
        a1 = new AndGate();
        a2 = new AndGate();
        n1 = new NotGate();
    }
    public void setInput(boolean a, boolean b)
    {
        this.a = a;
        this.b = b;
        execute();
    }

    public void setInputs(boolean a, boolean b, boolean c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        executeTrice();
    }

    public boolean getA(){return a;}
    public boolean getB(){return b;}
    public boolean getC(){return c;}
    public boolean getS(){return s;}
    public boolean getT(){return t;}


    private void executeTrice()
    {
        o1.setInputs(a,b,c);
        a1.setInputs(a,b,c);
        a2.setInputs(o1.getOutput(),!a1.getOutput());
        s = a2.getOutput();
        t = a1.getOutput();
    }

    private void execute()
    {
        // this follow the particular adder given in the slides of ch.2
        o1.setInputs(a,b);
        a1.setInputs(a,b);
        a2.setInputs(o1.getOutput(),!a1.getOutput());
        s = a2.getOutput();
        t = a1.getOutput();
    }
}
