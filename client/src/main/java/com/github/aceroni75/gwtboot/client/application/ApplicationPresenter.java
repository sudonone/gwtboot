package com.github.aceroni75.gwtboot.client.application;

import com.github.aceroni75.gwtboot.client.application.menu.MenuPresenter;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.presenter.slots.PermanentSlot;
import com.gwtplatform.mvp.client.proxy.Proxy;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {

    public static final PermanentSlot<MenuPresenter> SLOT_MENU = new PermanentSlot<>();
    public static final NestedSlot SLOT_MAIN = new NestedSlot();

    private final MenuPresenter menuPresenter;

    @Inject
    ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy, MenuPresenter menuPresenter) {
        super(eventBus, view, proxy, RevealType.Root);
        this.menuPresenter = menuPresenter;
    }

    @Override
    protected void onBind() {
        super.onBind();

        setInSlot(SLOT_MENU, menuPresenter);
    }

    interface MyView extends View {
    }

    @ProxyStandard
    interface MyProxy extends Proxy<ApplicationPresenter> {
    }
}
