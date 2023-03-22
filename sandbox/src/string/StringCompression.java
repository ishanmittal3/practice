package string;

public class StringCompression {
    // Trellis Energy interview:
    // String Compression: Implement a method to perform
// basic string compression using the counts of
// repeated characters.  For example, the string
// aabcccccaaa would become a2b1c5a3.  If the
// "compressed" string would not become smaller
// than the original string, your method should
// return the original string.  You can assume the
// string has only uppercase and lowercase letters
// (a-z).

    // aabcccccaaa
    // aaabb
    // a3b2

    /*
    * Dept, Emp, Salary
    * Dept - DeptId, DeptName
    * Emp - EmpId, DeptId, Contact
    * Salary - EmpId, Salary
    *
    * DeptName - HighestSalary
    * select d.deptName, s.salary from emp e, dept d, salary s where e.empId = s.empId and e.deptId = d.deptId
    * group by e.deptId on
    * */

    public static void main(String[] args) {
        System.out.println(compress("aabcccccaaa"));
    }

    public static String compress(String str) {
        if (str.length() < 2) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int pos1 = 0;
        int pos2 = 1;
        while ((pos1 < str.length()) && (pos2 < str.length())) {
            while (str.charAt(pos2) == str.charAt(pos1)) {
                pos2++;
            }
            if (pos2 == str.length()) {
                break;
            }
            sb.append(str.charAt(pos1));
            sb.append(pos2-pos1);
            pos1 = pos2;
            pos2 = pos1 + 1;
        }
        sb.append(str.charAt(pos1));
        sb.append(pos2 - pos1);
        String compressed = sb.toString();
        if (compressed.length() >= str.length()) {
            return str;
        } else {
            return compressed;
        }
    }
}
