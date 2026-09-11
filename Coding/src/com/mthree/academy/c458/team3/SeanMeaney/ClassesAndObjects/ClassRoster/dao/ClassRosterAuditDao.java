package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dao;

public interface ClassRosterAuditDao {

    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;

}