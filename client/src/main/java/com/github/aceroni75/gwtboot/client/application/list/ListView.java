package com.github.aceroni75.gwtboot.client.application.list;

import com.github.aceroni75.gwtboot.shared.entity.Task;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;
import org.gwtbootstrap3.client.ui.base.HasHref;

class ListView extends ViewWithUiHandlers<ListHandlers> implements ListPresenter.MyView {
    @UiField
    ListGroup tasks;

    @Inject
    ListView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setTasks(Iterable<Task> tasks) {
        this.tasks.clear();
        ListHandlers handlers = getUiHandlers();
        for(Task task : tasks) {
            ListGroupItem item = new ListGroupItem();
            Anchor a = new Anchor(task.getTitle(), HasHref.EMPTY_HREF);
            a.addClickHandler(e -> {
                handlers.viewTask(task.getId());
            });
            item.add(a);
            this.tasks.add(item);
        }
    }

    @UiHandler("add")
    public void onAddClick(ClickEvent event) {
        getUiHandlers().onAdd();
    }

    interface Binder extends UiBinder<Widget, ListView> {
    }
}
