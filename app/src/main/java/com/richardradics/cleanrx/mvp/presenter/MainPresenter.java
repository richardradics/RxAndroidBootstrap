package com.richardradics.cleanrx.mvp.presenter;

import com.richardradics.cleanrx.app.CleanErrorHandler;
import com.richardradics.cleanrx.domain.City;
import com.richardradics.cleanrx.exception.GetCitiesException;
import com.richardradics.cleanrx.interactor.GetCitiesUseCase;
import com.richardradics.cleanrx.mvp.view.MainView;
import com.richardradics.cleanrx.mvp.view.model.MainListViewModel;
import com.richardradics.core.interactor.DefaultSubscriber;
import com.richardradics.core.mvp.Presenter;
import com.richardradics.core.mvp.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by radicsrichard on 15. 06. 01..
 */
public class MainPresenter extends DefaultSubscriber<List<City>> implements Presenter {


    private GetCitiesUseCase getCitiesUseCase;

    private MainView mainView;

    @Inject
    protected CleanErrorHandler cleanErrorHandler;

    @Inject
    public MainPresenter(GetCitiesUseCase getCitiesUseCase) {
        this.getCitiesUseCase = getCitiesUseCase;
    }


    @Override
    public void setView(View view) {
        this.mainView = (MainView) view;
        initView();
    }

    private void initView() {
        try {
            getCitiesUseCase.execute(this);
            mainView.showLoading("Loading...");
        }catch (Exception e){
            cleanErrorHandler.logException(e);
        }
    }

    @Override
    public void resume() {
        //no-op
    }

    @Override
    public void pause() {
        //no-op
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        cleanErrorHandler.handlerError(new GetCitiesException());
        mainView.hideLoading(false);
    }

    @Override
    public void onNext(List<City> cities) {
        super.onNext(cities);
        mainView.setListViewModels(convertToMainModel(cities));
    }

    public List<MainListViewModel> convertToMainModel(List<City> cityList) {
        List<MainListViewModel> modelList = new ArrayList<>();
        try {
            int pictureId = 0;

            for (City item : cityList) {
                MainListViewModel mainListViewModel = new MainListViewModel();

                mainListViewModel.setId(item.getId());
                mainListViewModel.setImageUrl("http://lorempixel.com/400/200/city/" + (pictureId + 1));
                mainListViewModel.setTitle(item.getName());

                modelList.add(mainListViewModel);

                pictureId++;
                if (9 < pictureId) { //lorempixels gives only 10 image :)
                    pictureId = 0;
                }
            }
        } catch (Exception e) {

        }
        return modelList;
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        cleanErrorHandler.showSnackbar("Items loaded!");
        mainView.hideLoading(true);
    }
}
