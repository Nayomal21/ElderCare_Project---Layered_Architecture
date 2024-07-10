package lk.ijse.repository;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MedicalReportSave {

    public static Boolean add(List<File> selectedFiles, String isSavedE) {
        int numOfImages= selectedFiles.size();
        System.out.println(numOfImages);
        int saved = 0;
        String outputFormat = "png"; // or any other supported format, e.g., "jpg"


        List<String> supportedFormats = Arrays.asList(ImageIO.getWriterFormatNames());

        if (supportedFormats.contains("bmp")) {

            outputFormat = "bmp";

        } else if (supportedFormats.contains("jpg")) {

            outputFormat = "jpg";

        } else if (supportedFormats.contains("jpeg")) {

            outputFormat = "jpeg";

        }

        File outputDirectory = new File("/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/"+isSavedE+"/medicalReports");
        System.out.println(selectedFiles);

        if (selectedFiles != null) {
            for (File selectedFile : selectedFiles) {
                Image image = new Image(selectedFile.toURI().toString());

                try {
                    File outputFile = new File(outputDirectory, "saved_image_" + System.currentTimeMillis() + outputFormat);

                    boolean png = ImageIO.write(SwingFXUtils.fromFXImage(image, null), outputFormat, outputFile);
                    if (png) saved++;

                    System.out.println("Image saved to " + outputFile.getAbsolutePath());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return  numOfImages==saved;
    }

    public static Boolean add(List<File> selectedFiles, String isSavedE,int x) {
        int numOfImages= selectedFiles.size();
        System.out.println(numOfImages+"-"+isSavedE);
        int saved = 0;

        File outputDirectory = new File("/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Employee/"+isSavedE);
        System.out.println(selectedFiles);

        if (selectedFiles != null) {
            for (File selectedFile : selectedFiles) {
                Image image = new Image(selectedFile.toURI().toString());

                try {
                    File outputFile = new File(outputDirectory, "saved_image_" + System.currentTimeMillis() + ".png");

                    boolean png = ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outputFile);
                    if (png) saved++;

                    System.out.println("Image saved to " + outputFile.getAbsolutePath());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return  numOfImages==saved;
    }
}
