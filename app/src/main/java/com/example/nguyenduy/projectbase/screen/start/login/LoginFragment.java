package com.example.nguyenduy.projectbase.screen.start.login;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.firebase.FirebaseAuthen;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.screen.main.MainActivity;
import com.example.nguyenduy.projectbase.screen.start.ForgotPassword.ForgotPasswordFragment;
import com.example.nguyenduy.projectbase.screen.start.SignUp.SignUpFragment;
import com.example.nguyenduy.projectbase.utils.FormValidator;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment<ILoginPresenter> implements ILoginView {

    public static final String TAG = MethodUtils.getTagClass(SignUpFragment.class);

    private FirebaseAuthen firebaseAuthen;
    private FormValidator formValidator;

    @BindView(R.id.til_email)
    TextInputLayout tilEmail;

    @BindView(R.id.tiet_email)
    TextInputEditText tietEmail;

    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    @BindView(R.id.tiet_password)
    TextInputEditText tietPassword;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new LoginPresenterImp(this);
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
        addActionDoneEditText(tietPassword, new CallbackAddActionDoneListener() {
            @Override
            public void callback() {
                onClickButtonLogin();
            }
        });
    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_login)
    public void onClickButtonLogin() {
        String email = ViewUtils.getText(tietEmail);
        String password = ViewUtils.getText(tietPassword);

        if (formValidator.isValidateFormLogin(email, password, tilEmail, tilPassword)) {
            firebaseAuthen.signInEmailAndPassword(
                    getRootActivity(),
                    email,
                    password,
                    new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            SharedPreferenceUtils.getInstance().setUserInformation(firebaseAuthen.getUserProfile());
                            getRootActivity().startRootActivity(MainActivity.class);
                        }
                    },
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e.getMessage().contains("A network error (such as timeout, interrupted connection or unreachable host) has occurred."))
                                showSnackbar(ResourceUtils.getString(R.string.msg_connection_internet_timeout), ResourceUtils.getString(R.string.msg_reconnection), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        onClickButtonLogin();
                                    }
                                });
                            else if (e.getMessage().contains("There is no user record corresponding to this identifier. The user may have been deleted.")
                                    || e.getMessage().contains("The password is invalid or the user does not have a password.")) {
                                formValidator.showError(tilEmail, ResourceUtils.getString(R.string.email_or_password_incorrect));
                            }
                            LogUtils.d(TAG + "onClickButtonLogin() :failure, Exception: " + e.getMessage());
                        }
                    });
        }
    }

    @OnClick(R.id.btn_forgot_password)
    public void onClickButtonForgotPassword() {
        getRootActivity().replaceFragment(new ForgotPasswordFragment(), true);
    }

    @OnClick(R.id.btn_sign_up)
    public void onClickButtonSignUp() {
        getRootActivity().replaceFragment(new SignUpFragment(), true);
    }

}
