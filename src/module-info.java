module JavaFX {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.media;
	requires java.sql;
	requires javafx.base;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
}
