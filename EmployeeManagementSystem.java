package com.dugu.acc.dev.Core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EmployeeManagementSystem {

	public static List<Employee> employees = new ArrayList<>();

	static {
		employees.add(new Employee(112, "Deepak", "18/04/2001"));
		employees.add(new Employee(136, "Asutosh", "18/04/2008"));
		employees.add(new Employee(890, "Samasa", "18/04/2003"));
		employees.add(new Employee(321, "Saroj", "18/04/2010"));
	}

	public static void getEmployeeInfo() {
		Map<Employee, Long> employeeMap = new HashMap<>();
		List<Long> years = new ArrayList<>();
		for (Employee employee : employees) {
			employeeMap.put(employee, getYear(employee.getDoj()));
			years.add(getYear(employee.getDoj()));
		}
		Collections.sort(years);
		Collections.reverse(years);
		for (long value : years) {
			System.out.println(getKeyFromValue(employeeMap, value));
		}
	}

	private static Employee getKeyFromValue(Map<Employee, Long> hm, long value) {
		for (Employee o : hm.keySet()) {
			if (hm.get(o).equals(value)) {
				return o;
			}
		}
		return null;
	}

	private static long getYear(String doj) {
		Date d1 = null;
		Date d2 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = sdf.format(new Date());
		int diff = 0;
		try {
			d1 = sdf.parse(doj);
			d2 = sdf.parse(currentDate);
			Calendar a = getCalendar(d1);
			Calendar b = getCalendar(d2);
			diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
			if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH)
					&& a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
				diff--;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return diff;
	}

	private static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}

	public static void main(String[] args) throws ParseException {
		getEmployeeInfo();
	}

}
