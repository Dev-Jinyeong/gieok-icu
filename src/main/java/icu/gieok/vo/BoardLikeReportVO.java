package icu.gieok.vo;

public class BoardLikeReportVO {
    
	private int report_no;
    private int user_code;
    private int board_no;
    private char board_like;
    private char board_report;

	public int getReport_no() {
		return report_no;
	}

	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}
	
    public int getUser_code() {
        return user_code;
    }
    
    public void setUser_code(int user_code) {
        this.user_code = user_code;
    }
    
    public int getBoard_no() {
        return board_no;
    }
    
    public void setBoard_no(int board_no) {
        this.board_no = board_no;
    }

    public char getBoard_like() {
        return board_like;
    }
    
    public void setBoard_like(char board_like) {
        this.board_like = board_like;
    }
    
    public char getBoard_report() {
        return board_report;
    }
    
    public void setBoard_report(char board_report) {
        this.board_report = board_report;
    }
}
