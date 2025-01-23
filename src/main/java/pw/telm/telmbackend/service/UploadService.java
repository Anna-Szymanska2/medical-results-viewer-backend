package pw.telm.telmbackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface UploadService {
    File convert(MultipartFile file) throws IOException;

    boolean checkIfDicom(File fileConverted) throws IOException;

    String saveFile(MultipartFile file) throws IOException;
}
