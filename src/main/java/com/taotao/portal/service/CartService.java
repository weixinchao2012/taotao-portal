package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.pojo.CartItem;
import com.taotao.pojo.TaotaoResult;

public interface CartService {
	public TaotaoResult addCartItem(long itemId, int num, 
			HttpServletRequest request, HttpServletResponse response);
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
