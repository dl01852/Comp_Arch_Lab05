package com.example.CSCI_5331_Spring2016_Lab05_DavidLewis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    AndGate A1;
    OrGate O1;
    NotGate N1A,N1B,N1C; // Each input get's their own Not gate.
    HalfAdder H1;
    FullAdder F1;

    boolean checkA = false;
    boolean checkB = false;
    boolean checkC = false;


    boolean inA, inB, inC;  // booleans to correspond to it's CheckBox i.e inA = checkBoxA etc...
    String strA = "A=0", strB = "B=0", strC = "C=0", strS = "S=?", strT = "T=?"; // Strings for the texView XML object.

    TextView txtA, txtB, txtC,txtS,txtT;
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
        //inA = inputA.isChecked();
        //inB = inputB.isChecked();
        //inC = inputC.isChecked();

        // RadioButtons to decide what gate to use.
        radBtnAnd = (RadioButton)findViewById(R.id.radBtnAnd);
        radBtnOr = (RadioButton)findViewById(R.id.radBtnOr);
        radBtnNot = (RadioButton)findViewById(R.id.radBtnNot);
        radBtnHa = (RadioButton)findViewById(R.id.radBtnHa);
        radBtnFa = (RadioButton)findViewById(R.id.radBtnFa);



        // Text Views to change the output. Not final cause we want to Read and Write.
         txtA = (TextView)findViewById(R.id.txtA);
         txtB = (TextView)findViewById(R.id.txtB);
         txtC = (TextView)findViewById(R.id.txtC);
         txtS = (TextView)findViewById(R.id.txtS); // S is for the result for AND/OR/NOT gates.
         txtT = (TextView)findViewById(R.id.txtT); // T along with S is for the result of the Half/Full Adder

        // setting text to initialize
        txtA.setText(strA);
        txtB.setText(strB);
        txtC.setText(strC);
        txtS.setText(strS);
        txtT.setText(strT);


        //Somethings gotta kick things into gear?
       final Button Start = (Button)findViewById(R.id.btnGo);

        Start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                inA = inputA.isChecked();
                inB = inputB.isChecked();
                inC = inputC.isChecked();
                if(radBtnAnd.isChecked())
                {
                    if ((inA && inB) || (inA && inC) || (inB && inC))
                        strS = Boolean.toString(AndGateWork(true, true));
                    else if(inA && inB && inC)
                        strS = Boolean.toString(AndGateWork(true, true, true));
                    else
                        strS = Boolean.toString(AndGateWork(true,false));

                    txtS.setText(strS);
                }
                else if(radBtnOr.isChecked()) {
                    if ((inA || inB) || (inA || inC) || (inB || inC))
                        strS = Boolean.toString(OrGateWork(true, true));
                    else
                        strS = Boolean.toString(OrGateWork(false, false));

                    txtS.setText(strS);
                }

                else if(radBtnHa.isChecked())
                {
                    HalfAdderWork(inA, inB, inC);
                    txtS.setText(strS);
                    txtT.setText(strT);
                }


            }
        });

    }

    // Handle AndGates for 2 inputs and 3 inputs...
    public boolean AndGateWork(boolean inputA, boolean inputB)
    {
        boolean output;
        A1 = new AndGate();
        A1.setInputs(inputA,inputB);
        output = A1.getOutput();
        return output;
    }
    public boolean AndGateWork(boolean inputA, boolean inputB,boolean inputC)
    {
        A1 = new AndGate();
        A1.setInputs(inputA,inputB,inputC);
       return A1.getOutput();
    }
    public boolean OrGateWork(boolean inputA, boolean inputB)
    {
        boolean output;
        O1 = new OrGate();
        O1.setInputs(inputA,inputB);
        output = O1.getOutput();
        return output;
    }
    public void HalfAdderWork(boolean inputA, boolean inputB, boolean inputC)
    {

        H1 = new HalfAdder();
        H1.setInputs(inputA,inputB,inputC);
        strS = H1.getS()?"true":"false";
        strT = H1.getC()?"true":"false";
        txtS.setText(strS);
        txtT.setText(strT);
    }


    // This is to update the UI on the fly when you check a box.
    public void onCheckboxClicked(View view) // It must follow this signature. Kinda like a Delegate(in C#)
    {

        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId())
        {
            case R.id.chkBoxA:
                txtA.setText(checked?"A=1":"A=0");
                checkA = !checkA;
                break;
            case R.id.chkBoxB:
                txtB.setText(checked? "B=1":"B=0");
                checkB = !checkB;
                break;
            case R.id.chkBoxC:
                txtC.setText(checked? "C=1":"C=0");
                checkC = !checkC;
                break;
        }
        radBtnAnd.setEnabled(!(checkA && checkB && checkC));
        radBtnOr.setEnabled(!(checkA&&checkB&&checkC));
        radBtnHa.setEnabled(!(checkA&&checkB&&checkC));

    }

    public void doWork() {
        if (radBtnAnd.isChecked()) // working with And Gate..
        {
            A1 = new AndGate();
            if (inA && inB) // A and B
            {
                A1.setInputs(true, true);
                strA = Byte.toString(ctb(inA));
                strB = Byte.toString(ctb(inB));
            } else if (inA && inC) // A and C
            {
                A1.setInputs(true, true);
                strA = Byte.toString(ctb(inA));
                strC = Byte.toString(ctb(inC));
            } else if (inB && inC) // B and C..
            {
                A1.setInputs(true, true);
                strB = Byte.toString(ctb(inB));
                strC = Byte.toString(ctb(inC));
            } else {
                A1.setInputs(inA, inB, inC); // either just A,B,orC(should return false) or all three(return true)
                strA = Byte.toString(ctb(inA));
                strB = Byte.toString(ctb(inB));
                strC = Byte.toString(ctb(inC));
            }
            strS = Byte.toString(ctb(A1.getOutput())); // get the output of AndGate, Convert to 0 or 1 then to a string.
        } else if (radBtnOr.isChecked()) {
            O1 = new OrGate();
            // essentially the same code as the and...
            if (inA || inB) {
                O1.setInputs(inA, inB);
                strA = Byte.toString(ctb(inA));
                strB = Byte.toString(ctb(inB));
            } else if (inA || inC) {
                O1.setInputs(false, true);
                strA = Byte.toString(ctb(inA));
                strC = Byte.toString(ctb(inC));
            } else if (inB || false) {
                O1.setInputs(inB, inC);
                strB = Byte.toString(ctb(inB));
                strC = Byte.toString(ctb(inC));
            } else {
                O1.setInputs(false, false, false);
                strA = Byte.toString(ctb(inA));
                strB = Byte.toString(ctb(inB));
                strC = Byte.toString(ctb(inC));
            }

            strS = Byte.toString(ctb(O1.getOutput()));
        } else if (radBtnNot.isChecked()) {
            if (inA) {
                N1A = new NotGate();
                N1A.setInputs(inA);
                strA = Byte.toString(ctb(N1A.getOutput()));
            }
            if (inB) {
                N1B = new NotGate();
                N1B.setInputs(inB);
                strB = Byte.toString(ctb(N1B.getOutput()));
            }
            if (inC) {
                N1C = new NotGate();
                N1C.setInputs(inC);
                strC = Byte.toString(ctb(N1C.getOutput()));
            }
        } else if (radBtnHa.isChecked()) {
            // same exact code as AndGate
            H1 = new HalfAdder();
            if (inA && inB) {
                H1.setInput(true, true);
                strA = Byte.toString(ctb(inA));
                strB = Byte.toString(ctb(inB));
            } else if (inA && inC) {
                H1.setInput(true, true);
                strA = Byte.toString(ctb(inA));
                strC = Byte.toString(ctb(inC));
            } else if (inB && inC) {
                H1.setInput(true, true);
                strB = Byte.toString(ctb(inB));
                strC = Byte.toString(ctb(inC));
            } else {
                H1.setInputs(inA, inB, inC);
                strA = Byte.toString(ctb(inA));
                strB = Byte.toString(ctb(inB));
                strC = Byte.toString(ctb(inC));
            }
            strS = Byte.toString(ctb(H1.getS()));
            strT = Byte.toString(ctb(H1.getT()));
        } else if (radBtnFa.isChecked()) // The easiest one of them all
        {
            F1 = new FullAdder();
            F1.setInputs(inA, inB, inC);
            strA = Byte.toString(ctb(inA));
            strB = Byte.toString(ctb(inB));
            strC = Byte.toString(ctb(inC));
            strS = Byte.toString(ctb(F1.getS()));
            strT = Byte.toString(ctb(F1.gett()));
        }
    }

    private byte ctb(boolean input) // ctb = Convert To Binary.. just turn the falses to 0 and true's to 1s
    {
        return input?(byte)1:0;
    }
}
