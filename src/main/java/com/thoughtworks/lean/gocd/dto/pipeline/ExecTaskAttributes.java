package com.thoughtworks.lean.gocd.dto.pipeline;

import java.util.List;

public class ExecTaskAttributes implements TaskAttributes{

    List<String> run_if;
    String command;
    List<String> arguments;
    String working_directory;
    Task on_cancel;

    public List<String> getRun_if() {
        return run_if;
    }

    public void setRun_if(List<String> run_if) {
        this.run_if = run_if;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public String getWorking_directory() {
        return working_directory;
    }

    public void setWorking_directory(String working_directory) {
        this.working_directory = working_directory;
    }

    public Task getOn_cancel() {
        return on_cancel;
    }

    public void setOn_cancel(Task on_cancel) {
        this.on_cancel = on_cancel;
    }

    @Override
    public String toString() {
        return "ExecTaskAttributes{" +
                "run_if=" + run_if +
                ", command='" + command + '\'' +
                ", arguments=" + arguments +
                ", working_directory='" + working_directory + '\'' +
                ", on_cancel=" + on_cancel +
                '}';
    }
}
