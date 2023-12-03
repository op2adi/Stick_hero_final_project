module com.example.stick_hero_final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jdk.jfr;


    opens com.example.stick_hero_final_project to javafx.fxml;
    exports com.example.stick_hero_final_project;
}