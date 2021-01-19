package com.inyta.assciation.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inyta.assciation.entity.po.Event;
import com.inyta.assciation.mapper.EventMapper;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author: zhangwei
 * @Date: 2020/11/10 14:53
 */
@Service
@Slf4j
public class EventService extends ServiceImpl<EventMapper, Event> {

  @Value("${file.upload.path}")
  private String uploadPath;

  @Autowired
  private EventMapper eventMapper;

  public boolean applyEvent(MultipartFile file, Event event) throws IOException {
    if(file.isEmpty()){

    }
    String path = "D:/czxcxz";
    File dest = new File(path);
    //1、判断目录是否存在
    if (!dest.isDirectory()) {
      dest.mkdirs();
    }
    //2、生成唯一UUID文件名，拼接 路径+文件名
    String fileName = UUID.randomUUID().toString() + FilenameUtils.EXTENSION_SEPARATOR
        + FilenameUtils.getExtension(file.getOriginalFilename());
    File dist = new File(path + File.separator + fileName);
    file.transferTo(dist);
    log.info("上传成功" + file.getOriginalFilename());
    event.setEventPic(fileName);
    return this.save(event);
  }
}
