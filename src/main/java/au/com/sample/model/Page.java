package au.com.sample.model;

import java.util.List;

public class Page<T> {
	
	private final long currentPage;
	
	private final long totalElements;
	
	private final List<T> elements;
	
	private final int pageSize;
	
	public Page(long currentPage, long totalElements, int pageSize, List<T> elements) {
		this.currentPage = currentPage;
		this.totalElements = totalElements;
		this.pageSize = pageSize;
		this.elements = elements;
	}
	
	public long getCurrentPage() {
		return currentPage;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public List<T> getElements() {
		return elements;
	}

	public int getPageSize() {
		return pageSize;
	}
	
}
