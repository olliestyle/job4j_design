package ru.job4j.solid.srp.reports;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.function.Predicate;

public class ReportJSON implements Report {

    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        for (Employee employee: store.findBy(filter)) {
            JSONObject employeeJSON = new JSONObject(employee);
            array.put(employeeJSON);
        }
        result.put("employees", array);
        return result.toString();
    }
}
