package com.vhh.appointmentapp.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vhh.appointmentapp.Domain.CategoryModel;
import com.vhh.appointmentapp.Domain.DoctorsModel;

import java.util.ArrayList;
import java.util.List;

public class MainRepository {
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public LiveData<List<CategoryModel>> localCategory() {
        final MutableLiveData<List<CategoryModel>> listData = new MutableLiveData<>();
        DatabaseReference ref = firebaseDatabase.getReference("Category");
        Log.d("DEBUG", "Firebase Path: " + ref.toString());
        Log.d("DEBUG", "Firebase Key: " + ref.getKey());
        Log.d("DEBUG", "Firebase Parent: " + (ref.getParent() != null ? ref.getParent().toString() : "null"));
        Log.d("DEBUG", "Firebase Root: " + ref.getRoot().toString());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<CategoryModel> lists = new ArrayList<>();
                for(DataSnapshot childSnapshot:snapshot.getChildren()) {
                    CategoryModel item = childSnapshot.getValue(CategoryModel.class);
                    if(item != null) {
                        lists.add(item);
                    }
                }
                listData.setValue(lists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Lỗi Firebase: " + error.getMessage());
            }

        });
                return listData;
    }

    public LiveData<List<DoctorsModel>> loadDoctor() {
        final MutableLiveData<List<DoctorsModel>> liveData = new MutableLiveData<>();
        DatabaseReference ref = firebaseDatabase.getReference("Doctors");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<DoctorsModel> lists = new ArrayList<>();
                for(DataSnapshot childSnapshot:snapshot.getChildren()) {
                    DoctorsModel item = childSnapshot.getValue(DoctorsModel.class);
                    if(item != null) {
                        lists.add(item);
                    }
                }
                liveData.setValue(lists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        return liveData;
    }
}
