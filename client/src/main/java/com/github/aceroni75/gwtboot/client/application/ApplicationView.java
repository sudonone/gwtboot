package com.github.aceroni75.gwtboot.client.application;

import com.google.gwt.user.client.ui.SimplePanel;
import com.gwtplatform.mvp.client.ViewImpl;

class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {

    ApplicationView() {
        SimplePanel main = new SimplePanel();

        initWidget(main);
        bindSlot(ApplicationPresenter.SLOT_MAIN, main);
    }
}
