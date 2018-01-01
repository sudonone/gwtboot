package com.github.aceroni75.gwtboot.client.application.home;

import com.github.aceroni75.gwtboot.client.application.ApplicationPresenter;
import com.github.aceroni75.gwtboot.client.util.Places;
import com.github.aceroni75.gwtboot.client.util.Rest;
import com.github.aceroni75.gwtboot.shared.entity.Task;
import com.github.aceroni75.gwtboot.shared.resource.TaskResource;
import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static com.github.aceroni75.gwtboot.client.place.NameTokens.HOME;
import static com.github.aceroni75.gwtboot.client.place.NameTokens.TASK;
import static com.github.aceroni75.gwtboot.client.place.ParameterTokens.ID;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> implements HomeHandlers {

    private final PlaceManager placeManager;
    private final ResourceDelegate<TaskResource> taskDelegate;

    @Inject
    HomePresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
                  ResourceDelegate<TaskResource> taskDelegate) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        this.placeManager = placeManager;
        this.taskDelegate = taskDelegate;
        view.setUiHandlers(this);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        Rest.using(taskDelegate)
                .call(TaskResource::getAllTasks)
                .onSuccess(getView()::setTasks)
                .onFailure(t -> GWT.log("Error", t))
                .apply();
    }

    @Override
    public void viewTask(Long id) {
        Places.using(placeManager).with(ID, id.toString()).reveal(TASK);
    }

    interface MyView extends View, HasUiHandlers<HomeHandlers> {
        void setTasks(Iterable<Task> tasks);
    }

    @ProxyStandard
    @NameToken(value = HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }
}