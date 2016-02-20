package com.example.CSCI_5331_Spring2016_Lab05_DavidLewis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    AndGate A1;
    OrGate O1;
    NotGate N1A,N1B,N1C; // Each input get's their own Not gate.
    HalfAdder H1;
    FullAdder F1;

    // these three booleans are for the checkBox onClickEvent handler.
    // I need a way of keeping track of if 'ALL' the checkBoxes are checked in order to disable radio buttons.
    boolean checkA = false;
    boolean checkB = false;
    boolean checkC = false;


    // these are useless now
   // boolean inA, inB, inC;  // booleans to correspond to it's CheckBox i.e inA = checkBoxA etc...
    String strA = "A=0", strB = "B=0", strC = "C=0", strS = "S=?", strT = "T=?"; // Strings for the texView XML object.

    // Controls of the UI.
    TextView txtA, txtB, txtC,txtS,txtT;
    RadioButton radBtnAnd, radBtnOr, radBtnNot, radBtnHa, radBtnFa;
    CheckBox inputA, inputB, inputC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        // CheckBoxes for the input.
         inputA = (CheckBox)findViewById(R.id.chkBoxA);
         inputB = (CheckBox)findViewById(R.id.chkBoxB);
         inputC = (CheckBox)findViewById(R.id.chkBoxC);

        // since we have the checkBoxes, let's go ahead and grab those values. Checked = true = 1; NotChecked = false = 0
        //inA = inputA.isChecked(); // disregard the initial comment i made, I was being a dumbAss... grabbing values
        //inB = inputB.isChecked(); // right after being initialized, will always be false. This caused a logic error
        //inC = inputC.isChecked();

        // RadioButtons to decide what gate to use.
        radBtnAnd = (RadioButton)findViewById(R.id.radBtnAnd);
        radBtnOr = (RadioButton)findViewById(R.id.radBtnOr);
        radBtnNot = (RadioButton)findViewById(R.id.radBtnNot);
        radBtnHa = (RadioButton)findViewById(R.id.radBtnHa);
        radBtnFa = (RadioButton)findViewById(R.id.radBtnFa);

        // Text Views to change the output.
         txtA = (TextView)findViewById(R.id.txtA);
         txtB = (TextView)findViewById(R.id.txtB);
         txtC = (TextView)findViewById(R.id.txtC);
         txtS = (TextView)findViewById(R.id.txtS); // S is for the result for AND/OR/NOT/Half Adder gates.
         txtT = (TextView)findViewById(R.id.txtT); // T along with S is for the result of the Full Adder


        // setting text to initialize
        txtA.setText(strA);
        txtB.setText(strB);
        txtC.setText(strC);
        txtS.setText(strS);
        txtT.setText(strT);


        //Somethings gotta kick things into gear?
       final Button Start = (Button)findViewById(R.id.btnGo);

        Start.setOnClickListener(new View.OnClickListener() { // button click handler.
            public void onClick(View v) {
                execute();
            }
        });

    }

    // The real work is here..
    public void execute()
    {
        if(radBtnAnd.isChecked()) // AndGate selected.
        {
            A1 = new AndGate();//initialize andGate.
            if ((inputA.isChecked() && inputB.isChecked()) || (inputA.isChecked() && inputC.isChecked()) //((A and B) or (A and C)
                    || (inputB.isChecked() && inputC.isChecked())) // OR (B and C))
                A1.setInputs(true,true);  // then set(true,true(
            else
                A1.setInputs(true,false); // else set(true,false)


            txtS.setText("S="+A1.getOutput()); // display the output.
        }
        else if(radBtnOr.isChecked()) // Or selected
        {
            O1 = new OrGate();
            if (inputA.isChecked() || inputB.isChecked() || inputC.isChecked()) // (A or B or C)
                O1.setInputs(true,false); // either set it to (true,true) or (true,false) or(false,true) doesn't matter...
            else
                O1.setInputs(false,false); // if nothing is selected... set(false,false)

            txtS.setText("S="+O1.getOutput()); // display output...
        }

        else if(radBtnHa.isChecked()) // Half adder selected
        {
            H1 = new HalfAdder();
            // (A and B) or (A and C) or (B and C) then setInput(true,true)
            if((inputA.isChecked() && inputB.isChecked()) || (inputA.isChecked() && inputC.isChecked())
                    || (inputB.isChecked() && inputC.isChecked()))
            {
                H1.setInput(true,true);
            }
            else if(inputA.isChecked() || inputB.isChecked()) //(A and(!B or !C)) OR (B and !notC) then Set(T,F)
            {   if((!inputB.isChecked() || !inputC.isChecked()))
                H1.setInput(true,false);
            }
            else if(!inputA.isChecked() || !inputB.isChecked()) //(!A and(B or C)) OR(!B and C) then set(F,T)
            {
                if(inputB.isChecked() || inputC.isChecked())
                {
                    H1.setInput(false,true);
                }
            }
            else // None of the above... set(F,F)
                H1.setInput(false,false);

            txtS.setText("S="+ctb(H1.getS()));
            txtT.setText("T="+ctb(H1.getC())); //change T to C and ABC to XYZ

        }
        else if(radBtnNot.isChecked()) // each input get's their own Not Gate so just pass in the values and get output.
        {
            N1A = new NotGate();
            N1B = new NotGate();
            N1C = new NotGate();

            N1A.setInputs(inputA.isChecked());
            N1B.setInputs(inputB.isChecked());
            N1C.setInputs(inputC.isChecked());

            txtA.setText("A="+(ctb(N1A.getOutput())));
            txtB.setText("B="+(ctb(N1B.getOutput())));
            txtC.setText("C="+(ctb(N1C.getOutput())));
        }
        else if(radBtnFa.isChecked()) // Full adder takes 3 inputs by default, so always pass in the three values.
        {
            F1 = new FullAdder();
            F1.setInputs(inputA.isChecked(),inputB.isChecked(),inputC.isChecked());
            txtA.setText("A="+ctb(F1.getA()));
            txtB.setText("B="+ctb(F1.getB()));
            txtC.setText("C="+ctb(F1.getC()));
            txtS.setText("S="+ctb(F1.getS()));
            txtT.setText("T="+ctb(F1.gett()));
        }
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


    private byte ctb(boolean input) // ctb = Convert To Binary.. just turn the falses to 0 and true's to 1s
    {
        return input?(byte)1:0;
    }


//    public View[] getAllChildren() // dumbass Android doesn't have a getAllChildren so i have to do it myself! -_-
//    {
//        LinearLayout outerlayout = (LinearLayout) findViewById(R.id.LinearID);
//        int childCount = outerlayout.getChildCount();
//        View[] allViews = new View[childCount];
//        for(int i = 0; i < childCount; i++)
//        {
//            allViews[i] = outerlayout.getChildAt(i);
//        }
//        return allViews;
//    }
}
