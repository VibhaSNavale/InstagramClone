package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtpunchspeed, edtpunchpower, edtkickspeed, edtkickpower;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUp.this);

        edtName = findViewById(R.id.edtName);
        edtpunchspeed = findViewById(R.id.edtPunchSpeed);
        edtpunchpower = findViewById(R.id.edtPunchPower);
        edtkickspeed = findViewById(R.id.edtKickSpeed);
        edtkickpower = findViewById(R.id.edtKickPower);


    }

    @Override
    public void onClick(View view) {
        try {
            final ParseObject kickboxer = new ParseObject("Kickboxer");
            kickboxer.put("name", edtName.getText().toString());
            kickboxer.put("punchSpeed", Integer.parseInt(edtpunchspeed.getText().toString()));
            kickboxer.put("punchPower", Integer.parseInt(edtpunchpower.getText().toString()));
            kickboxer.put("kickSpeed", Integer.parseInt(edtkickspeed.getText().toString()));
            kickboxer.put("kickPower", Integer.parseInt(edtkickpower.getText().toString()));

            kickboxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null)
                        FancyToast.makeText(SignUp.this, kickboxer.get("name") + " is saved successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    else
                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                }
            });

        }catch (Exception e){
            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }

//    public void helloWorldTapped(View view){

//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if(e==null)
//                    Toast.makeText(SignUp.this,"Boxer is saved successfully",Toast.LENGTH_SHORT).show();
//
//            }
//        });


}
