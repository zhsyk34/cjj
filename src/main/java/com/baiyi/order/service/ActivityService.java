package com.baiyi.order.service;

import java.util.Collection;
import java.util.List;

import com.baiyi.order.model.Activity;
import com.baiyi.order.util.EnumList.ActivityTypeEnum;
import com.baiyi.order.vo.ActivityVO;

public interface ActivityService {

	public void update(Activity activity);

	public void update(Collection<Activity> activities);

	public Activity find(Integer id);

	public List<Activity> findList();

	public List<Activity> findList(Integer kitchenId, Integer foodId, ActivityTypeEnum type, Boolean used);

	public List<Activity> findList(String kitchen, String food, ActivityTypeEnum type, Boolean used);

	public List<Activity> findList(String kitchen, String food, ActivityTypeEnum type, Boolean used, String sort, String order, int pageNo, int pageSize);

	public List<ActivityVO> findVOList(String kitchen, String food, ActivityTypeEnum type, Boolean used, String sort, String order, int pageNo, int pageSize);

	public int count(String kitchen, String food, ActivityTypeEnum type, Boolean used);

	public int countVO(String kitchen, String food, ActivityTypeEnum type, Boolean used);

}
