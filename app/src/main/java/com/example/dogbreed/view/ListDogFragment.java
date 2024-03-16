package com.example.dogbreed.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dogbreed.databinding.FragmentListDogBinding;
import com.example.dogbreed.model.DogBreed;
import com.example.dogbreed.viewmodel.DogAdapter;
import com.example.dogbreed.viewmodel.DogApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListDogFragment extends Fragment {
    private DogApiService apiService;
    private RecyclerView rvDogs;
    private ArrayList<DogBreed> dogBreeds;
    private DogAdapter dogAdapter;
    private FragmentListDogBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding=FragmentListDogBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        dogBreeds=new ArrayList<DogBreed>();
        dogAdapter=new DogAdapter(dogBreeds);
        binding.rvDogs.setAdapter(dogAdapter);
        binding.rvDogs.setLayoutManager(new GridLayoutManager(root.getContext(),2));
        apiService=new DogApiService();
        apiService.getDogs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<DogBreed> dogBreeds1) {
                        for(DogBreed dog: dogBreeds1)
                        {
                            dogBreeds.add(dog);

                        }
                        dogAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });

        return root;
        //return inflater.inflate(R.layout.fragment_list_dog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // binding.toolbar.inflateMenu(R.menu.menu_search);

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                dogAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

}