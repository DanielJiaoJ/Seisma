import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Application {
	private static String Input;
	private static String Output;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File directory = new File("");
		Input=directory.getAbsolutePath()+"/src/input.csv";
		Output=directory.getAbsolutePath()+"/src/output.csv";
		BufferedReader br = null;
		BufferedWriter writeText = null;
		File writeFile = new File(Output);
        String line = "";
        String firstName;
        String lastName;
        int annualSalary;
        int superRate;
        String payPeriod;
        try {
        	writeText = new BufferedWriter(new FileWriter(writeFile));
            br = new BufferedReader(new FileReader(Input));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");       
                firstName=data[0];
                lastName=data[1];
                annualSalary=Integer.parseInt(data[2]);
                superRate=Integer.parseInt(data[3].substring(0,data[3].length()-1));
                payPeriod=data[4];
                int grossIncome=countGrossIncome(annualSalary);
                int incomeTax=countIncomeTax(annualSalary);
                int netIncome=grossIncome-incomeTax;
                int monthlySuper=countSuper(grossIncome,superRate);
                writeText.write(firstName+" "+lastName);
                writeText.write(","+payPeriod);
                writeText.write(","+Integer.toString(grossIncome));
                writeText.write(","+Integer.toString(incomeTax));
                writeText.write(","+Integer.toString(netIncome));
                writeText.write(","+Integer.toString(monthlySuper));
                writeText.newLine();
                //Unit test for testing reading the input file
//                System.out.print(firstName);
//                System.out.print("|");
//                System.out.print(lastName);
//                System.out.print("|");
//                System.out.print(annualSalary);
//                System.out.print("|");
//                System.out.print(superRate);
//                System.out.print("|");
//                System.out.print(startDate);
//                System.out.println("|");
              //Unit test for testing counting the gross income
//                System.out.println(countGrossIncome(13));
//                System.out.println(countGrossIncome(18));
//                System.out.println(countGrossIncome(19));
              //Unit test for testing counting the income tax
//                System.out.println(countIncomeTax(18200)*12);
//                System.out.println(countIncomeTax(37000)*12);
//                System.out.println(countIncomeTax(87000)*12);
//                System.out.println(countIncomeTax(180000)*12);
//                System.out.println(countIncomeTax(60050));
              //Unit test for testing counting the income tax
//              System.out.println(countSuper(5004,9));
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writeText != null) {
                try {
                	writeText.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	private static int countGrossIncome(int annualSalary) {
		return Math.round((float) annualSalary/12);
	}
	
	private static int countIncomeTax(int annualSalary) {
		if(annualSalary<=18200) {
			return 0;
		}else if(annualSalary<=37000) {
			return Math.round((float) ((annualSalary-18200)*0.19)/12);
		}else if(annualSalary<=87000) {
			return Math.round((float) (3572+(annualSalary-37000)*0.325)/12);
		}else if(annualSalary<=180000) {
			return Math.round((float) (19822+(annualSalary-87000)*0.37)/12);
		}else {
			return Math.round((float) (54232+(annualSalary-180000)*0.45)/12);
		}
	}
	
	private static int countSuper(int grossIncome, int superRate) {
		return Math.round((float) grossIncome*superRate/100);
	}
	
	
}