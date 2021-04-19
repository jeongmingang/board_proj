package board_proj.dto;

public class PageInfo {
	private int page;
	private int maxpage;
	private int startpage;
	private int endpage;
	private int listCount;
	
	public PageInfo() {
		
	}
	
	public PageInfo(int page, int maxpage, int startpage, int endpage, int listCount) {
		this.page = page;
		this.maxpage = maxpage;
		this.startpage = startpage;
		this.endpage = endpage;
		this.listCount = listCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	@Override
	public String toString() {
		return String.format("[page=%s, maxpage=%s, startpage=%s, endpage=%s, listCount=%s]", page, maxpage,
				startpage, endpage, listCount);
	}
	
	
}
