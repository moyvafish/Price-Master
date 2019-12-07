package com.example.mc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DataAdapterGoods extends RecyclerView.Adapter<DataAdapterGoods.ViewHolder> {

    private LayoutInflater inflater;
    private List<Good> goods;
    private OnNoteListener mOnNoteListener;

    public DataAdapterGoods(Context context, List<Good> goods, OnNoteListener OnNoteListener) {
        this.goods = goods;
        this.inflater = LayoutInflater.from(context);
        this.mOnNoteListener = OnNoteListener;
    }
    @Override
    public DataAdapterGoods.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_goods, parent, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Good good = goods.get(position);
        holder.imageView.setImageResource(good.getImage());
        holder.nameView.setText(good.getName());
        holder.companyView.setText(good.getCompany());
        holder.priceView.setText(good.getPrice());
    }
    @Override
    public int getItemCount() {
        return goods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView imageView;
        final TextView nameView, companyView, priceView;
        OnNoteListener onNoteListener;
        ViewHolder(View view, OnNoteListener onNoteListener){
            super(view);
            imageView = (ImageView)view.findViewById(R.id.icon2);
            nameView = (TextView) view.findViewById(R.id.name);
            companyView = (TextView) view.findViewById(R.id.company);
            priceView = (TextView) view.findViewById(R.id.price);
            this.onNoteListener = onNoteListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
