package com.zjh.cms.system.service.impl;

import com.zjh.cms.system.domain.Notice;
import com.zjh.cms.system.mapper.NoticeMapper;
import com.zjh.cms.system.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 老雷
 * @since 2020-04-13
 */
@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
