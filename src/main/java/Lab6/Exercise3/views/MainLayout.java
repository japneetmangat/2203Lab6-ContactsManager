package Lab6.Exercise3.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainLayout extends AppLayout {
    public MainLayout() {
        //title
        H1 title = new H1("My App");
        //router links and vertical layout
        RouterLink contactsLink = new RouterLink("Contacts", ContactsView.class);
        RouterLink dashboardLink = new RouterLink("Dashboard", DashboardView.class);
        VerticalLayout sideBar = new VerticalLayout(dashboardLink, contactsLink);
        //adding to navbar
        addToNavbar(title);
        addToDrawer(sideBar);
    }
}
