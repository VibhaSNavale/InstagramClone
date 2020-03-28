package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtpunchspeed, edtpunchpower, edtkickspeed, edtkickpower;
    private TextView txtGetData;
    private Button btnGetAllData;
    private String allKickboxers;
    private Button btnTransition;

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

        txtGetData = findViewById(R.id.txtGetData);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Kickboxer");
                parseQuery.getInBackground("wljX4IkZst", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if(object != null && e == null){

                            txtGetData.setText(object.get("name") + " - " + "Punch power: " + object.get("punchPower"));

                        }
                    }
                });


            }
        });

        btnGetAllData = findViewById(R.id.btnGetAllData);
        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allKickboxers="";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Kickboxer");

                //queryAll.whereGreaterThan("punchPower", 500);
                //queryAll.whereGreaterThanOrEqualTo("punchPower", 500);
                //queryAll.setLimit(1);

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e == null){

                            if(objects.size() > 0){

                                for(ParseObject kickBoxer:objects){
                                    allKickboxers = allKickboxers + kickBoxer.get("name") + "\n";
                                }
                                FancyToast.makeText(SignUp.this, allKickboxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            }
                            else{
                                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }

                    }
                });

            }
        });

        btnTransition = findViewById(R.id.btnNextActivity);
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUp.this, SignupLoginActivity.class);
                startActivity(intent);
            }
        });

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

        } catch (Exception e){
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
//                if(e == null)
//                    Toast.makeText(SignUp.this,"Boxer is saved successfully",Toast.LENGTH_SHORT).show();
//
//            }
//        });


}
