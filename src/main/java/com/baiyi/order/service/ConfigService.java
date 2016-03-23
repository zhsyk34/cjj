package com.baiyi.order.service;

import java.util.List;

import com.baiyi.order.model.Config;

public interface ConfigService {

	public void save(Config config);

	public void update(Config config);

	public void merge(Config config);

	public List<Config> findList();

	public Config findCurrent();

}
