module com.ozturk.ozturkmob {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
//    requires pi4j.core;

    // allow access to classes in the following namespaces for Pi4J annotation processing
    opens com.ozturk.ozturkmob to javafx.fxml;

    exports com.ozturk.ozturkmob;
}