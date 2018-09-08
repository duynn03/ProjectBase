package com.example.nguyenduy.projectbase.screen.start.ForgotPassword;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.firebase.FirebaseAuthen;
import com.example.nguyenduy.projectbase.screen.start.SignUp.SignUpFragment;
import com.example.nguyenduy.projectbase.utils.FormValidator;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgotPasswordFragment extends BaseFragment<IForgotPasswordPresenter> implements IForgotPasswordView {

    public static final String TAG = MethodUtils.getTagClass(ForgotPasswordFragment.class);

    private FirebaseAuthen firebaseAuthen;
    private FormValidator formValidator;

    @BindView(R.id.til_email)
    TextInputLayout tilEmail;

    @BindView(R.id.tiet_email)
    TextInputEditText tietEmail;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_forgot_password;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new ForgotPasswordPresenterImp(this);
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
        addActionDoneEditText(tietEmail, new CallbackAddActionDoneListener() {
            @Override
            public void callback() {
                onClickButtonForgotPassword();
            }
        });
    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_forgot_password)
    public void onClickButtonForgotPassword() {
        String email = ViewUtils.getText(tietEmail);

        if (formValidator.isValidateFormForgotPassWord(email, tilEmail)) {
            firebaseAuthen.forgotPassword(
                    getRootActivity(),
                    email,
                    new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            formValidator.showSuccess(ResourceUtils.getString(R.string.forgot_password_success) + email);
                            onClickButtonLogin();
                        }
                    },
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e.getMessage().contains("A network error (such as timeout, interrupted connection or unreachable host) has occurred."))
                                showSnackbar(ResourceUtils.getString(R.string.msg_connection_internet_timeout), ResourceUtils.getString(R.string.msg_reconnection), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        onClickButtonForgotPassword();
                                    }
                                });
                            else if (e.getMessage().contains("There is no user record corresponding to this identifier. The user may have been deleted.")) {
                                formValidator.showError(tilEmail, ResourceUtils.getString(R.string.email_not_exist));
                            }
                            LogUtils.d(TAG + "onClickButtonForgotPassword() :failure, Exception: " + e.getMessage());
                        }
                    });
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void onClickButtonSignUp() {
        getRootActivity().replaceFragment(new SignUpFragment(), false);
    }

    @OnClick(R.id.btn_login)
    public void onClickButtonLogin() {
        getRootActivity().onBackPressed();
    }

}
