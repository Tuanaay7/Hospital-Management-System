package com.example.ui;

import com.example.base.ui.MainLayout;
import com.example.domain.personel.Personel;
import com.example.domain.personel.PersonelService;
import com.example.exception.DuplicateTcException;
import com.example.exception.PersonelHasHastaException;
import com.example.util.TcKimlikValidator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "admin/personel", layout = MainLayout.class)
public class PersonelView extends VerticalLayout {

    public PersonelView(PersonelService personelService) {

        setSizeFull();
        setPadding(true);
        setSpacing(true);
        setAlignItems(Alignment.CENTER);

        getStyle().set(
                "background",
                "linear-gradient(180deg, #eef4ff, #e2ebff)"
        );

        H2 title = new H2("Personel Yönetimi");
        title.getStyle().set("color", "#1e3a5f");

        FormLayout form = new FormLayout();
        form.setMaxWidth("700px");

        TextField ad = new TextField("Ad");
        TextField soyad = new TextField("Soyad");
        TextField tc = new TextField("TC Kimlik No");
        tc.setMaxLength(11);

        form.add(ad, soyad, tc);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("600px", 3)
        );

        Button ekle = new Button("Personel Ekle");
        ekle.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Grid<Personel> grid = new Grid<>(Personel.class, false);
        grid.setWidthFull();
        grid.setHeight("380px");

        grid.addColumn(Personel::getAd).setHeader("Ad");
        grid.addColumn(Personel::getSoyad).setHeader("Soyad");
        grid.addColumn(Personel::getTcNo).setHeader("TC");

        grid.setItems(personelService.findAll());

        Button sil = new Button("Seçili Personeli Sil");
        sil.addThemeVariants(ButtonVariant.LUMO_ERROR);
        sil.setEnabled(false);

        grid.asSingleSelect().addValueChangeListener(e ->
                sil.setEnabled(e.getValue() != null)
        );

        ekle.addClickListener(e -> {

            String tcVal = tc.getValue().trim();

            if (ad.isEmpty() || soyad.isEmpty() || tcVal.isEmpty()) {
                Notification.show("Tüm alanları doldurun");
                return;
            }

            if (!TcKimlikValidator.isValid(tcVal)) {
                Notification.show("Geçersiz TC");
                return;
            }

            try {

                Personel p = new Personel();
                p.setAd(ad.getValue());
                p.setSoyad(soyad.getValue());
                p.setTcNo(tcVal);

                personelService.save(p);
                grid.setItems(personelService.findAll());

                ad.clear();
                soyad.clear();
                tc.clear();

            } catch (DuplicateTcException ex) {
                Notification.show(ex.getMessage());
            }
        });

        sil.addClickListener(e -> {

            Personel selected = grid.asSingleSelect().getValue();

            if (selected != null) {
                try {

                    personelService.deleteById(selected.getId());
                    grid.setItems(personelService.findAll());
                    sil.setEnabled(false);

                } catch (PersonelHasHastaException ex) {
                    Notification.show(ex.getMessage());
                }
            }
        });

        HorizontalLayout actions = new HorizontalLayout(ekle, sil);

        add(title, form, actions, grid);
    }
}
