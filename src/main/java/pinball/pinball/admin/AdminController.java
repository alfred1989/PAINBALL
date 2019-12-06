package pinball.pinball.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pinball.pinball.Comment.CommentRepository;


@Controller
public class AdminController {


    CommentRepository commentRepository;

    public AdminController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/admin")
    public String admin(Model model) {

        double average = commentRepository.average();
        model.addAttribute("average", average);
        int quantityComment= commentRepository.quantityComment();
        model.addAttribute("quantityComment", quantityComment);
        return "admin";
    }

}




