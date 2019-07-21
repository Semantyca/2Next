package com.toonext.core.cli;


import com.toonext.Environment;
import com.toonext.domain.AppEnv;
import com.toonext.log.JavaConsoleLogger;
import com.toonext.log.Lg;
import com.toonext.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Console implements Runnable {
    public static final String format = "%-40s%s%n";
    public static final String superShortFormat = "%-22s%s%n";
    public static final String shortFormat = "%-20s%s%n";
    public static final String advancedFormat = "%-20s%-25s%s%n";


    public static List<String> getValFromConsole(String prefix, String pattern) {
        System.out.print(prefix);
        List<String> result = new ArrayList();
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
                String command = in.nextLine();
                cliHandler(command);
            } catch (Exception e) {
                Lg.exception(e);
            } finally {
                // in.close();
            }
        }
    }

    public void cliHandler(String command) {
        command = command.trim();
        if (command.startsWith("echo") || command.startsWith("log")) {
            String echo = getThirdParameter(command, "echo", "log");
            System.out.println(echo);
        } else {
            System.out.println("> " + command);
            if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("q")) {
                //Server.shutdown();
            } else if (command.equalsIgnoreCase("clean display") || command.equalsIgnoreCase("cls")) {
                System.out.print("\033\143");

            } else if (command.equalsIgnoreCase("info") || command.equalsIgnoreCase("i")) {
                Info.showServerInfo();
            } else if (command.equalsIgnoreCase("show jvm options") || command.equalsIgnoreCase("sjo")) {
                Info.showJVMOptions(new JavaConsoleLogger());
            } else if (command.equalsIgnoreCase("modules info") || command.equalsIgnoreCase("mi")) {
                for (AppEnv app : Environment.getApplications()) {
                    System.out.printf(format, app.appName + ": ", app.getDefaultPage());
                }
            } else if (command.equals("help") || command.equalsIgnoreCase("h")) {
                System.out.println(StringUtil.readResource("console_commands.txt"));
            } else {
                if (!command.trim().equalsIgnoreCase("")) {
                    System.err.println("error -command \"" + command
                            + "\" is not recognized, try to type 'help' to connect a short guide about commands");
                }
            }
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
