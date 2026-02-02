package com.example.ui;

import com.example.base.ui.MainLayout;
import com.example.ui.components.NavigationCard;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {

    public HomeView() {

        setSizeFull();
        setPadding(false);
        setSpacing(false);
        setAlignItems(Alignment.CENTER);

        getStyle().set(
                "background",
                "linear-gradient(180deg, #eef4ff, #e2ebff)"
        );


        VerticalLayout header = new VerticalLayout();
        header.setAlignItems(Alignment.CENTER);
        header.setSpacing(false);
        header.setPadding(true);
        header.getStyle().set("margin-top", "40px");

        H1 title = new H1("Hastane Yönetim Sistemi");
        title.getStyle()
                .set("color", "#1e3a5f")
                .set("margin-bottom", "6px");

        Paragraph subtitle = new Paragraph(
                "Hasta ve personel işlemlerini güvenli ve merkezi şekilde yönetin."
        );
        subtitle.getStyle()
                .set("color", "#5b6f91")
                .set("margin-top", "0");

        header.add(title, subtitle);

        HorizontalLayout mainCards = new HorizontalLayout();
        mainCards.setSpacing(true);
        mainCards.setJustifyContentMode(JustifyContentMode.CENTER);
        mainCards.setWidthFull();
        mainCards.setMaxWidth("820px");
        mainCards.getStyle().set("margin-top", "56px");

        NavigationCard hastaCard = new NavigationCard(
                "Hastalar",
                "Hasta kayıtları, geçmiş ve takip işlemleri",
                "hasta-panel"
        );
        hastaCard.getStyle()
                .set("background", "linear-gradient(135deg, #ffffff, #f1f6ff)")
                .set("box-shadow", "0 14px 30px rgba(63,135,255,0.15)");

        NavigationCard personelCard = new NavigationCard(
                "Personeller",
                "Yetkilendirme, görevler ve personel yönetimi",
                "personel-panel"
        );
        personelCard.getStyle()
                .set("background", "linear-gradient(135deg, #ffffff, #f1f6ff)")
                .set("box-shadow", "0 14px 30px rgba(47,111,224,0.15)");

        mainCards.add(hastaCard, personelCard);

        add(header, mainCards);
    }
}
