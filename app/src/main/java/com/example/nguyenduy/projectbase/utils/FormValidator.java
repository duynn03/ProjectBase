package com.example.nguyenduy.projectbase.utils;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;

import java.util.regex.Pattern;

public class FormValidator {

    private final static String FORMAT_SPECIAL_CHARACTERS = "[!\"#$%&'()*+,\\-./:;<=>?@[\\\\]^_{|}~]";

    public boolean isValidateFormLogin(String email, String password, TextInputLayout tilEmail, TextInputLayout tilPassword) {
        if (!isValidEmail(email, tilEmail)) {
            return false;
        }

        if (!isValidPassword(password, tilPassword)) {
            return false;
        }
        return true;
    }

    public boolean isValidateFormSignUp(String name, String email, String password, String confirmPassword, String phoneNumber, String birthDay,
                                        TextInputLayout tilName, TextInputLayout tilEmail, TextInputLayout tilPassword,
                                        TextInputLayout tilConfirmPassword, TextInputLayout tilPhoneNumber, TextInputLayout tilBirthday) {
        if (!isValidFullName(name, tilName)) {
            return false;
        }

        if (!isValidEmail(email, tilEmail)) {
            return false;
        }

        if (!isValidPassword(password, tilPassword)) {
            return false;
        }

        if (!isValidConfirmPassword(password, confirmPassword, tilConfirmPassword)) {
            return false;
        }

        if (!isValidPhoneNumber(phoneNumber, tilPhoneNumber)) {
            return false;
        }

        return true;
    }

    private boolean isValidFullName(String fullName, TextInputLayout tilName) {
        if (TextUtils.isEmpty(fullName)) {
            showError(tilName, ResourceUtils.getString(R.string.name_empty));
            return false;
        }
        return isValidFormatFullName(fullName, tilName);
    }

    private boolean isValidFormatFullName(String fullName, TextInputLayout tilName) {
        if (fullName.length() < ResourceUtils.getInt(R.string.name_length_min) || fullName.length() > ResourceUtils.getInt(R.string.name_length_max)) {
            showError(tilName, ResourceUtils.getString(R.string.name_invalid));
            return false;
        }
        if (isMatchPattern(fullName, FORMAT_SPECIAL_CHARACTERS)) {
            showError(tilName, ResourceUtils.getString(R.string.name_invalid));
            return false;
        }
        showError(tilName, null);
        return true;
    }

    private boolean isValidEmail(String email, TextInputLayout tilEmail) {
        if (TextUtils.isEmpty(email)) {
            showError(tilEmail, ResourceUtils.getString(R.string.email_empty));
            return false;
        }
        return isValidFormatEmail(email, tilEmail);
    }

    private boolean isValidFormatEmail(String email, TextInputLayout tilEmail) {
        if (email.length() < ResourceUtils.getInt(R.string.email_length_min) || email.length() > ResourceUtils.getInt(R.string.email_length_max)) {
            showError(tilEmail, ResourceUtils.getString(R.string.email_invalid));
            return false;
        }
        if (!isMatchPattern(email, android.util.Patterns.EMAIL_ADDRESS)) {
            showError(tilEmail, ResourceUtils.getString(R.string.email_invalid));
            return false;
        }
        showError(tilEmail, null);
        return true;
    }

    private boolean isValidPassword(String password, TextInputLayout tilPassword) {
        if (TextUtils.isEmpty(password)) {
            showError(tilPassword, ResourceUtils.getString(R.string.password_empty));
            return false;
        }
        return isValidFormatPassword(password, tilPassword);
    }

    private boolean isValidFormatPassword(String password, TextInputLayout tilPassword) {
        if (password.length() < ResourceUtils.getInt(R.string.password_length_min) || password.length() > ResourceUtils.getInt(R.string.password_length_max)) {
            showError(tilPassword, ResourceUtils.getString(R.string.password_invalid));
            return false;
        }
        showError(tilPassword, null);
        return true;
    }

    private boolean isValidConfirmPassword(String confirmPassword, String password, TextInputLayout tilConfirmPassword) {
        if (!password.equals(confirmPassword)) {
            showError(tilConfirmPassword, ResourceUtils.getString(R.string.confirm_password_invalid));
            return false;
        }
        showError(tilConfirmPassword, null);
        return true;
    }

    private boolean isValidPhoneNumber(String phoneNumber, TextInputLayout tilPhoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            showError(tilPhoneNumber, ResourceUtils.getString(R.string.phone_number_empty));
            return false;
        }
        return isValidFormatPhoneNumber(phoneNumber, tilPhoneNumber);
    }

    private boolean isValidFormatPhoneNumber(String phoneNumber, TextInputLayout tilPhoneNumber) {
        if (phoneNumber.length() < ResourceUtils.getInt(R.string.phone_length_min) || phoneNumber.length() > ResourceUtils.getInt(R.string.phone_length_max)) {
            showError(tilPhoneNumber, ResourceUtils.getString(R.string.phone_number_invalid));
            return false;
        }
        if (!isMatchPattern(phoneNumber, android.util.Patterns.PHONE)) {
            showError(tilPhoneNumber, ResourceUtils.getString(R.string.phone_number_invalid));
            return false;
        }
        showError(tilPhoneNumber, null);
        return true;
    }

    /*public boolean isValidateFormForgotPassWord(String email) {
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
*/

    private boolean isMatchPattern(String input, String pattern) {
        return Pattern.compile(pattern).matcher(input).find();
    }

    private boolean isMatchPattern(String input, Pattern pattern) {
        return pattern.matcher(input).matches();
    }

    /* private boolean isValidDateOfBirth(String dateOfBirth) {
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
 */
    private void showError(TextInputLayout textInputLayout, String message) {
        if (!TextUtils.isEmpty(message)) {
            textInputLayout.setError(message);
            textInputLayout.setErrorEnabled(true);
            textInputLayout.requestFocus();
        } else {
            textInputLayout.setErrorEnabled(false);
        }
    }

    public void showSuccess(String message) {
        Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_LONG).show();
    }

}
