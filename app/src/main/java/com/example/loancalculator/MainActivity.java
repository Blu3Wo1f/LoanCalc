package com.example.loancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText carcost;
    private EditText Downcost;
    private EditText Payment;
    private EditText APRcost;
    private RadioButton Loan;
    private RadioButton Lease;
    private SeekBar Time;
    private TextView barlabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carcost=findViewById(R.id.carcost);
        Downcost=findViewById(R.id.DownCost);
        APRcost=findViewById(R.id.APRCost);
        Payment=findViewById(R.id.Payment);
        Loan=findViewById(R.id.Loan);
        Lease=findViewById(R.id.Lease);
        barlabel=findViewById(R.id.Time);
        Time=findViewById(R.id.seekBar);
        APRcost.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                calculate(v);
                return false;
            }
        });
        Time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                barlabel.setText(progress+ " month(s)");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void clear(View v) {
        if (carcost.getText().length() > 0 && Downcost.getText().length() > 0 && APRcost.getText().length() > 0) {
            APRcost.setText("");
            Downcost.setText("");
            carcost.setText("");
            Payment.setText("");


        }
    }
    public void calculate(View v){
            if(carcost.getText().length()>0 && Downcost.getText().length()>0 && APRcost.getText().length()>0){
                if(Loan.isChecked()){
                    String Down =Downcost.getText().toString();
                    String APR = APRcost.getText().toString();
                    String Cost =carcost.getText().toString();
                    double monthval= Time.getProgress();
                    double downVal = Double.parseDouble(Down);
                    double APRval =Double.parseDouble(APR);
                    double costval= Double.parseDouble(Cost);
                    double total = (APRval/12.0)*(costval-downVal)/1-(Math.pow(1+(APRval/12),-monthval));
                    Payment.setText(String.format("%.2f",total));
                }else if(Lease.isChecked()){
                    String Down =Downcost.getText().toString();
                    String APR = APRcost.getText().toString();
                    String Cost =carcost.getText().toString();
                    double downVal = Double.parseDouble(Down);
                    double APRval =Double.parseDouble(APR);
                    double costval= Double.parseDouble(Cost);
                    double total = (APRval/3.0)*(costval-downVal)/1-(Math.pow(1+(APRval/3),-36));
                    Payment.setText(String.format("%.2f",total));
                }else{
                    Toast.makeText(this, "Neither Loan or Leased has been checked.", Toast.LENGTH_SHORT).show();
                 }
                }
            else{
                Toast.makeText(this, "Values aren't entered.", Toast.LENGTH_SHORT).show();
            }

            }
    }

