package com.github.aceroni75.gwtboot.client.application.task;

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

import static com.github.aceroni75.gwtboot.client.application.ApplicationPresenter.SLOT_MAIN;
import static com.github.aceroni75.gwtboot.client.place.NameTokens.EDIT;
import static com.github.aceroni75.gwtboot.client.place.NameTokens.HOME;
import static com.github.aceroni75.gwtboot.client.place.NameTokens.TASK;
import static com.github.aceroni75.gwtboot.client.place.ParameterTokens.ID;

public class TaskPresenter extends Presenter<TaskPresenter.MyView, TaskPresenter.MyProxy> implements TaskHandlers {

    private final PlaceManager placeManager;
    private final ResourceDelegate<TaskResource> taskDelegate;

    @Inject
    TaskPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
                  ResourceDelegate<TaskResource> taskDelegate) {
        super(eventBus, view, proxy, SLOT_MAIN);
        this.placeManager = placeManager;
        this.taskDelegate = taskDelegate;
        view.setUiHandlers(this);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        Places.from(request).getLong(ID).ifPresent(id -> {
                Rest.using(taskDelegate)
                        .call(r -> r.getTask(id))
                        .onSuccess(getView()::setTask)
                        .onFailure(t -> GWT.log("Error", t))
                        .apply();
            }
        );
    }

    @Override
    public void onEdit(Long id) {
        Places.using(placeManager).with(ID, id).reveal(EDIT);
    }

    @Override
    public void onDelete(Long id) {
        Rest.using(taskDelegate)
                .call(r -> r.deleteTask(id))
                .onSuccess(v -> Places.using(placeManager).reveal(HOME))
                .onFailure(t -> GWT.log("Error", t))
                .apply();
    }

    @Override
    public void onBack() {
        Places.using(placeManager).reveal(HOME);
    }

    interface MyView extends View, HasUiHandlers<TaskHandlers> {
        void setTask(Task task);
    }

    @ProxyStandard
    @NameToken(value = TASK)
    interface MyProxy extends ProxyPlace<TaskPresenter> {
    }
}