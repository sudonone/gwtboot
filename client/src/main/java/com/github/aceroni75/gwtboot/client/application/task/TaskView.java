package com.github.aceroni75.gwtboot.client.application.task;

import com.github.aceroni75.gwtboot.shared.entity.Task;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.html.Text;

class TaskView extends ViewImpl implements TaskPresenter.MyView {
    @UiField
    Heading title;
    @UiField
    Text text;

    @Inject
    TaskView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setTask(Task task) {
        title.setText(task.getTitle());
        text.setText(task.getText());
    }

    interface Binder extends UiBinder<Widget, TaskView> {
    }
}
