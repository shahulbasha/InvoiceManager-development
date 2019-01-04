package com.invoice.utilities;

import com.invoice.view.DatabaseEntryView;

/*
 *Author:Shavar Litchmore
 * The @isValidCredentials checks the customer credentials and returns a boolean
 * if the credentials matches the regular expression
 * set the textfield focus color to green or blue based on boolean returned
 *
 */
public class RegexPatternMatch {

    private DatabaseEntryView credentials;

    public boolean isValidCredentials(DatabaseEntryView credentials, String userName, String email, String phoneNumber) {
        this.credentials = credentials;
        boolean verify = false;
        String usernameRegex = "[_A-Za-z{1,12}_0-9]+";
        String emailRegex = "[A-Za-z0-9._%-]+@[A-Za-z0-9._-]+\\.[A-Za-z]{2,4}";
        String phoneRegex = "([0-9]( |-)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-)?([0-9]{3}( |-)?[0-9]{4}|[a-zA-Z0-9]{7})";
        if (userName.matches(usernameRegex) && email.matches(emailRegex) && phoneNumber.matches(phoneRegex)) {
            verify = true;

        }
        return verify;
    }

}
