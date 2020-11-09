package ca.dal.bartertrader.presentation.view.provider_home;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("android:src")
    public static void setImage(ImageView imageView, Integer id) {
        imageView.setImageResource(id);
    }

}
