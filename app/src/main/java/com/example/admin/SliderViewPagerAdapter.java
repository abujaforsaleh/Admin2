package com.example.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class SliderViewPagerAdapter extends PagerAdapter {
    Context context;

    ArrayList<Integer> images;
    ArrayList<Integer> headings;
    ArrayList<Integer> description;

    public SliderViewPagerAdapter(Context context, ArrayList<Integer> images, ArrayList<Integer> headings, ArrayList<Integer> description) {
        this.context = context;
        this.images = images;
        this.headings = headings;
        this.description = description;
    }

    LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        return headings.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.viewpager_layout_sample, container, false);
        
        ImageView imageView = view.findViewById(R.id.slide_image_Id);
        TextView heading = view.findViewById(R.id.slide_title_id);
        TextView disc = view.findViewById(R.id.slide_desc_id);

        imageView.setImageResource(images.get(position));
        heading.setText(headings.get(position));
        disc.setText(description.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }


}
