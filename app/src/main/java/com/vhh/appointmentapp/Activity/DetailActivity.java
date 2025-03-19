package com.vhh.appointmentapp.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.vhh.appointmentapp.Adapter.DateAdapter;
import com.vhh.appointmentapp.Adapter.TimeAdapter;
import com.vhh.appointmentapp.Domain.DoctorsModel;
import com.vhh.appointmentapp.databinding.ActivityDetailBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private DoctorsModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        item=(DoctorsModel) getIntent().getSerializableExtra("object");
        setVariable();
        InitDate();
        InitTime();
    }

    private void InitDate() {
        binding.dateView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.dateView.setAdapter(new DateAdapter(genereateDates()));
    }

    private void InitTime() {
        binding.timeView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.timeView.setAdapter(new TimeAdapter(genereateTimeSlots()));
    }

    public static List<String> genereateDates() {
        List<String> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE/dd/MMM");
        for(int i = 0; i < 7; i++) {
            dates.add(today.plusDays(i).format(formatter));
        }
        return dates;
    }

    public static List<String> genereateTimeSlots() {
        List<String> timeSlots = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        for(int i = 0; i < 24; i+=2) {
            LocalTime time = LocalTime.of(i, 0);
            timeSlots.add(time.format(formatter));
        }
        return timeSlots;
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());

        Glide.with(DetailActivity.this)
                .load(item.getPicture())
                .into(binding.img);
        binding.addressTxt.setText(item.getAddress());
        binding.nameTxt.setText(item.getName());
        binding.specialTxt.setText(item.getSpecial());
        binding.patiensTxt.setText(item.getPatiens()+"");
        binding.experienceTxt.setText(item.getExprience()+" Years");
    }
}