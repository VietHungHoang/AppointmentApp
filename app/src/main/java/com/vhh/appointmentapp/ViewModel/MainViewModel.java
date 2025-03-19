package com.vhh.appointmentapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vhh.appointmentapp.Domain.CategoryModel;
import com.vhh.appointmentapp.Domain.DoctorsModel;
import com.vhh.appointmentapp.Repository.MainRepository;

import java.util.List;
// sdffdsfdsgg
public class MainViewModel extends ViewModel {
    private final MainRepository repository;

    public MainViewModel() {
        this.repository = new MainRepository();
    }

    public LiveData<List<CategoryModel>> localCategory() {
        return repository.localCategory();
    }
    public LiveData<List<DoctorsModel>> localDoctors() {
        return repository.loadDoctor();
    }
}
