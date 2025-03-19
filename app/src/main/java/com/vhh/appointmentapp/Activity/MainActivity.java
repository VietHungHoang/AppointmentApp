package com.vhh.appointmentapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.vhh.appointmentapp.Adapter.CategoryAdapter;
import com.vhh.appointmentapp.Adapter.TopDoctorAdapter;
import com.vhh.appointmentapp.ViewModel.MainViewModel;
import com.vhh.appointmentapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new MainViewModel();
        setContentView(binding.getRoot());

        initCategory();
        initTopDoctors();
    }

    private void initTopDoctors() {
        binding.progressBarDoctor.setVisibility(View.VISIBLE);
        viewModel.localDoctors().observe(this, doctorsModels -> {
            binding.doctorView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            binding.doctorView.setAdapter(new TopDoctorAdapter(doctorsModels));
            binding.progressBarDoctor.setVisibility(View.GONE);
        });
    }

    private void initCategory() {
        binding.progressBarCat.setVisibility(View.VISIBLE);

        viewModel.localCategory().observe(this, categoryModels -> {
            binding.catView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            binding.catView.setAdapter(new CategoryAdapter(categoryModels));
            binding.progressBarCat.setVisibility(View.GONE);

        });
    }
}