package ru.job4j.solid.srp.reports;

public class EuroSalaryConverter implements SalaryConverter {

    @Override
    public double convert(double salary) {
        return salary / 88;
    }
}
