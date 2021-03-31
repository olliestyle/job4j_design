package ru.job4j.solid.srp.reports;

import java.util.function.Predicate;

public class ReportAccountant implements Report {

    Store store;
    SalaryConverter salaryConverter;

    public ReportAccountant(Store store, SalaryConverter salaryConverter) {
        this.store = store;
        this.salaryConverter = salaryConverter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(salaryConverter.convert(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
