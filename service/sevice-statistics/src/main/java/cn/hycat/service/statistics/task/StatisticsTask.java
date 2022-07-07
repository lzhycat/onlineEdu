package cn.hycat.service.statistics.task;

import cn.hycat.service.statistics.service.DailyService;
import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : lzhycat
 * @date : 2022-07-07 16:37
 */
@Component
public class StatisticsTask {

    @Autowired
    private DailyService dailyService;

    @Scheduled(cron = "0 0/10 1/1 * * ? *")
    public void DailyInfoTask() {
        DateTime dateTime = new DateTime();
        String day = dateTime.toString("yyyy-MM-dd");
        dailyService.dailyInfo(day);
    }
}
