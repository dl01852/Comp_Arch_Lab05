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
    private boolean s;
    private boolean c;


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
        //print();
    }
    public boolean getA(){return a;}
    public boolean getB(){return b;}
    public boolean getC(){return c;}
    public boolean getS(){return s;}


    private void execute()
    {
        // this follow the particular adder given in the slides of ch.2
        o1.setInputs(a,b);
        a1.setInputs(a,b);
        a2.setInputs(o1.getOutput(),!a1.getOutput());
        s = a2.getOutput();
        c = a1.getOutput();
    }

    private void print()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("a:%d b:%d ",ctb(a),ctb(b)));
        sb.append(String.format("s:%d c:%d",ctb(s),ctb(c)));
        System.out.println(sb.toString());
    }

    private byte ctb(boolean input) // ctb = Convert To Binary.. just turn the falses to 0 and true's to 1s
    {
        return input?(byte)1:0;
    }
}
