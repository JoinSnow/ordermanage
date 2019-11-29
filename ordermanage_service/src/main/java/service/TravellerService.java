package service;

import dao.TravellerDao;
import domain.Traveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellerService {
    @Autowired
    private TravellerDao travellerDao;

    public List<Traveller> findByMemberId(String memberId) {
        return travellerDao.findByMemberId(memberId);
    }

    public Traveller findById(String id) {
        return travellerDao.findById(id);
    }
}
