package com.github.aceroni75.gwtboot.client.application.task;

import com.github.aceroni75.gwtboot.shared.entity.Task;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.html.Text;

class TaskView extends ViewWithUiHandlers<TaskHandlers> implements TaskPresenter.MyView {
    @UiField
    Heading title;
    @UiField
    Text text;

    private Task task;

    @Inject
    TaskView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
        title.setText(task.getTitle());
        text.setText(task.getText());
    }

    @UiHandler("edit")
    public void editClick(ClickEvent event) {
        getUiHandlers().onEdit(task.getId());
    }

    @UiHandler("delete")
    public void deleteClick(ClickEvent event) {
        getUiHandlers().onDelete(task.getId());
    }

    @UiHandler("back")
    public void backClick(ClickEvent event) {
        getUiHandlers().onBack();
    }

    interface Binder extends UiBinder<Widget, TaskView> {
    }
}
