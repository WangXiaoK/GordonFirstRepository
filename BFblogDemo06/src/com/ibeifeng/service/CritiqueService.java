package com.ibeifeng.service;

import com.ibeifeng.fenye.Page;
import com.ibeifeng.fenye.Result;
import com.ibeifeng.po.Critique;

public interface CritiqueService {
	//添加评论
	public void addCritique(Critique critique);
	
	//分页显示评论
	public Result showCritiqueByPage(int AId,Page page);
}
