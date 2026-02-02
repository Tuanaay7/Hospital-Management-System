package com.example.ui.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NavigationCard extends VerticalLayout {

    public NavigationCard(String title, String description, String route) {

        setPadding(true);
        setSpacing(false);
        setWidthFull();
        setMaxWidth("360px");

        getStyle()
                .set("border-radius", "18px")
                .set("padding", "24px")
                .set("background", "linear-gradient(135deg, #ffffff, #f7f9fc)")
                .set("box-shadow", "0 10px 25px rgba(0,0,0,0.08)")
                .set("cursor", "pointer")
                .set("transition", "transform 0.2s ease, box-shadow 0.2s ease");

        H3 header = new H3(title);
        header.getStyle()
                .set("margin", "0")
                .set("color", "#1e3a5f");

        Paragraph desc = new Paragraph(description);
        desc.getStyle()
                .set("margin-top", "8px")
                .set("color", "#5b6f91");

        Paragraph arrow = new Paragraph("");
        arrow.getStyle()
                .set("margin-top", "16px")
                .set("font-size", "18px")
                .set("font-weight", "600")
                .set("color", "#3f87ff");

        add(header, desc, arrow);

        // CLICK = NAVIGATE
        addClickListener(e ->
                UI.getCurrent().navigate(route)
        );

        // HOVER EFFECT
        addAttachListener(e ->
                getElement().executeJs(
                        "this.addEventListener('mouseenter',()=>{" +
                        "this.style.transform='translateY(-6px)';" +
                        "this.style.boxShadow='0 18px 40px rgba(0,0,0,0.12)';});" +
                        "this.addEventListener('mouseleave',()=>{" +
                        "this.style.transform='translateY(0)';" +
                        "this.style.boxShadow='0 10px 25px rgba(0,0,0,0.08)';});"
                )
        );
    }
}
