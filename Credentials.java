package com.example.alevelapp;

import java.util.HashMap;

class Credentials {

    private HashMap<String, String> credentialsMapper = new HashMap<String, String>();

    void addCredentials(String name, String password)
    {
        credentialsMapper.put(name, password);
    }

    boolean checkCredentials(String name, String password)
    {
        if(credentialsMapper.containsKey(name))
        {
            if(password.equals(credentialsMapper.get(name)))
            {
                return true;
            }
        }
        return false;
    }
}