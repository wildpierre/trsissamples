package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface LoginService {

    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public User getUserByLogin(String login) throws UsernameNotFoundException;

}
