package Lab6.Exercise3.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)

public class DashboardView extends VerticalLayout {
    public DashboardView() {
        add(new H1("Dashboard"));
    }
}
