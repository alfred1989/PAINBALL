package pinball.pinball.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pinball.pinball.Comment.CommentRepository;
import pinball.pinball.message.Message;
import pinball.pinball.message.MessageRepository;

import java.util.List;


@Controller
public class AdminController {


    CommentRepository commentRepository;
    MessageRepository messageRepository;
    public AdminController(CommentRepository commentRepository, MessageRepository messageRepository ) {
        this.commentRepository = commentRepository;
        this.messageRepository = messageRepository;

    }





    @GetMapping("/admin")
    public String admin(Model model) {

        double average = commentRepository.average();
        model.addAttribute("average", average);
        int quantityComment= commentRepository.quantityComment();
        model.addAttribute("quantityComment", quantityComment);
        List<Message> messageList = messageRepository.findAll();
        model.addAttribute("messageMein", messageList);

        return "admin";
    }

}




