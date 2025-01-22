package pw.telm.telmbackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface UploadService {
    String saveFile(MultipartFile file, String startPath) throws IOException;

    File convert(MultipartFile file) throws IOException;

    boolean checkIfDicom(File fileConverted) throws IOException;
    public boolean isTextFile(File file);

}
