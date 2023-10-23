package com.obalweb.gosler.repository;

public class UserQuery {
    public static final String FIND_ONE =
            "select id, name, surname, username, email " +
                    "from users where id = ?";

    public static final String SAVE =
            "insert into users(name, surname, username, email) " +
                    "values (?, ?, ?, ?)";

    public static final String SAVE_AND_RETURN_ID =
            "insert into users(name, surname, username, email) " +
                    "values (?, ?, ?, ?)";
}
