package com.example.mc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapterUnderCatalog extends RecyclerView.Adapter<DataAdapterUnderCatalog.ViewHolder> {

    private LayoutInflater inflater;
    private List<UnderCatalog> underCatalogs;


    public DataAdapterUnderCatalog(Context context, List<UnderCatalog> underCatalogs) {
        this.underCatalogs = underCatalogs;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapterUnderCatalog.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapterUnderCatalog.ViewHolder holder, int position) {
        UnderCatalog underCatalog = underCatalogs.get(position);
        holder.iconView.setImageResource(underCatalog.getIcon());
        holder.nameView.setText(underCatalog.getName());
    }
    @Override
    public int getItemCount() {
        return underCatalogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iconView;
        TextView nameView;
        Button btnView;
        public ViewHolder(View view) {
            super(view);
            iconView = (ImageView) view.findViewById(R.id.icon2);
            nameView = (TextView) view.findViewById(R.id.nameGood);
        }
    }
}
