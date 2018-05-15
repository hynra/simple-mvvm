package com.github.hynra.simplemvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.hynra.simplemvvm.R;
import com.github.hynra.simplemvvm.databinding.ItemMovieBinding;
import com.github.hynra.simplemvvm.model.entity.Movie;
import com.github.hynra.simplemvvm.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hynra on 15/05/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.BindingHolder> {

    private List<Movie> mMovies;

    public MovieAdapter() {
        mMovies = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.item_movie,parent,false);
        return new BindingHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        MovieViewModel movieViewModel = new MovieViewModel(mMovies.get(position));
        holder.itemBinding.setViewModel(movieViewModel);
    }

    @Override
    public int getItemCount() {

        return mMovies.size();
    }

    public void addItem(Movie movie) {
        mMovies.add(movie);
        notifyItemInserted(mMovies.size() - 1);
    }

    public void clearItems() {
        mMovies.clear();
        notifyDataSetChanged();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding itemBinding;
        BindingHolder(ItemMovieBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}