package com.example.nguyenduy.projectbase.screen.main.sharedpreference.settingActivity.customPreference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.util.AttributeSet;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class CustomDialogPreference extends DialogPreference {

    private static final String PREFERENCE_KEY = "CUSTOM_DIALOG";
    private static final String PREFERENCE_DEFAULT_VALUE = "init value dialog";


    public CustomDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.preference_custom_dialog);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);

        setDialogIcon(null);
    }

    // Initializing the current value
    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        // existing value in sharedPreference
        String value;
        if (restorePersistedValue) {
            value = this.getPersistedString(PREFERENCE_KEY);
        } else {
            // Set default value from the XML attribute
            persistString(PREFERENCE_DEFAULT_VALUE);
        }
    }

    // Providing a default value
    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        String valueDefault = a.getString(index);
        return !MethodUtils.isEmpty(valueDefault) ? valueDefault : PREFERENCE_DEFAULT_VALUE;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        // Check whether this Preference is persistent (continually saved)
        if (isPersistent()) {
            // No need to save instance state since it's persistent,
            // use superclass state
            return superState;
        }

        // Create instance of custom BaseSavedState
        final SavedState myState = new SavedState(superState);
        // Set the state's value with the class member that holds current
        // setting value
        myState.value = this.getPersistedString(PREFERENCE_KEY);
        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        // Check whether we saved the state in onSaveInstanceState
        if (state == null || !state.getClass().equals(SavedState.class)) {
            // Didn't save the state, so call superclass
            super.onRestoreInstanceState(state);
            return;
        }

        // Cast state to custom BaseSavedState and pass to superclass
        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());

        // Set this Preference's widget to reflect the restored state
        // mNumberPicker.setValue(myState.value);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        // When the user selects "OK", persist the new value
        if (positiveResult) {
            // save value to sharedPreference
            persistString("custom value dialog");
        }
    }
}
