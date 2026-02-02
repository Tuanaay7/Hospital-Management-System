package com.example.ui;

import com.example.base.ui.MainLayout;
import com.example.domain.hasta.Hasta;
import com.example.domain.hasta.HastaService;
import com.example.domain.personel.Personel;
import com.example.domain.personel.PersonelService;
import com.example.util.TcKimlikValidator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "admin/hasta", layout = MainLayout.class)
public class HastaView extends VerticalLayout {

    public HastaView(HastaService hastaService, PersonelService personelService) {

        /* ROOT */
        setSizeFull();
        setPadding(true);
        setSpacing(true);
        setAlignItems(Alignment.CENTER);

        getStyle().set(
                "background",
                "linear-gradient(180deg, #eef4ff, #e2ebff)"
        );

        /* TITLE */
        H2 title = new H2("Hasta Yönetimi");
        title.getStyle().set("color", "#1e3a5f");

        /* FORM */
        FormLayout form = new FormLayout();
        form.setMaxWidth("800px");

        TextField ad = new TextField("Ad");
        TextField soyad = new TextField("Soyad");
        TextField tc = new TextField("TC Kimlik No");
        tc.setMaxLength(11);

        ComboBox<Personel> personelBox = new ComboBox<>("Sorumlu Personel");
        personelBox.setItems(personelService.findAll());
        personelBox.setItemLabelGenerator(p -> p.getAd() + " " + p.getSoyad());

        form.add(ad, soyad, tc, personelBox);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("600px", 2)
        );

        Button ekle = new Button("Hasta Ekle");
        ekle.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        /* GRID */
        Grid<Hasta> grid = new Grid<>(Hasta.class, false);
        grid.setWidthFull();
        grid.setHeight("380px");

        grid.addColumn(Hasta::getAd).setHeader("Ad");
        grid.addColumn(Hasta::getSoyad).setHeader("Soyad");
        grid.addColumn(Hasta::getTcNo).setHeader("TC");
        grid.addColumn(h ->
                h.getPersonel().getAd() + " " + h.getPersonel().getSoyad()
        ).setHeader("Personel");

        grid.setItems(hastaService.findAll());

        /* DELETE */
        Button sil = new Button("Seçili Hastayı Sil");
        sil.addThemeVariants(ButtonVariant.LUMO_ERROR);
        sil.setEnabled(false);

        grid.asSingleSelect().addValueChangeListener(e ->
                sil.setEnabled(e.getValue() != null)
        );

        /* ACTIONS */
        ekle.addClickListener(e -> {

            String tcVal = tc.getValue().trim();

            if (ad.isEmpty() || soyad.isEmpty() || tcVal.isEmpty() || personelBox.isEmpty()) {
                Notification.show("Tüm alanları doldurun");
                return;
            }

            if (!TcKimlikValidator.isValid(tcVal)) {
                Notification.show("Geçersiz TC");
                return;
            }

            if (hastaService.findByTc(tcVal).isPresent()) {
                Notification.show("Bu TC zaten kayıtlı");
                return;
            }

            Hasta h = new Hasta();
            h.setAd(ad.getValue());
            h.setSoyad(soyad.getValue());
            h.setTcNo(tcVal);
            h.setPersonel(personelBox.getValue());

            hastaService.save(h);
            grid.setItems(hastaService.findAll());

            ad.clear();
            soyad.clear();
            tc.clear();
            personelBox.clear();
        });

        sil.addClickListener(e -> {
            Hasta selected = grid.asSingleSelect().getValue();
            if (selected != null) {
                hastaService.deleteById(selected.getId());
                grid.setItems(hastaService.findAll());
                sil.setEnabled(false);
            }
        });

        HorizontalLayout actions = new HorizontalLayout(ekle, sil);

        add(title, form, actions, grid);
    }
}
