package com.foggger.domain.entities.program;

import com.foggger.domain.entities.message.Module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Client {

    private String code;
    private String name;
    private String notes;
    private String endpoint;
    private List<Module> modules;

    public Client() {
        modules = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public void setModules(Module... modules) {
        this.modules = new ArrayList<>(Arrays.asList(modules));
    }

    @Override
    public String toString() {
        return "Client{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", modules=" + modules +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(code, client.code) &&
                Objects.equals(name, client.name) &&
                Objects.equals(endpoint, client.endpoint) &&
                Objects.equals(modules, client.modules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, endpoint, modules);
    }
}
