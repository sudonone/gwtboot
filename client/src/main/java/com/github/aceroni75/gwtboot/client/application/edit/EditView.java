package com.github.aceroni75.gwtboot.client.application.edit;

import com.github.aceroni75.gwtboot.shared.entity.Task;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;

class EditView extends ViewWithUiHandlers<EditHandlers> implements EditPresenter.MyView {
    @UiField
    TextBox title;
    @UiField
    TextArea text;

    private Task task;

    @Inject
    EditView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
        title.setText(task.getTitle());
        text.setText(task.getText());
    }

    @Override
    public void clearTask() {
        task = null;
        title.setText("");
        text.setText("");
    }

    @UiHandler("save")
    public void saveClick(ClickEvent event) {
        Task newTask = new Task(title.getText(), text.getText());
        getUiHandlers().onSave((task != null) ? task.getId() : null, newTask);
    }

    @UiHandler("cancel")
    public void cancelClick(ClickEvent event) {
        getUiHandlers().onCancel();
    }

    interface Binder extends UiBinder<Widget, EditView> {
    }
}
