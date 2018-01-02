package com.github.aceroni75.gwtboot.client.application.list;

import com.github.aceroni75.gwtboot.client.application.ApplicationPresenter;
import com.github.aceroni75.gwtboot.shared.entity.Task;
import com.github.aceroni75.gwtboot.shared.resource.TaskResource;
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

import java.util.Collections;

import static com.github.aceroni75.gwtboot.client.place.NameTokens.*;
import static com.github.aceroni75.gwtboot.client.place.ParameterTokens.ID;
import static com.github.aceroni75.gwtboot.client.util.Places.using;
import static com.github.aceroni75.gwtboot.client.util.Rest.popupAndLog;
import static com.github.aceroni75.gwtboot.client.util.Rest.using;

public class ListPresenter extends Presenter<ListPresenter.MyView, ListPresenter.MyProxy> implements ListHandlers {

    private final PlaceManager placeManager;
    private final ResourceDelegate<TaskResource> taskDelegate;

    @Inject
    ListPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
                  ResourceDelegate<TaskResource> taskDelegate) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        this.placeManager = placeManager;
        this.taskDelegate = taskDelegate;
        view.setUiHandlers(this);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        getView().setTasks(Collections.emptyList());
        using(taskDelegate)
                .call(TaskResource::getAllTasks)
                .onSuccess(getView()::setTasks)
                .onFailure(popupAndLog("Cannot retrieve task list"))
                .apply();
    }

    @Override
    public void viewTask(Long id) {
        using(placeManager).with(ID, id).reveal(TASK);
    }

    @Override
    public void onAdd() {
        using(placeManager).reveal(ADD);
    }

    @Override
    public void onRefresh() {
        placeManager.revealCurrentPlace();
    }

    interface MyView extends View, HasUiHandlers<ListHandlers> {
        void setTasks(Iterable<Task> tasks);
    }

    @ProxyStandard
    @NameToken(value = LIST)
    interface MyProxy extends ProxyPlace<ListPresenter> {
    }
}