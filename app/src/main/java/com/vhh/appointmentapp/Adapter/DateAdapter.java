package com.vhh.appointmentapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.vhh.appointmentapp.R;
import com.vhh.appointmentapp.databinding.ViewholderDateBinding;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.Viewholder> {
    private final List<String> timeSlots;
    private int selectedPosition=-1;
    private int lastSelectedPosition=-1;

    public DateAdapter(List<String> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @NonNull
    @Override
    public DateAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderDateBinding binding = ViewholderDateBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DateAdapter.Viewholder holder, int position) {
        holder.bind(timeSlots.get(position), position, this);
    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private final ViewholderDateBinding binding;
        public Viewholder(ViewholderDateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String date, int position, DateAdapter dateAdapter) {
            String[] dateParts = date.split("/");
            if(dateParts.length == 3) {
                binding.dayTxt.setText(dateParts[0]);
                binding.dateMonthTxt.setText(dateParts[1] + " " + dateParts[2]);
                Context context = binding.getRoot().getContext();
                if(dateAdapter.selectedPosition == position) {
                    binding.mainLayout.setBackgroundResource(R.drawable.blue_btn_bg);
                    binding.dayTxt.setTextColor(ContextCompat.getColor(context, R.color.white));
                    binding.dateMonthTxt.setTextColor(ContextCompat.getColor(context, R.color.white));
                } else {
                    binding.mainLayout.setBackgroundResource(R.drawable.light_grey_bg);
                    binding.dayTxt.setTextColor(ContextCompat.getColor(context, R.color.black));
                    binding.dateMonthTxt.setTextColor(ContextCompat.getColor(context, R.color.black));
                }

                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dateAdapter.lastSelectedPosition = dateAdapter.selectedPosition;
                        dateAdapter.selectedPosition = position;
                        dateAdapter.notifyItemChanged(dateAdapter.lastSelectedPosition);
                        dateAdapter.notifyItemChanged(dateAdapter.selectedPosition);
                    }
                });
            }
        }
    }
}
