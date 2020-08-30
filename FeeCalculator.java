package com.assignment.feecalculator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.opencsv.CSVReader;

public class FeeCalculator {
	public static void main(String args[]) throws ParseException {
		try {
			String file = "C:\\Users\\raveena\\eclipse-workspace\\Sample_Data_Fee_Calculator.csv";
			CSVReader reader = new CSVReader(new FileReader(file), ',');
			ArrayList<ArrayList<String>> transactions = new ArrayList<ArrayList<String>>();
			String[] record;
			ArrayList<String> transaction = null;
			try {
				while ((record = reader.readNext()) != null) {
					transaction = new ArrayList<String>();
					for (String value : record) {
						transaction.add(value);
					}
					transaction.add("0");
					transactions.add(transaction);
				}
				transactions.remove(0);
				// sorting according to dates
				Collections.sort(transactions, new Comparator<ArrayList<String>>() {
					public int compare(ArrayList<String> a, ArrayList<String> b) {
						java.util.Date date1 = null, date2 = null;
						try {
							date1 = new SimpleDateFormat("dd/MM/yyyy").parse(a.get(4));
							date2 = new SimpleDateFormat("dd/MM/yyyy").parse(b.get(4));
						} catch (ParseException e) {
							e.printStackTrace();
						}

						return date1.compareTo(date2);
					}
				});
				boolean check_intra_transaction = false;
				for (int i = 0; i < transactions.size(); i++) {
					ArrayList<String> record1 = transactions.get(i);

					ArrayList<String> record2 = i > 0 ? transactions.get(i - 1) : null;

					String priority = record1.get(6);
					if (i != 0 && (record1.get(1)).equalsIgnoreCase(record2.get(1))// check if Intraday
							&& (record1.get(2)).equalsIgnoreCase(record2.get(2))
							&& (record1.get(4)).equalsIgnoreCase(record2.get(4))) {
						if (((record1.get(3)).equalsIgnoreCase("BUY") && (record2.get(3)).equalsIgnoreCase("SELL"))
								|| ((record1.get(3)).equalsIgnoreCase("SELL")
										&& (record2.get(3)).equalsIgnoreCase("BUY"))) {
							if (check_intra_transaction != true) {
								check_intra_transaction = true;
								record1.set(7, String.valueOf(10 + Integer.parseInt(record1.get(7))));
								record2.set(7, String.valueOf(10 + Integer.parseInt(record2.get(7))));
							} else {
								check_intra_transaction = false;
							}
						}
					} else if (priority.equalsIgnoreCase("Y")) {
						record1.set(7, String.valueOf(500 + Integer.parseInt(record1.get(7))));
					} else if (priority.equalsIgnoreCase("N")) {
						if ((record1.get(3)).equalsIgnoreCase("SELL")
								|| (record1.get(3)).equalsIgnoreCase("WITHDRAW")) {

							record1.set(7, String.valueOf(100 + Integer.parseInt(record1.get(7))));
						} else {
							record1.set(7, String.valueOf(50 + Integer.parseInt(record1.get(7))));
						}
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			// calling summary report API
			SummaryReportAPI.summaryReport(transactions);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
