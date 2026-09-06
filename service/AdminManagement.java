package service;

import java.util.ArrayList;
import java.util.StringTokenizer;
import model.Admin;
import util.FileHelper;

public class AdminManagement {
    public ArrayList<Admin> aList = new ArrayList<>();
    private static final String ADMIN_FILE = "Admin.txt";

    private FileHelper<Admin> fileAdmin = new FileHelper<Admin>(aList) {
        @Override
        public Admin handleLine(String line) {
            try {
                StringTokenizer st = new StringTokenizer(line, "|");
                String id = st.nextToken().trim();
                String userName = st.nextToken().trim();
                String password = st.nextToken().trim();
                return new Admin(id, userName, password);
            } catch (Exception e) {
                System.out.println("  [!] Skip invalid admin line: " + line);
                return null;
            }
        }
    };

    public AdminManagement() {
        fileAdmin.loadFromFile(ADMIN_FILE);
    }

    public Admin login(String userName, String password) {
        for (Admin a : aList) {
            if (a.login(userName, password)) return a;
        }
        return null;
    }
}