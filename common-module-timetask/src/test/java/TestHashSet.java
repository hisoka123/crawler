import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class TestHashSet {

	public static void main(String[] args) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long now = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(now);
		System.out.println(now + " = " + formatter.format(calendar.getTime()));
			}
		}

