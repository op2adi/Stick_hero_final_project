module com.example.stick_hero_final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jdk.jfr;
    requires java.desktop;
    requires junit;


    opens com.example.stick_hero_final_project to javafx.fxml;
    exports com.example.stick_hero_final_project;
}