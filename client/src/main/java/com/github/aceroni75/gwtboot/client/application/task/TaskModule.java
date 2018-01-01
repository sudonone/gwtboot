package com.github.aceroni75.gwtboot.client.application.task;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TaskModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(TaskPresenter.class, TaskPresenter.MyView.class, TaskView.class, TaskPresenter.MyProxy.class);
    }
}
