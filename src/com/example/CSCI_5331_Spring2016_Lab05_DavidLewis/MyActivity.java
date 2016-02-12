package com.example.CSCI_5331_Spring2016_Lab05_DavidLewis;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import org.w3c.dom.Text;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    AndGate A1;
    OrGate O1;
    NotGate N1A,N1B,N1C; // Each input get's their own Not gate.
    HalfAdder H1;
    FullAdder F1;


    boolean inA, inB, inC;  // booleans to correspond to it's CheckBox i.e inA = checkBoxA etc...
    String strX = "X=?", strY = "Y=?", strZ = "Z=?"; // Strings for the texView XML object.

    RadioButton radBtnAnd, radBtnOr, radBtnNot, radBtnHa, radBtnFa;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // CheckBoxes for the input. final keyword cause it's Readonly.
        final CheckBox inputA = (CheckBox)findViewById(R.id.chkBoxA);
        final CheckBox inputB = (CheckBox)findViewById(R.id.chkBoxB);
        final CheckBox inputC = (CheckBox)findViewById(R.id.chkBoxC);

        // since we have the checkBoxes, let's go ahead and grab those values. Checked = true = 1; NotChecked = false = 0
        inA = inputA.isChecked();
        inB = inputB.isChecked();
        inC = inputC.isChecked();

        // RadioButtons to decide what gate to use.
        radBtnAnd = (RadioButton)findViewById(R.id.radBtnAnd);
        radBtnOr = (RadioButton)findViewById(R.id.radBtnOr);
        radBtnNot = (RadioButton)findViewById(R.id.radBtnNot);
        radBtnHa = (RadioButton)findViewById(R.id.radBtnHa);
        radBtnFa = (RadioButton)findViewById(R.id.radBtnFa);



        // Text Views to change the output. Not final cause we want to Read and Write.
        TextView txtX = (TextView)findViewById(R.id.txtX);
        TextView txtY = (TextView)findViewById(R.id.txtY);
        TextView txtZ = (TextView)findViewById(R.id.txtZ);

        // setting text to initialize
        txtX.setText(strX);
        txtY.setText(strY);
        txtZ.setText(strZ);


        //Somethings gotta kick things into gear?
       final Button Start = (Button)findViewById(R.id.btnGo);

    }

    public void doWork() {
        if (radBtnAnd.isChecked()) // working with And Gate..
        {
            A1 = new AndGate();
            if(inA && inB) // A and B
                A1.setInputs(inA,inB);
            else if(inA && inC) // A and C
                A1.setInputs(inA,inC);
            else if(inB && inC) // B and C..
                A1.setInputs(inB, inC);
            else
                A1.setInputs(inA,inB,inC); // either just A,B,orC(should return false) or all three(return true)
        }
        else if(radBtnOr.isChecked())
        {
            O1 = new OrGate();
            // essentially the same code as the and...
            if(inA || inB)
                O1.setInputs(inA,inB);
            else if(inA || inC)
                O1.setInputs(inA,inC);
            else if(inB || inC)
                O1.setInputs(inB,inC);
            else
                O1.setInputs(inA,inB,inC);
        }
        else if(radBtnNot.isChecked())
        {
            if(inA)
            {   N1A = new NotGate();
                N1A.setInputs(inA);
            }
            if(inB)
            {
                N1B = new NotGate();
                N1B.setInputs(inB);
            }
            if(inC)
            {
                N1C = new NotGate();
                N1C.setInputs(inC);
            }
        }
        else if(radBtnHa.isChecked())
        {
            // same exact code as AndGate
            H1 = new HalfAdder();
            if(inA && inB)
                H1.setInput(inA,inB);
            else if(inA && inC)
                H1.setInput(inA,inC);
            else if(inB && inC)
                H1.setInput(inB,inC);
            else
                H1.setInputs(inA,inB,inC);
        }
        else if(radBtnFa.isChecked())
        {
            // The easiest one of them all
            // SHIT!, WHY CAN'T THEY ALL BE LIKE THE FULL ADDER
            F1 = new FullAdder();
            F1.setInputs(inA,inB,inC);
        }
    }
}
