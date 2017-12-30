package com.github.aceroni75.gwtboot.client.application.menu;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class MenuPresenter extends PresenterWidget<MenuPresenter.MyView> {

    @Inject
    MenuPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);
    }

    interface MyView extends View {
    }
}
