package pw.telm.telmbackend.service;

import com.pixelmed.dicom.DicomException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class UploadServiceImpl implements UploadService{
    /**
     * Saves the uploaded file to a predefined directory.
     *
     * @param file the {@link MultipartFile} to be saved
     * @return a {@link String} representing the path where the file is saved
     * @throws IOException    if an I/O exception occurs during file saving
     * @throws DicomException if the uploaded file is not a valid DICOM file
     */
    @Override
    public String saveFile(MultipartFile file) throws IOException {
        Path dicomPath = Paths.get("src/main/resources/dicoms/");
        if (!Files.exists(dicomPath)) {
            Files.createDirectories(dicomPath);
        }
        Path filePath = dicomPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return filePath.toString();
    }

    /**
     * Converts the uploaded multipart file to a {@link File}.
     *
     * @param file the {@link MultipartFile} to be converted
     * @return a {@link File} representing the converted file
     * @throws IOException if an I/O exception occurs during file conversion
     */
    @Override
    public File convert(MultipartFile file) throws IOException {
        File tempDir = Files.createTempDirectory("dicom_temp").toFile();
        File convFile = new File(tempDir, Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }

    /**
     * Checks if the specified file is a valid DICOM file.
     *
     * @param file the {@link File} to be checked
     * @return {@code true} if the file is a DICOM file; {@code false} otherwise
     */
    @Override
    public boolean checkIfDicom(File file) throws IOException {
        byte[] asciiKey = new byte[]{68, 73, 67, 77};   //DICM in ASCII

        try (RandomAccessFile header = new RandomAccessFile(file, "r")) {
            byte[] bytes = new byte[132];
            header.read(bytes);

            for (int i = 0; i < asciiKey.length; i++) {
                if (asciiKey[i] != bytes[128 + i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
