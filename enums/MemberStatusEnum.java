
package enums;

public enum MemberStatusEnum {
    VIP(1, 5),
    REGULAR(2, 3);
    private final int priorityLevel;
    private final int maxBookAllowed;

    private MemberStatusEnum(int priorityLevel, int maxBookAllowed) {
        this.priorityLevel = priorityLevel;
        this.maxBookAllowed = maxBookAllowed;
    }
        public int getPriorityLevel() {
        return priorityLevel;
        }

     public int getMaxBookAllowed() {
        return maxBookAllowed;
    }
        
}
