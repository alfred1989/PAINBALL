package pinball.pinball.message;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MessageController {

    private MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/message")
    public String message(Model model){
        model.addAttribute("messages", new Message());
        return "message";
    }

    @PostMapping("/addMessage")
    public String addMessage(Message message){
        messageRepository.save(message);
        return "index";
    }


//    @GetMapping("/deleteMessage")
//    public String participantOpinionAdmin(Model model) {
//        List<Message> messageList = messageRepository.findAll();
//        model.addAttribute("messageMein", messageList);
//
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/delete/{id}")
//
//    public String deleteOpinion(@PathVariable Long id) {
//
//
//        commentRepository.deleteById(id);
//        return "redirect:/deleteComment";
//    }
}
