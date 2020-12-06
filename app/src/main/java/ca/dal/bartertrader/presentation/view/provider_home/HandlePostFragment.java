package ca.dal.bartertrader.presentation.view.provider_home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import ca.dal.bartertrader.R;
import ca.dal.bartertrader.databinding.FragmentHandlePostBinding;
import ca.dal.bartertrader.di.view_model.provider_home.HandlePostViewModelFactory;
import ca.dal.bartertrader.presentation.view_model.provider_home.HandlePostViewModel;
import ca.dal.bartertrader.utils.BindingUtils;
import ca.dal.bartertrader.utils.LocationServiceManager;
import ca.dal.bartertrader.utils.NavigationUtils;
import ca.dal.bartertrader.utils.handler.resource.Status;

import static androidx.core.content.FileProvider.getUriForFile;

public class HandlePostFragment extends Fragment {

    private FragmentHandlePostBinding binding;
    private HandlePostViewModel viewModel;
    private Uri imageUri;
    private final HandlePostViewModelFactory handlePostViewModelFactory;


    public HandlePostFragment(HandlePostViewModelFactory handlePostViewModelFactory) {
        this.handlePostViewModelFactory = handlePostViewModelFactory;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, handlePostViewModelFactory).get(HandlePostViewModel.class);

        binding = FragmentHandlePostBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(getViewLifecycleOwner());

        binding.setViewModel(viewModel);

        String title = getArguments().getString("title");
        String description = getArguments().getString("description");
        Uri imageUri = getArguments().getParcelable("imageUri");
        double lat = getArguments().getDouble("lat");
        double lon = getArguments().getDouble("lon");
        String postUid = getArguments().getString("postUid");

        if (postUid != null)
        {
            viewModel.setExistingPostData(title, description, imageUri, lat, lon);
        }
        else
        {
            LocationServiceManager lsm = LocationServiceManager.getInstance();
            if (lsm !=null )
            {
                viewModel.setLocation(lsm.getCityFromCurrentLocation(), lsm.getProvinceFromCurrentLocation());
            }
        }

        return binding.getRoot();
    }

    private final ActivityResultLauncher<String> requestPermissions = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (!isGranted) {
            Toast.makeText(getContext(), "Access Denied: Posts Require Permission! Please enable it in your app settings.", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView()).navigateUp();
        }
    });

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        NavigationUtils.setUpToolBar(getView(), (MaterialToolbar) binding.toolbar, R.id.providerHomeFragment);
        viewModel.getUploadImageFromCameraEvent().observe(getViewLifecycleOwner(), __ -> {
            try {
                imageUri = createImageFile();
                validatePermissions(Manifest.permission.CAMERA, imageUri, takePicture::launch);
            } catch (IOException e) {
                Toast.makeText(getContext(), "Something went wrong... Please try again!", Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getUploadImageFromGalleryEvent().observe(getViewLifecycleOwner(), __ -> {
            validatePermissions(Manifest.permission.READ_EXTERNAL_STORAGE, "image/*", pickPicture::launch);
        });

        viewModel.getTitleIsValid().observe(getViewLifecycleOwner(), validity -> {
            BindingUtils.setErrorOnTextInputLayout(binding.handlePostTitle, validity, "Title cannot be empty");
        });

        viewModel.getPostResults().observe(getViewLifecycleOwner(), result -> {
            Status resultStatus = result.getStatus();

            if (resultStatus == Status.FULFILLED) {
                Toast.makeText(getContext(), "Post successfully created!", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigateUp();
                return;
            }

            if (resultStatus == Status.REJECTED) {
                Toast.makeText(getContext(), result.getError().getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    private final ActivityResultLauncher<Uri> takePicture = registerForActivityResult(new ActivityResultContracts.TakePicture(), pictureTaken -> {
        if (pictureTaken) {
            viewModel.setImage(imageUri);
        }
    });

    private final ActivityResultLauncher<String> pickPicture = registerForActivityResult(new ActivityResultContracts.GetContent(), image -> {
        if (image != null) {
            viewModel.setImage(image);
        }
    });


    private <ContractInputT> void validatePermissions(String permission, ContractInputT input, Consumer<ContractInputT> contract) {
        if (getContext().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
            contract.accept(input);
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            Toast.makeText(getContext(), "Access Denied: Permissions Required!", Toast.LENGTH_SHORT).show();
        } else {
            requestPermissions.launch(permission);
        }
    }

    private Uri createImageFile() throws IOException {
        File imagePath = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String timeStamp = String.valueOf(System.currentTimeMillis());
        File post = File.createTempFile(timeStamp, ".jpg", imagePath);

        return getUriForFile(getContext(), "ca.dal.bartertrader", post);
    }
}
