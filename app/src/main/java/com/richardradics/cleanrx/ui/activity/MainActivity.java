package com.richardradics.cleanrx.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.richardradics.cleanrx.R;
import com.richardradics.cleanrx.app.BaseActivity;
import com.richardradics.cleanrx.di.HasComponent;
import com.richardradics.cleanrx.di.component.DaggerUseCaseComponent;
import com.richardradics.cleanrx.di.component.UseCaseComponent;
import com.richardradics.cleanrx.mvp.presenter.MainPresenter;
import com.richardradics.cleanrx.mvp.view.MainView;
import com.richardradics.cleanrx.mvp.view.model.MainListViewModel;
import com.richardradics.cleanrx.mvp.view.model.MainModelAdapter;
import com.richardradics.cleanrx.ui.adapter.RecyclerItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by evan on 3/29/15.
 */

public class MainActivity extends BaseActivity implements MainView, HasComponent<UseCaseComponent> {

    private UseCaseComponent useCaseComponent;

    @InjectView(R.id.mainModelListView)
    RecyclerView mainRecyclerView;

    private MainModelAdapter mainModelAdapter;

    @Inject
    MainPresenter mainPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeInjector();

        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainPresenter.setView(this);

        mainRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            MainListViewModel mainListViewModel = mainModelAdapter.getItemByPosition(position);
            DetailActivity.launch(MainActivity.this, view.findViewById(R.id.image), mainListViewModel.getImageUrl(), mainListViewModel.getTitle());
        }));

    }

    @Override
    public UseCaseComponent getComponent() {
        return useCaseComponent;
    }

    private void initializeInjector() {
        this.useCaseComponent = DaggerUseCaseComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        useCaseComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainPresenter.pause();
    }

    @Override
    public void setListViewModels(List<MainListViewModel> mainListViewModelList) {
        if (mainModelAdapter == null) {
            mainModelAdapter = new MainModelAdapter();
            mainRecyclerView.setAdapter(mainModelAdapter);
        }
        mainModelAdapter.addAll(mainListViewModelList);
    }

    @Override
    public void showLoading(String message) {

    }

    @Override
    public void hideLoading(boolean sucess) {

    }

    @Override
    public void showActionLabel(String message) {

    }

    @Override
    public void hideActionLabel() {

    }
}
