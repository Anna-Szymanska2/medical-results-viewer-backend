package pw.telm.telmbackend.service;

import com.pixelmed.dicom.DicomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pw.telm.telmbackend.DicomMetadataReader;
import pw.telm.telmbackend.model.*;
import pw.telm.telmbackend.repository.*;

import java.io.File;
import java.io.IOException;

import static pw.telm.telmbackend.Generators.generateLogin;
import static pw.telm.telmbackend.Generators.generatePesel;

@Service
public class DicomServiceImpl implements DicomService{
    public DicomServiceImpl(PatientRepository patientRepository, PatientLogRepository patientLogRepository, DoctorLogRepository doctorLogRepository, StudyRepository studyRepository, SeriesRepository seriesRepository) {
        this.patientRepository = patientRepository;
        this.patientLogRepository = patientLogRepository;
        this.doctorLogRepository = doctorLogRepository;
        this.studyRepository = studyRepository;
        this.seriesRepository = seriesRepository;
    }

    private final PatientRepository patientRepository;
    private final PatientLogRepository patientLogRepository;
    private final DoctorLogRepository doctorLogRepository;
    private final StudyRepository studyRepository;
    private final SeriesRepository seriesRepository;

    @Transactional
    public void addDicom(String dicomFilePath, Doctor doctor) {
        try {
            String outputPath = "src/main/resources/images";
            File images_folder = new File(outputPath);
            // Sprawdzenie, czy images_folder już istnieje
            if (!images_folder.exists()) {
                images_folder.mkdirs();
            }
            String searchString = "dicoms";

            // Znalezienie indeksu końca szukanego słowa
            int startIndex = dicomFilePath.indexOf(searchString) + searchString.length();

            // Wycięcie wszystkiego po "dicoms/"
            String resultPath = dicomFilePath.substring(startIndex);

            // Sprawdzenie, czy ciąg zawiera ".dcm" i usunięcie go
            if (resultPath.contains(".dcm")) {
                resultPath = resultPath.replace(".dcm", "");
            }
            outputPath = outputPath + resultPath;
            File final_folder = new File(outputPath);
            if (!final_folder.exists()) {
                final_folder.mkdirs();
            } else
                return;

            Patient patient = DicomMetadataReader.returnPatientFromDicom(dicomFilePath);
           // System.out.println(index + patient.getName());

            String patientName = patient.getName();

            Patient patientFromDB = patientRepository.findByName(patientName).orElse(null);
            if (patientFromDB == null) {
                patient.setDoctor(doctor);
                patient.setPesel(generatePesel(patient.getBirthDate(),patient.getSex()));
                // patientId = addPatient(patient, doctorId);
                int login = generateLogin();
                while (patientLogRepository.existsByLogin(login) || doctorLogRepository.existsByLogin(login)) {
                    login = generateLogin();
                }
                PatientLog patientLog = new PatientLog();
                patientLog.setLogin(login);
                patientLog.setPatient(patient);
                patient.setPatientLog(patientLog);
                // addPatientLog(login, patientId);
            }
            else{
                patient = patientFromDB;
            }

            Study study = DicomMetadataReader.returnStudyFromDicom(dicomFilePath);
            study.setText(false);
            Study studyFromDB = studyRepository.findByUidStudy(study.getUidStudy()).orElse(null);
            if(studyFromDB == null){
                study.setPatient(patient);
                patient.getStudies().add(study);
            }else{
                study = studyFromDB;
            }

            // int studyId = addStudy(study, patientId);
            Series series = DicomMetadataReader.returnSeriesFromDicom(dicomFilePath);
            Series seriesFromDB = seriesRepository.findByUidSeries(series.getUidSeries()).orElse(null);

            if(seriesFromDB == null){
                series.setStudy(study);
                //int seriesId = addSeries(series, studyId);
                study.getSeriesList().add(series);
            }else{
                series = seriesFromDB;
            }

            Image image = DicomMetadataReader.returnImageFromDicom(dicomFilePath);
            image.setPath(dicomFilePath);
            image.setSeries(series);
            series.getImages().add(image);
            //int imageId = addImage(image, seriesId, dicomFilePath);
            DicomMetadataReader.saveAllFrames(dicomFilePath, outputPath);
            for (int frameNumber = 0; frameNumber < image.getNumFrames(); frameNumber++) {
                Frame frame = new Frame();
                frame.setFilePath(outputPath + "/frame_" + frameNumber + ".png");
                frame.setImage(image);
                image.getFrames().add(frame);
                // addFrame(outputPath + "/frame_" + frameNumber + ".png", imageId);
            }
            patientRepository.save(patient);
        }
        catch (DicomException e) {
            System.out.println("Problem z plikiem dicom");
        }
        catch (IOException e) {
            System.out.println("Problem z plikiem");;
        }
    }

}
