package com.example.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("admin-login")
public class AdminLoginView extends VerticalLayout {

    public AdminLoginView() {

        /* ROOT */
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        getStyle().set(
                "background",
                "linear-gradient(180deg, #eef4ff, #e2ebff)"
        );


        /* LOGIN CARD */
        VerticalLayout card = new VerticalLayout();
        card.setWidth("360px");
        card.setPadding(true);
        card.setSpacing(true);

        card.getStyle()
                .set("margin", "80px auto")
                .set("border-radius", "18px")
                .set("background", "rgba(255,255,255,0.95)")
                .set("box-shadow", "0 18px 40px rgba(0,0,0,0.12)");

        /* HEADER */
        H2 title = new H2("Yönetici Girişi");
        title.getStyle()
                .set("margin", "0")
                .set("color", "#1e3a5f");

        Paragraph subtitle = new Paragraph("Yetkili kullanıcılar için erişim");
        subtitle.getStyle()
                .set("margin-top", "4px")
                .set("color", "#5b6f91");

        /* FORM */
        TextField username = new TextField("Kullanıcı Adı");
        username.setWidthFull();

        PasswordField password = new PasswordField("Şifre");
        password.setWidthFull();

        Button login = new Button("Giriş Yap");
        login.setWidthFull();
        login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        login.addClickListener(e -> {
            if ("admin".equals(username.getValue())
                    && "1234".equals(password.getValue())) {

                UI.getCurrent().navigate("admin");

            } else {
                Notification.show(
                        "Hatalı giriş bilgileri",
                        3000,
                        Notification.Position.TOP_CENTER
                );
            }
        });

        card.add(title, subtitle, username, password, login);
        add(card);
    }
}
