package com.example.CSCI_5331_Spring2016_Lab05_DavidLewis;

/**
 * Created by David on 2/11/2016.
 * this simulates 2 particular Half adders to make a full adder.
 *
 */
public class FullAdder {

    private OrGate o1;
    private HalfAdder h1;
    private HalfAdder h2;
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean s;
    private boolean t;

    public FullAdder()
    {
        o1 = new OrGate();
        h1 = new HalfAdder();
        h2 = new HalfAdder();
    }
    public void setInputs(boolean a, boolean b, boolean c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        execute();
    }
    // these are quite useless to me as i did everything in house..
    public boolean getA(){return a;}
    public boolean getB(){return b;}
    public boolean getC(){return c;}
    public boolean getS(){return s;}
    public boolean gett(){return t;}


    public void execute()
    {
        h1.setInput(b,c);
        h2.setInput(a,h1.getS());
        s = h2.getS();
        o1.setInputs(h2.getC(), h1.getC());
        t = o1.getOutput();
    }

    private void print()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("a:%d b:%d c:%d ",ctb(a),ctb(b),ctb(c)));
        sb.append(String.format("s:%d t:%d",ctb(s),ctb(t)));
        System.out.println(sb.toString());
    }

    private byte ctb(boolean input) // ctb = Convert To Binary.. just turn the falses to 0 and true's to 1s
    {
        return input?(byte)1:0;
    }
}
