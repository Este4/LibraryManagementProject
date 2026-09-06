package service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import model.Member;
import enums.MemberStatusEnum;
import enums.MemberManagementResult;
import util.FileHelper;

public class MemberManagement {
    public ArrayList<Member> mList = new ArrayList<>();
    private static final String MEMBER_FILE = "Memeber.txt";

    private FileHelper<Member> fileMember = new FileHelper<Member>(mList) {
        @Override
        public Member handleLine(String line) {
            try {
                String[] parts = line.split("\\|", -1);
                String id = parts[0].trim();
                String userName = parts[1].trim();
                String password = parts[2].trim();
                MemberStatusEnum status = MemberStatusEnum.valueOf(parts[3].trim());

                Member m = new Member(id, userName, password, status);

                if (parts.length > 4 && !parts[4].trim().isEmpty()) {
                    StringTokenizer st = new StringTokenizer(parts[4], ",");
                    while (st.hasMoreTokens()) {
                        m.addBorrowedBook(st.nextToken().trim());
                    }
                }
                return m;
            } catch (Exception e) {
                System.out.println("  [!] Skip invalid member line: " + line);
                return null;
            }
        }
    };

    public MemberManagement() {
        loadAll();
    }

    public void loadAll() {
        fileMember.loadFromFile(MEMBER_FILE);
    }

    public void saveAll() {
        fileMember.saveToFile(MEMBER_FILE);
    }

    public MemberManagementResult createMember(String id, String userName,
                                               String password, MemberStatusEnum status) {
        if (searchById(id) != null) return MemberManagementResult.DUPLICATE_ID;
        if (searchByUserName(userName) != null) return MemberManagementResult.DUPLICATE_USERNAME;
        mList.add(new Member(id, userName, password, status));
        saveAll();
        return MemberManagementResult.SUCCESS;
    }

    public List<Member> readAll() {
        return mList;
    }

    public MemberManagementResult updateMember(String id, String newUserName,
                                               String newPassword, MemberStatusEnum newStatus) {
        Member m = searchById(id);
        if (m == null) return MemberManagementResult.NOT_FOUND;

        if (newUserName != null && !newUserName.isEmpty()) m.setUserName(newUserName);
        if (newPassword != null && !newPassword.isEmpty()) m.setPassword(newPassword);
        if (newStatus != null) m.setStatusMem(newStatus);
        saveAll();
        return MemberManagementResult.SUCCESS;
    }

    public MemberManagementResult deleteMember(String id) {
        Member m = searchById(id);
        if (m == null) return MemberManagementResult.NOT_FOUND;
        if (!m.getBorrowedBook().isEmpty()) return MemberManagementResult.HAS_BORROWED_BOOKS;
        mList.remove(m);
        saveAll();
        return MemberManagementResult.SUCCESS;
    }

    public Member searchById(String id) {
        for (Member m : mList) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }

    public Member searchByUserName(String userName) {
        for (Member m : mList) {
            if (m.getUserName().equals(userName)) return m;
        }
        return null;
    }

    public List<Member> searchByKeyword(String keyword) {
        List<Member> result = new ArrayList<>();
        String lower = keyword.toLowerCase();
        for (Member m : mList) {
            if (m.getUserName().toLowerCase().contains(lower)
                    || m.getId().toLowerCase().contains(lower)) {
                result.add(m);
            }
        }
        return result;
    }

    public Member login(String userName, String password) {
        for (Member m : mList) {
            if (m.login(userName, password)) return m;
        }
        return null;
    }

    public boolean isBookBorrowedByAnyone(String editId) {
        for (Member m : mList) {
            if (m.getBorrowedBook().contains(editId)) return true;
        }
        return false;
    }
}
