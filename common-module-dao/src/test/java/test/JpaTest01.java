package test;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.module.dao.entity.data.Picture;
import com.module.dao.repository.data.PictureDao;

/**
 * @author kingly
 * @date 2015年8月28日
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaTest01 {
	@Autowired
	private PictureDao pictureDao;
	
	@Test
	public void testFindAll() {
		System.out.println("====================testFindAll start=========================");
		List<Picture> pictures = pictureDao.findAll();
		for (Picture picture : pictures) {
			System.out.println(picture.getUrl());
		}
		System.out.println("====================testFindAll end=========================");
	}
	
}
