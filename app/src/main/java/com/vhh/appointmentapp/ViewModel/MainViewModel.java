package com.vhh.appointmentapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vhh.appointmentapp.Domain.CategoryModel;
import com.vhh.appointmentapp.Repository.MainRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final MainRepository repository;

    public MainViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<CategoryModel>> localCategory() {
        return repository.localCategory();
    }
}
