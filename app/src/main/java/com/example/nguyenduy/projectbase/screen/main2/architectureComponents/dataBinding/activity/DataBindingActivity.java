package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.dataBinding.activity;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.databinding.ActivityDataBindingBinding;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;

public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // cách 1
        // setContentView(R.layout.activity_data_binding);
        // ActivityDataBindingBinding binding = ActivityDataBindingBinding.inflate(getLayoutInflater());
        // cách 2
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        binding.setActivity(this);

        User user = new User("Duy", "Nguyen");
        binding.setUser(user);

        // observable field
        UserObservableField userObservableField = new UserObservableField();
        userObservableField.setFirstName(new ObservableField<>("DuyObservableField"));
        userObservableField.setLastName(new ObservableField<>("NguyenObservableField"));
        userObservableField.setAge(new ObservableInt(6));
        binding.setUserObservableField(userObservableField);

        // Observable Object
        UserObservableObject userObservableObject = new UserObservableObject();
        userObservableObject.setFirstName("DuyObservableObject");
        userObservableObject.setLastName("NguyenObservableObject");
        binding.setUserObservableObject(userObservableObject);

        // binding logic setter
        int colorDataBindingSetter = ContextCompat.getColor(this, R.color.colorPrimary);
        binding.setColorDataBindingSetter(colorDataBindingSetter);
        // binding logic load image
        binding.setImageUrl("https://images.pexels.com/photos/457702/pexels-photo-457702.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
    }

    public void onClickButtonMethodReference(View view) {
        Toast.makeText(MyApplication.getAppContext(), "Binding Method Reference", Toast.LENGTH_SHORT).show();
    }

    public void onClickButtonListenerBinding(User user) {
        Toast.makeText(MyApplication.getAppContext(), "Binding Listener: User: " + user.getFirstName(), Toast.LENGTH_SHORT).show();
    }

    public boolean isUserDuy(User user) {
        return "Duy".equals(user.getFirstName());
    }

    public void onClickButtonChangeFirstNameObservableFieldUser(UserObservableField userObservableField) {
        userObservableField.getFirstName().set(userObservableField.getFirstName().get() + " Changed");
    }

    public void onClickButtonChangeFirstNameObservableObjectUser(UserObservableObject userObservableObject) {
        userObservableObject.setFirstName(userObservableObject.getFirstName() + " Changed");
    }

    @BindingAdapter("android:paddingLeft")
    public static void setPaddingLeft(View view, int padding) {
        view.setPadding(padding,
                view.getPaddingTop(),
                view.getPaddingRight(),
                view.getPaddingBottom());
    }

    /*requireAll: false nghĩa là không yêu cầu tất cả các attribute*/
    @BindingAdapter(value = {"imageUrl", "placeHolder"}, requireAll = false)
    public static void loadImage(ImageView view, String url, Drawable placeHolder) {
        if (TextUtils.isEmpty(url)) view.setImageDrawable(placeHolder);
        else ViewUtils.setImage(view, url, placeHolder);
    }
}
