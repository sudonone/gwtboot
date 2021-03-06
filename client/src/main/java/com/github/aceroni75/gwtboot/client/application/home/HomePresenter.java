package com.github.aceroni75.gwtboot.client.application.home;

import com.github.aceroni75.gwtboot.client.application.ApplicationPresenter;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import static com.github.aceroni75.gwtboot.client.place.NameTokens.HOME;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> {

    @Inject
    HomePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
    }

    interface MyView extends View {
    }

    @ProxyStandard
    @NameToken(value = HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }
}