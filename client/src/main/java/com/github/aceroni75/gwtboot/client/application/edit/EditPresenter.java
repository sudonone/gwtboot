package com.github.aceroni75.gwtboot.client.application.edit;

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

import static com.github.aceroni75.gwtboot.client.place.NameTokens.EDIT;
import static com.github.aceroni75.gwtboot.client.place.ParameterTokens.ID;

public class EditPresenter extends Presenter<EditPresenter.MyView, EditPresenter.MyProxy> implements EditHandlers {

    private final PlaceManager placeManager;
    private final ResourceDelegate<TaskResource> taskDelegate;

    @Inject
    EditPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
                  ResourceDelegate<TaskResource> taskDelegate) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
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
        });
    }

    @Override
    public void onSave(Long id, Task task) {
        Rest.using(taskDelegate)
                .call(r -> (id != null) ? r.updateTask(id, task) : r.addTask(task))
                .onSuccess(e -> placeManager.navigateBack())
                .onFailure(t -> GWT.log("Error", t))
                .apply();
    }

    @Override
    public void onCancel() {
        placeManager.navigateBack();
    }

    interface MyView extends View, HasUiHandlers<EditHandlers> {
        void setTask(Task task);
    }

    @ProxyStandard
    @NameToken(value = EDIT)
    interface MyProxy extends ProxyPlace<EditPresenter> {
    }
}