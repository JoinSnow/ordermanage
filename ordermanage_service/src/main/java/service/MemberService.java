package service;

import dao.MemberDao;
import domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;

    public List<Member> findAll(){
        return memberDao.findAll();
    }

    public Member findById(String id){
        return memberDao.findById(id);
    }
}
