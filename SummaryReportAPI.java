package com.assignment.feecalculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

class SortReport implements Comparator<ArrayList<String>> {

	public int compare(ArrayList<String> o1, ArrayList<String> o2) {
		if (o1.get(1).equalsIgnoreCase(o2.get(1))) {
			if (o1.get(3).equalsIgnoreCase(o2.get(3))) {
				Date date1 = null, date2 = null;
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(o1.get(4));
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(o2.get(4));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				if (date1.equals(date2)) {
					return (o1.get(6)).compareTo(o2.get(6));
				} else {
					return date1.compareTo(date2);
				}

			} else {
				return (o1.get(3)).compareTo(o2.get(3));
			}

		}
		return (o1.get(1)).compareTo(o2.get(1));

	}

}

public class SummaryReportAPI {
	public static void summaryReport(ArrayList<ArrayList<String>> transactions) {
		SortReport tosort = new SortReport();
		Collections.sort(transactions, tosort);
		System.out.format("%10s %16s %16s %5s %10s\n", "Client_Id", "Transaction_Type", "Transaction_Date", "Priority",
				"Processing_Fee");
		for (ArrayList<String> s : transactions) {
			System.out.format("%10s %16s %16s %5s %10s\n", s.get(1), s.get(3), s.get(4), s.get(6), s.get(7));
		}

	}
}
