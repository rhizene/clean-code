package com.b.simple.design.business.student;
public class StudentHelper {

	/* PROBLEM 1 */
	/*
	* You get a grade B if marks are between 51 and 80 (both inclusive). Except for Maths where the upper limit is increased by 10.
	*/
	public boolean isGradeB(int marks, boolean isMaths) {
		final int GRADE_MIN = 51;
		final int GRADE_MAX = 80;
		final int GRADE_MAX_MATH = GRADE_MAX + 10;
		final int GRADE_CEIL = isMaths ? GRADE_MAX_MATH : GRADE_MAX;

		return marks >= GRADE_MIN &&  marks<= GRADE_CEIL;
	}

	/* PROBLEM 2 */
	/*
	You are awarded a grade based on your marks.
	Grade A = 91 to 100, Grade B = 51 to 90, Otherwise Grade C
	Except for Maths where marks to get a Grade are 5 higher than required for other subjects.
	*/

	public String getGrade(int mark, boolean isMaths) {
		final int GRADE_MODIFIER_MATH = 5;
		final int modifier = isMaths ? GRADE_MODIFIER_MATH : 0;
		final int minGradeA = 91 + modifier;
		final int minGradeB = 51 + modifier;


		if (mark >= minGradeA) return "A";
		if (mark >= minGradeB && mark < minGradeA) return "B";

		return "C";
	}

    /*  PROBLEM 3
     * You and your Friend are planning to enter a Subject Quiz.
     * However, there is a marks requirement that you should attain to qualify.
     * 
     * Return value can be YES, NO or MAYBE.
     * 
     * YES If either of you are very good at the subject(has 80 or more marks)
     * However, there is an exception that if either of you is not good in the subject(20 or less marks), it is NO.
     * In all other conditions, return MAYBE.
     * 
     * However, the definition for good and not good are 5 marks higher if the subject is Mathematics.
     * 
     * marks1 - your marks
     * marks2 - your friends marks
    */
        
    public String willQualifyForQuiz(int marks1, int marks2, boolean isMaths) {
		final int modifier = isMaths ? 5 : 0;
		final int NO_THRESHOLD = 20 + modifier;
		final int YES_THRESHOLD = 80 + modifier;

		if (marks1 <= NO_THRESHOLD || marks2 <= NO_THRESHOLD) return "NO";

		if (marks1 >= YES_THRESHOLD || marks2 >= YES_THRESHOLD ) return "YES";

        return "MAYBE";
    }	

}