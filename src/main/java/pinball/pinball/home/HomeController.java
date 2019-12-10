package pinball.pinball.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pinball.pinball.FreeDates.FreeDareRepository;
import pinball.pinball.FreeDates.FreeDates;

import java.util.List;

@Controller



public class HomeController {

    FreeDareRepository dareRepository;

    public HomeController(FreeDareRepository dareRepository) {
        this.dareRepository = dareRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<FreeDates> allData = dareRepository.findAll();


        model.addAttribute("game_data", allData);
        return "index";
    }






    @GetMapping("/comment")
    public String comment(){
        return "comment";
    }

}
