package ca.dal.bartertrader.presentation.view_model;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Integer> destID = new MutableLiveData<>();

    //@BindingAdapter("destID")
    public void navigate(int destID) {
        this.destID.setValue(destID);
    }

    //@InverseBindingAdapter(attribute = "destID")
    public MutableLiveData<Integer> getDestID() {
        return destID;
    }
}
