package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class MyLogin extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    public MyLogin(User user) {
        super(user.getLogin(), user.getHash(), AuthorityUtils.createAuthorityList("ALL"));
    }

}
