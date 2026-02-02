package com.example.ui;

import com.example.base.ui.MainLayout;
import com.example.ui.components.NavigationCard;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "admin", layout = MainLayout.class)
public class AdminHomeView extends VerticalLayout {

    public AdminHomeView() {


        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setSpacing(false);

        getStyle().set(
                "background",
                "linear-gradient(180deg, #eef4ff, #e2ebff)"
        );



        VerticalLayout container = new VerticalLayout();
        container.setWidthFull();
        container.setMaxWidth("1100px");
        container.setPadding(true);
        container.setAlignItems(Alignment.CENTER);
        container.setSpacing(true);
        container.getStyle()
                .set("margin-top", "48px")
                .set("margin-bottom", "48px");


        H1 title = new H1("Yönetim Paneli");
        title.getStyle()
                .set("color", "#1e3a5f")
                .set("margin-bottom", "4px");

        Paragraph subtitle = new Paragraph(
                "Sistem modüllerini buradan yönetin"
        );
        subtitle.getStyle()
                .set("color", "#5b6f91")
                .set("margin-top", "0");
        

        HorizontalLayout cards = new HorizontalLayout();
        cards.setWidthFull();
        cards.setSpacing(true);
        cards.setJustifyContentMode(JustifyContentMode.CENTER);

        NavigationCard hastaYonetim = new NavigationCard(
                "Hasta Yönetimi",
                "Hasta ekleme, silme ve listeleme işlemleri",
                "admin/hasta"
        );

        NavigationCard personelYonetim = new NavigationCard(
                "Personel Yönetimi",
                "Personel ekleme, silme ve listeleme işlemleri",
                "admin/personel"
        );

        cards.add(hastaYonetim, personelYonetim);

 
        container.add(title, subtitle, cards);
        add(container);
    }
}
