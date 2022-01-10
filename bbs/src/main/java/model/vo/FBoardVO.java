package model.vo;

public class FBoardVO {
	private int id;
	private String writer;
	private String title;
	private String content;
	private String writeDate;
	private int cnt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getCnt() {
		return cnt;
	}
	//application scope
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
