/*
 *  Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.alibaba.fescar.samples.springboot.service.impl;

import java.math.BigDecimal;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.samples.springboot.service.AssetService;
import com.alibaba.fescar.samples.springboot.sys.domain.Asset;
import com.alibaba.fescar.samples.springboot.sys.repository.AssetRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service(interfaceClass = AssetService.class, timeout = 10000)
@Component
public class AssetServiceImpl implements AssetService {

	public static final Logger LOGGER = LoggerFactory.getLogger(AssetService.class);

	public static final String ASSET_ID = "DF001";

	@Autowired
	private AssetRepository assetRepository;

	@Override
	public int increase() {
		LOGGER.info("Asset Service Begin ... xid: " + RootContext.getXID() + "\n");

		Asset asset = assetRepository.findById(ASSET_ID).get();
		asset.setAmount(asset.getAmount().add(new BigDecimal("1")));
		assetRepository.save(asset);
		throw new RuntimeException("test exception for fescar");
	}
}
