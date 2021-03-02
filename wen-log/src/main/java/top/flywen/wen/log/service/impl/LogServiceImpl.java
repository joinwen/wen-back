package top.flywen.wen.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.flywen.wen.entity.SysLog;
import top.flywen.wen.log.service.LogService;
import top.flywen.wen.mp.mapper.LogMapper;

@Service
public class LogServiceImpl implements LogService {

  @Autowired
  private LogMapper logMapper;

  @Override
  public void saveLog(SysLog log) {
    logMapper.insert(log);
  }
}
