package �������̽�;

public class BoardDto {
	private String title="";
	private String writer="";
	private String contents="";
	
	public BoardDto() {
		super();
	}
	
	// �⺻ �������� ��� ����ڰ� ������ ������ �ý����� �ڵ� �����Ͽ� ȣ��
	// ����ڰ� ����Ʈ������(�Ű��������� ������)�� ������ �ʰ� 
	// �Ű����� �ִ� �ٸ� �����ڸ� ����� �ý����� ����� ����Ʈ ���������� �� ���� ��ü ������ �⺻ ������ �ȵ�
	// �ٸ� ������ ���� �� �� ����Ʈ �����ڵ� ������!
	
	public BoardDto(String title, String writer, String contents) {
		super();
		this.title = title;
		this.writer = writer;
		this.contents = contents;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s", title, writer, contents);
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
