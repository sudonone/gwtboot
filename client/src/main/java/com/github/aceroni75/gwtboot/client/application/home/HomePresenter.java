package com.github.aceroni75.gwtboot.client.application.home;

import com.github.aceroni75.gwtboot.client.place.NameTokens;
import com.github.aceroni75.gwtboot.client.application.ApplicationPresenter;
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

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> {

    private final ResourceDelegate<TaskResource> taskDelegate;

    @Inject
    HomePresenter(EventBus eventBus, MyView view, MyProxy proxy, ResourceDelegate<TaskResource> taskDelegate) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        this.taskDelegate = taskDelegate;
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        Rest.with(taskDelegate)
                .call(TaskResource::getAllTasks)
                .onSuccess(getView()::setTasks)
                .onFailure(t -> GWT.log("Error", t))
                .apply();
    }

    interface MyView extends View {
        void setTasks(Iterable<Task> tasks);
    }

    @ProxyStandard
    @NameToken(NameTokens.HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }
}