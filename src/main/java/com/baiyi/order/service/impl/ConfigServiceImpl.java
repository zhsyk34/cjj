package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.ConfigDao;
import com.baiyi.order.model.Config;
import com.baiyi.order.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Resource
	private ConfigDao configDao;

	@Override
	public void save(Config config) {
		configDao.save(config);
	}

	@Override
	public void update(Config config) {
		configDao.update(config);
	}

	@Override
	public void merge(Config config) {
		configDao.merge(config);
	}

	@Override
	public List<Config> findList() {
		return configDao.findList();
	}

	@Override
	public Config findCurrent() {
		List<Config> list = configDao.findList();
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

}
