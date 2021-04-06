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
    public void whenHTMLReport() {
        store.add(worker);
        Report engine = new ReportHTML(store);
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

    @Test
    public void whenJSONReport() {
        store.add(worker);
        store.add(worker1);
        Report report = new ReportJSON(store);
        String expected = "{\"employees\":[{\"name\":\"Ivan\",\"salary\":100},{\"name\":\"Oleg\",\"salary\":200}]}";
        assertThat(report.generate(em -> true), is(expected));
    }

    @Test
    public void whenXMLReport() {
        store.add(worker);
        store.add(worker1);
        Report report = new ReportXML(store);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<employees>\n" +
                "    <employee name=\"Ivan\" salary=\"100.0\"/>\n" +
                "    <employee name=\"Oleg\" salary=\"200.0\"/>\n" +
                "</employees>\n";
        assertThat(report.generate(em -> true), is(expected));
    }
}