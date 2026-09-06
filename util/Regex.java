/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author ledan
 */
public class Regex {
    public static final String MEMBER_ID_REGEX = "^M\\d{3}$";
    public static final String ADMIN_ID_REGEX  = "^A\\d{3}$";
    public static final String TITLE_ID_REGEX  = "^T\\d{3}$";
    public static final String EDIT_ID_REGEX   = "^E\\d{3}$";
    public static final String USERNAME_REGEX  = "^[a-zA-Z0-9_]{3,20}$";
    public static final String PASSWORD_REGEX  = "^.{6,}$";
    public static final String NAME_REGEX      = "^[\\p{L} .'-]{2,60}$";
}
