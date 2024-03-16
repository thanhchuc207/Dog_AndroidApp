package com.example.dogbreed.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dogbreed.R;
import com.example.dogbreed.databinding.FragmentDetailBinding;
import com.example.dogbreed.model.DogBreed;
import com.squareup.picasso.Picasso;


public class DetailFragment extends Fragment {
    private DogBreed dogBreed;
    private FragmentDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dogBreed = (DogBreed) getArguments().getSerializable("dogBreed");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.fragment_detail, null, false);
        View viewRoot = binding.getRoot();
        binding.setDog(dogBreed);

        Picasso.get().load(dogBreed.getUrl()).into(binding.ivAvatar);

        return viewRoot;
    }
}