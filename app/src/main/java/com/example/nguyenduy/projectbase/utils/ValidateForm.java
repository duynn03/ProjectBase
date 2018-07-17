package com.example.nguyenduy.projectbase.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.utils.method.DateUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class ValidateForm {

    private final static int EMAIL_LENGTH_MIN = 6;
    private final static int EMAIL_LENGTH_MAX = 30;
    private final static int PASSWORD_LENGTH_MIN = 8;
    private final static int PASSWORD_LENGTH_MAX = 16;
    private final static int FULL_NAME_LENGTH_MIN = 3;
    private final static int FULL_NAME_LENGTH_MAX = 30;
    private final static String FORMAT_SPECIAL_CHARACTERS = "[!\"#$%&'()*+,\\-./:;<=>?@[\\\\]^_{|}~]";
    private final static int PHONE_LENGTH_MIN = 8;
    private final static int PHONE_LENGTH_MAX = 15;

    public boolean isValidateFormLogin(String email, String password) {
        if (!isValidEmail(email)) {
            return false;
        }

        if (!isValidPassword(password)) {
            return false;
        }
        return true;
    }

    public boolean isValidateFormSignUp(String fullName, String dob, String gender, String phone, String email, String password, String confirmPassword) {
        if (!isValidFullName(fullName)) {
            return false;
        }

        if (!isValidDateOfBirth(dob)) {
            return false;
        }

        if (!isValidGender(gender)) {
            return false;
        }

        if (!isValidPhoneNumber(phone)) {
            return false;
        }

        if (!isValidEmail(email)) {
            return false;
        }

        if (!isValidPassword(password)) {
            return false;
        }

        if (!isValidConfirmPassword(password, confirmPassword)) {
            return false;
        }
        return true;
    }

    public boolean isValidateFormForgotPassWord(String email) {
        if (!isValidEmail(email)) {
            return false;
        }
        return true;
    }

    public boolean isValidateFormEditProfileChangePassword(String oldPassword, String newPassword, String confirmNewPassword) {
        if (!isValidOldPassword(oldPassword)) {
            return false;
        }
        if (!isValidNewPassword(newPassword, oldPassword)) {
            return false;
        }
        if (!isValidConfirmPassword(newPassword, confirmNewPassword)) {
            return false;
        }
        return true;
    }

    public boolean isValidateFormEditProfileChangeInformation(String fullName, String dob, String gender, String phone, String email) {
        if (!isValidFullName(fullName)) {
            return false;
        }

        if (!isValidDateOfBirth(dob)) {
            return false;
        }

        if (!isValidGender(gender)) {
            return false;
        }

        if (!isValidPhoneNumber(phone)) {
            return false;
        }

        if (!isValidEmail(email)) {
            return false;
        }
        return true;
    }

    private boolean isValidOldPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return isValidFormatOldPassword(password);
    }

    private boolean isValidFormatOldPassword(String password) {
        if (password.length() < PASSWORD_LENGTH_MIN || password.length() > PASSWORD_LENGTH_MAX) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return true;
    }

    private boolean isValidNewPassword(String password, String oldPassword) {
        if (TextUtils.isEmpty(password)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        // validate new password must != old password
        if (password.equals(oldPassword)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }

        return isValidFormatNewPassword(password);
    }

    private boolean isValidFormatNewPassword(String password) {
        if (password.length() < PASSWORD_LENGTH_MIN || password.length() > PASSWORD_LENGTH_MAX) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return true;
    }


    private boolean isMatchPattern(String input, String pattern) {
        return Pattern.compile(pattern).matcher(input).find();
    }

    private boolean isMatchPattern(String input, Pattern pattern) {
        return pattern.matcher(input).matches();
    }

    private boolean isValidPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return isValidFormatPassword(password);
    }

    private boolean isValidFormatPassword(String password) {
        if (password.length() < PASSWORD_LENGTH_MIN || password.length() > PASSWORD_LENGTH_MAX) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return isValidFormatEmail(email);
    }

    private boolean isValidFormatEmail(String email) {
        if (email.length() < EMAIL_LENGTH_MIN || email.length() > EMAIL_LENGTH_MAX) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        if (!isMatchPattern(email, android.util.Patterns.EMAIL_ADDRESS)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return true;
    }

    private boolean isValidFullName(String fullName) {
        if (TextUtils.isEmpty(fullName)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return isValidFormatFullName(fullName);
    }

    private boolean isValidFormatFullName(String fullName) {
        if (fullName.length() < FULL_NAME_LENGTH_MIN || fullName.length() > FULL_NAME_LENGTH_MAX) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        if (isMatchPattern(fullName, FORMAT_SPECIAL_CHARACTERS)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return true;
    }

    private boolean isValidDateOfBirth(String dateOfBirth) {
        if (TextUtils.isEmpty(dateOfBirth)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }

        // convert dateOfBirth to calendar
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(new SimpleDateFormat("MM/dd/yyyy", Locale.KOREA).parse(dateOfBirth));
        } catch (ParseException e) {
            e.printStackTrace();
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        if (DateUtils.compare(date, Calendar.getInstance()) != DateUtils.PASSED_DAY) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }

        return true;
    }

    private boolean isValidGender(String gender) {
        if (TextUtils.isEmpty(gender)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return true;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        if (!isValidFormatPhoneNumber(phoneNumber)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return true;
    }

    private boolean isValidFormatPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() < PHONE_LENGTH_MIN || phoneNumber.length() > PHONE_LENGTH_MAX) {
            return false;
        }
        return isMatchPattern(phoneNumber, android.util.Patterns.PHONE);
    }

    private boolean isValidConfirmPassword(String confirmPassword, String password) {
        if (!password.equals(confirmPassword)) {
            showError(ResourceUtils.getString(R.string.app_name_production));
            return false;
        }
        return true;
    }

    public void showError(String message) {
        Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showSuccess(String message) {
        Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }

}
