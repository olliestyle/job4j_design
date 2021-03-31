package ru.job4j.solid.srp.reports;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ReportEngineTest {

    MemStore store = new MemStore();
    Calendar now = Calendar.getInstance();
    Employee worker = new Employee("Ivan", now, now, 100);
    Employee worker1 = new Employee("Oleg", now, now, 200);

    @Test
    public void whenOldGenerated() {
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRReport() {
        store.add(worker);
        store.add(worker1);
        Report reportHR = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary")
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(reportHR.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountantReport() {
        store.add(worker);
        SalaryConverter salaryConverter = new EuroSalaryConverter();
        Report engine = new ReportAccountant(store, salaryConverter);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() / 88).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenProgrammersReport() {
        store.add(worker);
        Report engine = new ReportProgrammers(store);
        StringBuilder expect = new StringBuilder()
                .append("HTML Format:").append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}