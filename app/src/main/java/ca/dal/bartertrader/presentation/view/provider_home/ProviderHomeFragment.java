package ca.dal.bartertrader.presentation.view.provider_home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import ca.dal.bartertrader.R;
import ca.dal.bartertrader.data.model.Post;
import ca.dal.bartertrader.databinding.FragmentProviderHomeBinding;
import ca.dal.bartertrader.presentation.view_model.HomeViewModel;

public class ProviderHomeFragment extends Fragment implements PostListener {
    private PostAdapter postAdapter;
    private FragmentProviderHomeBinding binding;
    private HomeViewModel homeViewModel;
    private FilterDialog filterDialog;
    private Pair<String, String> searchQuery;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(ProviderHomeFragment.this).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_provider_home,container,false);
        binding.setHomeViewModel(homeViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filterDialog = new FilterDialog(getContext(), homeViewModel);

        getItems();
        initRecycler(view);



        SearchView search = view.findViewById(R.id.appbar_search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sendSearchQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sendSearchQuery(newText);
                return true;
            }
        });

        view.findViewById(R.id.filter).setOnClickListener(v -> {
            filterDialog.displayDialog();
        });


    }

    public void sendSearchQuery(String query){
        query = query.toLowerCase().trim();
        if (query.contains(":")){
            String[] queryArray = query.split(":");

            if (queryArray.length != 1) {
                searchQuery = new Pair<>(queryArray[0], queryArray[1]);
                homeViewModel.sendSearchQuery(searchQuery);
            }
        }
    }

    public void displayDatePicker() {

    }

    public void getItems() {
        binding.setIsLoading(true);
        homeViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            binding.setIsLoading(false);
            postAdapter.setPosts(items);
            postAdapter.notifyDataSetChanged();
        });
    }

    public void initRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        postAdapter = new PostAdapter(this);
        recyclerView.setAdapter(postAdapter);
    }

    @Override
    public void onEditClick(Post post) {
        final NavController navController = Navigation.findNavController(getView());

        ProviderHomeFragmentDirections.ActionProviderHomeFragmentToProviderHomeFragmentViewPost
                action = ProviderHomeFragmentDirections.actionProviderHomeFragmentToProviderHomeFragmentViewPost(
                        post.getId(), post.getTitle(), post.getDescription(), post.getLocation(),
                        post.getImg()
                );
        navController.navigate(action);

    }

    @Override
    public void onDeleteClick(Post post) {
        homeViewModel.deleteItem(post);
    }

}



