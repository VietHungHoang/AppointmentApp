package com.vhh.appointmentapp.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.vhh.appointmentapp.Adapter.CategoryAdapter;
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