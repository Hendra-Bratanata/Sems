package com.shadad.epm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HardwareAdapter extends RecyclerView.Adapter<HardwareAdapter.ViewHolder> {
    private RecycleViewClickInterface recycleViewClickInterface;
    private final ArrayList<DataItemHardware> listHardware;

    public HardwareAdapter(ArrayList<DataItemHardware> listHardware, RecycleViewClickInterface clickInterface) {
        this.listHardware = listHardware;
        this.recycleViewClickInterface = clickInterface;
    }

    @NonNull
    @Override
    public HardwareAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hardware, parent, false);
        return new ViewHolder(view, recycleViewClickInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull HardwareAdapter.ViewHolder holder, int position) {

        holder.tvID.setText("ID \t\t\t\t\t\t\t\t: "+listHardware.get(position).getID());
        holder.tvStatus.setText("STACK \t\t: "+listHardware.get(position).getSTACK());
        holder.tvStack.setText("STATUS \t: "+listHardware.get(position).getSTATUS());



    }

    @Override
    public int getItemCount() {
        return listHardware.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvID;
        TextView tvStack;
        TextView tvStatus;



        public ViewHolder(@NonNull View itemView, RecycleViewClickInterface recycleViewClickInterface) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvHardwareId);
            tvStack = itemView.findViewById(R.id.tvHardwareStack);
            tvStatus = itemView.findViewById(R.id.tvHardwareStatus);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recycleViewClickInterface != null){
                        int pos = getAdapterPosition();
                        recycleViewClickInterface.onItemClick(pos);

                    }

                }
            });

        }
    }
}
