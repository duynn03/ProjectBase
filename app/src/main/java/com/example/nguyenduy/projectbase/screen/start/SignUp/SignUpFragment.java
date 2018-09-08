package com.example.nguyenduy.projectbase.screen.start.SignUp;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.firebase.FirebaseAuthen;
import com.example.nguyenduy.projectbase.base.sharedPreference.UserInformation;
import com.example.nguyenduy.projectbase.screen.start.ForgotPassword.ForgotPasswordFragment;
import com.example.nguyenduy.projectbase.utils.FormValidator;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpFragment extends BaseFragment<ISignUpPresenter> implements ISignUpView {

    public static final String TAG = MethodUtils.getTagClass(SignUpFragment.class);

    private FirebaseAuthen firebaseAuthen;
    private FormValidator formValidator;

    @BindView(R.id.til_name)
    TextInputLayout tilName;

    @BindView(R.id.tiet_name)
    TextInputEditText tietName;

    @BindView(R.id.til_email)
    TextInputLayout tilEmail;

    @BindView(R.id.tiet_email)
    TextInputEditText tietEmail;

    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    @BindView(R.id.tiet_password)
    TextInputEditText tietPassword;

    @BindView(R.id.til_confirm_password)
    TextInputLayout tilConfirmPassword;

    @BindView(R.id.tiet_confirm_password)
    TextInputEditText tietConfirmPassword;

    @BindView(R.id.til_phone_number)
    TextInputLayout tilPhoneNumber;

    @BindView(R.id.tiet_phone_number)
    TextInputEditText tietPhoneNumber;

    @BindView(R.id.til_birthday)
    TextInputLayout tilBirthday;

    @BindView(R.id.tiet_birthday)
    TextInputEditText tietBirthday;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new SignUpPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
        firebaseAuthen = new FirebaseAuthen();
        formValidator = new FormValidator();
    }

    @Override
    public void setEvents() {
        addActionDoneEditText(tietBirthday, new CallbackAddActionDoneListener() {
            @Override
            public void callback() {
                onClickButtonSignUp();
            }
        });
    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_sign_up)
    public void onClickButtonSignUp() {
        String name = ViewUtils.getText(tietName);
        String email = ViewUtils.getText(tietEmail);
        String password = ViewUtils.getText(tietPassword);
        String confirmPassword = ViewUtils.getText(tietConfirmPassword);
        String phoneNumber = ViewUtils.getText(tietPhoneNumber);
        String birthDay = ViewUtils.getText(tietBirthday);

        if (formValidator.isValidateFormSignUp(name, email, password, confirmPassword, phoneNumber, birthDay,
                tilName, tilEmail, tilPassword, tilConfirmPassword, tilPhoneNumber, tilBirthday)) {
            firebaseAuthen.signUpEmailAndPassword(
                    getRootActivity(),
                    new UserInformation()
                            .setName(name)
                            .setEmail(email)
                            .setPassword(password)
                            .setPhoneNumber(phoneNumber)
                            .setBirthDay(birthDay),
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e.getMessage().contains("A network error (such as timeout, interrupted connection or unreachable host) has occurred."))
                                showSnackbar(ResourceUtils.getString(R.string.msg_connection_internet_timeout), ResourceUtils.getString(R.string.msg_reconnection), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        onClickButtonSignUp();
                                    }
                                });
                            else if (e.getMessage().contains("The email address is already in use by another account.")) {
                                formValidator.showError(tilEmail, ResourceUtils.getString(R.string.email_exist));
                            }
                            LogUtils.d(TAG + "onClickButtonSignUp() :failure, Exception: " + e.getMessage());
                        }
                    },
                    new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            formValidator.showSuccess(ResourceUtils.getString(R.string.signup_success) + " with email: " + firebaseAuthen.getUser().getEmail());
                            onClickButtonLogin();
                        }
                    },
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e.getMessage().contains("A network error (such as timeout, interrupted connection or unreachable host) has occurred."))
                                showSnackbar(ResourceUtils.getString(R.string.msg_connection_internet_timeout), null, null);
                            LogUtils.d(TAG + "onClickButtonSignUp() :failure, Exception: " + e.getMessage());
                        }
                    }
            );
        }
    }

    @OnClick(R.id.btn_login)
    public void onClickButtonLogin() {
        getRootActivity().onBackPressed();
    }

    @OnClick(R.id.btn_forgot_password)
    public void onClickButtonForgotPassword() {
        getRootActivity().replaceFragment(new ForgotPasswordFragment(), false);
    }

}
