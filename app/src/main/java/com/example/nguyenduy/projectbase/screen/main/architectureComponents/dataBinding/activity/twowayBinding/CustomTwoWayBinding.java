package com.example.nguyenduy.projectbase.screen.main.architectureComponents.dataBinding.activity.twowayBinding;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/*TODO*/
public class CustomTwoWayBinding extends BaseObservable {

    private double number;

    public CustomTwoWayBinding() {
        this.number = 0;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    @InverseBindingAdapter(attribute = "realValue")
    public double getRealValue(EditText editText) {
        return Double.parseDouble(editText.getText().toString()) / 100;
    }

    @BindingAdapter("realValue")
    public void setRealValue(EditText editText, double number) {
        if (this.number == number) return;
        editText.setText(String.valueOf(number * 10));
    }

    /*suffix: AttrChanged*/
    @BindingAdapter("realValueAttrChanged")
    public static void setListener(EditText editText, final InverseBindingListener listener) {
        if (null == listener) return;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                listener.onChange();
                editText.setSelection(editText.getText().length());
            }
        });
    }
}
