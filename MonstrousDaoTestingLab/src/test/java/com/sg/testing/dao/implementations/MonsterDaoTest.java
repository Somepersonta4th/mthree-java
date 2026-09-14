package com.sg.testing.dao.implementations;

import com.sg.testing.dao.MonsterDao;
import com.sg.testing.model.Monster;
import com.sg.testing.model.MonsterType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterDaoTest {
    protected MonsterDao dao;
    /*  Test Plan:

     addMonster
     **  addMonster(aMonster)
     *   getMonster(aMonster.id) -> aMonster

     getAll2
     **  addMonster(aMonster)
     *   addMonster(bMonster)
     *   getAllMonster() -> aMonster, bMonster

     updateMonster
     **  addMonster(aMonster)
     *   updateMonster(bMonster)
     *   getMonster(aMonster.id) -> bMonster

     removeMonster
     **  addMonster(aMonster)
     *  addMonster(bMonster)
     *   removeMonster(aMonster)
     *   getAllMonster() -> bMonster

     getEmpty
     **  getMonster(aMonster.id) ->

     getAllEmpty
     **  getAllMonster() ->

     updateEmpty
     **  updateMonster(aMonster)
     *   getAllMonster() ->

     removeEmpty
     **  removeMonster(aMonster)
     *   getAllMonster() ->

     **
     */

    private Monster aMonster = new Monster("a", MonsterType.YETI,0,"test");
    private Monster bMonster = new Monster("b", MonsterType.LIZARDMAN,10,"test");
    
    public MonsterDaoTest() {this.dao=new AGoodMonsterDao();}

    protected MonsterDaoTest(MonsterDao dao) {
        this.dao = dao;
    }

    @Test
    public void addMonsterTest() {
        dao.addMonster(0,aMonster);

        Monster got = dao.getMonster(0);

        assertTrue(aMonster.equals(got));
    }

    @Test
    public void getAll2Test() {
        dao.addMonster(0,aMonster);
        dao.addMonster(1,bMonster);

        List<Monster> got = dao.getAllMonsters();

        assertTrue(aMonster.equals(got.get(0)));
        assertTrue(bMonster.equals(got.get(1)));

    }

    @Test
    public void updateMonsterTest() {
        dao.addMonster(0,aMonster);

        dao.updateMonster(0,bMonster);
        Monster got = dao.getMonster(0);

        assertTrue(bMonster.equals(got));
    }

    @Test
    public void removeMonsterTest() {
        dao.addMonster(0,aMonster);
        dao.addMonster(1,bMonster);

        dao.removeMonster(0);
        List<Monster> got = dao.getAllMonsters();

        assertTrue(bMonster.equals(got.get(0)));
    }

    @Test
    public void getEmptyTest() {

        Monster got = dao.getMonster(0);

        assertNull(got);
    }

    @Test
    public void getAllEmptyTest() {

        List<Monster> got = dao.getAllMonsters();

        assertTrue(got.isEmpty());
    }

    @Test
    public void updateEmptyTest() {

        dao.updateMonster(0,aMonster);
        List<Monster> got = dao.getAllMonsters();

        assertTrue(got.isEmpty());

    }

    @Test
    public void removeEmptyTest() {

        dao.removeMonster(0);
        List<Monster> got = dao.getAllMonsters();

        assertTrue(got.isEmpty());

    }

}
