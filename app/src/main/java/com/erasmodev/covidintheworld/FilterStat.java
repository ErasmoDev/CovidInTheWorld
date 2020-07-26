package com.erasmodev.covidintheworld;

import android.widget.Filter;

import java.util.ArrayList;

public class FilterStat extends Filter {

    private AdapterStat adapterStat;
    private ArrayList<ModelStat> filterList;

    public FilterStat() {
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //check constraint validity
        if (constraint != null && constraint.length() > 0) {
            //change to upper case
            constraint = constraint.toString().toUpperCase();
            //store
            ArrayList<ModelStat> filteredModels = new ArrayList<>();
            for (int i=0; i <filterList.size(); i++) {

                if (filterList.get(i).getCountry().toUpperCase().contains(constraint)) {
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count = filteredModels.size();
            results.values = filteredModels;
        } else {
            results.count = filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterStat.statArrayList = (ArrayList<ModelStat>) results.values;

        adapterStat.notifyDataSetChanged();

    }

    public FilterStat(AdapterStat adapterStat, ArrayList<ModelStat> filterList) {
        this.adapterStat = adapterStat;
        this.filterList = filterList;
    }



}
