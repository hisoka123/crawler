import java.text.ParseException;

import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


public class test_quartz {

	public static void main(String[] args) throws ParseException, SchedulerException {
		ApplicationContext ctx  = new ClassPathXmlApplicationContext("applicationContext-quartz.xml");
		JobDetailImpl jobDetail = (JobDetailImpl) ctx.getBean("sinaEmergencyStore");
		System.err.println(jobDetail);
		CronTriggerImpl cronTrigger = (CronTriggerImpl)ctx.getBean("cronTrigger");
		System.err.println(cronTrigger);
		cronTrigger.setCronExpression("0 0/1 * * * ?");
		StdScheduler scheduler = (StdScheduler) ctx.getBean("schedulerFactoryBean");
		scheduler.rescheduleJob(cronTrigger.getKey(), cronTrigger);
		System.err.println(scheduler);
		}
}
