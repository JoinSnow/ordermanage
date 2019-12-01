package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.LogDao;
import domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogDao logDao;

    public void add(Log log) {
        logDao.add(log);
    }

    public PageInfo<Log> all(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Log> all = logDao.all();
        return new PageInfo<>(all);
    }
}
