package com.github.aceroni75.gwtboot.client.application.list;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ListModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ListPresenter.class, ListPresenter.MyView.class, ListView.class, ListPresenter.MyProxy.class);
    }
}
