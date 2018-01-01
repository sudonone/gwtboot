package com.github.aceroni75.gwtboot.client.application.menu;

import com.github.aceroni75.gwtboot.client.util.Places;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import static com.github.aceroni75.gwtboot.client.place.NameTokens.HOME;
import static com.github.aceroni75.gwtboot.client.place.NameTokens.LIST;

public class MenuPresenter extends PresenterWidget<MenuPresenter.MyView> implements MenuHandlers {

    private final PlaceManager placeManager;

    @Inject
    MenuPresenter(EventBus eventBus, MyView view, PlaceManager placeManager) {
        super(eventBus, view);
        this.placeManager = placeManager;
        view.setUiHandlers(this);
    }

    @Override
    public void onHome() {
        Places.using(placeManager).reveal(HOME);
    }

    @Override
    public void onTasks() {
        Places.using(placeManager).reveal(LIST);
    }

    interface MyView extends View, HasUiHandlers<MenuHandlers> {
    }
}
