 package com.example.myfirstapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtField;
    MaterialButton btnac,btnpm,btnper,btnequal,btndot;
    MaterialButton btnplus,btnminus,btndivide,btnmulti;
    MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    String calculate = "";
    int pflag = 0;
    String a,b;
    int cflag = 0;
    int miflag = 0;
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtField = findViewById(R.id.textField);

        assignID(btnac,R.id.ac);
        assignID(btnpm,R.id.plusMinus);
        assignID(btnper,R.id.percent);
        assignID(btndot,R.id.dot);
        assignID(btnequal,R.id.equal);
        assignID(btnplus,R.id.plus);
        assignID(btnminus,R.id.minus);
        assignID(btnmulti,R.id.mul);
        assignID(btndivide,R.id.devide);
        assignID(btn0,R.id.zero);
        assignID(btn1,R.id.one);
        assignID(btn2,R.id.two);
        assignID(btn3,R.id.three);
        assignID(btn4,R.id.four);
        assignID(btn5,R.id.five);
        assignID(btn6,R.id.six);
        assignID(btn7,R.id.seven);
        assignID(btn8,R.id.eight);
        assignID(btn9,R.id.nine);
    }

    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

     @SuppressLint("SetTextI18n")
     @Override
     public void onClick(View v) {
        MaterialButton btn = (MaterialButton) v;
        String btnText = btn.getText().toString();

        if(btnText.equals("AC")) {
            txtField.setText("0");
            data = "";
            return;
        }
        if(btnText.equals("=")){
            data = txtField.getText().toString();
            return;
        }

         if(btnText.equals("+/-")){
             if(!txtField.getText().toString().startsWith("-")) {
                 txtField.setText("-" + txtField.getText().toString());
             }else{
                 txtField.setText(txtField.getText().toString().replace("-",""));
             }
         }

        if(btnText.equals("%")){
            double a = Double.parseDouble(txtField.getText().toString());
            a = a/100;
            txtField.setText(String.valueOf(a));
        }

        if(btnText.equals("÷")){
            btnText = "/";
        }

        if(btnText.equals("×")){
            btnText = "*";
        }
        data = data + btnText;



        String fires = getResult(data);

        if(!fires.equals("Error")){
            txtField.setText(fires);
        }
     }

     String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable st = context.initStandardObjects();
            String res = context.evaluateString(st,data,"Javascript",1,null).toString();
            if(res.endsWith(".0")){
                res = res.replace(".0","");
            }
            return res;
        }catch (Exception e){
            return "Error";
        }
     }
 }