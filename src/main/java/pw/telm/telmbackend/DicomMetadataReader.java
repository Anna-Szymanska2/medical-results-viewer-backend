package pw.telm.telmbackend;

import com.pixelmed.dicom.*;
import com.pixelmed.display.SourceImage;
import pw.telm.telmbackend.model.Image;
import pw.telm.telmbackend.model.Patient;
import pw.telm.telmbackend.model.Series;
import pw.telm.telmbackend.model.Study;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.Objects;

import static pw.telm.telmbackend.DataConverter.convertDateFormat;
import static pw.telm.telmbackend.DataConverter.convertTimeFormat;


public class DicomMetadataReader {

    static AttributeList list = new AttributeList();
    public static void main(String[] args) throws IOException, DicomException {
        String path = "src/main/resources/dicoms/dicom2.dcm";

        System.out.println(returnPatientFromDicom(path));
        System.out.println(returnStudyFromDicom(path));
        System.out.println(returnSeriesFromDicom(path));
        System.out.println(returnImageFromDicom(path));
    }


    public static Patient returnPatientFromDicom(String dicomFilePath) throws IOException, DicomException {
        File dicomFile = new File(dicomFilePath);

        Patient patient = new Patient();

        list.read(dicomFile);
        patient.setName(getTagInformation(TagFromName.PatientName));
        patient.setSex(getTagInformation(TagFromName.PatientSex));
        try {
            patient.setBirthDate(Date.valueOf(convertDateFormat(getTagInformation(TagFromName.PatientBirthDate))));
        }
        catch (IllegalArgumentException e) {
            patient.setBirthDate(null);
        }

        list.clear();
        return patient;
    }


    public static Study returnStudyFromDicom(String dicomFilePath) throws IOException, DicomException {
        File dicomFile = new File(dicomFilePath);

        Study study = new Study();

        list.read(dicomFile);
        study.setUidStudy(getTagInformation(TagFromName.StudyInstanceUID));
        study.setDescription(getTagInformation(TagFromName.StudyDescription));
        try {
            study.setStudyTime(Time.valueOf(convertTimeFormat(getTagInformation(TagFromName.StudyTime))));
        }
        catch (IllegalArgumentException e) {
            study.setStudyTime(null);
        }
        try {
            study.setStudyDate(Date.valueOf(convertDateFormat(getTagInformation(TagFromName.StudyDate))));
        }
        catch (IllegalArgumentException e) {
            study.setStudyDate(null);
        }

        list.clear();
        return study;
    }

    public static Series returnSeriesFromDicom(String dicomFilePath) throws IOException, DicomException {
        File dicomFile = new File(dicomFilePath);

        Series series = new Series();

        list.read(dicomFile);
        series.setUidSeries(getTagInformation(TagFromName.SeriesInstanceUID));
        series.setManufacturer(getTagInformation(TagFromName.Manufacturer));
        series.setModality(getTagInformation(TagFromName.Modality));
        series.setNumber(Integer.valueOf(getTagInformation(TagFromName.SeriesNumber)));

        list.clear();
        return series;
    }


    public static Boolean ifNM(File file) throws DicomException, IOException {
        list.read(file);
        return Objects.equals(getTagInformation(TagFromName.Modality), "NM");
    }


    public static Image returnImageFromDicom(String dicomFilePath) throws IOException, DicomException {
        File dicomFile = new File(dicomFilePath);

        Image image = new Image();

        list.read(dicomFile);
        image.setType(getTagInformation(TagFromName.ImageType));
        image.setPixelSpacing(getTagInformation(TagFromName.PixelSpacing));
       // image.setFramePointer(getTagInformation(TagFromName.FrameIncrementPointer));
        image.setPhotometricInterpretation(getTagInformation(TagFromName.PhotometricInterpretation));
        try { image.setRowsImage(Integer.valueOf(getTagInformation(TagFromName.Rows))); } catch (Exception ignored) {}
        try { image.setColumnsImage(Integer.valueOf(getTagInformation(TagFromName.Columns))); } catch (Exception ignored) {}
        try { image.setBitsAllocated(Integer.valueOf(getTagInformation(TagFromName.BitsAllocated))); } catch (Exception ignored) {}
        try { image.setBitsStored(Integer.valueOf(getTagInformation(TagFromName.BitsStored))); } catch (Exception ignored) {}
        try { image.setHighBit(Integer.valueOf(getTagInformation(TagFromName.HighBit))); } catch (Exception ignored) {}
        try { image.setNumFrames(Integer.valueOf(getTagInformation(TagFromName.NumberOfFrames))); } catch (Exception ignored) {
            image.setNumFrames(1);
        }
        try { image.setPixelRepresentation(Integer.valueOf(getTagInformation(TagFromName.PixelRepresentation))); } catch (Exception ignored) {}
        try { image.setSamplePerPixel(Integer.valueOf(getTagInformation(TagFromName.SamplesPerPixel))); } catch (Exception ignored) {}

        list.clear();
        return image;
    }


    public static void saveAllFrames(String dicomFilePath, String outputFilePath) {
        try {
            AttributeList list = new AttributeList();
            // Czytanie wszystkich tagów z pliku dicom
            list.read(new File(dicomFilePath));
            SourceImage sourceImage = new SourceImage(list);
            int totalFrames = sourceImage.getNumberOfFrames();
            int width = sourceImage.getWidth();
            int height = sourceImage.getHeight();

            // Zebranie wartosci wszystkich pikseli dicoma
            OtherWordAttribute pixelAttribute = (OtherWordAttribute) (list.get(TagFromName.PixelData));
            short[] data = pixelAttribute.getShortValues();

            double[] doubleData = new double[data.length];
            for (int i = 0; i < data.length; i++) {
                doubleData[i] = data[i];
            }

            // Znajdowanie minimalnej i maksymalnej wartosci ze wszystkich obrazów
            double maxValue = 0;
            double minValue = Double.MAX_VALUE;
            for (double value : data) {
                if (value > maxValue) {
                    maxValue = value;
                }
                if (value < minValue) {
                    minValue = value;
                }
            }

            // Normalizacja obrazów do wartosci pikseli <0, 155>

            for (int frameNumber = 0; frameNumber < totalFrames; frameNumber++) {
                double[] frameData = Arrays.copyOfRange(doubleData, frameNumber * width * height, (frameNumber+1) * width * height);
                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int index = y * width + x;
                        if (index < frameData.length) {
                            int normalizedPixel = (int) (255 * frameData[index] / maxValue);
                            bufferedImage.getRaster().setSample(x, y, 0, normalizedPixel);
                        }
                    }
                }
                // Zapis obrazu do pliku png
                File outputfile = new File(outputFilePath + "/frame_" + frameNumber + ".png");
                ImageIO.write(bufferedImage, "png", outputfile);
            }

        } catch (DicomException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static String getTagInformation(AttributeTag attrTag) {
        return Attribute.getDelimitedStringValuesOrDefault(list, attrTag, null);
    }

}

