package com.sg.testing.dao.implementations.buggy;

import com.sg.testing.dao.MonsterDao;
import com.sg.testing.dao.implementations.AGoodMonsterDao;
import com.sg.testing.dao.implementations.MonsterDaoTest;

public class BadMonsterDaoCTest extends MonsterDaoTest {
    public BadMonsterDaoCTest(MonsterDao dao) {
        super(new AGoodMonsterDao());
    }
}
