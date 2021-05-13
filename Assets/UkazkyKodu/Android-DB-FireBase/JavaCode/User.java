package com.example.fdb2;

public class User
{
    private String name, role, naposledAktivni, ban;
    public User(String name, String role, String naposledAktivni, String ban)
    {
        this.name = name;
        this.role = role;
        this.naposledAktivni = naposledAktivni;
        this.ban = ban;
    }
    public User()
    {
        this.name = "Null";
        this.role = "Guest";
        this.naposledAktivni = "NotYet";
        this.ban = "false";
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public void setNaposledAktivni(String naposledAktivni)
    {
        this.naposledAktivni = naposledAktivni;
    }

    public void setBan(String ban)
    {
        this.ban = ban;
    }

    public String getName()
    {
        return name;
    }

    public String getRole()
    {
        return role;
    }

    public String getNaposledAktivni()
    {
        return naposledAktivni;
    }

    public String getBan()
    {
        return ban;
    }
}
