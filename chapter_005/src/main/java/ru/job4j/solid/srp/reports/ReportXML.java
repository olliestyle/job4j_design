package ru.job4j.solid.srp.reports;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ReportXML implements Report {

    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees();
        String employeeXML = "";
        try (StringWriter sw = new StringWriter()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            employees.setEmployees(store.findBy(em -> true));
            marshaller.marshal(employees, sw);
            employeeXML = sw.getBuffer().toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeXML;
    }
}
