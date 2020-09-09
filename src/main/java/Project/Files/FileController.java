package Project.Files;

import Project.Session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileRepository fileRepository;
    @Autowired
    FileService fileService;
    @Autowired
    SessionRepository sessionRepository;


//    String url = "http://www.youtube-nocookie.com/embed/zaaU9lJ34c5?rel=0";
//    String str = url.substring(url.lastIndexOf("/") + 1, url.indexOf("?"));

    // Get all files from DB
    @GetMapping(value = "/files")
    @CrossOrigin
    public List<FileModel> getFiles(@RequestParam("sessionId") String sessionId) {
        return fileRepository.findFileModelsBySessionId(Integer.parseInt(sessionId));
    }

    // Upload a file in C:/Users/javad/Desktop/New folder (7)/files
    @PostMapping(value = "uploadFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin
    public void uploadFile(@RequestParam("files") List<MultipartFile> files) {
        for (MultipartFile file : files) {
            String fileUniqueName = file.getOriginalFilename();
            Boolean isSaved = fileService.saveFileToDb(fileUniqueName);
            if (isSaved) {
                fileService.uploadFile(file, fileUniqueName);
            }

        }

    }

    // Get a file using filename.
    @GetMapping("/file")
    @CrossOrigin
    public ChartModel getFile(@RequestParam("fileName") String fileName) {
        FileModel f = fileRepository.findFileModelByUniqueName(fileName);
        ArrayList<int[]> data1 = new ArrayList<int[]>();
        int[] d = new int[]{2, 3};
        int[] l = new int[]{4, 9};
        int[] h = new int[]{6, 2};
        int[] m = new int[]{8, 5};
        data1.add(0, d);
        data1.add(0, l);
        data1.add(0, h);
        data1.add(0, m);
        ArrayList<int[]> data2 = new ArrayList<int[]>();
        int[] d2 = new int[]{2, 6};
        int[] l2 = new int[]{3, 4};
        int[] h2 = new int[]{6, 7};
        int[] m2 = new int[]{8, 1};
        data2.add(0, d2);
        data2.add(0, l2);
        data2.add(0, h2);
        data2.add(0, m2);
        ArrayList<int[]> data3 = new ArrayList<int[]>();
        int[] d3 = new int[]{2, 1};
        int[] l3 = new int[]{3, 10};
        int[] h3 = new int[]{7, 7};
        int[] m3 = new int[]{8, 3};
        data3.add(0, d3);
        data3.add(0, l3);
        data3.add(0, h3);
        data3.add(0, m3);
        String color1= "rgba(255, 0, 0, .5)" ;
        String color2= "rgba(0, 12, 255, .5)" ;
        String color3= "rgba(9, 183, 9, .5)" ;

        List<Series> series = new ArrayList<Series>();
        series.add(new Series("canal1",data1,color1));
        series.add(new Series("canal2",data2,color2));
        series.add(new Series("canal3",data3, color3));
        return new ChartModel("title", "subtitle", "yAxisTitle", "xAxisTitle", series);
    }


    // Downloads a file using filename.
    @GetMapping("/downloadFile/{fileName}")
    @CrossOrigin
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        File file = fileService.loadFileAsFile(fileName);
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
    public void deleteFile(@PathVariable int fileId) {
        FileModel file = this.fileRepository.findOne(fileId);
        this.fileRepository.delete(fileId);
        this.fileService.deleteFile(file.getUniqueName());

    }


}
