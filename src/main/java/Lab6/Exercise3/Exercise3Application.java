package Lab6.Exercise3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
@StyleSheet("styles.css")
public class Exercise3Application implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Exercise3Application.class, args);
    }
}
