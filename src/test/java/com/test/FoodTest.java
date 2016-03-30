package com.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baiyi.order.dao.FoodDao;
import com.baiyi.order.dao.FoodStyleDao;
import com.baiyi.order.dao.FoodTasteDao;
import com.baiyi.order.dao.MaterialDao;
import com.baiyi.order.dao.StyleDao;
import com.baiyi.order.dao.TasteDao;
import com.baiyi.order.dao.TypeDao;
import com.baiyi.order.model.Food;
import com.baiyi.order.model.FoodStyle;
import com.baiyi.order.model.FoodTaste;
import com.baiyi.order.model.Material;
import com.baiyi.order.model.Style;
import com.baiyi.order.model.Taste;
import com.baiyi.order.model.Type;
import com.baiyi.order.util.EnumList.MaterialTypeEnum;
import com.baiyi.order.util.RandomUtil;
import com.baiyi.order.vo.FoodVO;
import com.baiyi.order.vo.TasteVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring2.xml")
public class FoodTest {

	@Resource
	private FoodDao foodDao;
	@Resource
	private FoodTasteDao foodTasteDao;
	@Resource
	private FoodStyleDao foodStyleDao;
	@Resource
	private TypeDao typeDao;
	@Resource
	private TasteDao tasteDao;
	@Resource
	private StyleDao styleDao;
	@Resource
	private MaterialDao materialDao;

	@Test
	public void find1() {
		for (int i = 1; i < 8; i++) {
			Type type = foodDao.findType(i);
			Material material = foodDao.findMaterial(i);
			List<Style> styles = foodDao.findStyleList(i);
			List<Taste> tastes = foodDao.findTasteList(i);

			System.out.print("type: " + type.getName());
			System.out.print(" material: " + material.getName());
			System.out.print(" styles: " + JSONArray.fromObject(styles));
			System.out.println(" tastes: " + JSONArray.fromObject(tastes));
		}

		for (int i = 1; i < 8; i++) {
			FoodVO vo = foodDao.findVO(i);
			System.out.println(JSONObject.fromObject(vo));
		}
	}

	@Test
	public void find2() {
		List<TasteVO> taste = tasteDao.findVOList("咸", null, null);
		System.out.println(JSONArray.fromObject(taste));

		List<FoodVO> food = foodDao.findVOList();
		System.out.println(JSONArray.fromObject(food));

	}

	@Test
	public void init() {
		String[] types = { "饭", "面", "饮料", "甜点", "水果" };
		for (int i = 0; i < types.length; i++) {
			Type type = new Type();
			type.setName(types[i]);
			typeDao.save(type);
		}

		String[] styles = { "酸", "甜", "苦", "辣", "咸" };
		for (int i = 0; i < styles.length; i++) {
			Style style = new Style();
			style.setName(styles[i]);
			styleDao.save(style);
		}

		int tastes = 15;
		for (int i = 0; i < tastes; i++) {
			Taste taste = new Taste();
			taste.setPrice(RandomUtil.randomInteger(5, 35));
			int index = RandomUtil.randomInteger(1, styles.length - 1);
			taste.setStyleId(index + 1);
			taste.setName(styles[index] + (i + 1));
			tasteDao.save(taste);
		}

		int materials = 7;
		for (int i = 1; i <= materials; i++) {
			Material material = new Material();
			material.setName("素材" + i);
			material.setPath(RandomUtil.randomString(30));
			material.setType(MaterialTypeEnum.values()[RandomUtil.randomInteger(0, 1)]);
			materialDao.save(material);
		}

		for (int i = 1; i < 8; i++) {
			Food food = new Food();
			food.setName("food" + i);
			food.setPrice(RandomUtil.randomInteger(15, 50));
			food.setMaterialId(RandomUtil.randomInteger(1, materials));
			food.setTypeId(RandomUtil.randomInteger(1, types.length));
			foodDao.save(food);

			for (int k = 1; k < styles.length + 1; k++) {
				FoodStyle fs = new FoodStyle();
				fs.setFoodId(i);
				fs.setStyleId(k);
				foodStyleDao.save(fs);

				k += RandomUtil.randomInteger(1, 3);
			}
			for (int k = 1; k < tastes; k++) {
				FoodTaste ft = new FoodTaste();
				ft.setFoodId(i);
				ft.setTasteId(k);
				foodTasteDao.save(ft);
				k += RandomUtil.randomInteger(1, 5);
			}
		}

	}

	@Test
	public void temp() {
		Integer id = 155;
		System.out.println(id.equals(155));
		System.out.println(id == 155);
		
		Integer.valueOf(2015);
	}

}
