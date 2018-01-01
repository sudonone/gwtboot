package com.github.aceroni75.gwtboot.client.application;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {

    @UiField
    HTMLPanel menu;
    @UiField
    HTMLPanel main;

    @Inject
    ApplicationView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
        bindSlot(ApplicationPresenter.SLOT_MENU, menu);
        bindSlot(ApplicationPresenter.SLOT_MAIN, main);
    }

    interface Binder extends UiBinder<Widget, ApplicationView> {
    }
}
