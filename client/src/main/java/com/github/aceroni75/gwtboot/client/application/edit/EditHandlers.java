package com.github.aceroni75.gwtboot.client.application.edit;

import com.github.aceroni75.gwtboot.shared.entity.Task;
import com.gwtplatform.mvp.client.UiHandlers;

public interface EditHandlers extends UiHandlers {
    void onSave(Long id, Task task);

    void onCancel();
}
