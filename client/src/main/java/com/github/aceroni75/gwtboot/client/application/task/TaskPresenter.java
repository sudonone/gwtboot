package com.github.aceroni75.gwtboot.client.application.task;

import com.github.aceroni75.gwtboot.client.util.Places;
import com.github.aceroni75.gwtboot.client.util.Rest;
import com.github.aceroni75.gwtboot.shared.entity.Task;
import com.github.aceroni75.gwtboot.shared.resource.TaskResource;
import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static com.github.aceroni75.gwtboot.client.application.ApplicationPresenter.SLOT_MAIN;
import static com.github.aceroni75.gwtboot.client.place.NameTokens.TASK;
import static com.github.aceroni75.gwtboot.client.place.ParameterTokens.ID;

public class TaskPresenter extends Presenter<TaskPresenter.MyView, TaskPresenter.MyProxy> {

    private final ResourceDelegate<TaskResource> taskDelegate;

    @Inject
    TaskPresenter(EventBus eventBus, MyView view, MyProxy proxy, ResourceDelegate<TaskResource> taskDelegate) {
        super(eventBus, view, proxy, SLOT_MAIN);
        this.taskDelegate = taskDelegate;
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

    interface MyView extends View {
        void setTask(Task task);
    }

    @ProxyStandard
    @NameToken(value = TASK)
    interface MyProxy extends ProxyPlace<TaskPresenter> {
    }
}