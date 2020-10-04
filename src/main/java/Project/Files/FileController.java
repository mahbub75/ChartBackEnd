package Project.Files;

import Project.Session.SessionRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileRepository fileRepository;
    @Autowired
    FileService fileService;
    @Autowired
    SessionRepository sessionRepository;




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
    public ChartModel getFile(@RequestParam("fileName") String fileName) throws IOException, JSONException {
        List<Series> series = new ArrayList<>();
        File file = fileService.loadFileAsFile(fileName);
        ArrayList<Double[]> data1 = new ArrayList<Double[]>();
        ArrayList<Double[]> data2 = new ArrayList<Double[]>();
        ArrayList<Double[]> data3 = new ArrayList<Double[]>();
        ArrayList<Double[]> data4 = new ArrayList<Double[]>();
        String xtitle = "";
        String ytitle = "";
        String title = "";
       String description = "";
       String[] subTitles = new String[]{"","",""};
        String[] colors =new String[]{"rgba(255, 0, 0, .5)","rgba(255, 0, 0, .5)","rgba(255, 0, 0, .5)","rgba(25, 150, 50, .5)"};
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null){
            if(line.equals("****")){
                break;
            }
            String[] parts = line.split(":", 4);
            if(parts[0].equals("title")){
            title= parts[1];
            }
            else if(parts[0].equals("xtitle")){
            xtitle= parts[1];
            }
            else if(parts[0].equals("ytitle")){
            ytitle= parts[1];
            } else if(parts[0].equals("description")){
                description = parts[1];
            }
           else if(parts[0].equals("colors")){
               colors= parts[1].split("-");
            } else if (parts[0].equals("subTitles")){
                subTitles =  parts[1].split(",");
            }
        }
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",", 4);
            if (parts.length >= 4) { ;
                Double x1 = Double.parseDouble(parts[0].split(":",2)[0]);
                Double y1 = Double.parseDouble(parts[0].split(":",2)[1]);
                Double x2 = Double.parseDouble(parts[1].split(":",2)[0]);
                Double y2 = Double.parseDouble(parts[1].split(":",2)[1]);
                Double x3 = Double.parseDouble(parts[2].split(":",2)[0]);
                Double y3 = Double.parseDouble(parts[2].split(":",2)[1]);
                Double x4 = Double.parseDouble(parts[3].split(":",2)[0]);
                Double y4 = Double.parseDouble(parts[3].split(":",2)[1]);
                data1.add(0,new Double[]{x1,y1});
                data2.add(0, new Double[]{x2,y2});
                data3.add(0,new Double[]{x3,y3});
                data4.add(0,new Double[]{x4,y4});
            }
        }

        series.add(new Series(subTitles[0],colors[0],data1));
        series.add(new Series(subTitles[1],colors[1],data2));
        series.add(new Series(subTitles[2],colors[2],data3));
        series.add(new Series(subTitles[3],colors[3],data4));
        ChartContent chartContent = new ChartContent(title, ytitle, xtitle, series);
   return new ChartModel(chartContent,description);
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
