package com.erasmodev.covidintheworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterStat extends RecyclerView.Adapter<AdapterStat.HolderStat> implements Filterable {

    private Context context;
    public ArrayList<ModelStat> statArrayList, filterList;
    private FilterStat filter;

    public AdapterStat(Context context, ArrayList<ModelStat> statArrayList) {
        this.context = context;
        this.statArrayList = statArrayList;
        this.filterList = statArrayList;
    }

    @NonNull
    @Override
    public HolderStat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_stat, parent, false);

        return new HolderStat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderStat holder, int position) {

        ModelStat modelStat = statArrayList.get(position);
        //get data
        String country = modelStat.getCountry();
        String countryCode = modelStat.getCountryCode();
        String slug = modelStat.getSlug();
        String newConfirmed = modelStat.getNewConfirmed();
        String totalConfirmed = modelStat.getTotalConfirmed();
        String newDeaths = modelStat.getNewDeaths();
        String totalDeaths = modelStat.getTotalDeaths();
        String newRecovered = modelStat.getNewRecovered();
        String totalRecovered = modelStat.getTotalRecovered();
        String date = modelStat.getDate();
        //set data
        holder.countryTv.setText(country);
        holder.casesTv.setText(totalConfirmed);
        holder.todayCasesTv.setText(newConfirmed);
        holder.deathsTv.setText(totalDeaths);
        holder.todayDeathsTv.setText(newDeaths);
        holder.recoveredTv.setText(totalRecovered);
        holder.todayRecoveredTv.setText(newRecovered);

    }

    @Override
    public int getItemCount() {
        return statArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new FilterStat(this, filterList);
        }
        return filter;
    }


    class HolderStat extends RecyclerView.ViewHolder{

        TextView countryTv, casesTv, todayCasesTv, deathsTv, todayDeathsTv, recoveredTv, todayRecoveredTv;

        public HolderStat(@NonNull View itemView) {
            super(itemView);

            countryTv = itemView.findViewById(R.id.countryTv);
            casesTv = itemView.findViewById(R.id.casesTv);
            todayCasesTv = itemView.findViewById(R.id.todayCasesTv);
            deathsTv = itemView.findViewById(R.id.deathsTv);
            todayDeathsTv = itemView.findViewById(R.id.todayDeathsTv);
            recoveredTv = itemView.findViewById(R.id.recoveredTv);
            todayRecoveredTv = itemView.findViewById(R.id.todayRecoveredTv);

        }
    }

}
