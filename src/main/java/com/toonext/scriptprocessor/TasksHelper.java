package com.toonext.scriptprocessor;


import com.toonext.EnvConst;
import com.toonext.core.cli.Console;
import com.toonext.scriptprocessor.java.IServerScript;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Kayra created 17-10-2016
 */

public class TasksHelper {
    private static String MODULE_NAME = "admin";
    private static String TASKS_PACKAGE = "task.console";

    private static TreeMap<String, ServerTaskClass> tasks;

    public static TreeMap<String, ServerTaskClass> getAllTasks(boolean showConsoleOutput) {
        tasks = new TreeMap<String, ServerTaskClass>();

        if (showConsoleOutput) {
            System.out.printf(Console.advancedFormat, "Application", "Command", "Trigger");
            System.out.printf(Console.advancedFormat, "--------------", "-----", "-------");
        }

        String appPackageName = EnvConst.MAIN_PACKAGE + "." + MODULE_NAME + "." + TASKS_PACKAGE;
        Reflections appReflections = new Reflections(appPackageName);
        Set<Class<?>> appClasses = appReflections.getTypesAnnotatedWith(Command.class);
        for (Class<?> taskClass : appClasses) {
            ServerTaskClass sc = new ServerTaskClass(MODULE_NAME, (Class<IServerScript>) taskClass);
            tasks.put(sc.getCommand(), sc);
            if (showConsoleOutput) {
                outToConsole(MODULE_NAME, sc.getInitializerClass());
            }
        }


        return tasks;

    }

    public static List<ServerTaskClass> getAllTriggeredTasks(String appName, Trigger trigger) {
        List<ServerTaskClass> triggerdTasks = new ArrayList<ServerTaskClass>();
        if (tasks == null) {
            getAllTasks(false);
        }
        for (ServerTaskClass t : tasks.values()) {
            if (t.getTrigger() == trigger && t.getAppName().equals(appName)) {
                triggerdTasks.add(t);
            }
        }
        return triggerdTasks;

    }

    public static ServerTaskClass getTaskClass(String command) {
        if (tasks == null) {
            getAllTasks(false);
        }
        return tasks.get(command);

    }



    private static void outToConsole(String appName, Class<IServerScript> clazz) {
        Command command = clazz.getAnnotation(Command.class);
        if (command != null) {
            System.out.printf(Console.advancedFormat, appName + ": ", command.name(), command.trigger());
        } else {
            System.out.printf(Console.advancedFormat, appName + ": ", clazz.getName(), "");
        }
    }
}
