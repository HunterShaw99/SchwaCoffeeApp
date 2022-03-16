module com.schwa.schwacoffe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires aws.java.sdk.sqs;


    opens com.schwa.schwacoffe to javafx.fxml;
    exports com.schwa.schwacoffe;
    exports com.schwa.schwacoffe.models;
    opens com.schwa.schwacoffe.models to javafx.fxml;
    exports com.schwa.schwacoffe.core.controllers;
    opens com.schwa.schwacoffe.core.controllers to javafx.fxml;
}
