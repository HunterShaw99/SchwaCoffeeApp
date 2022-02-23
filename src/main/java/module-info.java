module com.schwa.schwacoffe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;


    opens com.schwa.schwacoffe to javafx.fxml;
    exports com.schwa.schwacoffe;
}
