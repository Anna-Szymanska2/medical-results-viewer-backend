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

    @Override
    public String saveFile(MultipartFile file, String startPath) throws IOException {
        Path path = Paths.get(startPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        Path filePath = path.resolve(Objects.requireNonNull(file.getOriginalFilename()));

        // Poprawne zamknięcie InputStream po skopiowaniu pliku
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return filePath.toString();
    }



    @Override
    public File convert(MultipartFile file) throws IOException {
        // Używamy unikalnego folderu tymczasowego dla każdego pliku
        File tempDir = Files.createTempDirectory("temp").toFile();
        File convFile = new File(tempDir, Objects.requireNonNull(file.getOriginalFilename()));

        // Blok try-with-resources, aby upewnić się, że FileOutputStream jest zamknięty
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }

        return convFile;
    }



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

    @Override
    public boolean isTextFile(File file){
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".txt");
    }
}
