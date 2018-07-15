package com.example.als.i_vaga.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class InputValidation {
    private Context context;

    public InputValidation(Context context){
        this.context = context;
    }

    public boolean isInputEditTextFilled(EditText editText, String message){
        String value = editText.getText().toString().trim();
        if (value.isEmpty()){
            editText.setError(message);
            hideKeyboardFrom(editText);
            return false;
        }
        return true;
    }

    private void hideKeyboardFrom(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}