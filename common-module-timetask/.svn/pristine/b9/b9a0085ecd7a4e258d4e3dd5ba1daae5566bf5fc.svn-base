
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class TastPersistenceSchedule {

	public static void main(String[] args) throws SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		ApplicationContext springContext  = new ClassPathXmlApplicationContext("applicationContext-quartz.xml");
		StdScheduler scheduler = (StdScheduler) springContext.getBean("quartzScheduler");
		Class clazz = Class.forName("frameworkx.springframework.scheduling.quartz.MyQuartzJobBean");
//		JobDetailImpl jobDetailImpl = new JobDetailImpl("a", "b", clazz);
		//JobDetailImpl detail = (JobDetailImpl) springContext.getBean("jobDetail");
		//scheduler.addJob(detail, true);
		//CronTriggerImpl trigger = (CronTriggerImpl) springContext.getBean("TaskTwoTrigger");
//		scheduler.scheduleJob(jobDetailImpl, trigger);
		JobDetail detail = JobBuilder.newJob(clazz).withDescription("hasdfoaho")
				.withIdentity("aaa", "bbb").build();
		//JobDetail detail = scheduler.getJobDetail(new JobKey("2","3"));
		CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
		//scheduler.addJob(detail, true);
		CronTrigger trigger =  (CronTrigger) TriggerBuilder.newTrigger().withIdentity("aaa","bbb").withSchedule(builder).build();
		//scheduler.deleteJob(new JobKey("2", "3"));
		scheduler.scheduleJob(detail, trigger);
	}

}
