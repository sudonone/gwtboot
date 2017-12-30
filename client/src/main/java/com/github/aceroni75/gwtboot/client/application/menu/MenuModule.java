package com.github.aceroni75.gwtboot.client.application.menu;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class MenuModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindSingletonPresenterWidget(MenuPresenter.class, MenuPresenter.MyView.class, MenuView.class);
    }
}
