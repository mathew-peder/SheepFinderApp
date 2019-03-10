package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import java.io.File;

/**
 * @author Mathew Peder
 *
 * Applied Computing - 20073231
 */



public class Controller
{

    private Image image;

    //Calls all items from the FXML file and initialises them
    @FXML
    BorderPane borderpane;
    @FXML
    ButtonBar buttonbar;
    @FXML
    Button openbutton;
    @FXML
    Button scanimage;
    @FXML
    Button closebutton;
    @FXML
    Button colorbutton;
    @FXML
    ImageView imageview;
    @FXML
    Slider slider;

    //method for opening an image by setting the button specified to open filechooser allowing user to open an image
    @FXML
    public Image openImage(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog( null );

        if (selectedFile != null)
        {
            image = new Image( selectedFile.toURI().toString() );
            imageview.setImage( image );
        }
        else
        {
            System.out.println( "Not a valid file" );
        }
        return image;
    }

    //method for opening an image by setting the button specified to call the changeColor method
    @FXML
    public void colorChange(ActionEvent event)
    {
        if (imageview != null)
        {
            changeColor();
        }
        else
        {
            System.out.println( "No image imported" );
        }
    }

    //makes an image a writable image in order to change the color of the image.
    private void changeColor()
    {
        PixelReader pixelReader = image.getPixelReader();

        WritableImage writableImage =
                new WritableImage(
                        (int) image.getWidth(),
                        (int) image.getHeight()
                );

        //Makes an image a writable image
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        //finds the width and height, then reads the pixel color and prints it in the console
        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {
                Color color = pixelReader.getColor( x, y );


                //sets the threshold of color and changes everything above that to white. Everything else is changed to black
                if (color.getRed() * 255 > 100 && color.getGreen() * 255 > 100 && color.getBlue() * 255 > 100)
                {
                    pixelWriter.setColor( x, y, Color.WHITE);
                }
                else
                {
                    pixelWriter.setColor( x, y, Color.BLACK );
                }
            }
        }
        //sets the writable image as the new image to be called
        imageview.setImage( writableImage );
    }

    //method for opening an image by setting the button specified to call the scanImage method
    @FXML
    public void imageScan(ActionEvent event)
    {
        if (imageview != null)
        {
            scanImage();
        }
        else
        {
            System.out.println( "No Image to scan" );
        }
    }

    //scans the image using pixelReader and finds the pixels that have been chaged to white.
    // Then sets these pixels into an array to be used by the counting method.
    private void scanImage()
    {
        PixelReader pixelReader = imageview.getImage().getPixelReader();

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        int sheepSet = 1;

        int[][] a = new int[width][height];
        int size[] = new int[10000];
        System.out.println( "Starting Image Scan" );
        int total=0;
        for (int x = 0; x < image.getWidth(); x++)
        {
            for (int y = 0; y < image.getHeight(); y++)
            {
                Color c = pixelReader.getColor( x, y );

                //Finding the white pixels and it's neighbors, if the neighbors are white, they are added to the array
                //If they are black, then the scan continues.
                if (c.equals( Color.WHITE ))
                {
                    if ((x - 1) > 0 && pixelReader.getColor( x - 1, y ).equals( c.WHITE ))
                    {
                        a[x][y] = a[x - 1][y];
                    }
                    else if ((x - 1) > 0 && (y - 1) > 0 && pixelReader.getColor( x - 1, y - 1 ).equals( c.WHITE ))
                    {
                        a[x][y] = a[x - 1][y - 1];
                    }
                    else if ((y - 1) > 0 && pixelReader.getColor( x, y - 1 ).equals( c.WHITE ))
                    {
                        a[x][y] = a[x][y - 1];
                    }
                    else if ((x + 1) < width && (y - 1) > 0 && pixelReader.getColor( x + 1, y - 1 ).equals( c.WHITE ))
                    {
                        a[x][y] = a[x + 1][y - 1];
                    }

                    else
                    {
                        a[x][y] = sheepSet++;
                    }
                    size[sheepSet] ++;

                }

            }

            if (size[sheepSet] > 15)
            {
//              System.out.print( size[sheepSet] + ":" );
                total++;
            }

        }
        System.out.println("The number of sheep in this image is approximately:" + total );
    }



    //method for using a button action to close an image from the window.
    // If there is an image present, sets image to null
    @FXML
    public void closeImage(ActionEvent event)
    {
        if (imageview != null)
        {
            imageview.setImage( null );
        }
        else
        {
            System.out.println( "No image imported" );
        }
    }

    //Work in progress. Uses a slider to change the intenity of black and white threshold.
    public void changeIntensity(MouseEvent mouseEvent)
    {
        if(imageview != null)
        {
            intenitySlider();
        }
        else
        {
            System.out.println( "No image imported" );
        }
    }

    //Work in progress.
    private void intenitySlider()
    {


    }


}








