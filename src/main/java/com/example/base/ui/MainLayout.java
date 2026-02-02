package com.example.base.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;

public class MainLayout extends AppLayout {

    public MainLayout() {

        /* =======================
           TITLE
        ======================== */
        H2 title = new H2("HospitalApp");
        title.getStyle()
                .set("margin", "0")
                .set("font-size", "20px")
                .set("font-weight", "600")
                .set("color", "#1e3a5f");

        /* =======================
           SUBTITLE (OPTIONAL)
        ======================== */
        Span subtitle = new Span("Hastane Yönetim Sistemi");
        subtitle.getStyle()
                .set("font-size", "12px")
                .set("color", "#5b6f91");

        /* =======================
           BRAND AREA
        ======================== */
        com.vaadin.flow.component.orderedlayout.VerticalLayout brand =
                new com.vaadin.flow.component.orderedlayout.VerticalLayout(title, subtitle);

        brand.setSpacing(false);
        brand.setPadding(false);
        brand.getStyle()
                .set("padding", "8px 16px");

        addToNavbar(brand);

        setPrimarySection(Section.NAVBAR);

        /* =======================
           NAVBAR STYLE
        ======================== */
        getElement().getStyle()
                .set("background", "rgba(83, 74, 110, 0.79)")
                .set("box-shadow", "0 2px 10px rgba(0,0,0,0.06)");
    }
}
