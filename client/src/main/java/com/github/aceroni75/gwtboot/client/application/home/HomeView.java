package com.github.aceroni75.gwtboot.client.application.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

class HomeView extends ViewImpl implements HomePresenter.MyView {

    @Inject
    HomeView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    interface Binder extends UiBinder<Widget, HomeView> {
    }
}
