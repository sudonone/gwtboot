package com.github.aceroni75.gwtboot.client.application.home;

import com.github.aceroni75.gwtboot.shared.entity.Task;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;

class HomeView extends ViewImpl implements HomePresenter.MyView {
    @UiField
    ListGroup tasks;

    HomeView() {
        initWidget(new Label("Hello World!"));
    }

    @Override
    public void setTasks(Iterable<Task> tasks) {
        this.tasks.clear();
        for(Task task : tasks) {
            ListGroupItem item = new ListGroupItem();
            item.add(new Anchor(task.getTitle(), "#"));
            this.tasks.add(item);
        }
    }
}
