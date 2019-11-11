package pinball.pinball.message;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
}
