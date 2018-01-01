package com.github.aceroni75.gwtboot.client.application.edit;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class EditModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(EditPresenter.class, EditPresenter.MyView.class, EditView.class, EditPresenter.MyProxy.class);
    }
}
