package com.example.CSCI_5331_Spring2016_Lab05_DavidLewis;
/**
 * Created by David on 2/10/2016.
 * Not Gate to simulate the compliment from boolean logic
 * input1      output
 * 0            1
 * 1            0
 */

/****Note***: Vargas had a equals function that returned a boolean
 *            and a makeEquals function that took in an NotGate as a parameter.
 *            I couldn't see the use for these two functions so i didn't add them.
 */
public class NotGate {

    private boolean inputA;
    private boolean output;

    public NotGate()
    {
        // default...
    }

    public void setInputs(boolean inputA){this.inputA = inputA; execute();}
    public boolean getA(){return inputA;}
    public boolean getOutput(){return output;}
    private void execute(){output = !inputA;}
}
