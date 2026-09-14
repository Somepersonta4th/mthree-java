package com.sg.testing.dao.implementations.buggy;

import com.sg.testing.dao.MonsterDao;
import com.sg.testing.dao.implementations.AGoodMonsterDao;
import com.sg.testing.dao.implementations.MonsterDaoTest;

public class BadMonsterDaoFTest extends MonsterDaoTest {
    public BadMonsterDaoFTest(MonsterDao dao) {
        super(new AGoodMonsterDao());
    }
}