package com.springboot_test.service.impl;

import com.springboot_test.dao2.NoteDao;
import com.springboot_test.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: com.springboot_test.service.impl.NoteServiceImpl
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/8/8 16:50
 * @Version: 1.0
 */
@Service
@Slf4j
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;

}
