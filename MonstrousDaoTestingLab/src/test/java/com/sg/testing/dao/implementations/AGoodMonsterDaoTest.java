package com.sg.testing.dao.implementations;

import com.sg.testing.dao.MonsterDao;
import org.junit.jupiter.api.Test;

public class AGoodMonsterDaoTest extends MonsterDaoTest{

    public AGoodMonsterDaoTest() {
        super(new AGoodMonsterDao());
    }
}
