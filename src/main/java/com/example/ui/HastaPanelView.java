package com.example.ui;

import com.example.base.ui.MainLayout;
import com.example.domain.hasta.Hasta;
import com.example.domain.hasta.HastaService;
import com.example.util.TcKimlikValidator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route(value = "hasta-panel", layout = MainLayout.class)
public class HastaPanelView extends VerticalLayout {

    public HastaPanelView(HastaService hastaService) {

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        getStyle().set(
                "background",
                "linear-gradient(180deg, #eef4ff, #e2ebff)"
        );

        VerticalLayout card = new VerticalLayout();
        card.setWidth("420px");
        card.setPadding(true);
        card.setSpacing(true);

        card.getStyle()
                .set("margin", "80px auto")
                .set("border-radius", "18px")
                .set("background", "rgba(255,255,255,0.95)")
                .set("box-shadow", "0 18px 40px rgba(0,0,0,0.12)");

        H2 title = new H2("Hasta Bilgi Sorgulama");
        title.getStyle().set("margin", "0").set("color", "#1e3a5f");

        Paragraph subtitle = new Paragraph(
                "TC Kimlik Numaranızı girerek kayıtlı bilgilerinizi görüntüleyebilirsiniz"
        );
        subtitle.getStyle().set("margin-top", "4px").set("color", "#5b6f91");

        TextField tcField = new TextField("TC Kimlik No");
        tcField.setWidthFull();
        tcField.setMaxLength(11);

        Button sorgulaBtn = new Button("Bilgilerimi Gör");
        sorgulaBtn.setWidthFull();
        sorgulaBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout resultLayout = new VerticalLayout();
        resultLayout.setPadding(true);
        resultLayout.setSpacing(false);
        resultLayout.getStyle()
                .set("margin-top", "16px")
                .set("border-radius", "14px")
                .set("background", "#f7faff");

        sorgulaBtn.addClickListener(e -> {

            resultLayout.removeAll();

            String tc = tcField.getValue().trim();

            if (tc.isEmpty()) {
                showError("TC Kimlik Numarası giriniz");
                return;
            }

            if (!TcKimlikValidator.isValid(tc)) {
                showError("Geçersiz TC Kimlik Numarası");
                return;
            }

            Optional<Hasta> hastaOpt = hastaService.findByTc(tc);

            if (hastaOpt.isEmpty()) {
                showInfo("Hasta kaydı bulunamadı");
                return;
            }

            Hasta h = hastaOpt.get();

            resultLayout.add(
                    new H2("Hasta Bilgileri"),
                    new Paragraph("Ad: " + h.getAd()),
                    new Paragraph("Soyad: " + h.getSoyad()),
                    new Paragraph("TC Kimlik No: " + h.getTcNo()),
                    new Paragraph(
                            "Sorumlu Personel: " +
                                    (h.getPersonel() != null
                                            ? h.getPersonel().getAd() + " " + h.getPersonel().getSoyad()
                                            : "-")
                    )
            );
        });

        card.add(title, subtitle, tcField, sorgulaBtn, resultLayout);
        add(card);
    }

    private void showError(String message) {
        Notification notification = Notification.show(message);
        notification.setPosition(Notification.Position.TOP_CENTER);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }

    private void showInfo(String message) {
        Notification notification = Notification.show(message);
        notification.setPosition(Notification.Position.TOP_CENTER);
    }
}
