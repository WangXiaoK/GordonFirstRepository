package com.ibeifeng.service;

import java.util.List;

import com.ibeifeng.dao.CritiqueDAO;
import com.ibeifeng.fenye.Page;
import com.ibeifeng.fenye.PageUtil;
import com.ibeifeng.fenye.Result;
import com.ibeifeng.po.Article;
import com.ibeifeng.po.Critique;

public class CritiqueServiceImpl implements CritiqueService {
	private CritiqueDAO critiqueDAO;

	public CritiqueDAO getCritiqueDAO() {
		return critiqueDAO;
	}

	public void setCritiqueDAO(CritiqueDAO critiqueDAO) {
		this.critiqueDAO = critiqueDAO;
	}

	public void addCritique(Critique critique) {
		critiqueDAO.addCritique(critique);
	}

	public Result showCritiqueByPage(int AId, Page page) {
		page = PageUtil.createPage(page,critiqueDAO.queryCritiqueCount(AId));
		List<Critique> all = critiqueDAO.queryByPage(AId, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}
}
