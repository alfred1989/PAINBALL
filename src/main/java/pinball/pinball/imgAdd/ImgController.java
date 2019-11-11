package pinball.pinball.imgAdd;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImgController {

    private static final String BASE_PATH = "/images";
    private static final String FILENAME = "{filename:.+}";

    private final  ImgService imgService;

    @Autowired
    public ImgController(ImgService imgService){

        this.imgService = imgService;
    }

    @RequestMapping(value = "/addImg")
    public String indexImg(Model model, Pageable pageable){
        final Page<Image> page = imgService.findPage(pageable);
        model.addAttribute("page", page);
        return "addImg" ;
    }

    @RequestMapping(value = "/IMG")
    public String xImg(Model model, Pageable pageable){
        final Page<Image> page = imgService.findPage(pageable);
        model.addAttribute("page", page);
        return "IMG" ;
    }


    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + "/" + FILENAME + "/raw")
    @ResponseBody
    public ResponseEntity<?> oneRawImage(@PathVariable String filename) {
        try {


            Resource file = imgService.findOneImage(filename);
            return ResponseEntity.ok()
                    .contentLength(file.contentLength())
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new InputStreamResource(file.getInputStream()));
        }catch (IOException e){
            return ResponseEntity.badRequest()
                    .body("Could't find" + filename + "=>" + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = BASE_PATH)

    public String createFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,Model model, Pageable pageable ) {

        try {
            imgService.createImg(file);
            final Page<Image> page = imgService.findPage(pageable);
            model.addAttribute("page", page);
            return "addImg";
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("flash.message", "Failed to upload" + file.getOriginalFilename() + "=>" + e.getMessage());
        }
        return "addImg";
    }



    @RequestMapping(method = RequestMethod.DELETE, value = BASE_PATH + "/" + FILENAME)
    public String deleteFile(@PathVariable String filename, RedirectAttributes redirectAttributes, Model model, Pageable pageable) {
        try {
            imgService.deleteImage(filename);
            final Page<Image> page = imgService.findPage(pageable);
            model.addAttribute("page", page);
            return "addImg" ;
//           redirectAttributes.addFlashAttribute("flash.message","Successfully deleted" + filename);
        }catch (IOException| RuntimeException e){
            redirectAttributes.addFlashAttribute("flash.message","Failed to deleted" + filename + "=>" + e.getMessage());
        }
        return "addImg ";
    }
}





