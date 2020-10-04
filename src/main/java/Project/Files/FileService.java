package Project.Files;

import Project.Session.Session;
import Project.Session.SessionRepository;
import Project.User.User;
import Project.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    @Autowired
    private Environment env;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    FileRepository fileRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FileService fileService;

    // Save a file properties in DB
    public Boolean saveFileToDb(String fileUniqueName) {
        String[] nameParts = fileUniqueName.split("&");
        String creationDate = nameParts[1];
        String teamName = nameParts[0];
        String subject = nameParts[2];
        Boolean isSaved = false;
        if (!fileRepository.existsByUniqueName(fileUniqueName)) {
            Session session;
            if(userRepository.existsByName(teamName)){
                User user = userRepository.findByName(teamName);
                Boolean isSession = sessionRepository.existsByTopicAndUser(creationDate, user);
                if (isSession) {
                    session = sessionRepository.findByTopicAndUser(creationDate, user);
                } else {
                    session = new Session(creationDate, user);
                    sessionRepository.save(session);
                }
                FileModel fileModel = new FileModel(subject, fileUniqueName, session);
                fileRepository.save(fileModel);
                isSaved=true;
            }
        }
        return isSaved;
    }

    public void uploadFile(MultipartFile file,String uniqueName) {
        try {
            Path fileStorageLocation = Paths.get(env.getProperty("file.upload-dir"))
                    .toAbsolutePath().normalize();
            Path targetLocation = fileStorageLocation.resolve(uniqueName);
            Files.copy(file.getInputStream(), targetLocation,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            System.out.println("Exception:" + ex);
        }
    }

    public void deleteFile(String fileName) {
        Path fileStorageLocation = Paths.get(env.getProperty("file.upload-dir"))
                .toAbsolutePath().normalize();
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        FileSystemUtils.deleteRecursively(filePath.toFile());
    }
    public File loadFileAsFile(String fileName) {
        return new File(env.getProperty("file.upload-dir") + "/" +fileName);
    }


    public Resource loadFileAsResource(String fileName) throws MalformedURLException {
        Path fileStorageLocation = Paths.get(env.getProperty("file.upload-dir"))
                .toAbsolutePath().normalize();
        Path filePath = fileStorageLocation.resolve(fileName+".txt").normalize();
        return new UrlResource(filePath.toUri());

    }
}
