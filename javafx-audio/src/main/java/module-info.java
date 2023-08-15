module site.achun.audio.javafx.javafxaudio {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
            requires atlantafx.base;
            
    opens site.achun.audio.javafx.javafxaudio to javafx.fxml;
    exports site.achun.audio.javafx.javafxaudio;
}