package service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.ErrorLogDao;
import domain.ErrorLog;
import domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorLogService {
    @Autowired
    private ErrorLogDao errorLogDao;

    public void add(ErrorLog errorLog) {
        errorLogDao.add(errorLog);
    }

    public PageInfo<ErrorLog> all(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<ErrorLog> errorLogs = errorLogDao.all();
        return new PageInfo<>(errorLogs);
    }
}
