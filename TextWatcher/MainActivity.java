package com.example.utshaw.textwatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    EditText myText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (EditText) findViewById(R.id.edit_text);
        myText.addTextChangedListener(this);

    }

    //This method is called to notify you that, within s,
    // the count characters beginning at start are about to be replaced by new text with length after.
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    //This method is called to notify you that, within s,
    // the count characters beginning at start have just replaced old text that had length before.
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    //This method is called to notify you that, somewhere within s, the text has been changed.
    @Override
    public void afterTextChanged(Editable s) {

        String numberString = s.toString();

        if(numberString.length() > 0) {
            int no = Integer.parseInt(numberString);
            if (no > 100) {
                s.replace(0, s.length(), "100");
            }
        }

    }
}
