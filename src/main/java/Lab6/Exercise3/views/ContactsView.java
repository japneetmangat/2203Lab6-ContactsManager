package Lab6.Exercise3.views;

import Lab6.Exercise3.domain.Contact;
import Lab6.Exercise3.service.ContactService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route(value = "contacts", layout = MainLayout.class)
public class ContactsView extends HorizontalLayout {

    private final ContactService contactService;

    private final Grid<Contact> grid = new Grid<>(Contact.class, false);

    private final TextField name = new TextField("Name");
    private final TextField email = new TextField("Email");
    private final ComboBox<String> status = new ComboBox<>("Status");

    private final Button saveButton = new Button("Save");
    private final Button newButton = new Button("New");
    private final Button deleteButton = new Button("Delete");

    private final Binder<Contact> binder = new Binder<>(Contact.class);

    private Contact currentContact;

    public ContactsView(ContactService contactService) {
        this.contactService = contactService;

        setSizeFull();
        setPadding(true);
        setSpacing(true);
        setAlignItems(Alignment.START);

        configureGrid();
        configureForm();
        configureButtons();

        VerticalLayout gridLayout = new VerticalLayout(grid);
        gridLayout.setSizeFull();
        gridLayout.setPadding(false);
        gridLayout.setSpacing(false);
        gridLayout.setFlexGrow(1, grid);

        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, newButton, deleteButton);
        buttonLayout.setSpacing(true);

        VerticalLayout formLayout = new VerticalLayout(name, email, status, buttonLayout);
        formLayout.setWidth("420px");
        formLayout.setPadding(false);
        formLayout.setSpacing(true);
        formLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        add(gridLayout, formLayout);
        expand(gridLayout);

        refreshGrid();
        clearForm();
    }

    private void configureGrid() {
        grid.setSizeFull();

        grid.addColumn(Contact::getId).setHeader("ID").setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Contact::getName).setHeader("Name").setAutoWidth(true).setFlexGrow(1);
        grid.addColumn(Contact::getEmail).setHeader("Email").setAutoWidth(true).setFlexGrow(1);
        grid.addColumn(Contact::getStatus).setHeader("Status").setAutoWidth(true).setFlexGrow(1);

        grid.asSingleSelect().addValueChangeListener(event -> {
            Contact selected = event.getValue();
            if (selected != null) {
                currentContact = selected;
                binder.readBean(currentContact);
            }
        });
    }

    private void configureForm() {
        name.setRequired(true);
        email.setRequired(true);
        status.setRequired(true);

        name.setWidthFull();
        email.setWidthFull();
        status.setWidthFull();

        status.setItems("New", "Active", "Inactive");

        binder.forField(name)
                .asRequired("Name is required")
                .bind(Contact::getName, Contact::setName);

        binder.forField(email)
                .asRequired("Email is required")
                .bind(Contact::getEmail, Contact::setEmail);

        binder.forField(status)
                .asRequired("Status is required")
                .bind(Contact::getStatus, Contact::setStatus);
    }

    private void configureButtons() {
        newButton.addClickListener(event -> {
            currentContact = new Contact();
            binder.readBean(currentContact);
            grid.asSingleSelect().clear();
        });

        saveButton.addClickListener(event -> {
            if (currentContact == null) {
                currentContact = new Contact();
            }

            if (binder.writeBeanIfValid(currentContact)) {
                contactService.save(currentContact);
                refreshGrid();
                clearForm();
            }
        });

        deleteButton.addClickListener(event -> {
            if (currentContact != null && currentContact.getId() != null) {
                contactService.delete(currentContact);
                refreshGrid();
                clearForm();
            }
        });
    }

    private void refreshGrid() {
        grid.setItems(contactService.findAll());
    }

    private void clearForm() {
        currentContact = new Contact();
        binder.readBean(currentContact);
    }
}