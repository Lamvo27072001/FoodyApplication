package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.phamvohonglam19110154.foodyapplication.R;

public class AdapterRecyclerOdau extends RecyclerView.Adapter<AdapterRecyclerOdau.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_recyclerview_odau, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanAn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenQuanAn = itemView.findViewById(R.id.txtTenQuanAnODau);
        }
    }
    public AdapterRecyclerOdau(){

    }
}
