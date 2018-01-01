package com.github.aceroni75.gwtboot.client.application.list;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ListHandlers extends UiHandlers {
    void viewTask(Long id);

    void onAdd();
}
