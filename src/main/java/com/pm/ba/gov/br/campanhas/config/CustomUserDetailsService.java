package com.pm.ba.gov.br.campanhas.config;

import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
 
    private static Logger log = LogManager.getLogger(CustomUserDetailsService.class);
 
    @Value("${ldap.user.searchBase}")
    private String searchBase;
 
    @Value("${ldap.user.dn.pattern}")
    private String dnPattern;
 
    @Value("${spring.ldap.userid}")
    private String userId;
 
    @Value("${ldap.user.commanName}")
    private String userCommanName;
 
    @Value("${ldap.user.password}")
    private String userPassword;
 
    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AndFilter filter = new AndFilter();
            filter.and(new EqualsFilter("objectClass", "person"))
                  .and(new EqualsFilter(userId, username));
            
            List<LdapUserDetails> users = ldapTemplate.search(searchBase, filter.encode(), new LdapUserDetailsMapper());
            
            if (users.isEmpty()) {
                throw new UsernameNotFoundException("Usuário não encontrado: " + username);
            }
            
            LdapUserDetails user = users.get(0);
            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
            
        } catch (Exception e) {
            log.error("Erro ao carregar usuário do LDAP: " + e.getMessage());
            throw new UsernameNotFoundException("Erro ao autenticar usuário", e);
        }
    }

    private class LdapUserDetailsMapper implements AttributesMapper<LdapUserDetails> {
        @Override
        public LdapUserDetails mapFromAttributes(Attributes attributes) throws NamingException {
            try {
                LdapUserDetailsImpl.Essence essence = new LdapUserDetailsImpl.Essence();
                essence.setUsername((String) attributes.get(userCommanName).get());
                essence.setDn(dnPattern + "," + searchBase);
                essence.setPassword(userPassword);
                essence.setAuthorities(Collections.emptyList());
                return essence.createUserDetails();
            } catch (Exception e) {
                log.error("Erro ao mapear atributos LDAP: " + e.getMessage());
                throw e;
            }
        }
    }
}