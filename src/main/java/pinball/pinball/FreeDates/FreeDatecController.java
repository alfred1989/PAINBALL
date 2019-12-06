package pinball.pinball.FreeDates;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FreeDatecController {

    FreeDareRepository freeDareRepository;

    public FreeDatecController(FreeDareRepository freeDareRepository) {
        this.freeDareRepository = freeDareRepository;
    }

//    @GetMapping("/freeDates")
//    public  String freeDates(Model model){
//        List<FreeDates> freeDates = freeDareRepository.findAll();
//        model.addAttribute("freeGame", new FreeDates());
//        return "index";
//    }

    @GetMapping("/editDates")
    public String addFreeDates(Model model){
        model.addAttribute("addFreGame", new FreeDates());
        List<FreeDates> freeDates = freeDareRepository.findAll();
        model.addAttribute("allDetes", freeDates);
        return "editDates";

    }


    @PostMapping("/saveData")

    public String saveData(FreeDates freeDates) {
        freeDareRepository.save(freeDates);

            return "redirect:/editDates";

    }


//    @GetMapping("/deleteDates")
//    public String deletDates(Model model) {
//        List<FreeDates> freeDates = freeDareRepository.findAll();
//        model.addAttribute("deletDate", freeDates);
//
//        return "editDates";
//    }

    @GetMapping("/deleteDates/{id}")

    public String deletDatesGame(@PathVariable Long id) {


        freeDareRepository.deleteById(id);
        return "redirect:/editDates";
    }


}