package com.springboot_test.task;

import com.springboot_test.service.NoteService;
import java.util.Date;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

@Slf4j
@Component
public class FixedPrintTask {
    public static final int i = 5;

    private final NoteService noteService;

    public FixedPrintTask(NoteService noteService) {
        this.noteService = noteService;
    }


    @Scheduled(cron = "0 0/1 * * * ?")
    public void execute() {
        //log.info("thread id:{},FixedPrintTask execute times:{}", Thread.currentThread().getId(), ++i);
        try {
            noteService.a();
        } catch (Exception e) {
            System.out.println(999999);
            e.printStackTrace();
        }
    }

    /*
        '@Retryable'注解中，maxAttempts是最大尝试次数，backoff是重试策略，value 是初始重试间隔毫秒数，
        默认是3000l，multiplier是重试乘数，例如第一次是3000l，第二次是3000lmultiplier,
        第三次是3000lmultiplier2如此类推，maxDelay是最大延迟毫秒数，如果3000lmultiplier*n>maxDelay，延时毫秒数会用maxDelay。
     */
    @Retryable(value = RuntimeException.class,maxAttempts = i,
            backoff = @Backoff(multiplier = 1,value = 2000L,maxDelay = 10000L))
    public void a() {
        System.out.println(new Date());

        System.out.println(123);
        throw new RuntimeException("retry异常");
    }

}