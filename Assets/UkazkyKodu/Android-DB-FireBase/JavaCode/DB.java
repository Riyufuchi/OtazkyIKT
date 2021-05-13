package com.example.fdb2;

public class DB
{
    private static DB inst;
    private DB()
    {

    }

    public static DB getDB()
    {
        if(inst == null)
        {
            return inst = new DB();
        }
        else
        {
            return inst;
        }
    }

    public void getDataTable()
    {

    }
}