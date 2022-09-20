package top.flywen.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.flywen.asset.entity.SysLog;
import top.flywen.log.service.LogService;
import top.flywen.mybatis_plus.mapper.LogMapper;

@Service
public class LogServiceImpl implements LogService {

  @Autowired
  private LogMapper logMapper;

  @Override
  public void saveLog(SysLog log) {
    logMapper.insert(log);
  }
}
