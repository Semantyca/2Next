package com.toonext.core.cli;


import com.toonext.log.Lg;
import com.toonext.scriptprocessor.*;
import com.toonext.util.StringUtil;
import org.apache.commons.cli.*;

import java.util.*;


public class Console implements Runnable {
    public static final String format = "%-40s%s%n";
    public static final String superShortFormat = "%-22s%s%n";
    public static final String shortFormat = "%-20s%s%n";
    public static final String advancedFormat = "%-20s%-25s%s%n";
    private Options options = new Options();


    public Console() {
        options.addOption("h", "help", false, "show help.");
        options.addOption("st", "showtasks", false, "show all available task.");
        options.addOption("rt", "runtask", true, "run a task.");
    }

    public static List<String> getValFromConsole(String prefix, String pattern) {
        System.out.print(prefix);
        List<String> result = new ArrayList<>();
        String value = "";
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        try {
            while (in.hasNext()) {
                boolean isCorrect = false;
                value = in.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(value);
                while (tokenizer.hasMoreTokens()) {
                    String val = tokenizer.nextToken();
                    if (StringUtil.checkByPattren(val, pattern)) {
                        isCorrect = true;
                        result.add(val);
                    }
                }
                if (isCorrect) {
                    return result;
                }
                System.out.println("\"" + value + "\" is wrong value, enter another value ");
            }
        } catch (Exception e) {
            Lg.exception(e);
        } finally {
            // in.close();
        }
        return null;
    }

    @Override
    public void run() {
        @SuppressWarnings("resource") final Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            try {
                String[] command = in.nextLine().split("\\s{1,}");
                System.out.println(Arrays.toString(command));
                cliHandler(command);
            } catch (Exception e) {

                Lg.exception(e);
            } finally {
                // in.close();
            }
        }
    }

    public void cliHandler(String[] comm) {
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, comm);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (cmd.hasOption("h")) {
            System.out.println(cmd.getArgList());
        } else if (cmd.hasOption("rt")) {
            String optionValue = cmd.getOptionValue("rt");
            Option[] option = cmd.getOptions();
            if (optionValue != null) {
                TaskRunner ct = new TaskRunner();
                ServerTaskClass clazz = TasksHelper.getTaskClass(optionValue);
                if (clazz != null) {
                    ServerTaskOutcome outcome = ct.processCode(clazz);
                    if (outcome.getType() != InfoMessageType.OK) {
                        System.err.println(outcome.getException());
                    }
                } else {
                    System.err.println("\"" + optionValue + "\" task has not been found");
                }
            } else {
                System.err.println("Task is null");
            }
        } else if (cmd.hasOption("st")) {
            TasksHelper.getAllTasks(true);
        } else {
/*
            String command = comm[0].trim();
            if (command.startsWith("echo") || command.startsWith("log")) {
                String echo = getThirdParameter(command, "echo", "log");
                System.out.println(echo);
            } else {
                System.out.println("> " + command);
                if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("q")) {
                    // Server.shutdown();
                } else if (command.equalsIgnoreCase("clean display") || command.equalsIgnoreCase("cls")) {
                    System.out.print("\033\143");

                } else if (command.equalsIgnoreCase("info") || command.equalsIgnoreCase("i")) {
                    Info.showServerInfo();
                } else if (command.equalsIgnoreCase("show jvm options") || command.equalsIgnoreCase("sjo")) {
                    Info.showJVMOptions(new JavaConsoleLogger());
                } else if (command.equalsIgnoreCase("modules info") || command.equalsIgnoreCase("mi")) {
              *//*  for (AppEnv app : Environment.getApplications()) {
                    System.out.printf(format, app.appName + ": ", app.getDefaultPage());
                }*//*
                } else if (command.equalsIgnoreCase("variables info") || command.equalsIgnoreCase("vi")) {
                    //  Info.showVariablesInfo();
                } else if (command.equalsIgnoreCase("database info") || command.equalsIgnoreCase("dbi")) {
                    Info.showDatabaseInfo();
                } else if (command.equalsIgnoreCase("database speed") || command.equalsIgnoreCase("dbs")) {
                    //  System.out.println("availability index " + Info.showDatabaseSpeed());
                } else if (command.equalsIgnoreCase("applications info") || command.equalsIgnoreCase("ai")) {
                    //   Info.showApplicationsInfo();
                }

                if (command.equalsIgnoreCase("reset users") || command.equalsIgnoreCase("ru")) {
                    // System.out.println(ServerSessionPool.flush() + " sessions were reseted (included invalidated)");
                } else if (command.equalsIgnoreCase("show session pool") || command.equalsIgnoreCase("ssp")) {
               *//* for (Entry<String, UserSession> entry : SessionPool.getUserSessions().entrySet()) {
                    try {
                        UserSession ses = entry.getValue();
                        System.out.println(entry.getKey() + " " + ses.toString());
                    } catch (IllegalStateException ise) {

                    }
                }*//*
                } else if (command.equalsIgnoreCase("tune") || command.equalsIgnoreCase("tu")) {
            *//*    Tuner tuner = new Tuner(Environment.database.getConnectionPool());
                tuner.preStart();
                for (AppEnv env : Environment.getApplications()) {
                    tuner.postStart(env);
                }*//*

                } else if (command.equalsIgnoreCase("show tasks") || command.equalsIgnoreCase("st")) {
                    //  TasksHelper.getAllTasks(true);
                } else if (command.equalsIgnoreCase("show services info") || command.equalsIgnoreCase("ssi")) {
                    //  Info.showServicesInfo();
                } else if (command.contains("run task") || command.startsWith("rt")) {
                    String taskCommand = getThirdParameter(command, "run task", "rt");
                    if (taskCommand.trim().isEmpty()) {
                        System.err.println("error -task name is empty");
                    } else {
                   *//* ConsoleTaskRunner ct = new ConsoleTaskRunner();
                    ServerTaskClass clazz = TasksHelper.getTaskClass(taskCommand);
                    if (clazz != null) {
                        ServerTaskOutcome outcome = ct.processCode(clazz);
                        if (outcome.getType() != InfoMessageType.OK) {
                            System.err.println(outcome.getException());
                        }
                    } else {
                        System.err.println("\"" + taskCommand + "\" task has not been found");
                    }*//*
                    }
                } else if (command.contains("add substitute") || command.startsWith("as")) {
                    String addSubstitionActionString = getThirdParameter(command, "add substitute", "as");
                    if (addSubstitionActionString.trim().isEmpty()) {
                        System.err.println("error -substitute is empty");
                    } else {
                  *//*  UserDAO userDAO = new UserDAO(new UserSession(new SuperUser()));
                    StringTokenizer tokens = new StringTokenizer(addSubstitionActionString, ">");
                    IUser from = userDAO.findByLogin(tokens.nextElement().toString());
                    List<Long> substitutiesList = new ArrayList<>();
                    while (tokens.hasMoreElements()) {
                        String s = tokens.nextElement().toString();
                        IUser substUser = userDAO.findByLogin(s);
                        if (substUser != null) {
                            substitutiesList.add(substUser.getId());
                        } else {
                            System.err.println("error -substitute \"" + s + "\" has not been  found");
                        }
                    }
                    substitutiesList.addAll(from.getSubstitutes());
                    from.setSubstitutes(substitutiesList);
                    try {
                        userDAO.update(from);
                    } catch (DAOException | SecureException e) {
                        System.err.println(e);
                    }*//*

                    }
                } else if (command.equalsIgnoreCase("reload vocabulary") || command.equalsIgnoreCase("rv")) {

              *//* for (AppEnv env : Environment.getApplications()) {
                    env.templates.reset();
                    System.out.println("Templates and the dictionary were reloaded (" + env.appName + ")");
                }*//*
                } else if (command.equalsIgnoreCase("reload all") || command.equalsIgnoreCase("ra")) {
              *//*  for (AppEnv env : Environment.getApplications()) {
                    env.ruleProvider.resetRules(true);
                }

                for (AppEnv env : Environment.getApplications()) {

                    env.templates.reset();
                    System.out.println("Templates, rules and the dictionary were reloaded (" + env.appName + ")");
                }*//*
                } else if (command.equalsIgnoreCase("show scheduler queue") || command.equalsIgnoreCase("ssq")) {
              *//*  SchedulerHelper helper = new SchedulerHelper();
                try {
                    helper.getQueue(true);
                } catch (IOException | SchedulerException e) {
                    System.err.println(e);
                }*//*
                } else if (command.contains("backup entities") || command.startsWith("backent")) {
                    String entityAlias = getThirdParameter(command, "backup entities", "backent");
                    if (entityAlias.trim().equals("")) {
                        System.err.println("error -entity name or alias has not been pointed");
                    } else {
              *//*      BackupEntity be = new BackupEntity();

                    be.backup(entityAlias, true);

                    System.out.println("done");*//*
                    }
                } else if (command.contains("run batch") || command.startsWith("rubat")) {
                    String batch = getThirdParameter(command, "run batch", "rubat");
                    if (batch.trim().equals("")) {
                        System.err.println("error -batch name is empty");
                    } else {
               *//*     try (BufferedReader br = new BufferedReader(
                            new FileReader(EnvConst.RESOURCES_DIR + File.separator + batch))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (!line.startsWith("#")) {
                                cliHandler(line);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.err.println("\"" + batch + "\" batch file not found");
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    System.out.println("the batch \"" + batch + "\"  has been done");*//*
                    }
                } else if (command.contains("show batch") || command.startsWith("shbat")) {
                    String batch = getThirdParameter(command, "show batch", "shbat");
             *//*   if (batch.trim().equals("")) {
                    System.err.println("error -batch name is empty");
                } else {
                    try (BufferedReader br = new BufferedReader(
                            new FileReader(EnvConst.RESOURCES_DIR + File.separator + batch))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (FileNotFoundException e) {
                        System.err.println("\"" + batch + "\" batch file not found");
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    System.out.println("the batch \"" + batch + "\"  has been done");
                }*//*
                } else if (command.equalsIgnoreCase("show file to delete") || command.equalsIgnoreCase("sfd")) {
             *//*   if (TempFileCleaner.getFileToDelete().size() == 0) {
                    System.out.println("there are no any files to delete");
                } else {
                    for (String ci : TempFileCleaner.getFileToDelete().values()) {
                        System.out.println(ci);
                    }
                }*//*


                } else if (command.equals("help") || command.equalsIgnoreCase("h")) {
                    //  System.out.println(StringUtil.readResource("/com/exponentus/server/console_commands.txt"));
                } else {
                    if (!command.trim().equalsIgnoreCase("")) {
                        System.err.println("error -command \"" + command
                                + "\" is not recognized, try to type 'help' to connect a short guide about commands");
                    }
                }
            }*/
        }

    }

    private String getThirdParameter(String command, String name, String shortName) {
        int start = 0;
        if (command.contains(name)) {
            start = name.length();
        } else if (command.startsWith(shortName)) {
            start = shortName.length();
        }
        return command.substring(start).trim();
    }
}
