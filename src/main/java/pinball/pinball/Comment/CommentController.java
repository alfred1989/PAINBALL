package pinball.pinball.Comment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CommentController {

    private CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @GetMapping("/allComment")
    public String allComment(Model model) {
        List<Comment> commentList = commentRepository.findAll();
        model.addAttribute("coments", commentList);
        return "comment";
    }



    @GetMapping("/addComment")
    public String addComment(Model model) {
        model.addAttribute("commentUser", new Comment());
        return "addComment";
    }




    @PostMapping("/save")

    public String save(Comment comment) {
        commentRepository.save(comment);
        String pole = comment.getCommentUser();
        String commentUser = "kurczak";
        if (pole.equals(commentUser)) {

            return  "vulgarWords";
        } else
            return "redirect:/allComment";

    }

    @GetMapping("/deleteComment")
    public String participantOpinionAdmin(Model model) {
        List<Comment> allOpinion = commentRepository.findAll();
        model.addAttribute("opinions", allOpinion);

        return "deleteComment";
    }

    @GetMapping("/delete/{id}")

    public String deleteOpinion(@PathVariable Long id) {


        commentRepository.deleteById(id);
        return "redirect:/deleteComment";
    }

    @GetMapping("/vulgarWords")
    public String vulgarWords(){
        return "vulgarWords";
    }
}
