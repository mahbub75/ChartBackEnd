package Project.Files;

import Project.Session.Session;
import Project.Session.SessionRepository;
import Project.Share.CurrentDateTime;
import Project.Share.ResponseToFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    FileRepository fileRepository;
    // Save a file properties in DB
    public ResponseToFront saveFileToDb(String fileName, String filePath, int sessionId) {
        Session session = sessionRepository.findOne(sessionId);
        FileModel fileModel =  new FileModel(fileName,filePath,session);
        fileRepository.save(fileModel);
        return new ResponseToFront(fileName + "با موفقیت ذخیره شد");
    }

}
