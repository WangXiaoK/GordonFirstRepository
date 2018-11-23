package com.ibeifeng.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ibeifeng.fenye.Page;
import com.ibeifeng.fenye.Result;
import com.ibeifeng.po.Article;
import com.ibeifeng.service.ArticleService;
import com.ibeifeng.service.CritiqueService;
import com.ibeifeng.service.DianjiliangService;
import com.opensymphony.xwork2.ActionSupport;

public class ShowArticle extends ActionSupport {
	// ҵ���߼��������
	private ArticleService articleService;
	// id����
	private int id;
	// �������ҵ���߼����
	private DianjiliangService dianjiliangService;
	// ���۵�ҵ���߼����
	private CritiqueService critiqueService;
	//���õ�ǰҳ
	private int currentPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public CritiqueService getCritiqueService() {
		return critiqueService;
	}

	public void setCritiqueService(CritiqueService critiqueService) {
		this.critiqueService = critiqueService;
	}

	public DianjiliangService getDianjiliangService() {
		return dianjiliangService;
	}

	public void setDianjiliangService(DianjiliangService dianjiliangService) {
		this.dianjiliangService = dianjiliangService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public String execute() throws Exception {
		// ���������õ�request��Χ
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��ID��ѯ����
		Article article = articleService.showArticle(id);
		// ����û�IP
		String IP = request.getRemoteAddr();
		// ��õ�ǰʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String stime = sdf.format(new Date());
		Date time = sdf.parse(stime);

		if (!dianjiliangService.isVistor(id, IP, time)) {
			// ���������
			article.setHasread(article.getHasread() + 1);
		}
		// �����µ�Article���浽���ݱ���
		articleService.addArticle(article);
		
		
		//��ʾ����
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(5);
		
		Result result = critiqueService.showCritiqueByPage(id, page);
		
		request.setAttribute("page", result.getPage());
		request.setAttribute("allCri", result.getList());
		request.setAttribute("article", article);
		return this.SUCCESS;
	}

}
