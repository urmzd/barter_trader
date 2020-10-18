package ca.dal.bartertrader.account_recovery;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ca.dal.bartertrader.R;

import ca.dal.bartertrader.utils.EmailValidator;

public class AccountRecoveryFragment extends Fragment {

    private EditText emailEditText;
    private Button sendEmailButton;

    private AccountRecoveryViewModel mViewModel;

    public static AccountRecoveryFragment newInstance() {
        return new AccountRecoveryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.account_recovery_input_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailEditText= (EditText) view.findViewById(R.id.account_recovery_text_email);
        sendEmailButton = (Button) view.findViewById(R.id.account_recovery_send_email_button);

        view.findViewById(R.id.account_recovery_send_email_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //verify email structure
                if(validateEmail())
                {
                    NavHostFragment.findNavController(AccountRecoveryFragment.this)
                            .navigate(R.id.action_AccountRecoveryFragment_to_LoginFragment);

                }
                //navigate to next page
                    //navigate to login fragment again? When?


                }

        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccountRecoveryViewModel.class);
        // TODO: Use the ViewModel
    }

    public boolean validateEmail()
    {
        String email = emailEditText.getText().toString();
        EmailValidator validator = new EmailValidator(getContext(), email);
        if(!validator.validate())
        {
            emailEditText.setError(validator.getErrorMsg());
            return false;
        }
        return true;
    }
}