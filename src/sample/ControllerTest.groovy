package sample

import javafx.fxml.FXML
import javafx.scene.image.Image

import javax.swing.text.html.ImageView

/**
 * @author Mathew Peder
 *
 * Applied Computing - 20073231
 */
class ControllerTest extends groovy.util.GroovyTestCase {
    void setUp() {
        super.setUp()
    }

    void testOpenImage() {
        image = new Image( selectedFile.toURI().toString() )


    }

    void testColorChange() {
    }

    void testImageScan() {
    }

    void testCloseImage() {
    }

    void testChangeIntensity() {
    }
}
