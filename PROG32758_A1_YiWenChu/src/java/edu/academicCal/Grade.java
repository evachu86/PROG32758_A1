package edu.academicCal;

public enum Grade {
	
	A_PLUS(4, "A+", "90-100%"), 
	A(4, "A", "80-89%"), 
	B_PLUS(3.5, "B+", "75-79%"), 
	B(3, "B", "70-74%"), 
	C_PLUS(2, "C+", "65-69%"), 
	C(2, "C", "60-64%"), 
	D(1, "D", "50-59%"), 
	F(0, "F", "0-49%");

	private double gpa;
	private String gradeStr;
	private String percetStr;
	
	private Grade(double gpa, String gradeStr, String percentStr) {
		this.gpa = gpa;
		this.gradeStr = gradeStr;
		this.percetStr = percentStr;
	}
	
	public double getGpa() {
		return gpa;
	}

	public String getGradeStr() {
		return gradeStr;
	}

	public String getPercetStr() {
		return percetStr;
	}
	
	@Override
	public String toString() {
		return gradeStr+ " (" +percetStr+ ")";
	}
}
