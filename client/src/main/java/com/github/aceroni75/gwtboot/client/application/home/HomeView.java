package com.github.aceroni75.gwtboot.client.application.home;

import com.google.gwt.user.client.ui.Label;
import com.gwtplatform.mvp.client.ViewImpl;

class HomeView extends ViewImpl implements HomePresenter.MyView {
    HomeView() {
        initWidget(new Label("Hello World!"));
    }
}
