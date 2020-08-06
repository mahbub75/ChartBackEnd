package Project.Files;

import Project.Lesson.Lesson;
import Project.Session.Session;
import Project.Session.SessionRepository;
import Project.Share.ResponseToFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileRepository fileRepository;
    @Autowired
    FileStorage fileStorage;
    @Autowired
    FileService fileService;
    @Autowired
    SessionRepository sessionRepository;

    // Get all files from DB
    @GetMapping(value = "/files")
    @CrossOrigin
    public List<FileModel> getFiles(@RequestParam("sessionId") String sessionId){
        return fileRepository.findFileModelsBySessionId(Integer.parseInt(sessionId));
    }

// Upload a file in C:/Users/javad/Desktop/New folder (7)/files
    @PostMapping(value = "/session/{sessionId}/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin
    public void uploadFile(@RequestParam("file") MultipartFile file,@PathVariable int sessionId) {
        String filePath = fileStorage.uploadFile(file);
        String fileName = file.getOriginalFilename();
        this.fileService.saveFileToDb(fileName,filePath,sessionId);
    }

    // Get a file using filename.
    @GetMapping("/file")
    @CrossOrigin
    public ChartModel getFile(@RequestParam("fileName") String fileName)  {
       FileModel f = fileRepository.findFileModelByUniqueName(fileName);
       List<Series> series = new ArrayList<Series>();
       series.add(new Series("series name"));
       return new ChartModel("title", "subtitle","yAxisTitle","xAxisTitle",series) ;  }



    // Downloads a file using filename.
    @GetMapping("/downloadFile/{fileName}")
    @CrossOrigin
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        File file = fileStorage.loadFileAsFile(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(MediaType.parseMediaType("application/txt"))
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }

    // Delete a file using filename.
    @DeleteMapping("/deleteFile/{fileId}")
    @CrossOrigin
    public void deleteFile(@PathVariable int fileId){
        FileModel file = this.fileRepository.findOne(fileId);
     this.fileRepository.delete(fileId);
        this.fileStorage.deleteFile(file.getUniqueName());

    }


}
