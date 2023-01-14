package com.shadad.epm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataSemsAdapter extends RecyclerView.Adapter<DataSemsAdapter.ViewHolder> {

    private final ArrayList<DataItemSems> listHardware;

    public DataSemsAdapter(ArrayList<DataItemSems> listHardware) {
        this.listHardware = listHardware;
    }

    @NonNull
    @Override
    public DataSemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hardware, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataSemsAdapter.ViewHolder holder, int position) {

        holder.tvID.setText("ID \t\t\t\t\t\t\t\t: "+listHardware.get(position).getID());
        holder.tvCurrent.setText("Curret \t\t\t: "+listHardware.get(position).getAMPER());
        holder.tvVoltage.setText("Voltase \t: "+listHardware.get(position).getVOLT());
        holder.tvEnergy.setText("Energy \t\t: "+listHardware.get(position).getPOWER());


    }

    @Override
    public int getItemCount() {
        return listHardware.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvID;
        TextView tvVoltage;
        TextView tvCurrent;
        TextView tvEnergy;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvHardwareId);
            tvVoltage = itemView.findViewById(R.id.tvHardwareVoltage);
            tvCurrent = itemView.findViewById(R.id.tvHardwareCurrent);

        }
    }
}
