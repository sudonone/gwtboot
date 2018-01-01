package com.github.aceroni75.gwtboot.client.application.home;

import com.github.aceroni75.gwtboot.shared.entity.Task;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;

class HomeView extends ViewWithUiHandlers<HomeHandlers> implements HomePresenter.MyView {
    @UiField
    ListGroup tasks;

    @Inject
    HomeView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setTasks(Iterable<Task> tasks) {
        this.tasks.clear();
        HomeHandlers handlers = getUiHandlers();
        for(Task task : tasks) {
            ListGroupItem item = new ListGroupItem();
            Anchor a = new Anchor(task.getTitle(), "#");
            a.addClickHandler(e -> {
                handlers.viewTask(task.getId());
            });
            item.add(a);
            this.tasks.add(item);
        }
    }

    interface Binder extends UiBinder<Widget, HomeView> {
    }
}
