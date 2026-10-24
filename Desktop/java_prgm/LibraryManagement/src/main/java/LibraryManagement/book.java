package LibraryManagement;

public class book {
	
	
	
	private int bookid;
	private String bookname;
	private String bookdesc;
	private String bookurl;
	private String author;
	
	

	public book(int bookid, String bookname, String bookdesc, String bookurl,String author) {
		super();
		this.bookid = bookid;
		this.bookname = bookname;
		this.bookdesc = bookdesc;
		this.bookurl = bookurl;
		this.author = author;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBookdesc() {
		return bookdesc;
	}
	public void setBookdesc(String bookdesc) {
		this.bookdesc = bookdesc;
	}
	public String getauthor() {
		return author;
	}
	public void setauthor(String author) {
		this.author = author;
	}
	public String getBookurl() {
		return bookurl;
	}
	public void setBookurl(String bookurl) {
		this.bookurl = bookurl;
	}

}
