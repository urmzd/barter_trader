package ca.dal.bartertrader.presentation.view.provider_home;

import android.app.AlertDialog;
import android.content.Context;
import java.util.ArrayList;

import ca.dal.bartertrader.presentation.view_model.HomeViewModel;

public class FilterDialog {
    private boolean [] checkedFilters;
    private String[] filterStrings;
    private ArrayList<String> selectedFilters;

    private Context context;
    private HomeViewModel homeViewModel;

    public FilterDialog(Context context, HomeViewModel homeViewModel) {
        this.context = context;
        this.homeViewModel = homeViewModel;

        filterStrings = Filter.getFilterStrings();
        checkedFilters = new boolean[filterStrings.length];
        selectedFilters = new ArrayList<>();
    }

    public void displayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Filter Items")
                .setMultiChoiceItems(filterStrings, checkedFilters, (dialog, which, isChecked) ->
                        checkedFilters[which] = isChecked)

                .setPositiveButton("Ok", (dialog, which) -> {
                    for(int i = 0; i < checkedFilters.length; i++) {
                        if(checkedFilters[i] && !selectedFilters.contains(filterStrings[i])) {
                            selectedFilters.add(filterStrings[i]);
                        }
                        else if (!checkedFilters[i] && selectedFilters.contains(filterStrings[i])){
                            selectedFilters.remove(filterStrings[i]);
                        }
                    }
                    homeViewModel.setPreferences(selectedFilters);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    if(!selectedFilters.isEmpty()) {
                        homeViewModel.setPreferences(selectedFilters);
                    }
                    dialog.cancel();
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
