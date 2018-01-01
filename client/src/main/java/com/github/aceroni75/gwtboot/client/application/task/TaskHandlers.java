package com.github.aceroni75.gwtboot.client.application.task;

import com.gwtplatform.mvp.client.UiHandlers;

public interface TaskHandlers extends UiHandlers {
    void onEdit(Long id);

    void onDelete(Long id);

    void onBack();
}
