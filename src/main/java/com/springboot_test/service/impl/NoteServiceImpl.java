package com.springboot_test.service.impl;

import com.springboot_test.dao2.NoteDao;
import com.springboot_test.service.NoteService;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
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
    //@Value("${Retryable.maxAttempts}")
    public  int i ;
    //private static int i;
    //public  static  final  int j =  i ;
    public int c() {
        return i;
    }
    @Autowired
    private NoteDao noteDao;

    @Override
    public void a() {

    }

   /* @Override
    @Retryable(value = RuntimeException.class,maxAttempts = 3,
            backoff = @Backoff(multiplier = 1,value = 2000L,maxDelay = 10000L))
    public void a() {
        System.out.println(i);
        System.out.println(new Date());

        System.out.println(123);
        throw new RuntimeException("retry异常");
    }
    public int b() {
        return 1;
    }
*/
}
