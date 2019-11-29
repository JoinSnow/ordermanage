package controller;


import domain.Traveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TravellerService;

import java.util.List;

@Controller
@RequestMapping("/traveller")
public class TravellerController {
    @Autowired
    private TravellerService travellerService;

    @RequestMapping("/findByMemberId")
    public @ResponseBody List<Traveller> findByMemberId(String memberId) {
        List<Traveller> travellers = travellerService.findByMemberId(memberId);
        return travellers;
    }
}
