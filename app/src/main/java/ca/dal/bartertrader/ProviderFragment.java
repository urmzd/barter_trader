package ca.dal.bartertrader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

public class ProviderFragment extends Fragment {
    private ProviderViewModel providerviewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_provider, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        providerviewModel = new ViewModelProvider(this, new ProviderViewModelFactory())
                .get(ProviderViewModel.class);
        Button switchRoleButton = view.findViewById(R.id.switch_role_button);

        switchRoleButton.setOnClickListener(v ->
            providerviewModel.switchRoles()
        );

        providerviewModel.getUserRole().observe(getViewLifecycleOwner(), user ->
            Navigation.findNavController(getView())
                    .navigate(ProviderFragmentDirections.actionProviderFragmentToSplashFragment())
        );
    }
}

