package ca.dal.bartertrader.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import ca.dal.bartertrader.R;
import ca.dal.bartertrader.databinding.FragmentLoginBinding;
import ca.dal.bartertrader.presentation.view_model.LoginViewModel;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setModel(loginViewModel);
        return binding.getRoot();
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.getModel().getEmailIsInvalid().observe(getViewLifecycleOwner(), emailIsInvalid -> {
            if (emailIsInvalid) {
                binding.loginEmail.setError("Empty Email");
            }
            else {
                binding.loginEmail.setError(null);
            }
        });

        binding.getModel().getPasswordIsInvalid().observe(getViewLifecycleOwner(), passwordIsInvalid -> {
            if (passwordIsInvalid) {
                binding.loginPassword.setError("Empty Password");
            }
            else {
                binding.loginPassword.setError(null);
            }
        });

        binding.getModel().getUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                Toast.makeText(getContext(), "Log in successful", Toast.LENGTH_LONG).show();
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_splashFragment);
            }
            else {
                Toast.makeText(getContext(), "Log in failed", Toast.LENGTH_LONG).show();
            }
        });

        binding.getModel().getDestID().observe(getViewLifecycleOwner(), destID -> {
            if (destID == 1) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_registrationFragment);
//                Navigation.findNavController(getView())
//                        .navigate(LoginFragmentDirections.);
            }
            else if (destID == 2) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_passwordResetFragment);
            }
            else {
                Toast.makeText(getContext(), "An error has occurred", Toast.LENGTH_LONG).show();
            }
        });
    }
}
