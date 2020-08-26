package com.sapient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParseIncome {

	private BufferedReader fileReader;
	public ParseIncome(String input_file,String output_file) {
		IncomeInit.IN_FILE_LOCATION=input_file;
		IncomeInit.OUT_FILE_LOCATION=output_file;
	}
	public ParseIncome() {
		// Do nothing
	}
	public boolean averageIncome() throws IOException {
		Map<String, Float> countryMap = new HashMap<String, Float>();

		try {
			fileReader = new BufferedReader(new FileReader(IncomeInit.IN_FILE_LOCATION));
			fileReader.readLine();
			String data = "";
			while ((data = fileReader.readLine()) != null) {
				String[] s = data.split(IncomeInit.DELIMETER_COMMA);
				String city = s[0];
				String country = s[1];
				String gender = s[2];
				String currency = s[3];
				int averageIncome = Integer.parseInt(s[4]);
				float covertAvgIncome = (float) 0.0;
				covertAvgIncome = avgIncome(currency, averageIncome, covertAvgIncome);

				if (country.equals(" ")) {
					if (countryMap.containsKey(city + "," + gender)) {
						float avg = countryMap.get(city + "," + gender);
						avg = avg + covertAvgIncome;
						countryMap.put(city + "," + gender, avg);
					} else {
						countryMap.put(city + "," + gender, covertAvgIncome);
					}
				} else {
					if (countryMap.containsKey(country + "," + gender)) {
						float avg = countryMap.get(country + "," + gender);
						avg = avg + covertAvgIncome;
						countryMap.put(country + "," + gender, avg);
					} else {
						countryMap.put(country + "," + gender, covertAvgIncome);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		FileWriter fw = new FileWriter(IncomeInit.OUT_FILE_LOCATION);
		BufferedWriter writer = new BufferedWriter(fw);
		try {
			String msg = "City/Country Gender Average Income(in USD)";
			writer.write(msg);
			writer.newLine();
			for (Map.Entry<String, Float> ms : countryMap.entrySet()) {

				writeToFile(writer, ms, IncomeInit.FILE_CSV);
			}
			return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			writer.close();
		}

	}

	private void writeToFile(BufferedWriter writer, Map.Entry<String, Float> ms, String fileType) throws IOException {
		String key = ms.getKey();
		float value = ms.getValue();
		String countryGen[] = key.split(",");
		String recard = countryGen[0] + " " + countryGen[1] + " " + value;

		if (fileType.equals(IncomeInit.FILE_CSV)) {
			writer.write(key + "," + value);
		} else {
			writer.write(recard);
		}
		writer.newLine();
	}
	private float avgIncome(String currency, int averageIncome, float covertAvgIncome) {
		if (currency.equals(IncomeInit.CURRENCY_CODE_IND)) {
			covertAvgIncome = averageIncome / 66;
		} else if (currency.equals(IncomeInit.CURRENCY_CODE_GBP)) {
			covertAvgIncome = (float) (averageIncome / 0.67);
		} else if (currency.equals(IncomeInit.CURRENCY_CODE_SGP)) {
			covertAvgIncome = (float) (averageIncome / 1.5);
		} else if (currency.equals(IncomeInit.CURRENCY_CODE_HKD)) {
			covertAvgIncome = averageIncome / 8;
		} else if (currency.equals(IncomeInit.CURRENCY_CODE_USD)) {
			covertAvgIncome = averageIncome;
		}
		return covertAvgIncome;
	}

	public static void main(String args[]) {
		if(args.length==2) {
		ParseIncome ob = new ParseIncome(args[0],args[1]);
		try {
			ob.averageIncome();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		else {
			ParseIncome ob = new ParseIncome();
			try {
				ob.averageIncome();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	
}