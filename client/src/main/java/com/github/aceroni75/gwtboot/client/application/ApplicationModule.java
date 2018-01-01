package com.github.aceroni75.gwtboot.client.application;

import com.github.aceroni75.gwtboot.client.application.edit.EditModule;
import com.github.aceroni75.gwtboot.client.application.home.HomeModule;
import com.github.aceroni75.gwtboot.client.application.list.ListModule;
import com.github.aceroni75.gwtboot.client.application.menu.MenuModule;
import com.github.aceroni75.gwtboot.client.application.task.TaskModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new MenuModule());
        install(new HomeModule());
        install(new ListModule());
        install(new TaskModule());
        install(new EditModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}