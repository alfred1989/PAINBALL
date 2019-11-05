package pinball.pinball.Comment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CommentController {

    private CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/allComment")
    public String allComment(Model model){
        List<Comment> commentList = commentRepository.findAll();
        model.addAttribute("coments", commentList );
        return "comment";
    }


    @GetMapping("/addComment")
    public String addComment(Model model){
        model.addAttribute("commentUser", new Comment());
        return "addComment";
    }


    @PostMapping("/save")
    public String save(Comment comment){
        commentRepository.save(comment);
        return "redirect:/addComment";

    }
}
