package com.consulatations.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.LoginForm;

/**
 * Created by Роман on 05.02.2016.
 */
public class LoginView extends CustomComponent implements View {
    public static final String NAME = "login";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LoginForm form = new LoginForm();
        form.setHeight(500,Unit.PIXELS);
        form.setWidth(500,Unit.PIXELS);
        form.addLoginListener(new LoginForm.LoginListener() {
            @Override
            public void onLogin(LoginForm.LoginEvent event) {
                getUI().getNavigator().navigateTo(ConsultationView.NAME);
            }
        });
        setCompositionRoot(form);
    }
}
