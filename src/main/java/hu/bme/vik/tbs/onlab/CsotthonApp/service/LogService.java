package hu.bme.vik.tbs.onlab.CsotthonApp.service;

import hu.bme.vik.tbs.onlab.CsotthonApp.dto.LogDTO;
import hu.bme.vik.tbs.onlab.CsotthonApp.mapper.LogMapper;
import hu.bme.vik.tbs.onlab.CsotthonApp.model.Log;
import hu.bme.vik.tbs.onlab.CsotthonApp.model.User;
import hu.bme.vik.tbs.onlab.CsotthonApp.repository.LogRepository;
import hu.bme.vik.tbs.onlab.CsotthonApp.repository.UserRepository;
import hu.bme.vik.tbs.onlab.CsotthonApp.util.Time;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {
    @Autowired
    LogRepository logRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private final LogMapper logMapper;

    public LogService(){
        logMapper = Mappers.getMapper(LogMapper.class);
    }

    public LogDTO createLog(LogDTO logDTO){
        Log log=logMapper.logDTOtoLog(logDTO);
        log.setId(null);
        log.setTime(Time.getNowInUTC());
        User user=userRepository.findById(log.getUser().getId()).get();
        log.setUser(user);
        //majd a user setelése is ide jön, autentikáció során
        logRepository.save(log);
        return logMapper.logToLogDTO(log);
    }

    public List<LogDTO> getAll(){
        List<Log> logs=logRepository.findAllByOrderByTimeDesc();
        List<LogDTO> logDTOs=new ArrayList<>();
        for(Log log:logs){
            logDTOs.add(logMapper.logToLogDTO(log));
        }
        return logDTOs;
    }
    public Boolean delete(Integer id){
        logRepository.deleteById(id);
        if(logRepository.findById(id).isPresent()){
            return false;
        }else {
            return true;
        }
    }
}