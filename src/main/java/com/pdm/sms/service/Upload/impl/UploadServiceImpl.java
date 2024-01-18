package com.pdm.sms.service.Upload.impl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.pdm.sms.dao.Upload.UploadMapper;
import com.pdm.sms.domain.Upload;
import com.pdm.sms.service.Upload.UploadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/17 17:26
 * @description 上传业务层
 **/
@Service
public class UploadServiceImpl implements UploadService {

    private final Log logger = LogFactory.getLog(UploadServiceImpl.class);

    @Resource
    private UploadMapper uploadMapper;

    @Override
    public void upload(Upload upload) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("userId", upload.getUserId());
        condition.put("level", upload.getLevel());
        String oldUrl = uploadMapper.getHead(condition);
        if (oldUrl == null) {
            uploadMapper.add(upload);
        } else {
            File file = new File(oldUrl);
            //文件是否存在
            if (file.exists()) {
                if (file.delete()) {
                    logger.info("删除文件成功");
                }
            }
            uploadMapper.update(upload);
        }
    }

    @Override
    public String getHeader(Map<String, Object> condition) {
        //返回数据库存的图片url然后找到对应存放的路径然后返回给前端
        String imgUrl = uploadMapper.getHead(condition);
        return imgUrl == null ? "" : imgUrl.substring(imgUrl.indexOf("/"));
    }
}
