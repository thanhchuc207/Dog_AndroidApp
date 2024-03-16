package com.example.dogbreed.viewmodel;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogbreed.R;
import com.example.dogbreed.databinding.DogItemBinding;
import com.example.dogbreed.help.OnSwipeTouchListener;
import com.example.dogbreed.model.DogBreed;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> implements Filterable {
    private ArrayList<DogBreed> dogBreedList, dogBreedCopy;

    public DogAdapter(ArrayList<DogBreed> dogBreeds)
    {
        this.dogBreedList=dogBreeds;
        dogBreedCopy=dogBreeds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_item,parent,false);   // R.layout.contact_item là đường dẫn tới file xml của item thuộc //list đã thiết kế ở B4
        return new ViewHolder(view);

     /* using DataBinding
    DogItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.dog_item, parent,false);
        return new ViewHolder(binding);
     */

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(dogBreedList.get(position).getName());
        holder.tvOrigin.setText(dogBreedList.get(position).getOrigin());
        holder.layoutName.setText(dogBreedList.get(position).getName());
        holder.layoutLifeSpan.setText(dogBreedList.get(position).getLifeSpan());
        holder.layoutOrigin.setText(dogBreedList.get(position).getOrigin());

        Picasso.get().load(dogBreedList.get(position).getUrl()).into(holder.ivAvatar);
        /*
        holder.binding.setDog(dogBreedList.get(position));
                Picasso.get().load(dogBreedList.get(position).getUrl()).into(holder.binding.ivAvatar);

        */
    }

    @Override
    public int getItemCount() {
        return dogBreedList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String input=constraint.toString().toLowerCase();
                List<DogBreed> filteredDog= new ArrayList<>();

                if(!input.isEmpty())
                {
                    for(DogBreed dog:dogBreedCopy)
                    {
                        if(dog.getName().toString().toLowerCase().contains(input)){
                            filteredDog.add(dog);
                        }
                    }
                }
                else {
                    filteredDog.addAll(dogBreedCopy);
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values=filteredDog;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dogBreedList=new ArrayList<>();
                dogBreedList.addAll((List)results.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        TextView tvOrigin;
        ImageView ivAvatar;
        LinearLayout layout1,layout2;
        TextView layoutName,layoutLifeSpan,layoutOrigin;
        ImageButton btnLike, btnUnlike;
        LinearLayout layoutLike, layoutUnlike;

        public ViewHolder(View view)
        {	super(view);
            layout1=view.findViewById(R.id.layout1);
            layout2=view.findViewById(R.id.layout2);

            tvName=view.findViewById(R.id.tv_name);
            tvOrigin=view.findViewById(R.id.tv_origin);
            ivAvatar=view.findViewById(R.id.iv_avatar);

            layoutName=view.findViewById(R.id.layout2_name);
            layoutLifeSpan=view.findViewById(R.id.layout2_life_span);
            layoutOrigin=view.findViewById(R.id.layout2_origin);

            btnLike=view.findViewById(R.id.btn_like);
            btnUnlike=view.findViewById(R.id.btn_unlike);
            layoutLike=view.findViewById(R.id.layout_like);
            layoutUnlike=view.findViewById(R.id.layout_unlike);

            //Sự kiện click vào 1 item của recyclerview
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DogBreed dog=dogBreedList.get(getAdapterPosition());
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("dogBreed",dog);//cần implements Serializable cho class DogBreed
                    Navigation.findNavController(v).navigate(R.id.detailFragment,bundle);

                }
            });
            /*
            //Sự kiện click vào 1 item của recyclerview
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DogBreed dog=dogBreedList.get(getAdapterPosition());
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("dogBreed",dog);//cần implements Serializable cho class DogBreed
                    Navigation.findNavController(v).navigate(R.id.detailFragment,bundle);

                }
            });
            */

            itemView.setOnTouchListener(new OnSwipeTouchListener(){
                @Override
                public void onClick() {
                    DogBreed dog=dogBreedList.get(getAdapterPosition());
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("dogBreed",dog);//cần implements Serializable cho class DogBreed
                    Navigation.findNavController(itemView).navigate(R.id.detailFragment,bundle);
                    super.onClick();
                }

                @Override
                public void onSwipeLeft() {
                    if(layout1.getVisibility()==View.VISIBLE)
                    {
                        layout1.setVisibility(View.GONE);
                        layout2.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        layout2.setVisibility(View.GONE);
                        layout1.setVisibility(View.VISIBLE);
                    }
                    super.onSwipeLeft();
                }
            });
            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(layoutLike.getVisibility()==View.VISIBLE)
                   {
                       layoutLike.setVisibility(View.GONE);
                       layoutUnlike.setVisibility(View.VISIBLE);
                   }
                }
            });
            btnUnlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(layoutUnlike.getVisibility()==View.VISIBLE)
                    {
                        layoutUnlike.setVisibility(View.GONE);
                        layoutLike.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
        /* using DataBinding
            public DogItemBinding binging;
            public ViewHolder(DogItemBinding itemBinding)
            {
                super(itemBinding.getRoot());
                this.binging=itemBinding;
            }
            binding.ivAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DogBreed dog=dogBreedList.get(getAdapterPosition());
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("dogBreed",dog);//cần implements Serializable cho class DogBreed
                    Navigation.findNavController(v).navigate(R.id.detailFragment,bundle);
                }
            });
         */

    }

}

