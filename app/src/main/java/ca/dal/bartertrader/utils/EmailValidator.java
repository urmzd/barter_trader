package ca.dal.bartertrader.utils;

import android.content.Context;
import android.content.ContextWrapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.dal.bartertrader.R;

public class EmailValidator extends ContextWrapper {

    private String email;
    private String errorMsg;

    public String getEmail() {
        return email;
    }
    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setEmail( String email) {
        this.email = email;
    }

    public EmailValidator(Context base, String email) {
        super(base);
        this.email = email;
    }
    private boolean isEmailEmpty() {

        if(email == null || email.length() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isEmailValid() {
        Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");//("^[A-Za-z0-9+_.-]+@(.+)*$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validate()
    {
        errorMsg = null;
        if(isEmailEmpty())
        {
            errorMsg = getResources().getString(R.string.error_field_required);
            return false;
        }else if(!isEmailValid())
        {
            errorMsg = getResources().getString(R.string.error_email_invalid);
            return false;
        }
        return true;
    }

}

