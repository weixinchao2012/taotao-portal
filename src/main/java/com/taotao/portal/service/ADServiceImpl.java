package com.taotao.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.pojo.ADItem;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;

@Service
public class ADServiceImpl implements AdService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${INDEX_AD1_URL}")
	private String INDEX_AD1_URL;
	
	@Override
	public String getAdItemList() {
		//调用服务层的服务查询打广告位的数据

		String result = HttpClientUtil.doGet("http://localhost:8081/rest/content/category/89" );
		//把json数据转换成对象

		TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
		List<ADItem> itemList = new ArrayList<>();
		if (taotaoResult.getStatus() == 200 ) {
			List<TbContent> contentList = (List<TbContent>) taotaoResult.getData();
			for (TbContent tbContent : contentList) {
				ADItem item = new ADItem();
				item.setHeight(240);
				item.setWidth(670);
				item.setSrc(tbContent.getPic());
				item.setHeightB(240);
				item.setWidth(550);
				item.setSrcB(tbContent.getPic2());
				item.setAlt(tbContent.getTitleDesc());
				item.setHref(tbContent.getUrl());
				itemList.add(item);
			}
			
		}
		return JsonUtils.objectToJson(itemList);
	}

}
