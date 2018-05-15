package com.github.hynra.simplemvvm.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.hynra.simplemvvm.R;
import com.github.hynra.simplemvvm.databinding.FragmentMovieBinding;
import com.github.hynra.simplemvvm.viewmodel.MainViewModel;

/**
 * Created by hynra on 15/05/2018.
 */

public class MovieFragment extends Fragment implements
        SwipeRefreshLayout.OnRefreshListener, CompletedListener{

    private MainViewModel mViewModel;
    private MovieAdapter mMovieAdapter;
    private FragmentMovieBinding mMovieFragmentBinding;


    public static MovieFragment getInstance() {
        return new MovieFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_movie,container,false);
        mMovieFragmentBinding = FragmentMovieBinding.bind(contentView);
        initData();
        return contentView;
    }


    private void initData() {
        mMovieAdapter = new MovieAdapter();
        mMovieFragmentBinding.recyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false)
        );
        mMovieFragmentBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mMovieFragmentBinding.recyclerView.setAdapter(mMovieAdapter);
        mMovieFragmentBinding.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        mMovieFragmentBinding.swipeRefreshLayout.setOnRefreshListener(this);
        mViewModel = new MainViewModel(mMovieAdapter,this);
        mMovieFragmentBinding.setViewModel(mViewModel);
    }


    @Override
    public void onRefresh() {
        mMovieAdapter.clearItems();
        mViewModel.refreshData();
    }

    @Override
    public void onCompleted() {
        if (mMovieFragmentBinding.swipeRefreshLayout.isRefreshing()) {
            mMovieFragmentBinding.swipeRefreshLayout.setRefreshing(false);
        }
    }
}
