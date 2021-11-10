package com.uiolert.stars_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uiolert.stars_.engine.EngineWallpaper;
import com.uiolert.stars_.modes.ball.ModeBalls;
import com.uiolert.stars_.modes.star.ModeStar;

import java.util.ArrayList;

public class AdapterViewPager2 extends RecyclerView.Adapter<AdapterViewPager2.ViewPagerVH> {
    ArrayList<Mode> modes = new ArrayList<>();
    private Context context;

    public AdapterViewPager2(Context context) {
        this.context = context;
    }

    public ArrayList<Mode> getModes() {
        return modes;
    }

    public void setModes(ArrayList<Mode> modes) {
        this.modes = modes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewPagerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_surface_view, parent, false);
        return new ViewPagerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerVH holder, int position) {
        Mode mode = modes.get(position);
        EngineWallpaper engineWallpaper = new EngineWallpaper(holder.surfaceView.getHolder(),context,mode);
    }

    @Override
    public int getItemCount() {
        return modes.size();
    }

    class ViewPagerVH extends RecyclerView.ViewHolder{
        SurfaceView surfaceView;
        public ViewPagerVH(@NonNull View itemView) {
            super(itemView);
            surfaceView = itemView.findViewById(R.id.surface);
        }
    }
}
