package com.company.service;

import com.company.dao.ClientDao;
import com.company.entity.Client;
import com.company.entity.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsService implements UserDetailsService {

    private final ClientDao clientDao;

    @Autowired
    public ClientDetailsService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientDao.getByUserName(username);
        if (client == null){
            throw new UsernameNotFoundException(username);
        }
        return new ClientDetails(client);
    }
}
