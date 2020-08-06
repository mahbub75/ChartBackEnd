package Project.Files;

import Project.Share.CurrentDateTime;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileStorage {
    @Autowired
    private Environment env;
    @Autowired
    private CurrentDateTime currentDateTime;

    public String uploadFile(MultipartFile file) {
        // rename to unique name
        String fileName = file.getOriginalFilename();
        String extention = FilenameUtils.getExtension(fileName); // returns "txt"
        String fileNameWithoutExt = FilenameUtils.removeExtension(fileName);
        String uniqueName = StringUtils.cleanPath(fileNameWithoutExt + currentDateTime.value()+"."+extention);

        // Normalize file name
        System.out.println(uniqueName);
        try {
            Path fileStorageLocation = Paths.get(env.getProperty("file.upload-dir"))
                    .toAbsolutePath().normalize();
            Path targetLocation = fileStorageLocation.resolve(uniqueName);
            Files.copy(file.getInputStream(), targetLocation,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            System.out.println("Exception:" + ex);
        }
        return uniqueName;
    }

    public void deleteFile(String fileName) {
        Path fileStorageLocation = Paths.get(env.getProperty("file.upload-dir"))
                .toAbsolutePath().normalize();
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        FileSystemUtils.deleteRecursively(filePath.toFile());
    }
    public File loadFileAsFile(String fileName) {
        return new File(env.getProperty("file.upload-dir") + "/" +fileName +".txt");
    }


    public Resource loadFileAsResource(String fileName) throws MalformedURLException {
        Path fileStorageLocation = Paths.get(env.getProperty("file.upload-dir"))
                .toAbsolutePath().normalize();
        Path filePath = fileStorageLocation.resolve(fileName+".txt").normalize();
        return new UrlResource(filePath.toUri());

    }
}
