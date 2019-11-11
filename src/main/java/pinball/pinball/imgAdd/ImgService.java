package pinball.pinball.imgAdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImgService {
    private static String UPLOAD_ROOT = "upload-dir";
    private final ImgRepository repository;
    private final ResourceLoader resourceLoader;
//    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ImgService(ImgRepository repository, ResourceLoader resourceLoader) {
        this.repository = repository;
        this.resourceLoader = resourceLoader;
//        this.messagingTemplate = messagingTemplate;
    }

    public Page<Image> findPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Resource findOneImage(String filename) {
        return resourceLoader.getResource("file:" + UPLOAD_ROOT + "/" + filename);
    }

    public void createImg(MultipartFile file) throws IOException {


        if (!file.isEmpty()) {

            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
            repository.save(new Image(file.getOriginalFilename()));
//            messagingTemplate.convertAndSend("/topic/newImage", file.getOriginalFilename());
        }
    }


    public void deleteImage(String filename) throws IOException {

        final Image byName = repository.findByName(filename);
        repository.delete(byName);
        Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
//        messagingTemplate.convertAndSend("/topic/deleteImage", filename);

    }

//    @Bean
//    CommandLineRunner setUp(ImgRepository repository) throws IOException {
//
//        return (args) -> {
//            FileSystemUtils.deleteRecursively(new File(UPLOAD_ROOT));
//
//            Files.createDirectory(Paths.get(UPLOAD_ROOT));
//
//            FileCopyUtils.copy("Test file", new FileWriter(UPLOAD_ROOT +  ));
//            repository.save(new Image("test"));
////
////            FileCopyUtils.copy("Test file2", new FileWriter(UPLOAD_ROOT + "/test2"));
////            repository.save(new Image("test2"));
//        };

}
