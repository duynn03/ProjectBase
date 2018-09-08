package com.example.nguyenduy.projectbase.screen.main.navigation.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;

import butterknife.OnClick;

public class DialogFragment extends BaseFragment<IDialogPresenter> implements IDialogView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_dialog;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new DialogPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {

    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_show_dialog_builder)
    public void onClickButtonShowDialogBuilder() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getRootActivity());
        dialog
                .setTitle("Title of Dialog")
                // set icon in title
                .setIcon(R.drawable.ic_menu_camera)
                .setMessage("Message of Dialog Message of Dialog Message of Dialog Message of Dialog Message of Dialog Message of Dialog Message of Dialog Message of Dialog Message of Dialog")
                // có cho click ra ngoài để tắt dialog không
                .setCancelable(true)
                // listener khi user click ra ngoài dialog để tắt dialog
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        showToast("On Cancel ListenerDataBindingActivity");
                    }
                })
                // event này vào bất cứ khi nào dialog bị tắt đi (kể cả khi click Button cancel lẫn Button OK, hoặc click ra ngoài để tắt dialog)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        showToast("On Dismiss ListenerDataBindingActivity");
                    }
                })
                // event user nhập input from keyboard
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        showToast("key: " + event.getCharacters());
                        return false;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        showToast("Clicked button OK");
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        showToast("Clicked button Cancel");
                    }
                })
                .setNeutralButton("Learn More", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        showToast("Clicked button Learn More");
                    }
                })
        ;
        dialog.create();
        dialog.show();
    }

    @OnClick(R.id.btn_show_dialog_builder_custom)
    public void onClickButtonShowDialogBuilderCustom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getRootActivity());
        View mViewCustom = ViewUtils.createView(getRootActivity(), R.layout.fragment_dialog_custom);
        mViewCustom.findViewById(R.id.btn_dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Clicked button OK");
            }
        });
        mViewCustom.findViewById(R.id.btn_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Clicked button Cancel");
            }
        });
        builder.setView(mViewCustom);
        builder.create();
        builder.show();
    }
}
