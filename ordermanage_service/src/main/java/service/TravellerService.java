package service;

import dao.TravellerDao;
import domain.Traveller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellerService {
    private TravellerDao travellerDao;

    public List<Traveller> findByMemberId(String memberId){
        return travellerDao.findByMemberId(memberId);
    }
}
